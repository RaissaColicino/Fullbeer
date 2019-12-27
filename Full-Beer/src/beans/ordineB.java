package beans;

public class ordineB {

	public ordineB(){
		
		
	}
	
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}

	public int getN_fattura() {
		return n_fattura;
	}

	public void setN_fattura(int n_fattura) {
		this.n_fattura = n_fattura;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getImporto() {
		return importo;
	}

	public void setImporto(float importo) {
		this.importo = importo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private int n_fattura;
	private String date;
	private float importo;
	private String username;
	private String stato;
}
