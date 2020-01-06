package beans;

public class IndirizzoB {

	public IndirizzoB() {
		
	}
private String username;
private String citta;
private String via;
private String cap;

//permette di ottenere il valore della variabile d'istanza username
public String getUsername() {
	return username;
}

//permette di settare il valore della variabile d'istanza username
public void setUsername(String username) {
	this.username = username;
}

//permette di ottenere il valore della variabile d'istanza citta
public String getCitta() {
	return citta;
}

//permette di settare il valore della variabile d'istanza citta
public void setCittà(String citta) {
	this.citta = citta;
}

//permette di ottenere il valore della variablie d'istanza via
public String getVia() {
	return via;
}

//permette di settare il valore della variabile d'istanza via
public void setVia(String via) {
	this.via = via;
}

//permette di ttenere il valore della variabile d'istanza cap
public String getCap() {
	return cap;
}

//permette di settare il valore della variabile d'istanza cap
public void setCap(String cap) {
	this.cap = cap;
}


@Override
public String toString() {
	return citta + ", " + via + ", " + cap + ", " + username;
}

}
