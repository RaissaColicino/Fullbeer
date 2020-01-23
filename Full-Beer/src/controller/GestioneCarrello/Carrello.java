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
 * Servlet implementation class Carrello
 */
@WebServlet("/Carrello")
public class Carrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger log=Logger.getLogger("CarrelloDebugger");  
    public Carrello() {
        super();
        // TODO Auto-generated constructor stub
    }
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session=request.getSession();
	
	synchronized(session) {
		log.info("Verifico che il carrello esiste, se non esiste lo creo");
		CarrelloB carrello=(CarrelloB) session.getAttribute("Carrello");
		if(carrello==null)
			carrello=new CarrelloB();

		log.info("Aggiungo carrello alla sessione");
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
