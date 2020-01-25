package bottomup.carrelloB;

import beans.CarrelloB;
import beans.CarrelloItem;
import beans.ProdottoB;
import junit.framework.TestCase;

public class CarrelloBTestCase extends TestCase {
	public CarrelloBTestCase(String name) {
		super(name);
	}

	@Override
	public void setUp() {
	carrello=new CarrelloB();
	}
	
	@Override
	public void tearDown() {
		carrello.svuotaCarrello();
	}
	
	public void getProdotto() {
		//Creo prodotto
		ProdottoB prodotto=new ProdottoB();
		prodotto.setId("001");
		
		//Creo item
		CarrelloItem item=new CarrelloItem();
		item.setProdotto(prodotto);
	
		
		carrello.addProdotto(item);
		
		//Caso corretto
		assertNotNull(carrello.getProdotto(item.getProdotto().getId()));
		
		//Caso errato
		assertNull(carrello.getProdotto(null));
		
	}
	
	public void addProdotto() {
		//Creo prodotto
		ProdottoB prodotto=new ProdottoB();
		prodotto.setId("001");
		
		//Creo item
		CarrelloItem item=new CarrelloItem();
		item.setProdotto(prodotto);
		
		
		//Caso corretto
		carrello.addProdotto(item);
		
		assertNotNull(carrello.getProdotto(item.getProdotto().getId()));
		
		//Caso errato
		//Primo caso
		item.getProdotto().setId("");
		carrello.addProdotto(item);
		
		assertNull(carrello.getProdotto(item.getProdotto().getId()));
		
		
		//Terzo caso
		item.setQt(0);
		
		carrello.addProdotto(item);
		
		assertNull(carrello.getProdotto(item.getProdotto().getId()));
	}
	
	public void reAddProdotto() {
		//Creo prodotto
		ProdottoB prodotto=new ProdottoB();
		prodotto.setId("001");
		
		//Creo item
		CarrelloItem item=new CarrelloItem();
		item.setProdotto(prodotto);
	
		//Caso corretto
		//Aggiungo prodotto prima volta
		carrello.reAddProdotto(item);
		
		CarrelloItem temp=carrello.getProdotto(item.getProdotto().getId());
		assertNotNull(temp);
		assertTrue(temp.getQt()==1);
		
		//Aggiungo prodotto seconda volta
		carrello.reAddProdotto(item);
		
		temp=carrello.getProdotto(item.getProdotto().getId());
		assertNotNull(temp);
		assertTrue(temp.getQt()==2);
		
		//Caso errato
		//Primo casp
		item.getProdotto().setId("");
		carrello.addProdotto(item);
		
		assertNull(carrello.getProdotto(item.getProdotto().getId()));
		
		
		//secondo caso
		item.setQt(0);
		
		carrello.addProdotto(item);
		
		assertNull(carrello.getProdotto(item.getProdotto().getId()));
	}
	
	public void removeProdotto() {
		//Creo prodotto
		ProdottoB prodotto=new ProdottoB();
		prodotto.setId("001");
		
		//Creo item
		CarrelloItem item=new CarrelloItem();
		item.setProdotto(prodotto);
	
		//Aggiungo prodotto
		carrello.reAddProdotto(item);
		
		//Caso corretto
		int size=carrello.getCarrello().size();
		
		carrello.removeProdotto(item);
		
		assertNull(carrello.getProdotto(item.getProdotto().getId()));
		assertTrue(carrello.getCarrello().size()<size);
		
		//Caso errato
		//Primo caso
		size=carrello.getCarrello().size();

		carrello.removeProdotto(item);
		
		assertTrue(carrello.getCarrello().size()==size);
		
		//Secondo caso
		size=carrello.getCarrello().size();

		carrello.removeProdotto(null);
		
		assertTrue(carrello.getCarrello().size()==size);
	}
	
	public void modificaQt() {
		//Creo prodotto
		ProdottoB prodotto=new ProdottoB();
		prodotto.setId("001");
		
		//Creo item
		CarrelloItem item=new CarrelloItem();
		item.setProdotto(prodotto);
		
		
		//Aggiungo prodotto
		carrello.reAddProdotto(item);
		
		//Caso corretto
		//Primo caso
		int qt=item.getQt();
		
		carrello.modificaQT(item.getProdotto().getId(), CarrelloB.ACTION_PLUS);
		
		assertTrue(item.getQt()==qt+1);
		
		//Caso errato
		//Primo caso		
		qt=item.getQt();
		
		carrello.modificaQT("", CarrelloB.ACTION_PLUS);
		
		assertTrue(item.getQt()==qt);
		
		
		//Secondo caso
		qt=item.getQt();
		
		carrello.modificaQT(item.getProdotto().getId(), "pls");
		
		assertTrue(item.getQt()==qt);
	}
	
	public void contains() {
		//Creo prodotto
		ProdottoB prodotto=new ProdottoB();
		prodotto.setId("001");
		
		//Creo item
		CarrelloItem item=new CarrelloItem();
		item.setProdotto(prodotto);
	
		//Aggiungo prodotto
		carrello.reAddProdotto(item);
		
		//Caso corretto
		assertTrue(carrello.contains(item));
		
		//Caso errato
		//Primo caso
		assertFalse(carrello.contains(null));
		
		//Secondo caso
		item.getProdotto().setId("");
		
		assertFalse(carrello.contains(item));
		}
	
	public void svuotaCarrello() {
		//Creo prodotto
		ProdottoB prodotto=new ProdottoB();
		prodotto.setId("001");
		
		//Creo item
		CarrelloItem item=new CarrelloItem();
		item.setProdotto(prodotto);
		
		
		//Aggiungo prodotto
		carrello.reAddProdotto(item);
		
		//Caso corretto
		carrello.svuotaCarrello();
		
		assertTrue(carrello.isEmpty());
	}
	
	private CarrelloB carrello;
}