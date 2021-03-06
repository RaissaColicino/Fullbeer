
package controller.gestioneAutenticazione;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.RuoloB;

@WebServlet("/Ruolo")
public class Ruolo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Logger log=Logger.getLogger("RuoloDebugger");  
	String CATALOGO="Catalogo";
	String ORDINI="Ordini";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String permesso="";
		String redirectedPage="";
		
		HttpSession session=request.getSession();
		synchronized(session) {	
			log.info("Sono nella servlet Ruolo");
			
			permesso=request.getParameter("permesso");
			log.info("ruolo: " + permesso);
			
			session.setAttribute("ruolo", permesso);

			log.info("Se gestore degli ordini vado alla pagina di gestione degli ordini attivi");
			if(permesso.equals(RuoloB.ORDINI))
				redirectedPage="/OrdiniAttivi";
			else if(permesso.equals(RuoloB.CATALOGO)) {
				log.info("Vado alla pagina di gestione del catalogo");
				redirectedPage="/GestioneCatalogo";
			}
			else {
				log.info("Vado alla Home Page");
				redirectedPage="/Homepage.jsp";
			}
		}
		
		response.sendRedirect(request.getContextPath() + redirectedPage);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}