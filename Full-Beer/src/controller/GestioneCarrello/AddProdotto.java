package controller.GestioneCarrello;
import java.util.logging.Logger;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CarrelloB;
import beans.CarrelloItem;
import beans.CatalogoB;
import beans.ProdottoB;
import javax.servlet.RequestDispatcher;
/**
 * Servlet implementation class AddProdottoCarrello
 */
@WebServlet("/AddProdottoCarrello")
public class AddProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger log=Logger.getLogger("AddProdottoCarrelloDebugger"); 

    public AddProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
	
		synchronized(session) {
			log.info("Verifico che il carrello esista");
			CarrelloB carrello=(CarrelloB)session.getAttribute("Carrello");
			if(carrello==null) {
				log.info("Se non esiste, lo creo");
				carrello=new CarrelloB();
				session.setAttribute("Carrello", carrello);
			}
			log.info("Ottengo codice prodotto dalla richiesta");
			String codiceProdotto=request.getParameter("prodotto");
			
			log.info("procedo ottenendo il catalogo");
			CatalogoB catalogo=(CatalogoB) session.getAttribute("Catalogo");
			
			log.info("Ottengo il prodotto da aggiungere al carrello dal catalogo");
			ProdottoB prodottoDaAggiungere=catalogo.getProdotto(codiceProdotto);
			
			if(prodottoDaAggiungere!=null) {
				log.info("Creo il carrello item da aggiungere");
				CarrelloItem item=new CarrelloItem();
				item.setProdotto(prodottoDaAggiungere);
				
				log.info("Verifico se il prodotto � gi� nel carrello o meno");
				if(!carrello.contains(item)) {
					log.info("Non � nel carrello, lo aggiungo");
					carrello.addProdotto(item);
		}else{
			log.info("Gi� nel carrello, ne aumento solo la quantit�");
			carrello.reAddProdotto(item);
		}
		}
			log.info("Aggiorno il carrello nella sessione");
			session.setAttribute("Carrello", carrello);
			
			RequestDispatcher view=request.getRequestDispatcher("Carrello.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
