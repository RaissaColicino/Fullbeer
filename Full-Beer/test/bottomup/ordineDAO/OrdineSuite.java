package bottomup.ordineDAO;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class OrdineSuite {
	public static Test suite() {
		TestSuite suite=new TestSuite();

		suite.addTest(new OrdineDAOTestCase("doSave"));
		suite.addTest(new OrdineDAOTestCase("doRetrieveAll"));
		suite.addTest(new OrdineDAOTestCase("doRetrieveByNumero"));
		suite.addTest(new OrdineDAOTestCase("doRetrieveByUtente"));
		suite.addTest(new OrdineDAOTestCase("doRetrieveIfAttivi"));
		suite.addTest(new OrdineDAOTestCase("aggiornaStato"));
		
		return suite;
	}
	
	public static void main(String[] args) {
		TestRunner.run(suite());
	}
}