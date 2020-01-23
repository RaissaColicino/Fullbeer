package controller.gestioneOrdine;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.OrdineB;
import beans.RuoloB;
import beans.UtenteB;
import model.OrdineDAO;
import topdown.OrdineDAOStub;

import java.util.logging.Logger;
/**
 * Servlet implementation class AggiornaStato
 */
@WebServlet("/AggiornaStato")
public class AggiornaStato extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger log=Logger.getLogger("AggiornaStatoDebugger");
	String WRITE="write";
	String SAVE="save";
	
	String redirectedPage="";
	
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session=request.getSession();
		
		log.info("Controllo l'azione da eseguire");
		
		String what=request.getParameter("what");
		
		log.info("Azione da eseguire: " + what);
		if(what==null || what.equals(""))
				//errore
			what=WRITE;
		
		
		log.info("Controllo che l'utente sia autenticato");
		
		Boolean userAuth=(Boolean) session.getAttribute("userAuth");
		
		if((userAuth==null) || (!userAuth.booleanValue())) {
			
			session.setAttribute("previousPage", "/OrdiniAttivi" );
		
			redirectedPage="/Login.jsp";
			response.sendRedirect(request.getContextPath() + redirectedPage);
		
		}
				else{
			
					log.info("Controllo che l'utente autenticato sia un gestore degli ordini");
					UtenteB utente=(UtenteB) session.getAttribute("userLogged");
					
						if(!utente.getRuolo().containsKey(RuoloB.ORDINI)){
						

							redirectedPage="/ErrorPage.html";
					response.sendRedirect(request.getContextPath() + redirectedPage);
					
							}else {
				
			
				log.info("Se autenticato come gestore degli ordini procedo");
				String numero="";
				OrdineB ordineDaModificare=new OrdineB();
				OrdineDAO ordineDAO=new OrdineDAO();
		

				if(what.equals(WRITE)) {
					
						numero=request.getParameter("numero");
						log.info("numero dell'ordine da aggiornare: " + numero);
					try{
								ordineDaModificare=ordineDAO.doRetrieveByNumero(numero);
							if(ordineDaModificare==null || ordineDaModificare.getStato().equals(OrdineB.CONSEGNATO)){
						//Sostutuire con pagina di errore
						
									redirectedPage="/ErrorPage.html";
							response.sendRedirect(request.getContextPath() + redirectedPage);
				
								}else {
										log.info("Inserisco ordine nella sessione: " + ordineDaModificare.getN_fattura());
										session.setAttribute("OrdineDaModificare", ordineDaModificare);
					
										log.info("Vado alla pagina di aggiornamento");
										redirectedPage="/AggiornaStato.jsp";
										response.sendRedirect(request.getContextPath() + redirectedPage);
								
								}
					}
					catch (SQLException e) {
						log.info("AggiornaStato -> errore ottenimento ordine");
						e.printStackTrace();
					}
				}
							
				else if(what.equals(SAVE)) {
					
									log.info("Ottengo il nuovo stato dell'ordine");
									
									String stato=request.getParameter("scelta-stato");
									ordineDaModificare=(OrdineB) session.getAttribute("OrdineDaModificare");
									ordineDaModificare.setStato(stato);
					
									log.info("AggiornaStato -> se l'ordine è contrassegnato come consegnato, allora aggiorno la data di consegna");
					
									if(ordineDaModificare.getStato().equals(OrdineB.CONSEGNATO)) {
										
											ordineDaModificare.setConsegna(ordineDAO.generatoreSottomissione());
											log.info("AggiornaStato -> nuova data di consegna: " + ordineDaModificare.getConsegna());
									}

									try {
										ordineDAO.aggiornaStato(ordineDaModificare);
									} catch (SQLException e) {
										log.info("AggiornaStato -> errore aggiornamento ordine");
										e.printStackTrace();
									}
										log.info("Ordine aggiornato: " + ordineDaModificare.getN_fattura() 
										+ ", stato: " + ordineDaModificare.getStato());
				
				redirectedPage="/OrdiniAttivi?order=sottomissione";
				response.sendRedirect(request.getContextPath() + redirectedPage);

				}
		
				}			}
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
			}

	}
