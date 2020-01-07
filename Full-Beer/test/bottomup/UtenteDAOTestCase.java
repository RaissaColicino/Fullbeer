
package bottomup;

import java.sql.SQLException;

import beans.UtenteB;
import junit.framework.TestCase;
import model.UtenteDAO;
import static org.junit.Assert.*;
public class UtenteDAOTestCase extends TestCase {
	public UtenteDAOTestCase(String nome) {
		super(nome);
	}

	@Override
	public void setUp() {
		utenteDAO=new UtenteDAO();

		//Creo l'utente
		utenteTest=new UtenteB();
		utenteTest.setUsername("RaissaC");
		utenteTest.setPassword("RaissaC");
		utenteTest.setNome("Raissa");
		utenteTest.setCognome("Colicino");
		utenteTest.setMail("raissacolicino@gmail.it");
		
	}
	
	//Tests doSave
	public void doSaveCorretto() throws SQLException {
		utenteDAO.doSave(utenteTest);
		
		assertNotNull(utenteDAO.doRetrieveByUsername(utenteTest.getUsername()));
	}
	
	public void doSaveErrato() throws SQLException {
		UtenteB utenteErrato=new UtenteB();
		
		utenteDAO.doSave(utenteErrato);
		
		assertNull(utenteDAO.doRetrieveByUsername(utenteErrato.getUsername()));
	}
	//Fine test doSave
	
	//Tests validate
	public void validateCorretto() throws SQLException {
		UtenteB validato=utenteDAO.validate(utenteTest);
		
		assertNotNull(validato);
	}
	
	public void validateErrato() throws SQLException {
		//Primo test
		UtenteB validato=utenteDAO.validate(null);
		assertNull(validato);
		
		//Secondo test
		UtenteB user=new UtenteB();
		user.setUsername("");
		
		validato=utenteDAO.validate(user);
		assertNull(validato);
		
		//Terzo test
		user.setUsername("RaissaC");
		user.setPassword("");
		
		validato=utenteDAO.validate(user);
		assertNull(validato);
	}
	//Fine tests validate
	
	//Tests doRetrieveByUsername
	public void doRetrieveByUsernameCorretto() throws SQLException {
		UtenteB user=utenteDAO.doRetrieveByUsername(utenteTest.getUsername());
		
		assertNotNull(user);
	}
	
	public void doRetrieveByUsernameErrato() throws SQLException {
		UtenteB user=utenteDAO.doRetrieveByUsername("");
		
		assertNull(user);
	}
	//Fine tests doRetrieveByUsername
	
	//Tests doDelete
	public void doUpdateCorretto() throws SQLException {
		utenteTest.setNome("Anna");
		assertTrue(utenteDAO.doUpdate(utenteTest));
	}
	
	public void doUpdateErrato() throws SQLException {
		assertFalse(utenteDAO.doUpdate(null));
	}
	//Fine tests doDelete
	
	//Tests doDelete
	public void doDeleteCorretto() throws SQLException {		
		assertTrue(utenteDAO.doDelete(utenteTest));
	}
	//Fine tests doDelete
	
	private UtenteDAO utenteDAO;
	private UtenteB utenteTest;
}