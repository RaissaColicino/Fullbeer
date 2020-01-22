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
import model.OrdineDAO;
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
				log.info("Ordine -> stabilisco a quale pagina tornare");
				//redirectedPage="/Login.jsp";
				//response.sendRedirect(request.getContextPath() + redirectedPage);
				if(toDo.equals(GESTORE)) {
					String ord="sottomissione desc";
					session.setAttribute("previousPage", "/Ordine?toDo=gestore&order=" + ord);
				}
				else {
					String ord="sottomissione desc";
					session.setAttribute("previousPage", "/Ordine?toDo=utente&order=" + ord);
				}
				redirectedPage="/Login.jsp";
				response.sendRedirect(request.getContextPath() + redirectedPage);
			}
			else {
				OrdineDAO ordineDAO=new OrdineDAO();
				LinkedHashSet<OrdineB> ordini=new LinkedHashSet<OrdineB>();
				
				if(toDo.equals(GESTORE)) {
					log.info("Ottengo tutti gli ordini poichè l'utente è gestore degli ordini");
					
						try {
							ordini=(LinkedHashSet<OrdineB>) ordineDAO.doRetrieveAll();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
											
				}
				else {
					UtenteB utente=(UtenteB) session.getAttribute("userLogged");
					
					log.info("Ottengo solo gli ordini dell'utente");
					
						try {
							ordini=(LinkedHashSet<OrdineB>) ordineDAO.doRetrieveByUtente(utente);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
									
				}
				
				session.setAttribute("Ordini", ordini);
			
	
		if(toDo.equals(GESTORE)) {
			RequestDispatcher view=request.getRequestDispatcher("OrdineGestore.jsp");
			view.forward(request, response);
		}
		else {
			RequestDispatcher view=request.getRequestDispatcher("OrdiniUtente.jsp");
			view.forward(request, response);
		}
	}
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
