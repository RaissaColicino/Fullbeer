package controller.gestioneCatalogo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CatalogoB;
import beans.ProdottoB;
import model.ProdottoDAO;
import topdown.ProdottoDAOStub;


@WebServlet("/Catalogo")
public class Catalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Logger log= Logger.getLogger("CatalogoDebugger");
   
   
      


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session=request.getSession();
		
		
		synchronized(session){
		

			ProdottoDAOStub prodottoDAO= new ProdottoDAOStub();
	     	log.info("Ottendo i prodotti per il Catalogo");
		    CatalogoB catalogo= new CatalogoB();

	
				catalogo.setCatalogo(prodottoDAO.doRetrieveAll());
				session.setAttribute("Catalogo",catalogo);
				
			
		
		}
		
		  RequestDispatcher dispatcher=request.getRequestDispatcher("Catalogo.jsp");
		  dispatcher.forward(request, response);
			}
	
		

		
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
