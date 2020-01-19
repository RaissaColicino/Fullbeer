package controller.gestioneCatalogo;
import java.util.logging.Logger;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CatalogoB;
import topdown.ProdottoDAOStub;
import javax.servlet.RequestDispatcher;
/**
 * Servlet implementation class GestioneCatalogo
 */
@WebServlet("/GestioneCatalogo")
public class GestioneCatalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   Logger log=Logger.getLogger("GestioneCatalogoDebugger");
    public GestioneCatalogo() {
        super();
        // TODO Auto-generated constructor stub
    }protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session=request.getSession();
    	synchronized(session) {
    	ProdottoDAOStub prodottoDAO=new ProdottoDAOStub();
	
    	
    	log.info("GestioneCatalogo -> ottengo i prodotti per il catalogo");
		CatalogoB catalogo=new CatalogoB();
		catalogo.setCatalogo(prodottoDAO.doRetrieveAll());
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
