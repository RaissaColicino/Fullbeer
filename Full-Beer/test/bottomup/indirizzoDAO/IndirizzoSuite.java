  
package bottomup.indirizzoDAO;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class IndirizzoSuite {
	public static Test suite() {
		TestSuite suite=new TestSuite();
		suite.addTest(new IndirizzoDAOTestCase("doRetrieveByUtente"));
		suite.addTest(new IndirizzoDAOTestCase("doSave"));
		suite.addTest(new IndirizzoDAOTestCase("doDelete"));

		return suite;
	}
	
	public static void main(String[] args) {
		TestRunner.run(suite());
	}
}