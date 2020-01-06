package bottomup;
import junit.framework.*;
import junit.textui.TestRunner;


public class RuoloSuite {

	public static Test suite() {
		TestSuite suite=new TestSuite();
		suite.addTest((Test) new RuoloDAOTestCase("doRetrieveByUtente"));
		suite.addTest((Test) new RuoloDAOTestCase("doSave"));

		return suite;
	}
	
	public static void main(String[] args) {
		TestRunner.run(suite());
	}
}
