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
import javax.servlet.RequestDispatcher;
/**
 * Servlet implementation class RemoveProdottoCarrello
 */
@WebServlet("/RemoveProdottoCarrello")
public class RemoveProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Logger log=Logger.getLogger("RemoveProdottoCarrelloDebugger");  

    public RemoveProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
	
		synchronized(session) {
			log.info("Ottengo il carrello");
			CarrelloB carrello=(CarrelloB)session.getAttribute("Carrello");
			
			log.info("Ottengo codice prodotto da eliminare");
			String codiceProdotto=request.getParameter("prodotto");
		
			log.info("Ottengo il prodotto da rimuovere dal carrello");
			CarrelloItem prodottoDaRimuovere=carrello.getProdotto(codiceProdotto);
			
			log.info("Prodotto ottenuto: " + prodottoDaRimuovere.getProdotto().getId());
			if(prodottoDaRimuovere!=null)
			
			carrello.removeProdotto(prodottoDaRimuovere);
			
			log.info("Aggiorno il carrello");
			session.setAttribute("Carrello", carrello);
		}
		
		RequestDispatcher view=request.getRequestDispatcher("Carrello_.jsp");
		view.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
