package unit_test;

import gamecomponents.*;
import gameexceptions.IllegalColumnNumberException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import players.MyPlayer;
import players.Player;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class GameTest {

	
	private static Game _tested = null;
	private static MyPlayer _p1 = null;
	private static MyPlayer _p2 = null;
	private static MyBoard _b = null;
	
	
	private class PlayerMock extends MyPlayer{
		
		public PlayerMock(int num) {
			super(num);
		}
		
		
		public boolean isComp(){
			return true;
		}

		@Override
		public int chooseColumn(Board b, Disc myDisc, Disc hisDisc) {
			return 0;
		}
	}


	@Before
	public void setUp() throws Exception {
		
		_tested = null;
		_p1 = new PlayerMock(1);
		_p2 = new PlayerMock(2);
		_b = new MyBoard(4,4);
		_tested = new MyGame(_p1, _p2, _b);
		
	}

	@After
	public void tearDown() throws Exception {
		
		Field gameOverField = _tested.getClass().getDeclaredField("gameOver");
		gameOverField.setAccessible(true);
		gameOverField.set(_tested, false);
		gameOverField.setAccessible(false);
	}

	
	
	
	@Test
	public void runTurn_gameOver() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		Field gameOverField = _tested.getClass().getDeclaredField("gameOver");
		gameOverField.setAccessible(true);
		gameOverField.set(_tested, true);
		
		int retVal = _tested.runTurn();
		assertSame(retVal, -1);
		
	}
	
	
	
	@Test
	public void runTurn_invalidChoice() {
		
		try{
			class PlayerMockInvalidChoice extends PlayerMock{
				public PlayerMockInvalidChoice(int num) {
					super(num);
				}
				public int chooseColumn(Board b, Disc myDisc, Disc hisDisc){
					return -1;
				}			
			}
			
			_tested = new MyGame(new PlayerMockInvalidChoice(1), _p2, _b);
			_tested.runTurn();
	        fail("Failed: Should get a IllegalColumnNumberException Exception"); 
	    }
	    catch (IllegalColumnNumberException e) {
	        // Assert that this exception is thrown as expected
	    	assertEquals("-1",e.getMessage());        
	    }		
	}
	
	
	@Test
	public void runTurn_winningDisc() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		_b.putDiscInColumn(0, MyDisc.newDisc(1));
		_b.putDiscInColumn(0, MyDisc.newDisc(1));
		_b.putDiscInColumn(0, MyDisc.newDisc(1));
		int retVal = _tested.runTurn();

		assertTrue(_tested.gameOver()); //check if really entered the 'if'	
		assertSame(retVal, 0); // check method return value as usual
		
	}
	
	@Test
	public void runTurn_allGood() {
		
		int retVal = _tested.runTurn();	
		assertSame(retVal, 0); 		
	}
	
	@Test
	public void gameOver_allGood() {
		
		boolean retVal = _tested.gameOver();	
		assertFalse(retVal); 		
	}
	
	@Test
	public void winner_noWinner() {
		
		Player retVal = _tested.winner();	
		assertNull(retVal); 		
	}
	
	
	@Test
	public void winner_runTurn_thereIsAWinner() {

		_b.putDiscInColumn(0, MyDisc.newDisc(1));
		_b.putDiscInColumn(0, MyDisc.newDisc(1));
		_b.putDiscInColumn(0, MyDisc.newDisc(1));
		_tested.runTurn();		
		Player retVal = _tested.winner();	
		assertEquals(retVal, _p1); 		
	}
	
	@Test
	public void player1_allGood() {
	
		Player retVal = _tested.player1();	
		assertEquals(retVal, _p1); 		
	}
	
	@Test
	public void player2_allGood() {
	
		Player retVal = _tested.player2();	
		assertEquals(retVal, _p2); 		
	}
	
	@Test
	public void currentPlayer_allGood() {
	
		Player retVal = _tested.currentPlayer();	
		assertEquals(retVal, _p1); 		
	}
	
	@Test
	public void currentRival_allGood() {
	
		Player retVal = _tested.currentRival();	
		assertEquals(retVal, _p2); 		
	}
	
	@Test
	public void getBoard_allGood() {
	
		Board retVal = _tested.getBoard();	
		assertEquals(retVal, _b); 		
	}

	
	
	

}
