package topdown;
import java.util.LinkedHashMap;
import java.util.logging.Logger;
import java.util.Map;


import beans.RuoloB;
import beans.UtenteB;

public class RuoloDAOStub {
	static Logger log=Logger.getLogger("RuoloStub");
	
public RuoloDAOStub() {
		
	}
public Map<String, RuoloB> doRetrieveByUtente(UtenteB user){
	log.info("Metodo: doRetrieveByUtente");

	LinkedHashMap<String, RuoloB> ruoli=new LinkedHashMap<String, RuoloB>();
	
	RuoloB utente=new RuoloB();
	utente.setUsername(user.getUsername());
	utente.setRuolo("Utente");
	
	log.info("Inserisco ruolo: " + utente.getRuolo());
	ruoli.put(utente.getRuolo(), utente);
	
	RuoloB gestoreCatalogo=new RuoloB();
	gestoreCatalogo.setUsername(user.getUsername());
	gestoreCatalogo.setRuolo("Catalogo");

	log.info("Inserisco ruolo: " + gestoreCatalogo.getRuolo());
	ruoli.put(gestoreCatalogo.getRuolo(), gestoreCatalogo);
	
	RuoloB gestoreOrdini=new RuoloB();
	gestoreOrdini.setUsername(user.getUsername());
	gestoreOrdini.setRuolo("Ordini");
	
	log.info("Inserisco ruolo: " + gestoreOrdini.getRuolo());
	ruoli.put(gestoreOrdini.getRuolo(), gestoreOrdini);
	
	return ruoli;
}

}
