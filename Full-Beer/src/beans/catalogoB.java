package beans;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class catalogoB {

	public catalogoB() {
		this.catalogo=new LinkedHashSet<prodottoB>();
	}
	
//permette di ottenere il valore della variabile d'istanza catalogo
public Collection<prodottoB> getCatalogo() {
		return catalogo;
	}

//permette di settare il valore della variabile d'istanza catalogo
public void setCatalogo(Set<prodottoB> catalogo) {
		this.catalogo = catalogo;
	}

//permette di aggiungere un prodotto al catalogo
public void addProdotto(prodottoB prodotto){
	catalogo.add(prodotto);
}

//permette di rimuovere un prodotto dal catalogo
public void removeProdotto(prodottoB prodotto){
	catalogo.remove(prodotto);
}

//permette di ottenere un prodotto presente nel catalogo specificando il codice del prodottoù
public prodottoB getProdotto(String codiceProdotto){
	for(prodottoB p:catalogo)
		if(p.getId().equals(codiceProdotto))
			return p;
	return null;
	}

private Set<prodottoB> catalogo;
}
