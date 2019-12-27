package model;

import com.sun.istack.internal.logging.Logger;

import beans.UtenteB;

public class UtenteM {
	static Logger log=Logger.getLogger("UtenteMDebugger", null);
	
	public UtenteM() {
		// TODO Auto-generated constructor stub
	}
	
//funzionalità che permette di salvare un nuovo utente
public void doSave(UtenteB utente) {
		
	}

//permette di verificare che l'utente sia già registrato con delle specifiche credenziali
public UtenteB validate(UtenteB utente) {
	
	return null;
}

//permette di ottenere un utente specificando l'username
public UtenteB doRetrieveByUsername(String username) {
	
	return null;
}

//permette di aggiornare i dati di un utente già memorizzato
public void doUpdate(UtenteB utente) {
	
}

//permette di eliminare un utente
public void doDelete(UtenteB utente) {
	
}
}
