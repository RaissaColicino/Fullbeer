package bottomup.carrelloB;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class CarrelloSuite {
	public static Test suite() {
		TestSuite suite=new TestSuite();

		suite.addTest(new CarrelloBTestCase("getProdotto"));
		suite.addTest(new CarrelloBTestCase("addProdotto"));
		suite.addTest(new CarrelloBTestCase("reAddProdotto"));
		suite.addTest(new CarrelloBTestCase("removeProdotto"));
		suite.addTest(new CarrelloBTestCase("modificaQt"));
		suite.addTest(new CarrelloBTestCase("contains"));
		suite.addTest(new CarrelloBTestCase("svuotaCarrello"));

		return suite;
	}
	
	public static void main(String[] args) {
		TestRunner.run(suite());
	}
}