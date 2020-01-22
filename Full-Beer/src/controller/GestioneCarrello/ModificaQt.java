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
import javax.servlet.RequestDispatcher;
/**
 * Servlet implementation class ModificaQt
 */
@WebServlet("/ModificaQt")
public class ModificaQt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   Logger log=Logger.getLogger("ModificaQtDebugger");   
    public ModificaQt() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
HttpSession session=request.getSession();
		
		synchronized(session) {
			log.info("Ottengo il carrello");
			CarrelloB carrello=(CarrelloB) session.getAttribute("Carrello");
			
			log.info("Ottengo il codice del prodotto e l'azione da eseguire (aumento o diminuisco)");
			String codiceProdotto=request.getParameter("prodotto");
			String action=request.getParameter("action");
			
		
			log.info("Modifico la quantità del prodotto");
			carrello.modificaQT(codiceProdotto, action);
			
			log.info("Aggiorno il carrello");
			session.setAttribute("Carrello", carrello);
			
			response.sendRedirect(request.getContextPath()+ "/Carrello_.jsp");
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
