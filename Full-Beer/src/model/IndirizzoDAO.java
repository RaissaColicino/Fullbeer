package model;

import java.util.LinkedHashSet;
import java.util.Set;

import com.sun.istack.internal.logging.Logger;

import beans.IndirizzoB;
import beans.UtenteB;

public class IndirizzoDAO {
	static Logger log=Logger.getLogger("IndirizzoMDebugger", null);
	
	public IndirizzoDAO() {
		// TODO Auto-generated constructor stub
	}

	//permette di salvare un indirizzo
public void doSave(IndirizzoB indirizzo) {
		
	}

//permette di ottenere tutti gli indirizzi di un utente
public Set<IndirizzoB> doRetrieveByUtente(UtenteB utente){
	LinkedHashSet<IndirizzoB> indirizzi=new LinkedHashSet<IndirizzoB>();

	return indirizzi;
}

//permette di cancellare un indirizzo
public void doDelete(IndirizzoB indirizzo) {
	
}

}
