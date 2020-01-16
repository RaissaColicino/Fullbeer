package bottomup.composizioneDAO;


import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class ComposizioneSuite {
	public static Test suite() {
		TestSuite suite=new TestSuite();
		suite.addTest(new ComposizioneDAOTestCase("doSave"));
		suite.addTest(new ComposizioneDAOTestCase("doRetrieveByOrdine"));

		return suite;
	}
	
	public static void main(String[] args) {
		TestRunner.run(suite());
	}
}