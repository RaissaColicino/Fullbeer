package controller.gestioneOrdine;
import java.util.LinkedHashSet;
import java.util.logging.Logger;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;


import beans.OrdineB;
import beans.UtenteB;
import topdown.OrdineDAOStub;

/**
 * Servlet implementation class Ordine
 */
@WebServlet("/Ordine")
public class Ordine extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger log=Logger.getLogger("OrdineDebugger"); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ordine() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String GESTORE="gestore";
		
		String redirectedPage="";
		
		log.info("Distinguo tra utente e gestore degli ordini");
		String toDo=request.getParameter("toDo");
		if(toDo==null || toDo.equals(""))
			toDo="utente";
		
		synchronized(session) {
			log.info("Verifico che l'utente si sia autenticato");
			Boolean userAuth=(Boolean) session.getAttribute("userAuth");
			if((userAuth==null) || (!userAuth.booleanValue())) {
				redirectedPage="/Login.jsp";
				response.sendRedirect(request.getContextPath() + redirectedPage);
			}
			else {
				OrdineDAOStub ordineDAO=new OrdineDAOStub();
				LinkedHashSet<OrdineB> ordini=new LinkedHashSet<OrdineB>();

				log.info("Ottengo l'ordine per visualizzare gli ordini");
				String order=request.getParameter("order");
				if(order==null || order.equals(""))
					order="nome";
				
				if(toDo.equals(GESTORE)) {
					log.info("Ottengo tutti gli ordini poichè l'utente è gestore degli ordini");
					
						ordini=(LinkedHashSet<OrdineB>) ordineDAO.doRetrieveAll();
											
				}
				else {
					UtenteB utente=(UtenteB) session.getAttribute("userLogged");
					
					log.info("Ottengo solo gli ordini dell'utente");
					
						ordini=(LinkedHashSet<OrdineB>) ordineDAO.doRetrieveByUtente(utente);
									
				}
				
				session.setAttribute("Ordini", ordini);
			}
		}
		//Fine synchronized
		if(toDo.equals(GESTORE)) {
			RequestDispatcher view=request.getRequestDispatcher("OrdineGestore.jsp");
			view.forward(request, response);
		}
		else {
			RequestDispatcher view=request.getRequestDispatcher("OrdineUtente.jsp");
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
