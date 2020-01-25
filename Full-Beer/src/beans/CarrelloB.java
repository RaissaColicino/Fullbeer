package beans;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * classe per modellare il carrello singolo di un utente*/
public class CarrelloB {

	/*** Costruttore***/
		public CarrelloB(){
			
			carrello=new LinkedHashSet<CarrelloItem>();
		}
		
//permette di inserire un elemento nel carrello
public void addProdotto(CarrelloItem carrelloItem){
	if(carrelloItem==null || carrelloItem.getProdotto()==null || carrelloItem.getQt()<1 
			|| carrelloItem.getProdotto().getId()==null || carrelloItem.getProdotto().getId().equals(""))
			
		return;
	
	carrello.add(carrelloItem);
}

//permette di inserire un elemento nel carrello se già presente aumentandone la quantità
public void reAddProdotto(CarrelloItem carrelloItem){
	if(carrelloItem==null || carrelloItem.getProdotto()==null || carrelloItem.getQt()<1
			|| carrelloItem.getProdotto().getId()==null || carrelloItem.getProdotto().getId().equals(""))
		return;
	for(CarrelloItem item:carrello){
		if(item.equals(carrelloItem))
			item.setQt(item.getQt()+1);
	}
}

//permette di ottenere un elemento del carrello specificandone il codice
public CarrelloItem getProdotto(String codiceProdotto){
	if(codiceProdotto==null || codiceProdotto.equals(""))
		return null;
	
	
	for(CarrelloItem item :carrello){
		if(item.getProdotto().getId().equals(codiceProdotto))
			return item;
	}
	return null;
}

//permette di rimuovere un elemento dal carrello
public void removeProdotto(CarrelloItem carrelloItem){
	if(carrelloItem==null || carrelloItem.getProdotto()==null || carrelloItem.getQt()<1 
			|| carrelloItem.getProdotto().getId()==null || carrelloItem.getProdotto().getId().equals(""))
				return;
	
	ArrayList<CarrelloItem> array=new ArrayList<CarrelloItem>();
	for(CarrelloItem item:carrello){
		array.add(item);
	}
	for(int i=0; i<array.size();i++){
		if(array.get(i).equals(carrelloItem))
			array.remove(i);
	}
	svuotaCarrello(); //svuota il set per poterlo aggiornare
	
	for(CarrelloItem item:array){
		carrello.add(item);
	}
	
}

//permette di ottenere il valore della variabile d'istanza che modella il carrello
public Set<CarrelloItem> getCarrello(){
	return carrello;
}

//Permette di svuotare il carrello
 public void svuotaCarrello() {
	carrello.clear();
}
 
	/**
	 * Permette di verificare che un elemento sia gia presente nel carrello
	 *  @return true se il prodotto è già nel carrello, altrimenti false
	 */
	public boolean contains(CarrelloItem carrelloItem) {
		if(carrelloItem==null || carrelloItem.getProdotto()==null
				|| carrelloItem.getProdotto().getId()==null || carrelloItem.getProdotto().getId().equals(""))
			return false;

		
		for(CarrelloItem item: carrello)
			if(item.equals(carrelloItem))
				return true;

		return false;
	}
	
	// Permette di sapere se il carrello è vuoto oppure no @return true se vuoto, altrimenti false
	public boolean isEmpty() {
		if(carrello.size()==0)
			return true;
		
		return false;
	}
	//Permette di modificare il valore della variabile d'istanza carrello
	public void setCarrello(Set<CarrelloItem> carrello) {
		this.carrello=carrello;
	}
	
//permette di aumentare o diminuire di un unità la quantità di un elemento nel carrello
	public void modificaQT(String codiceProdotto, String action){
		if(codiceProdotto==null || codiceProdotto.equals(""))
				
			return;
		for(CarrelloItem item:carrello){
			if(item.getProdotto().getId().equals(codiceProdotto))
				if(action.equals("plus"))
					item.setQt(item.getQt()+1);
				else if(action.equals("minus")){
					item.setQt(item.getQt()-1);
				}
					}
	}
private Set<CarrelloItem> carrello;
public static final String ACTION_PLUS="plus";
public static final String ACTION_MINUS="minus";
}
