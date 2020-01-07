package beans;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class CatalogoB {

	public CatalogoB() {
		this.catalogo=new LinkedHashSet<ProdottoB>();
	}
	
//permette di ottenere il valore della variabile d'istanza catalogo
public Collection<ProdottoB> getCatalogo() {
		return catalogo;
	}

//permette di settare il valore della variabile d'istanza catalogo
public void setCatalogo(Set<ProdottoB> catalogo) {
		this.catalogo = catalogo;
	}

//permette di aggiungere un prodotto al catalogo
public void addProdotto(ProdottoB prodotto){
	catalogo.add(prodotto);
}

//permette di rimuovere un prodotto dal catalogo
public void removeProdotto(ProdottoB prodotto){
	catalogo.remove(prodotto);
}

//permette di ottenere un prodotto presente nel catalogo specificando il codice del prodotto
public ProdottoB getProdotto(String codiceProdotto){
	for(ProdottoB p:catalogo)
		if(p.getId().equals(codiceProdotto))
			return p;
	return null;
	}

private Set<ProdottoB> catalogo;
}
