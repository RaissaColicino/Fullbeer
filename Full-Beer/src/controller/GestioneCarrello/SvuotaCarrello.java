package controller.GestioneCarrello;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CarrelloB;

/**
 * Servlet implementation class SvuotaCarrello
 */
@WebServlet("/SvuotaCarrello")
public class SvuotaCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger log=Logger.getLogger("SvuotaCarrelloDebugger");
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
			
			log.info("Svuoto il carrello");
			CarrelloB carrello=(CarrelloB) session.getAttribute("Carrello");
			
			if(carrello!=null)
				carrello.svuotaCarrello();
			
			response.sendRedirect(request.getContextPath()+"/Carrello");
		
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
