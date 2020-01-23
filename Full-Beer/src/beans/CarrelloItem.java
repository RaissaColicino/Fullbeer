package beans;
/**
 * Questa classe modella gli elementi del carrello
 */
public class CarrelloItem {

	/**costruttore carrelloItem*/
	public CarrelloItem(){
		
	 qt = 1;
	}
/**
 * permette do ottenere la variabile d'istanza che moedella il prodotto*/
	public ProdottoB getProdotto() {
		return prodotto;
	}
	
/**
 * permette di settare la variabile d'istanza che modella il prodotto*/	
	public void setProdotto(ProdottoB prodotto) {
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
	/**
	 * Permette di confrontare due prodotti per verificare che essi siano uguali 
	 */
	@Override
	public boolean equals(Object item) {
		if(!(item instanceof CarrelloItem) || item==null)
			return false;
		
		CarrelloItem carrelloItem=(CarrelloItem) item;
		
		return prodotto.getId().equals(carrelloItem.getProdotto().getId());
	}
private int qt;
private ProdottoB prodotto; 
}
