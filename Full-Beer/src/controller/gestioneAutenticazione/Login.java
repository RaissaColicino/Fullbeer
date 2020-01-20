package controller.gestioneAutenticazione;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.logging.Logger;

import beans.RuoloB;
import beans.UtenteB;
import topdown.UtenteDAOStub;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger log=Logger.getLogger("LoginDebugger");
	String UTENTE="Utente";
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String redirectedPage="";
		
	
		HttpSession session=request.getSession();
		synchronized(session) {
			log.info("Sono nella servlet di login -> creo l'utente da loggare");
			UtenteB toLog=new UtenteB();
			toLog.setUsername(username);
			toLog.setPassword(password);
				
			UtenteDAOStub utenteDAO=new UtenteDAOStub();
			UtenteB user;
		
				user = utenteDAO.validate(toLog);


				log.info("Sono nello servlet di login -> terminato metodo: verifica");
				if(user!=null) {
					log.info("utente loggato: " + user.getUsername() + ", " + user.getPassword());
					session.setAttribute("userAuth", true);
					session.setAttribute("ruolo", RuoloB.UTENTE);
					session.setAttribute("userLogged", user);

					String pp=(String) session.getAttribute("previousPage");
						if(pp!=null && !pp.equals("")) {
								redirectedPage=pp;
								session.removeAttribute("previousPage");
							}
							else
									redirectedPage="/AreaUtente.jsp";
							}	
								else {
										session.setAttribute("userAuth", false);
										session.setAttribute("ruolo", UTENTE);
										session.setAttribute("userLogged", null);
				
										session.setAttribute("errore", "errore");
										redirectedPage="/Login.jsp";
								}


				response.sendRedirect(request.getContextPath() + redirectedPage);
			}
		}
	}