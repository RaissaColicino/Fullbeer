package controller.GestioneCarrello;
import java.util.logging.Logger;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CarrelloB;
import beans.CarrelloItem;
import beans.CatalogoB;
import beans.ProdottoB;
import model.ProdottoDAO;

import javax.servlet.RequestDispatcher;
/**
 * Servlet implementation class AddProdottoCarrello
 */
@WebServlet("/AddProdottoCarrello")
public class AddProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger log=Logger.getLogger("AddProdottoCarrelloDebugger"); 

   
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
	
		synchronized(session) {
			log.info("Verifico che il carrello esista");
			CarrelloB carrello=(CarrelloB)session.getAttribute("Carrello");
			if(carrello==null) {
				log.info("Se non esiste, lo creo");
				carrello=new CarrelloB();
				session.setAttribute("Carrello", carrello);
			}
			log.info("Ottengo codice prodotto dalla richiesta");
			String codiceProdotto=request.getParameter("id");
			if(codiceProdotto==null|| codiceProdotto.equals("")){
				response.sendRedirect(request.getContextPath() + "/ErrorPage.html");
			}else{
			log.info("procedo ottenendo il catalogo");
			//CatalogoB catalogo=(CatalogoB) session.getAttribute("Catalogo");
			ProdottoDAO prodottoDAO=new ProdottoDAO();
			try {
			log.info("Ottengo il prodotto da aggiungere al carrello dal catalogo");
			ProdottoB prodottoDaAggiungere;
			
				prodottoDaAggiungere = prodottoDAO.doRetrieveByCodice(codiceProdotto);
			
			
			if(prodottoDaAggiungere==null) {
				response.sendRedirect(request.getContextPath() + "/ErrorPage.html");
			}else{

		
			
				log.info("Creo il carrello item da aggiungere");
				CarrelloItem item=new CarrelloItem();
				item.setProdotto(prodottoDaAggiungere);
				
				log.info("AddProdottoCarrello -> aggiungo prodotto al carrello");
				carrello.addProdotto(item);
				
				log.info("AddProdottoCarrello -> aggiorno il carrello nella sessione");
				session.setAttribute("Carrello", carrello);
						
				response.sendRedirect(request.getContextPath()+"/Carrello");
			}
		}
			
       catch (SQLException e) {
		log.info("AddProdottoCarrello -> errore ottenimento prodotto");
		e.printStackTrace();
	}
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
