package topdown;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import beans.ComposizioneB;
import beans.OrdineB;
import beans.UtenteB;
public class OrdineDAOStub {
	static Logger log=Logger.getLogger("OrdineModelStubDebugger");
	public static String ELABORAZIONE="In elaborazione";
	public static String SPEDIZIONE="In spedizione";
	public static String CONSEGNATO="Consegnato";


public OrdineDAOStub() {
		
	}
public Set<OrdineB> doRetrieveAll() {	
	LinkedHashSet<OrdineB> ordini=new LinkedHashSet<OrdineB>();
	
	UtenteDAOStub utenteDAO=new UtenteDAOStub();
	LinkedHashMap<String, UtenteB> utenti=(LinkedHashMap<String, UtenteB>) utenteDAO.doRetrieveAll();
	
	OrdineB ordineA=createOrdine(utenti.get("root"), "root", "000001", ELABORAZIONE);	
	ordini.add(ordineA);
	
	OrdineB ordineB=createOrdine(utenti.get("AntonioG"), "AntonioG", "000002", SPEDIZIONE);	
	ordini.add(ordineB);
	
	OrdineB ordineC=createOrdine(utenti.get("RaissaC"), "RaissaC", "000003", CONSEGNATO);	
	ordini.add(ordineC);
	
	return ordini;
}


public Set<OrdineB> doRetrieveByUtente(UtenteB utente)  {
	LinkedHashSet<OrdineB> ordini=(LinkedHashSet<OrdineB>) doRetrieveAll();
	
	LinkedHashSet<OrdineB> ordiniUtente=new LinkedHashSet<OrdineB>();
	
	for(OrdineB ordine: ordini)
		if(ordine.getUsername().equals(utente.getUsername()))
			ordiniUtente.add(ordine);
	
	return ordiniUtente;
}



public OrdineB doRetrieveByNumero(String numero)  {
	LinkedHashSet<OrdineB> ordini=(LinkedHashSet<OrdineB>) doRetrieveAll();
	
	log.info("doRetrieveByNumero -> procedo all'ottenimento dell'ordine");
	for(OrdineB ordine: ordini)
		if(ordine.getN_fattura()==numero)
			return ordine;
	
	return null;
}



public void doSave(OrdineB ordine) {
	ComposizioneDAOStub composizioneDAO=new ComposizioneDAOStub();
	
	LinkedHashSet<ComposizioneB> composizione=(LinkedHashSet<ComposizioneB>) ordine.getComposizione();
	for(ComposizioneB comp: composizione)
		composizioneDAO.doSave(comp);
}



public Set<OrdineB> doRetrieveIfAttivi(String order)  {
	LinkedHashSet<OrdineB> ordini=new LinkedHashSet<OrdineB>();
	
	log.info("metodo: doRetrieveIfAttivi -> metodo: doCount -> ottengo gli ordini per la generazione del numero");
	LinkedHashSet<OrdineB> listaOrdini=(LinkedHashSet<OrdineB>) doRetrieveAll();
	
	for(OrdineB ordine: listaOrdini)
		if(ordine.getStato().equals(OrdineB.ELABORAZIONE) 
				|| ordine.getStato().equals(OrdineB.SPEDIZIONE))
			ordini.add(ordine);
	
	return ordini;
}



public String generatoreSottomissione() {
	log.info("Imposto la data di sottomissione come la data odierna");
	Date d=Calendar.getInstance().getTime();
	String format="yyyy-MM-dd";
	DateFormat df=new SimpleDateFormat(format);
	String sottomissione=df.format(d);
	
	return sottomissione;
}

public String generatoreConsegna() {
	log.info("Imposto la data di consegna");
	Calendar cal=Calendar.getInstance();
	cal.add(Calendar.DATE, 3);
	String formatOne="yyyy-MM-dd";
	DateFormat dfOne=new SimpleDateFormat(formatOne);
	String consegna=dfOne.format(cal.getTime());
	
	return consegna;
}


//metodo per la simulazione di ordini nel DataBase
private OrdineB createOrdine(UtenteB user, String username, String numero, String stato) {
	OrdineB ordA=new OrdineB();
	ordA.setUsername(user.getUsername());
	ordA.setN_fattura(numero);
	ordA.setStato(stato);

	
	log.info("Gestisco le date");
	ordA.setDate(generatoreSottomissione());
	ordA.setStato(generatoreConsegna());
	
	log.info("Ottengo i prodotti che compongono l'ordine");
	ComposizioneDAOStub composizioneDAO=new ComposizioneDAOStub();
	LinkedHashSet<ComposizioneB> comp=(LinkedHashSet<ComposizioneB>) 
			composizioneDAO.doRitrieveByOrdine(ordA);
	
	float totale=0;
	for(ComposizioneB cb: comp)
		totale+=cb.getPrezzo();
	
	ordA.setImporto(totale);
	ordA.setComposizione(comp);
	
	return ordA;
}

public void modificaStato(OrdineB ordine) {
	
}

}
