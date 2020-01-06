package topdown;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import beans.IndirizzoB;
import beans.UtenteB;


public class IndirizzoDAOStub {
	static Logger log=Logger.getLogger("IndirizzoStubDebugger");
	

	public IndirizzoDAOStub() {
		
	}
	

	public Set<IndirizzoB> doRetrieveByUtente(UtenteB user){
		LinkedHashSet<IndirizzoB> indirizzi=new LinkedHashSet<IndirizzoB>();

		IndirizzoB indirizzoA=new IndirizzoB();
		indirizzoA.setCittà("Montemarano");
		indirizzoA.setVia("C/da Macchia del monte,14");
		indirizzoA.setCap("83040");
		indirizzoA.setUsername(user.getUsername());
		indirizzi.add(indirizzoA);
		
		IndirizzoB indirizzoB=new IndirizzoB();
		indirizzoB.setUsername(user.getUsername());
		indirizzoB.setCap("83048");
		indirizzoB.setCittà("Montella");
		indirizzoB.setVia("Via San Francesco");
		indirizzi.add(indirizzoB);
		
		return indirizzi;
	}
}
