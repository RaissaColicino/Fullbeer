package topdown;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import beans.ComposizioneB;
import beans.OrdineB;

public class ComposizioneDAOStub {
	static Logger log=Logger.getLogger("ComposizioneModelStubDebugger");
	
	public ComposizioneDAOStub() {
		
	}
	
	public Set<ComposizioneB> doRetrieveByOrdine(OrdineB ordine){
		LinkedHashSet<ComposizioneB> comp=new LinkedHashSet<ComposizioneB>();
		
		ComposizioneB compOne=new ComposizioneB();
		compOne.setN_fattura(ordine.getN_fattura());
		compOne.setProdottoCodice("00");
		compOne.setNome_prodotto("Nastro Azzurro");
		compOne.setPrezzo(1.50);
		compOne.setQuantità(1);
		
		comp.add(compOne);
		
		return comp;
	}
	
	public void doSave(ComposizioneB composizione) {
		
	}
}