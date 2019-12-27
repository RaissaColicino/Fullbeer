package model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import com.sun.istack.internal.logging.Logger;

import beans.OrdineB;

public class OrdineDAO {
	static Logger log=Logger.getLogger("OrdineModelDebugger", null);
	public OrdineDAO() {
		// TODO Auto-generated constructor stub
	}

//permette di salvare un ordine
public void doSave(OrdineB ordine) {
		
	}

//permette di modificare lo stato di un ordine
public void modificaStato(OrdineB ordine, String stato) {
	
}

//permette di pttenere un ordine specificando il numero
public OrdineB doRetrieveByNumero(String numero) {
	
	return null;
}

//permette di generare la data di sottomissione dell'ordine
public String generatoreSottomissione() {
	log.info("Imposto la data di sottomissione come la data odierna");
	Date d=(Date) Calendar.getInstance().getTime();
	String format="yyyy-MM-dd";
	DateFormat df=new SimpleDateFormat(format);
	String sottomissione=df.format(d);
	
	return sottomissione;
}
//permette di ottenere gli ordini attivi
public Set<OrdineB> doRetrieveIfAttivi() {
	LinkedHashSet<OrdineB> ordini=new LinkedHashSet<OrdineB>();
	
	return ordini;
}

//permette di ottenere tutti gli ordini
public Set<OrdineB> doRetrieveAll(String order){
	LinkedHashSet<OrdineB> ordini=new LinkedHashSet<OrdineB>();
	
	return ordini;
}
}
