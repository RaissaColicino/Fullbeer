package topdown;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import beans.ProdottoB;
import beans.CatalogoB;

public class ProdottoDAOStub {

		static Logger log=Logger.getLogger("ProdottoDAOStubDebugger");
	
		public ProdottoDAOStub(){
			
		}
		
		public Set<ProdottoB> doRetrieveAll(){
			
			LinkedHashSet<ProdottoB> catalogo= new LinkedHashSet<ProdottoB>();
			log.info("Aggiungo prodotti ed eseguo simulazione");
			
			ProdottoB prodottoA= new ProdottoB();
			
			prodottoA.setId("001");
			prodottoA.setNome("Peroni");
			prodottoA.setPrezzo(1.50);
			prodottoA.setQt(40);
			prodottoA.setDescrizione("La birra Peroni è una birra lager italiana prodotta dall'omonimo birrificio a partire dal 1846. Dall'ottobre 2016 fa parte del gruppo giapponese Asahi Breweries. Viene prodotta negli stabilimenti del gruppo situati a Roma, Padova e Bari, mentre il malto proviene dalla Malteria Saplo di Pomezia.");
			prodottoA.setImmagine("peroni.jpg");
			
			
			
			
		}
}
