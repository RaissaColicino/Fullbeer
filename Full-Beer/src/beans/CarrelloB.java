package beans;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * classe per modellare il carrello singolo di un utente*/
public class CarrelloB {

	/**
	 * Costruttore*/
		public CarrelloB(){
			
			Carrello=new LinkedHashSet<carrelloItem>();
		}
 private Set<carrelloItem> Carrello;
}
