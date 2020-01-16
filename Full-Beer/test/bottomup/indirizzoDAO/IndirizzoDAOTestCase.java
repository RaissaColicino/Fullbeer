package bottomup.indirizzoDAO;

import java.sql.SQLException;
import java.util.LinkedHashSet;

import beans.IndirizzoB;
import beans.UtenteB;
import junit.framework.TestCase;
import model.IndirizzoDAO;

public class IndirizzoDAOTestCase extends TestCase {
	
	public IndirizzoDAOTestCase(String nome) {
		super(nome);
	}
	@Override
	public void setUp(){
		  indirizzoDAO = new IndirizzoDAO();
	
		 //creo indirizzo da salvare
		
		 indirizzo = new IndirizzoB();
		
		indirizzo.setCap("83040");
		indirizzo.setCittà("Avellino");
		indirizzo.setVia("S.Francesco");
		indirizzo.setUsername("VincenzaC");
	}
	
	public void doRetrieveByUtente() throws SQLException {	
		//Caso corretto
		//Creo l'utente
		UtenteB user=new UtenteB();
		user.setUsername("antonioga");
	
	//Ottengo gli indirizzi dell'utente
			LinkedHashSet<IndirizzoB> indirizzi=(LinkedHashSet<IndirizzoB>) indirizzoDAO.doRetrieveByUtente(user);

			// verifica caso corretto		
			assertFalse(indirizzi.isEmpty());
			assertNotNull(indirizzi);

			//caso errato e ottengo gli indirizzi dell'utente
			indirizzi=(LinkedHashSet<IndirizzoB>) indirizzoDAO.doRetrieveByUtente(null);

			//verifico correttezza
			assertNull(indirizzi);
}

// Test doSave
public void doSave() throws SQLException {
	//Creo l'utente
	UtenteB user=new UtenteB();
	user.setUsername("antonioga");

	//Caso corretto
	//Salvo l'indirizzo
	indirizzoDAO.doSave(indirizzo);

	// Ottengo gli indirizzi
	LinkedHashSet<IndirizzoB> indirizzi=(LinkedHashSet<IndirizzoB>) indirizzoDAO.doRetrieveByUtente(user);

	//Verifico caso corretto
	assertNotNull(indirizzi);
	assertFalse(indirizzi.isEmpty());

	//Caso errato
	//Creo indirizzo da salvare
	IndirizzoB indirizzo=new IndirizzoB();

	//Salvo l'indirizzo
	indirizzoDAO.doSave(indirizzo);

	//Ottengo gli indirizzi
	indirizzi=(LinkedHashSet<IndirizzoB>) indirizzoDAO.doRetrieveByUtente(user);

	//Verifico caso errato
	assertFalse(indirizzi.contains(indirizzo));
}

//Test doDelete
public void doDelete() throws SQLException {
	assertTrue(indirizzoDAO.doDelete(indirizzo));
}

private IndirizzoDAO indirizzoDAO;
private IndirizzoB indirizzo;
}
