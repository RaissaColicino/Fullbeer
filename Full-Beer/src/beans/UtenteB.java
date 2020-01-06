package beans;

import java.util.Set;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;


public class UtenteB {
	
	public UtenteB(){
		
		indirizzi=new LinkedHashSet<IndirizzoB>();
		ruoli=new LinkedHashMap<String, RuoloB>();	
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
		
		public IndirizzoB getIndirizzo(String username) {
			for(IndirizzoB indirizzo: indirizzi)
				if(indirizzo.getUsername()==username)
					return indirizzo;
			
			return null;
		}
		
		public void addIndirizzo(IndirizzoB indirizzo) {
			indirizzi.add(indirizzo);
		}
		public void removeIndirizzo(IndirizzoB indirizzo) {
			indirizzi.remove(indirizzo);
		}
		
		public Map<String, RuoloB> getRuolo() {
			return ruoli;
		}
		
		public void setRuolo(Map<String, RuoloB> ruoli) {
			this.ruoli = ruoli;
		}
		
		public void addRuolo(RuoloB ruolo) {
			 ruoli.put(""+ruolo.getRuolo(),ruolo);
		}
		
		public void removeRuolo(RuoloB ruolo) {
			ruoli.remove(ruolo.getRuolo());
		}
	private String nome; //variabile d'istanza
	private String cognome; //variabile d'istanza
	private String username; //variabile d'istanza
	private String mail; //variabile d'istanza
	private String password; //variabile d'istanza
	private Set<IndirizzoB> indirizzi;
	private Map<String, RuoloB> ruoli;
}
