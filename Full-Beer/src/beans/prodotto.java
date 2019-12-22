package beans;

public class prodotto {

	public prodotto() {
		
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQt() {
		return qt;
	}
	public void setQt(int qt) {
		this.qt = qt;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getImmagine() {
		return Immagine;
	}
	public void setImmagine(String immagine) {
		Immagine = immagine;
	}


	private String id;  //variabile d'istanza id
	private String nome; //variabile d'istanza nome
	private int qt; //variabile d'istanza quantità
	private double prezzo; //variabile d'istanza prezzo
 	private String descrizione; //variabile d'istanza descrizione
	private String Immagine; //variabile d'istanza immagine
}
