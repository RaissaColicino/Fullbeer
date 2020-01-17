package controller.gestioneOrdine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashSet;

import javax.servlet.RequestDispatcher;
import topdown.OrdineDAOStub;
import beans.OrdineB;
import java.util.logging.Logger;
/**
 * Servlet implementation class OrdiniAttivi
 */
@WebServlet("/OrdiniAttivi")
public class OrdiniAttivi extends HttpServlet {
	OrdineDAOStub ordiniDAO;
	private static final long serialVersionUID = 1L;
	Logger log=Logger.getLogger("OrdiniAttiviDebugger");
    public OrdiniAttivi() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("Servlet per gli ordini attivi");
		HttpSession session=request.getSession();
	
		log.info("OttenGO l'ordine di visualizzazione degli ordini attivi");
		String order=(String) request.getAttribute("order");
		if(order==null)
			order="sottomissione";
		synchronized(session) {
			 ordiniDAO=new OrdineDAOStub();
			
			log.info("Ottengo gli ordini attivi");
			LinkedHashSet<OrdineB> ordiniAttivi=(LinkedHashSet<OrdineB>) ordiniDAO.doRetrieveIfAttivi(order);
			
			log.info("Aggiungo ordini attivi alla sessione");
			session.setAttribute("OrdiniAttivi", ordiniAttivi);
		}
		RequestDispatcher view=request.getRequestDispatcher("OrdiniAttivi.jsp");
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
