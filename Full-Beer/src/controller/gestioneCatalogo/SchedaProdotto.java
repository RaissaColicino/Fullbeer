package controller.gestioneCatalogo;
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


import beans.ProdottoB;
import model.ProdottoDAO;
import topdown.ProdottoDAOStub;

/**
 * Servlet implementation class SchedaProdotto
 */
@WebServlet("/SchedaProdotto")
public class SchedaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger log=Logger.getLogger("SchedaProdottoDebugger");

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
		synchronized(session) {
			
			log.info("Scheda prodotto -> ottengo il codice del prodotto da mostrare dalla richiesta");
			String codiceProdotto=request.getParameter("id");
			
			ProdottoDAO prodottoDAO=new ProdottoDAO();
			
			log.info("Scheda prodotto -> ottengo il prodotto da mostrare");
			ProdottoB prodottoDaMostrare = new ProdottoB();
			try {
				prodottoDaMostrare = prodottoDAO.doRetrieveByCodice(codiceProdotto);
	
			System.out.println(prodottoDaMostrare);
			if(prodottoDaMostrare!=null){		
			
				log.info("Scheda prodotto -> aggiungo il prodotto da mostrare alla sessione");
				session.setAttribute("ProdottoDaMostrare", prodottoDaMostrare);
		
		RequestDispatcher view=request.getRequestDispatcher("SchedaProdotto.jsp");
		view.forward(request, response);
	}else{
		response.sendRedirect(request.getContextPath()+"/ErrorPage.html");
	}	
			} catch (SQLException e) {
				log.info("SchedaProdotto -> errore ottenimento prodotto");
				e.printStackTrace();
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
