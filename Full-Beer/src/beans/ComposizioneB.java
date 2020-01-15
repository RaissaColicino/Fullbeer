package beans;

public class ComposizioneB {

	public ComposizioneB(){
		
		
		
	}
	
	public String getN_fattura() {
		return n_fattura;
	}
	public void setN_fattura(String n_fattura) {
		this.n_fattura = n_fattura;
	}

	public String getProdottoCodice() {
		return prodotto_codice;
	}

	public void setProdottoCodice(String prodotto) {
		this.prodotto_codice = prodotto;
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

	private String n_fattura;
	private String prodotto_codice;
	private String nome_prodotto;
	private double prezzo;
	private int quantità;
}
