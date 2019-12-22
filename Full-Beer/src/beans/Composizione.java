package beans;

public class Composizione {

	public Composizione(){
		
		
		
	}
	
	public int getN_ordine() {
		return n_ordine;
	}
	public void setN_ordine(int n_ordine) {
		this.n_ordine = n_ordine;
	}

	public String getProdotto() {
		return prodotto;
	}

	public void setProdotto(String prodotto) {
		this.prodotto = prodotto;
	}

	public String getNome_prodotto() {
		return nome_prodotto;
	}

	public void setNome_prodotto(String nome_prodotto) {
		this.nome_prodotto = nome_prodotto;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getQuantità() {
		return quantità;
	}

	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}

	private int n_ordine;
	private String prodotto;
	private String nome_prodotto;
	private double prezzo;
	private int quantità;
}
