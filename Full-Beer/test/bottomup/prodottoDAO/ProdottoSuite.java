package bottomup.prodottoDAO;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class ProdottoSuite {
	public static Test suite() {
		TestSuite suite=new TestSuite();

		suite.addTest(new ProdottoDAOTestCase("doSave"));
		suite.addTest(new ProdottoDAOTestCase("doRetrieveAll"));
		suite.addTest(new ProdottoDAOTestCase("doRetrieveByCodice"));
		suite.addTest(new ProdottoDAOTestCase("doUpdate"));
		suite.addTest(new ProdottoDAOTestCase("doDelete"));
		
		return suite;
	}
	
	public static void main(String[] args) {
		TestRunner.run(suite());
	}
}
