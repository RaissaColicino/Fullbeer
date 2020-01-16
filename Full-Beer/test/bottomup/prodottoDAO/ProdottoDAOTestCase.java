package bottomup.prodottoDAO;

import junit.framework.TestCase;
import model.ProdottoDAO;

import java.sql.SQLException;
import java.util.LinkedHashSet;

import org.junit.Test;

import beans.ProdottoB;
public class ProdottoDAOTestCase extends TestCase {
	
	public ProdottoDAOTestCase(String nome){
				super(nome);	
				}


	public void setUp(){
		prodottoDAO = new ProdottoDAO();
		
	    prodottoTest = new ProdottoB();
		prodottoTest.setId("021");
		prodottoTest.setPrezzo(2.50);
		prodottoTest.setNome("Pippaax");
		prodottoTest.setDescrizione("axBirra dal manto rosso");
		prodottoTest.setImmagine("pippaox.jpg");
		prodottoTest.setQt(4);
		
	}

	public void doRetrieveByCodice() throws SQLException {
		//Creo prodotto
		ProdottoB prodotto=new ProdottoB();
		
		//Caso corretto
		prodotto.setId("001");
		
		//Verifico caso corretto
		assertNotNull(prodottoDAO.doRetrieveByCodice(prodotto.getId()));
		
		//Caso errato
		prodotto.setId("");
		
		//Verifico caso errato
		assertNull(prodottoDAO.doRetrieveByCodice(prodotto.getId()));
	}
	
	public void doSave() throws SQLException {
		//Caso corretto
		prodottoDAO.doSave(prodottoTest);
		
		//Verifico caso corretto
		assertNotNull(prodottoDAO.doRetrieveByCodice(prodottoTest.getId()));
		//Caso errato
		ProdottoB prodotto=new ProdottoB();
		prodotto.setId("");
		prodottoDAO.doSave(prodotto);
		
		//Verifico caso errato
		assertNull(prodottoDAO.doRetrieveByCodice(prodotto.getId()));
	}

	public void doRetrieveAll() throws SQLException {
		//Caso corretto
		LinkedHashSet<ProdottoB> prodotti=(LinkedHashSet<ProdottoB>) prodottoDAO.doRetrieveAll();
		
		//Verifico caso corretto
		assertNotNull(prodotti);
		
		//Caso errato
		prodotti=(LinkedHashSet<ProdottoB>) prodottoDAO.doRetrieveAll();
		
		//Verifico caso errato
		// assertNull(prodotti);
	}

	public void doUpdate() throws SQLException {
		//Caso corretto
		
		//Imposto delle modifiche
		prodottoTest.setDescrizione("doUpdate riuscito");
		prodottoTest.setQt(10);
		
		assertTrue(prodottoDAO.doUpdate(prodottoTest));
		
		//Caso errato
		assertFalse(prodottoDAO.doUpdate(null));
	}

	public void doDelete() throws SQLException {
		assertTrue(prodottoDAO.doDelete(prodottoTest));
		
		assertFalse(prodottoDAO.doDelete(null));
	}

	private ProdottoDAO prodottoDAO;
	private ProdottoB prodottoTest;
}
