package unit_test;

import gamecomponents.Board;
import gamecomponents.MyBoard;
import gamecomponents.MyDisc;
import org.junit.Before;
import org.junit.Test;
import players.ComputerPlayer;
import players.compstartegies.RandomStrategy;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;


public class RandomComputerPlayerTest {
	
	private static ComputerPlayer _tested = null;
	private static Board _b = null;
	private static MyDisc _myD = null;
	private static MyDisc _hisD = null;


	@Before
	public void setUp() throws Exception {
		
		_b = new MyBoard(4,4);
		_myD = MyDisc.newDisc(0);
		_hisD = MyDisc.newDisc(1);	
		_tested = new ComputerPlayer(0, new RandomStrategy());
		
		
	}

	@Test
	public void chooseColumn_canWin() {
		
		_b.putDiscInColumn(2, _myD);
		_b.putDiscInColumn(2, _myD);
		_b.putDiscInColumn(2, _myD);
		int retVal = _tested.chooseColumn(_b, _myD, _hisD);
		assertSame(retVal, 2);
	}
	
	@Test
	public void chooseColumn_canPrevent() {
		
		_b.putDiscInColumn(2, _hisD);
		_b.putDiscInColumn(2, _hisD);
		_b.putDiscInColumn(2, _hisD);
		int retVal = _tested.chooseColumn(_b, _myD, _hisD);
		assertSame(retVal, 2);
	}
	
	@Test
	public void chooseColumn_randColumn() {
		
		_b.putDiscInColumn(2, _hisD);
		_b.putDiscInColumn(3, _myD);
		int retVal = _tested.chooseColumn(_b, _myD, _hisD);
		assertTrue(retVal >= 0);
		assertTrue(retVal <= 3);
	}
	
	@Test
	public void chooseColumn_noGoodOption() {
		
		_b = new MyBoard(1,1);
		_b.putDiscInColumn(0, _hisD);
		int retVal = _tested.chooseColumn(_b, _myD, _hisD);
		assertSame(retVal, -1);
	}



}
