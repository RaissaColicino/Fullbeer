package controller.gestioneOrdine;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CarrelloB;
import beans.CarrelloItem;
import beans.ComposizioneB;
import beans.OrdineB;
import beans.UtenteB;
import topdown.OrdineDAOStub;

import java.util.logging.Logger;

/**
 * Servlet implementation class SottomissioneOrdine
 */
@WebServlet("/SottomissioneOrdine")
public class SottomissioneOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Logger log=Logger.getLogger("SottomissioneOrdineDebugger");
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String redirectedPage="";
		
		synchronized(session) {
			log.info("Sottomissione ordine -> controllo che l'utente sia autenticato");
			Boolean userAuth=(Boolean) session.getAttribute("userAuth");
			
			if((userAuth==null) || (!userAuth.booleanValue())) {
				redirectedPage="/Login.jsp";
				response.sendRedirect(request.getContextPath() + redirectedPage);
			}
			else {
				log.info("Sottomissione ordine -> ottengo l'utente che sta sottomettendo l'ordine");
				UtenteB user=(UtenteB) session.getAttribute("userLogged");
				
				log.info("Sottomissione ordine -> ottengo il carrello dalla sessione");
				CarrelloB carrello=(CarrelloB)session.getAttribute("Carrello");
				
				log.info("Sottomissione ordine -> controllo che non sia vuoto, altrimenti ritorno alla pagina del carrello");
				if(carrello==null || carrello.isEmpty()) {
					redirectedPage="/Carrello_.jsp";
					response.sendRedirect(request.getContextPath() + redirectedPage);
				}
				else {
					log.info("Sottomissione ordine -> ottengo i dati dalla richiesta");
					String indirizzo=request.getParameter("indirizzo");
					log.info("Indirizzo: " + indirizzo);
					if(indirizzo.equalsIgnoreCase("Scegli un indirizzo di spedizione")){
						redirectedPage="/Acquisto.jsp";
						response.sendRedirect(request.getContextPath() + redirectedPage);
					}
					else {
						OrdineDAOStub ordineDAO=new OrdineDAOStub();
						
						log.info("Sottomissione ordine -> creo l'ordine");
						OrdineB ordine=new OrdineB();
						
						ordine.setN_fattura(ordineDAO.generatoreNumero());
						ordine.setUsername(user.getUsername());
						ordine.setStato(OrdineDAOStub.ELABORAZIONE);
						ordine.setDate(ordineDAO.generatoreSottomissione());
						
						double totale=0;
						log.info("Sottomissione ordine -> uso i prodotti nel carrello per creare la composizione dell'ordine");
					
						for(CarrelloItem item: carrello.getCarrello()) {
							log.info("Sottomissione ordine -> creo la composizone dell'ordine");
						
							ComposizioneB cb=new ComposizioneB();
							cb.setN_fattura(ordine.getN_fattura());
							cb.setProdottoCodice(item.getProdotto().getId());
							cb.setNome_prodotto(item.getProdotto().getNome());
							cb.setPrezzo(item.getProdotto().getPrezzo());
							cb.setQuantità(item.getQt());
														
							totale+=item.getProdotto().getPrezzo();
	
							log.info("Sottomissione ordine -> aggiungo composizione all'ordine");
							ordine.addProdotto(cb);
						}
						
						log.info("Sottomissione ordine -> aggiorno totale dell'ordine");
						ordine.setImporto(totale);
						System.out.println(ordine.getImporto());
						log.info("Sottomissione ordine -> salvo l'ordine per completare la sottomissione");
						ordineDAO.doSave(ordine);
						
						
						log.info("Sottomissione ordine -> svuoto il carrello dopo la sottomissione");
						carrello.svuotaCarrello();
						
						log.info("Sottomissione ordine -> imposto il numero dell'ordine per la fattura");
						session.setAttribute("Riepilogo", ordine.getN_fattura());
						
						RequestDispatcher view=request.getRequestDispatcher("Riepilogo.jsp");
						view.forward(request, response);
					}
					//Fine else di controllo su indirizzo e pagamento
				}
				//Fine secondo else
			}
			//Fine primo else
		}
		//Fine synchronized
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
