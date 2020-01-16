package bottomup.ordineDAO;

import junit.framework.TestCase;
import model.OrdineDAO;

import java.sql.SQLException;
import java.util.LinkedHashSet;

import beans.ComposizioneB;
import beans.OrdineB;
import beans.UtenteB;

public class OrdineDAOTestCase extends TestCase{
	private OrdineDAO ordineDAO;
	private OrdineB ordineTest;
	public OrdineDAOTestCase(String name) {
		super(name);
	}
	@Override
	public void setUp() {
		ordineDAO=new OrdineDAO();
		
		ordineTest=new OrdineB();
		ordineTest.setN_fattura("");
		ordineTest.setDate("2020-01-16");
		ordineTest.setImporto(5.20);
		ordineTest.setStato(OrdineB.ELABORAZIONE);
		ordineTest.setUsername("root");
		}
	public void doRetrieveByNumero() throws SQLException {
		//Caso corretto
		assertNotNull(ordineDAO.doRetrieveByNumero(ordineTest.getN_fattura()));
		
		//Caso errato
		assertNull(ordineDAO.doRetrieveByNumero(""));
	}
	//Test doRetrieveAll
	public void doRetrieveAll() throws SQLException {
		assertNotNull(ordineDAO.doRetrieveAll());
	}
	//Test doSave
	public void doSave() throws SQLException {
		//Creo una composizione
		ComposizioneB comp=new ComposizioneB();
		comp.setN_fattura(ordineTest.getN_fattura());
		comp.setProdottoCodice("001");
		comp.setPrezzo(99.99);
		comp.setNome_prodotto("Camba");
		comp.setQuantità(1);
		
		
		//Aggiungo composizone all'ordine
		ordineTest.addProdotto(comp);
		
		//Caso corretto
		ordineDAO.doSave(ordineTest);
		
		//Verifico caso corretto
		assertNotNull(ordineDAO.doRetrieveByNumero(ordineTest.getN_fattura()));
		
		//Caso errato
		OrdineB ordine=new OrdineB();
		
		ordineDAO.doSave(ordine);
		
		//Verifico caso errato
		assertNull(ordineDAO.doRetrieveByNumero(ordine.getN_fattura()));
	}
	//Test doRetrieveByUtente
	public void doRetrieveByUtente() throws SQLException {
		//Caso corretto

		//Creo l'utente
		UtenteB user=new UtenteB();
		user.setUsername("root");
		
		assertNotNull(ordineDAO.doRetrieveByUtente(user));
		
		//Caso errato
		assertNull(ordineDAO.doRetrieveByUtente(null));
	}
	//Test doRetrieveIfAttivi
		public void doRetrieveIfAttivi() throws SQLException {
			//Caso corretto
			LinkedHashSet<OrdineB> ordini=(LinkedHashSet<OrdineB>) ordineDAO.doRetrieveIfAttivi();
			
			//Verifico caso corretto
			assertNotNull(ordini);
			
			for(OrdineB o: ordini)
				assertFalse(o.getStato().equals(OrdineB.CONSEGNATO));
		}
		//Test aggiornaStato
		public void aggiornaStato() throws SQLException {
			//Imposto delle modifiche
			ordineTest.setStato(OrdineB.SPEDIZIONE);
			
			ordineDAO.modificaStato(ordineTest);
			
			OrdineB ordine=ordineDAO.doRetrieveByNumero(ordineTest.getN_fattura());

			assertTrue(ordine.getStato().equals(OrdineB.SPEDIZIONE));
		}
		
}
