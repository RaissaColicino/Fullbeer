package bottomup;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import junit.framework.TestCase;



import beans.RuoloB;
import beans.UtenteB;
import model.RuoloDAO;


public class RuoloDAOTestCase extends TestCase {
	
	public RuoloDAOTestCase(String nome) {
		super(nome);
	}
	
	
	public void setUp() {
		ruoloDAO = new RuoloDAO();
	}
	
	public void doRetrieveByUtenteCorretto() throws SQLException {
		
		
		
		UtenteB user= new UtenteB();
		user.setUsername("RaissaC");
		
		
		LinkedHashMap<String, RuoloB> ruoli= (LinkedHashMap<String, RuoloB>) RuoloDAO.doRetrieveByUtente(user);
		
		assertFalse(ruoli.isEmpty());
		assertNotNull(ruoli);
	}
	
	
	public void doRetrieveByUtenteErrato() throws SQLException {	
		//Ottengo i ruoli dell'utente
		LinkedHashMap<String, RuoloB> ruoli=(LinkedHashMap<String, RuoloB>) ruoloDAO.doRetrieveByUtente(null);
		
		assertNull(ruoli);
	}

	
	public void doSaveCorretto() throws SQLException {
	
		
		UtenteB user=new UtenteB();
		user.setUsername("VincenzaC");
		
		
		
		RuoloB ruolo=new RuoloB();
		ruolo.setUsername("VincenzaC");
		ruolo.setRuolo("GestoreCatalogo");
		
		ruoloDAO.doSave(ruolo);
		
		LinkedHashMap<String, RuoloB> ruoli=(LinkedHashMap<String, RuoloB>) ruoloDAO.doRetrieveByUtente(user);
		
		assertNotNull(ruoli);
		assertFalse(ruoli.isEmpty());
		assertTrue(ruoli.containsKey(ruolo.getRuolo()));
		
	}
	
	
	public void doSaveErrato() throws SQLException {
		//Creo l'utente
		UtenteB user=new UtenteB();
		user.setUsername("GiovanniG");
		
		//Creo ruolo da salvare
		RuoloB ruolo=new RuoloB();
		ruolo.setUsername("");
		ruolo.setRuolo("");
		
		//Salvo il ruolo
		
		ruoloDAO.doSave(ruolo);
		
		//Ottengo i ruoli
		LinkedHashMap<String, RuoloB> ruoli=(LinkedHashMap<String, RuoloB>) ruoloDAO.doRetrieveByUtente(user);
		
		assertFalse(ruoli.containsKey(ruolo.getRuolo()));
	
	
}
	private RuoloDAO ruoloDAO;
}
