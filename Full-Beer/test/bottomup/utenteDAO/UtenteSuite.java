  
package bottomup.utenteDAO;

import junit.framework.*;
import junit.textui.TestRunner;

public class UtenteSuite {
	public static Test suite() {
		TestSuite suite=new TestSuite();

		suite.addTest(new UtenteDAOTestCase("doSaveCorretto"));
		suite.addTest(new UtenteDAOTestCase("doSaveErrato"));
		suite.addTest(new UtenteDAOTestCase("doRetrieveByUsernameCorretto"));
		suite.addTest(new UtenteDAOTestCase("doRetrieveByUsernameErrato"));
		suite.addTest(new UtenteDAOTestCase("validateCorretto"));
		suite.addTest(new UtenteDAOTestCase("validateErrato"));
		suite.addTest(new UtenteDAOTestCase("doUpdateCorretto"));
		suite.addTest(new UtenteDAOTestCase("doUpdateErrato"));
		suite.addTest(new UtenteDAOTestCase("doDeleteCorretto"));
		
		return suite;
	}
	
	public static void main(String[] args) {
		TestRunner.run(suite());
	}
}