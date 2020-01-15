package bottomup.ruoloDAO;
import junit.framework.*;
import junit.textui.TestRunner;


public class RuoloSuite {

	public static Test suite() {
		TestSuite suite=new TestSuite();
		suite.addTest(new RuoloDAOTestCase("doRetrieveByUtenteCorretto"));
		suite.addTest( new RuoloDAOTestCase("doRetrieveByUtenteErrato"));
		suite.addTest( new RuoloDAOTestCase("doSaveCorretto"));
		suite.addTest( new RuoloDAOTestCase("doSaveErrato"));
		
		return suite;
	}
	
	public static void main(String[] args) {
		TestRunner.run(suite());
	}
}
