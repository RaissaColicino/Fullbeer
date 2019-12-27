package model;

import java.util.LinkedHashMap;

import com.sun.istack.internal.logging.Logger;
import com.sun.javafx.collections.MappingChange.Map;

import beans.RuoloB;
import beans.UtenteB;

public class RuoloM {
	static Logger log=Logger.getLogger("RuoloMDebugger", null);

	public RuoloM() {
		// TODO Auto-generated constructor stub
	}
//permette di salvare un ruolo
public void doSave(RuoloB ruolo) {
		
	}	
//HashMap associa ad ogni chiave(nel nostro caso String) un valore (il ruolo)
public Map<String, RuoloB> getRuoloByUtente(UtenteB utente){
	LinkedHashMap<String, RuoloB> ruoli=new LinkedHashMap<String, RuoloB>();

	return (Map<String, RuoloB>) ruoli;
}
}
