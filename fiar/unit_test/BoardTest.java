package fiar.unit_test;

import fiar.gamecomponents.Board;
import fiar.gamecomponents.Disc;
import fiar.gamecomponents.MyBoard;
import fiar.gamecomponents.MyDisc;
import fiar.gameexceptions.ColumnFullException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
	
	private static Board _tested = null;


	@Before
	public void setUp() throws Exception {
		
		_tested = null;
		
	}

	@Test
	public void putDiscInColumn_AllGood() {
		
		_tested = new MyBoard(2,2);
		MyDisc d = MyDisc.newDisc(0);
		_tested.putDiscInColumn(1, d);
		Disc retVal = _tested.getTop(1);
		assertSame(retVal, d);
			
	}

	@Test
	public void putDiscInColumn_columnFull() {
		
		try {
			_tested = new MyBoard(2,2);
			_tested.putDiscInColumn(1, MyDisc.newDisc(0));
			_tested.putDiscInColumn(1, MyDisc.newDisc(1));
			_tested.putDiscInColumn(1, MyDisc.newDisc(0));
	        fail("Failed: Should get a ColumnFull Exception"); 
	    }
	    catch (ColumnFullException e) {
	        // Assert that this exception is thrown as expected
	    	assertEquals("1",e.getMessage());
	        
	    }
			
	}
	
	
	@Test
	public void getTopIndex_columnEmpty() {
		
		_tested = new MyBoard(2,2);
		int retVal = _tested.getTopIndex(1);
		assertSame(retVal, -1);
			
	}
	
	@Test
	public void getTopIndex_columnFull() {
		
		_tested = new MyBoard(2,2);
		_tested.putDiscInColumn(1, MyDisc.newDisc(1));
		_tested.putDiscInColumn(1, MyDisc.newDisc(1));
		int retVal = _tested.getTopIndex(1);
		assertSame(retVal, 1);
			
	}
	
	@Test
	public void getTopIndex_AllGood() {
		
		_tested = new MyBoard(2,2);
		_tested.putDiscInColumn(1, MyDisc.newDisc(1));
		int retVal = _tested.getTopIndex(1);
		assertEquals(retVal, 0);
			
	}
	
	@Test
	public void getTop_AllGood() {
		
		_tested = new MyBoard(2,2);
		MyDisc d = MyDisc.newDisc(1);
		_tested.putDiscInColumn(1, d);
		Disc retVal = _tested.getTop(1);
		assertSame(retVal, d);
			
	}
	
	@Test
	public void extractTop_AllGood() {
		
		_tested = new MyBoard(2,2);
		MyDisc d = MyDisc.newDisc(1);
		_tested.putDiscInColumn(1, d);
		Disc retVal = _tested.extractTop(1);
		assertSame(retVal, d);
			
	}
	
	
	@Test
	public void lowestAvailable_columnFull() {
		
		_tested = new MyBoard(2,2);
		MyDisc d = MyDisc.newDisc(1);
		_tested.putDiscInColumn(1, d);
		_tested.putDiscInColumn(1, d);
		int retVal = _tested.lowestAvailable(1);
		assertSame(retVal, -1);
			
	}
	
	@Test
	public void lowestAvailable_AllGood() {
		
		_tested = new MyBoard(2,2);
		MyDisc d = MyDisc.newDisc(1);
		_tested.putDiscInColumn(1, d);
		int retVal = _tested.lowestAvailable(1);
		assertSame(retVal, 1);
			
	}
	
	@Test
	public void lowestAvailable_columnEmpty() {
		
		_tested = new MyBoard(2,2);
		int retVal = _tested.lowestAvailable(1);
		assertSame(retVal, 0);
			
	}
	
	@Test
	public void columnFull_columnFull() {
		
		_tested = new MyBoard(2,2);
		MyDisc d = MyDisc.newDisc(1);
		_tested.putDiscInColumn(1, d);
		_tested.putDiscInColumn(1, d);
		boolean retVal = _tested.columnFull(1);
		assertSame(retVal, true);
			
	}
	
	@Test
	public void columnFull_columnNotFull() {
		
		_tested = new MyBoard(2,2);
		MyDisc d = MyDisc.newDisc(1);
		_tested.putDiscInColumn(1, d);
		boolean retVal = _tested.columnFull(1);
		assertSame(retVal, false);
			
	}
	
	
	@Test
	public void columnNotFull_columnFull() {
		
		_tested = new MyBoard(2,2);
		_tested.putDiscInColumn(1, MyDisc.newDisc(0));
		_tested.putDiscInColumn(1, MyDisc.newDisc(1));
		boolean retVal = _tested.columnNotFull(1);
		assertFalse(retVal);
			
	}
	
	@Test
	public void columnNotFull_columnNotFull() {
		
		_tested = new MyBoard(2,2);
		MyDisc d = MyDisc.newDisc(1);
		_tested.putDiscInColumn(1, d);
		boolean retVal = _tested.columnNotFull(1);
		assertSame(retVal, true);
			
	}
	
	@Test
	public void getDisc_AllGood() {
		
		_tested = new MyBoard(2,2);
		MyDisc d = MyDisc.newDisc(1);
		_tested.putDiscInColumn(1, d);
		Disc retVal = _tested.getDisc(1, 0);
		assertSame(retVal, d);
			
	}
	
	@Test
	public void getWidth_argConstructor() {
		
		_tested = new MyBoard(2,3);
		int retVal = _tested.getWidth();
		assertSame(retVal, 2);
			
	}
	
	@Test
	public void getHeight_argConstructor() {
		
		_tested = new MyBoard(2,3);
		int retVal = _tested.getHeight();
		assertSame(retVal, 3);
			
	}
	
	@Test
	public void clone_emptyBoard() {
		
		_tested = new MyBoard(0,0);
		Board retVal = _tested.clone();
		assertSame(0, retVal.getWidth());
		assertSame(0, retVal.getHeight());
			
	}
	
	@Test
	public void clone_noHeight() {
		
		_tested = new MyBoard(3,0);
		Board retVal = _tested.clone();
		assertSame(0, retVal.getHeight());
			
	}
	
	@Test
	public void clone_allGood() {
		
		_tested = new MyBoard(2,2);
		_tested.putDiscInColumn(1, MyDisc.newDisc(0));
		_tested.putDiscInColumn(1, MyDisc.newDisc(1));
		_tested.putDiscInColumn(0, MyDisc.newDisc(0));
		_tested.putDiscInColumn(0, MyDisc.newDisc(1));
		Board retVal = _tested.clone();
		assertSame(2, retVal.getHeight());
		assertSame(2, retVal.getWidth());
		assertSame(MyDisc.newDisc(0), retVal.getDisc(0, 0));
		assertSame(MyDisc.newDisc(1), retVal.getDisc(0, 1));
		assertSame(MyDisc.newDisc(0), retVal.getDisc(1, 0));
		assertSame(MyDisc.newDisc(1), retVal.getDisc(1, 1));
			
	}
	
	@Test
	public void clone_nullDisc() {
		
		_tested = new MyBoard(2,2);
		_tested.putDiscInColumn(1, MyDisc.newDisc(0));
		_tested.putDiscInColumn(1, MyDisc.newDisc(1));
		_tested.putDiscInColumn(0, MyDisc.newDisc(0));
		Board retVal = _tested.clone();
		assertSame(2, retVal.getHeight());
		assertSame(2, retVal.getWidth());
		assertSame(MyDisc.newDisc(0), retVal.getDisc(0, 0));
		assertNull(retVal.getDisc(0, 1));
		assertSame(MyDisc.newDisc(0), retVal.getDisc(1, 0));
		assertSame(MyDisc.newDisc(1), retVal.getDisc(1, 1));
			
	}
	
	
	
	
	
	

}
