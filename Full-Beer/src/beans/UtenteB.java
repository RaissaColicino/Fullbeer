package beans;

public class UtenteB {
	
	public UtenteB(){
		
			
		}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getData_nascita() {
		return data_nascita;
	}
	public void setData_nascita(String data_nascita) {
		this.data_nascita = data_nascita;
	}
	
	
	private String nome; //variabile d'istanza
	private String cognome; //variabile d'istanza
	private String username; //variabile d'istanza
	private String mail; //variabile d'istanza
	private String password; //variabile d'istanza
	private String data_nascita; //variabile d'istanza
}
