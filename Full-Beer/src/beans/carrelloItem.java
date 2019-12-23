package beans;
/**
 * Questa classe modella gli elementi del carrello
 */
public class carrelloItem {

	/**costruttore carrelloItem*/
	public carrelloItem(){
		
		int qt = 1;
	}
/**
 * permette do ottenere la variabile d'istanza che moedella il prodotto*/
	public prodottoB getProdotto() {
		return prodotto;
	}
	
/**
 * permette di settare la variabile d'istanza che modella il prodotto*/	
	public void setProdotto(prodottoB prodotto) {
		this.prodotto = prodotto;
	}

/*permette di ottenere la variabile quantità*/
	public int getQt() {
		return qt;
	}
/*permette di settare la variabile quantità*/
	public void setQt(int qt) {
		this.qt = qt;
	}

private int qt;
private prodottoB prodotto; 
}
