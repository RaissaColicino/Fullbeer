package model;

import com.sun.istack.internal.logging.Logger;

import beans.ProdottoB;

public class ProdottoM {
	static Logger log=Logger.getLogger("ProdottoMDebugger", null);
	public ProdottoM() {
		// TODO Auto-generated constructor stub
	}
	
	//permette di salvare un nuovo prodotto
public void doSave(ProdottoB prodotto) {
		
	}

//permette di ottenere un prodotto in base al suo codice
public ProdottoB doRetrieveByCodice(String codice){
	
	return null;
}

//permette di aggiornare un prodotto
public void doUpdate(ProdottoB prodotto) {
	
}

//permette di cancellare un prodotto
public void doDelete(ProdottoB prodotto) {
	
}

}
