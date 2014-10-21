package unit_test;

import gamecomponents.MyDisc;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class DiscTest {
	
	private static MyDisc _tested = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		_tested = MyDisc.newDisc(0);
	}


	@Test
	public void getPlayerNum_allGood() {
		assertSame(0,_tested.getPlayerNum());
	}
	
	@Test
	public void equals_equals() {
		assertTrue(_tested.equals(MyDisc.newDisc(0)));
	}
	
	
	@Test
	public void equals_not_Equals() {
		assertFalse(_tested.equals(MyDisc.newDisc(1)));
	}
	
	@Test
	public void clone_allGood() {
		assertEquals(_tested, _tested.clone());
	}

}
