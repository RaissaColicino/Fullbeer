  
package topdown;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

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
		
		OrdineB ordOne=createOrdine(utenti.get("root"),  "000001", ELABORAZIONE);	
		ordini.add(ordOne);
		
		OrdineB ordTwo=createOrdine(utenti.get("AntonioG"),  "000002", SPEDIZIONE);	
		ordini.add(ordTwo);
		
		OrdineB ordThree=createOrdine(utenti.get("AntonioG"),  "000003", CONSEGNATO);	
		ordini.add(ordThree);
		
		return ordini;
	}
	
	public Set<OrdineB> doRetrieveByUtente(UtenteB utente) {
		LinkedHashSet<OrdineB> ordini=(LinkedHashSet<OrdineB>) doRetrieveAll();
		
		LinkedHashSet<OrdineB> ordiniUtente=new LinkedHashSet<OrdineB>();
		
		for(OrdineB ordine: ordini)
			if(ordine.getUsername().equals(utente.getUsername()))
				ordiniUtente.add(ordine);
		
		return ordiniUtente;
	}
	
	public OrdineB doRetrieveByNumero(String numero) {
		LinkedHashSet<OrdineB> ordini=(LinkedHashSet<OrdineB>) doRetrieveAll();
		
		log.info("doRetrieveByNumero -> procedo all'ottenimento dell'ordine");
		for(OrdineB ordine: ordini)
			if(ordine.getN_fattura().equals(numero))
				return ordine;
		
		return null;
	}
	
	public void doSave(OrdineB ordine) {
		log.info("sto cercando di salvare  l'ordine");
		ComposizioneDAOStub composizioneDAO=new ComposizioneDAOStub();
		log.info("ho creato la composizione");
		LinkedHashSet<ComposizioneB> composizione=(LinkedHashSet<ComposizioneB>) ordine.getComposizione();
		for(ComposizioneB comp: composizione){
			log.info("cerco di salvare la composizione");
			composizioneDAO.doSave(comp);
		}}
	
	public void aggiornaStato(OrdineB ordine) {
		
	}
	
	public Set<OrdineB> doRetrieveIfAttivi(String order) {
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
	

	
	public String generatoreNumero() {		
		log.info("generatoreNumero -> eseguo doCount");
		int count=doCount();
		count++;
		
		return String.format("%06d", count);
	}
	
	public int doCount() {
		int count=0;
		
		log.info("metodo: generatoreNumero -> metodo: doCount -> ottengo gli ordini per la generazione del numero");
		LinkedHashSet<OrdineB> ordini=(LinkedHashSet<OrdineB>) doRetrieveAll();

		for(OrdineB ordine: ordini)
			count++;
		
		return count;
	}
	
	//Metodo per la simulazione di ordini nel DB
	private OrdineB createOrdine(UtenteB user, String numero, String stato) {
		OrdineB ordOne=new OrdineB();
		ordOne.setUsername(user.getUsername());
		ordOne.setN_fattura(numero);
		ordOne.setStato(stato);
	
		
		log.info("Gestisco le date");
		ordOne.setDate(generatoreSottomissione());
		
		
		log.info("Ottengo i prodotti che compongono l'ordine");
		ComposizioneDAOStub composizioneDAO=new ComposizioneDAOStub();
		LinkedHashSet<ComposizioneB> comp=(LinkedHashSet<ComposizioneB>) 
				composizioneDAO.doRetrieveByOrdine(ordOne);
		
		double totale=0;
		for(ComposizioneB cb: comp)
			totale+=cb.getPrezzo();
		
		ordOne.setImporto(totale);
		ordOne.setComposizione(comp);
		
		return ordOne;
	}
}
