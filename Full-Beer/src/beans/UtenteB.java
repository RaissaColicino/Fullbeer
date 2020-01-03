package beans;

import java.util.Set;

import com.sun.javafx.collections.MappingChange.Map;

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
	
	 
		public void setIndirizzi(Set<IndirizzoB> indirizzi) {
			this.indirizzi=indirizzi;
		}
		public IndirizzoB getIndirizzo(String codice) {
			for(IndirizzoB indirizzo: indirizzi)
				if(indirizzo.getUsername()==codice)
					return indirizzo;
			
			return null;
		}
		public void addIndirizzo(IndirizzoB indirizzo) {
			indirizzi.add(indirizzo);
		}
		public void removeIndirizzo(IndirizzoB indirizzo) {
			indirizzi.remove(indirizzo);
		}
		
	private String nome; //variabile d'istanza
	private String cognome; //variabile d'istanza
	private String username; //variabile d'istanza
	private String mail; //variabile d'istanza
	private String password; //variabile d'istanza
	private Set<IndirizzoB> indirizzi;
	private Map<String, RuoloB> ruoli;
}
