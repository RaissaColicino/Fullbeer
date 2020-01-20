package controller.gestioneOrdine;
import java.util.logging.Logger;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.OrdineB;
import beans.UtenteB;
import topdown.OrdineDAOStub;
import topdown.UtenteDAOStub;
import javax.servlet.RequestDispatcher;
/**
 * Servlet implementation class Fattura
 */
@WebServlet("/Fattura")
public class Fattura extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger log=Logger.getLogger("FatturaDebugger");   
  
    public Fattura() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String redirectedPage="";
		
		synchronized(session){
			log.info("Servlet Fattura -> controllo che l'utente si sia autenticato");
			Boolean userAuth=(Boolean) session.getAttribute("userAuth");
			if((userAuth==null) || (!userAuth.booleanValue())) {
				String ord="sottomissione desc";
				session.setAttribute("previousPage", "/Ordine?toDo=utente&order=" + ord);
				redirectedPage="/Login.jsp";
				response.sendRedirect(request.getContextPath() + redirectedPage);
			}
			else {
				log.info("Servlet Fattura -> recupero il numero identificativo dell'ordine dalla richiesta");
				String numeroOrdine=request.getParameter("numeroOrdine");
	
               OrdineDAOStub ordineDAO=new OrdineDAOStub();
				
				log.info("Servlet Fattura -> ottengo l'ordine in base al numero");
				OrdineB ordine;
				
		     	
					ordine = ordineDAO.doRetrieveByNumero(numeroOrdine);
		
								
				if(ordine!=null)
					session.setAttribute("Ordine", ordine);

				UtenteDAOStub utenteDAO=new UtenteDAOStub();

				log.info("Ottengo l'utente per la fattura");
				UtenteB utente=utenteDAO.doRetrieveByUsername(ordine.getUsername());
				if(utente!=null)
					session.setAttribute("UtenteFattura", utente);
			}
			
		
		}
		RequestDispatcher view=request.getRequestDispatcher("Fattura_.jsp");
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
