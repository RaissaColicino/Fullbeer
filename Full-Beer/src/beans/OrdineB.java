package beans;

import java.util.LinkedHashSet;
import java.util.Set;

public class OrdineB {

	public OrdineB(){
		
		composizione=new LinkedHashSet<ComposizioneB>();
	}
	

	public String getStato() {
		return stato;
	}
	
	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getN_fattura() {
		return n_fattura;
	}

	public void setN_fattura(String n_fattura) {
		this.n_fattura = n_fattura;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getConsegna() {
		return consegna;
	}
	public void setConsegna(String consegna) {
		this.consegna = consegna;
	}

	//permette di aggiungere un prodotto nella composizione dell'ordine
	public void addProdotto(ComposizioneB prodotto) {
		composizione.add(prodotto);
	}
	//permette di modificare il valore della variabile d'istanza che modella la composizione dell'ordine
	public void setComposizione(Set<ComposizioneB> composizione) {
		this.composizione = composizione;
	}
	//permette di ottenre il valore della variabile che modella la composizione dell'ordine
	public Set<ComposizioneB> getComposizione() {
		return composizione;
	}
	private String n_fattura;
	private String date;
	private double importo;
	private String username;
	private String consegna;
	private String stato;
	private Set<ComposizioneB> composizione;
	/**
	 * Variabili statiche che descrivono i possibili stati di un ordine
	 * Un ordine è attivo se stato=ELABORAZIONE oppure stato=SPEDIZIONE, altrimenti è chiuso
	 */
	public static String ELABORAZIONE="In elaborazione";
	public static String SPEDIZIONE="In spedizione";
	public static String CONSEGNATO="Consegnato";
}
