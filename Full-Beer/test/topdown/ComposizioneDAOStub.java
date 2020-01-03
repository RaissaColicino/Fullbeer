package topdown;

import java.util.LinkedHashSet;
import java.util.Set;

import com.sun.istack.internal.logging.Logger;

import beans.OrdineB;
import beans.ComposizioneB;
public class ComposizioneDAOStub {

		static Logger log=Logger.getLogger("ComposizioneDAOStubDebugger", null);
		
		public ComposizioneDAOStub(){
			
		}
		

		public Set<ComposizioneB> doRitrieveByOrdine(OrdineB ordine){
			LinkedHashSet<ComposizioneB> composizione= new LinkedHashSet<ComposizioneB>();
			
			ComposizioneB composizioneA= new ComposizioneB();
			
			composizioneA.setN_fattura(ordine.getN_fattura());
			composizioneA.setProdottoCodice("001");
			composizioneA.setNome_prodotto("NastroAzzurro");
			composizioneA.setPrezzo(3.50);
			composizioneA.setQuantità(1);
			
			composizione.add(composizioneA);
			
			return composizione;
			
			
		} 

		public void doSave(ComposizioneB composizione){

		}
}
