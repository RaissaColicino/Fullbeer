package controller.gestioneOrdine;

import java.io.IOException;
import java.sql.SQLException;

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
import model.OrdineDAO;

import java.util.logging.Logger;
/**
 * Servlet implementation class OrdiniAttivi
 */
@WebServlet("/OrdiniAttivi")
public class OrdiniAttivi extends HttpServlet {
	OrdineDAO ordiniDAO;
	private static final long serialVersionUID = 1L;
	Logger log=Logger.getLogger("OrdiniAttiviDebugger");
    public OrdiniAttivi() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("Servlet per gli ordini attivi");
		HttpSession session=request.getSession();
		String redirectedPage="";
		synchronized(session) {
			log.info("Ordine -> verifico che l'utente si sia autenticato");
			Boolean userAuth=(Boolean) session.getAttribute("userAuth");
			if((userAuth==null) || (!userAuth.booleanValue())) {
				String ord="sottomissione desc";
				session.setAttribute("previousPage", "/OrdiniAttivi?order=" + ord);

				redirectedPage="/Login.jsp";
				response.sendRedirect(request.getContextPath() + redirectedPage);
			}
			else{
			 ordiniDAO=new OrdineDAO();
			
			log.info("Ottengo gli ordini attivi");
			LinkedHashSet<OrdineB> ordiniAttivi;
			try {
				ordiniAttivi = (LinkedHashSet<OrdineB>) ordiniDAO.doRetrieveIfAttivi();

				log.info("Aggiungo ordini attivi alla sessione");
				session.setAttribute("OrdiniAttivi", ordiniAttivi);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		RequestDispatcher view=request.getRequestDispatcher("OrdiniAttivi.jsp");
		view.forward(request, response);
	}
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
