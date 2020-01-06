package bottomup;
import java.sql.SQLException;
import java.util.LinkedHashMap;


import org.junit.Test;
import junit.framework.TestCase;
import static org.junit.Assert.*;


import beans.RuoloB;
import beans.UtenteB;
import model.RuoloDAO;


public class RuoloDAOTestCase {
	
	public RuoloDAOTestCase(String nome) {
		super();
	}
	public void doRetrieveByUtente() throws SQLException {
		RuoloDAO ruoloDAO=new RuoloDAO();
		
		UtenteB user=new UtenteB();
		user.setUsername("root");
		
		LinkedHashMap<String, RuoloB> map=(LinkedHashMap<String, RuoloB>) ruoloDAO.doRetrieveByUtente(user);
		
		assertFalse(map.isEmpty());
	}

	
	public void doSave() throws SQLException {
		RuoloDAO ruoloDAO=new RuoloDAO();
		
		UtenteB user=new UtenteB();
		user.setUsername("GiovanniGambale");
		
		LinkedHashMap<String, RuoloB> map=(LinkedHashMap<String, RuoloB>) ruoloDAO.doRetrieveByUtente(user);
		int size=map.size();
		
		RuoloB ruolo=new RuoloB();
		ruolo.setUsername("GiovanniGambale");
		ruolo.setRuolo("GestoreCatalogo");
		
		ruoloDAO.doSave(ruolo);
		
		LinkedHashMap<String, RuoloB> saveMap=(LinkedHashMap<String, RuoloB>) ruoloDAO.doRetrieveByUtente(user);
		
		assertTrue(saveMap.size()>size);
	}
}
