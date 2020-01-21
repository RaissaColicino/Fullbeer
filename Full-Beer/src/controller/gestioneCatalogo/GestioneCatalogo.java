
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

import beans.CatalogoB;
import model.ProdottoDAO;
import topdown.ProdottoDAOStub;
import javax.servlet.RequestDispatcher;
/**
 * Servlet implementation class GestioneCatalogo
 */
@WebServlet("/GestioneCatalogo")
public class GestioneCatalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   Logger log=Logger.getLogger("GestioneCatalogoDebugger");
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session=request.getSession();
    	synchronized(session) {
    	ProdottoDAO prodottoDAO=new ProdottoDAO();
	
    	
    	log.info("GestioneCatalogo -> ottengo i prodotti per il catalogo");
		CatalogoB catalogo=new CatalogoB();
		try {
			catalogo.setCatalogo(ProdottoDAO.doRetrieveAll());
		} catch (SQLException e) {
			log.info("GestioneCatalogo -> errore ottenimento prodotti per gestione catalogo");
			e.printStackTrace();
		}
		session.setAttribute("CatalogoDaGestire", catalogo);
   
    }

	RequestDispatcher view= request.getRequestDispatcher("GestioneCatalogo.jsp");
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