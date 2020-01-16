package bottomup.composizioneDAO;


import java.sql.SQLException;
import java.util.LinkedHashSet;

import beans.ComposizioneB;
import beans.OrdineB;
import junit.framework.TestCase;
import model.ComposizioneDAO;
import model.OrdineDAO;

public class ComposizioneDAOTestCase extends TestCase {
	public ComposizioneDAOTestCase(String name) {
		super(name);
	}
	
	@Override
	public void setUp() {
		composizioneDAO=new ComposizioneDAO();
	}
	
	public void doSave() throws SQLException {
		//Creo ordine
		OrdineDAO ordineDAO=new OrdineDAO();
		
		//Creo un ordine
		OrdineB ordine=new OrdineB();
		ordine.setN_fattura("000005");
		ordine.setStato(OrdineB.ELABORAZIONE);
		ordine.setUsername("antonioga");
		ordine.setImporto(99.99);
		ordine.setDate("2020-01-16");
		
				
		ComposizioneB comp=new ComposizioneB();
		comp.setN_fattura(ordine.getN_fattura());
		comp.setProdottoCodice("001");
		comp.setPrezzo(99.99);
		comp.setNome_prodotto("chimay");
		comp.setQuantità(1);;
		
		
		ordine.addProdotto(comp);
		
		//Salvo l'ordine
		ordineDAO.doSave(ordine);
		
		//Caso corretto
		comp=new ComposizioneB();
		comp.setN_fattura(ordine.getN_fattura());
		comp.setProdottoCodice("002");
		comp.setPrezzo(89.99);
		comp.setNome_prodotto("Desrcos");
		comp.setQuantità(1);
		
		
		composizioneDAO.doSave(comp);
		
		LinkedHashSet<ComposizioneB> composizioni=(LinkedHashSet<ComposizioneB>) composizioneDAO.doRetrieveByOrdine(ordine);
		
		assertNotNull(composizioni);
		assertFalse(composizioni.isEmpty());
		
		//Caso errato
		comp=null;
		composizioneDAO.doSave(comp);
		
		composizioni=(LinkedHashSet<ComposizioneB>) composizioneDAO.doRetrieveByOrdine(ordine);
		
		assertFalse(composizioni.contains(comp));
	}
	public void doRetrieveByOrdine() throws SQLException {
		//Creo ordine
		OrdineB ordine=new OrdineB();
		ordine.setN_fattura("000002");
		
		//Caso corretto
		LinkedHashSet<ComposizioneB> composizione=(LinkedHashSet<ComposizioneB>) composizioneDAO.doRetrieveByOrdine(ordine);
		
		assertNotNull(composizione);
		assertFalse(composizione.isEmpty());
		
		//Caso errato
		ordine.setN_fattura("");
		
		composizione=(LinkedHashSet<ComposizioneB>) composizioneDAO.doRetrieveByOrdine(ordine);
		
		assertNull(composizione);
	}

	private ComposizioneDAO composizioneDAO;
}