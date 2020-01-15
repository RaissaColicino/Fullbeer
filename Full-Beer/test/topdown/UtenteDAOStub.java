package topdown;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.logging.Logger;


import beans.IndirizzoB;
import beans.RuoloB;
import beans.UtenteB;


public class UtenteDAOStub {
	static Logger log=Logger.getLogger("UtenteStubDebugger");


	public UtenteDAOStub() {
		
	}

	public UtenteB validate(UtenteB user) {
		log.info("Metodo: validate -> metodo: doRetrieveAll");
		LinkedHashMap<String, UtenteB> users=(LinkedHashMap<String, UtenteB>) doRetrieveAll();
		
		log.info("Comincio scorrimento");
		for(UtenteB utente: users.values()) {
			if(utente.getUsername().equals(user.getUsername()) && utente.getPassword().equals(user.getPassword())) {
				log.info("utente restituito: " + utente.getUsername() + ", password: " + utente.getPassword());
				
				return utente;
			}
		}
		
		return null;
	}

	public Map<String, UtenteB> doRetrieveAll(){
		log.info("Metodo: doRetrieveAll -> UtenteDAOStub");
		RuoloDAOStub ruoloDAO=new RuoloDAOStub();
		IndirizzoDAOStub indirizzoDAO=new IndirizzoDAOStub();
		

		LinkedHashMap<String, UtenteB> users=new LinkedHashMap<String, UtenteB>();
	
		
		log.info("Creo l'utente Antonio");
		UtenteB antonio=new UtenteB();
		antonio.setUsername("AntonioG");
		antonio.setPassword("antoniopass");
		antonio.setNome("Antonio");
		antonio.setCognome("Gambale");
		antonio.setMail("antoniogambale@libero.it");
		
		
		log.info("Ottengo indirizzi per Antonio");
		
		LinkedHashSet<IndirizzoB> indirizziAntonio=(LinkedHashSet<IndirizzoB>) indirizzoDAO.doRetrieveByUtente(antonio);
		System.out.println(indirizziAntonio);
		for(IndirizzoB indirizzo: indirizziAntonio){
			System.out.println(indirizzo);
			antonio.addIndirizzo(indirizzo);
		}
		
		log.info("Ottengo ruoli per Antonio");

		LinkedHashMap<String, RuoloB> ruoli=(LinkedHashMap<String, RuoloB>) ruoloDAO.doRetrieveByUtente(antonio);
		antonio.addRuolo(ruoli.get("Utente"));

		log.info("Inserisco: " + antonio.getUsername() + ", " + antonio.getPassword());
		users.put("" + antonio.getUsername(), antonio);
		
		log.info("Creo l'utente root");
		UtenteB root=new UtenteB();
		root.setUsername("root");
		root.setPassword("root");
		root.setNome("Amministratore");
		root.setCognome("Globale");
		root.setMail("root@fullBeer.com");
		
		
		log.info("Ottengo indirizzi per root");
		LinkedHashSet<IndirizzoB> indirizzi=(LinkedHashSet<IndirizzoB>) indirizzoDAO.doRetrieveByUtente(root);
		for(IndirizzoB indirizzo: indirizzi)
			root.addIndirizzo(indirizzo);
		
		log.info("Ottengo ruoli per root");
		LinkedHashMap<String, RuoloB> ruoliRoot=(LinkedHashMap<String, RuoloB>) ruoloDAO.doRetrieveByUtente(root);
		root.addRuolo(ruoliRoot.get("Utente"));
		root.addRuolo(ruoliRoot.get("GestoreCatalogo"));
		root.addRuolo(ruoliRoot.get("GestoreOrdini"));
		
		log.info("Inserisco: " + root.getUsername() + ", " + root.getPassword());
		users.put("" + root.getUsername(), root);

		return users;
	}
	public UtenteB doRetrieveByUsername(String username) {
		log.info("Metodo: doRetrieveByUsername -> metodo: doRetrieveAll");
		LinkedHashMap<String, UtenteB> users=(LinkedHashMap<String, UtenteB>) doRetrieveAll();
		
		log.info("Comincio scorrimento");
		for(UtenteB utente: users.values()) {
			if(utente.getUsername().equals(username)) {
				log.info("utente restituito: " + utente.getUsername());
				
				return utente;
			}
		}
		
		return null;
	}
}
