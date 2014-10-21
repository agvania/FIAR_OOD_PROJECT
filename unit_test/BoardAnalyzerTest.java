package unit_test;


import gamecomponents.Board;
import gamecomponents.BoardAnalyzer;
import gamecomponents.MyBoard;
import gamecomponents.MyDisc;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BoardAnalyzerTest {
	
	private static Board _b = null;

	@Before
	public void setUp() throws Exception {
		
		_b = new MyBoard(4,4);
	}

	
	@Test
	public void isWinningDisc_horizontalRight() {
		
		_b.putDiscInColumn(0, MyDisc.newDisc(0));
		_b.putDiscInColumn(1, MyDisc.newDisc(0));
		_b.putDiscInColumn(2, MyDisc.newDisc(0));
		_b.putDiscInColumn(3, MyDisc.newDisc(0));
		
		boolean retVal = BoardAnalyzer.isWinningDisc(_b,0,0);
		assertTrue(retVal);
	}
	
	@Test
	public void isWinningDisc_horizontalLeft() {
		
		_b.putDiscInColumn(0, MyDisc.newDisc(0));
		_b.putDiscInColumn(1, MyDisc.newDisc(0));
		_b.putDiscInColumn(2, MyDisc.newDisc(0));
		_b.putDiscInColumn(3, MyDisc.newDisc(0));
		
		boolean retVal = BoardAnalyzer.isWinningDisc(_b,3,0);
		assertTrue(retVal);
	}
	
	@Test
	public void isWinningDisc_verticalDown() {
		
		_b.putDiscInColumn(0, MyDisc.newDisc(0));
		_b.putDiscInColumn(0, MyDisc.newDisc(0));
		_b.putDiscInColumn(0, MyDisc.newDisc(0));
		_b.putDiscInColumn(0, MyDisc.newDisc(0));
		
		boolean retVal = BoardAnalyzer.isWinningDisc(_b,0,3);
		assertTrue(retVal);
	}
	
	@Test
	public void isWinningDisc_verticalUp() {
		
		_b.putDiscInColumn(0, MyDisc.newDisc(0));
		_b.putDiscInColumn(0, MyDisc.newDisc(0));
		_b.putDiscInColumn(0, MyDisc.newDisc(0));
		_b.putDiscInColumn(0, MyDisc.newDisc(0));
		
		boolean retVal = BoardAnalyzer.isWinningDisc(_b,0,0);
		assertTrue(retVal);
	}
	
	
	@Test
	public void isWinningDisc_firstDiagonalUp() {
		
		_b.putDiscInColumn(0, MyDisc.newDisc(1));
		_b.putDiscInColumn(0, MyDisc.newDisc(1));
		_b.putDiscInColumn(0, MyDisc.newDisc(1));
		_b.putDiscInColumn(0, MyDisc.newDisc(0));
		
		_b.putDiscInColumn(1, MyDisc.newDisc(1));
		_b.putDiscInColumn(1, MyDisc.newDisc(1));
		_b.putDiscInColumn(1, MyDisc.newDisc(0));
		
		_b.putDiscInColumn(2, MyDisc.newDisc(1));
		_b.putDiscInColumn(2, MyDisc.newDisc(0));
		
		_b.putDiscInColumn(3, MyDisc.newDisc(0));
		
		boolean retVal = BoardAnalyzer.isWinningDisc(_b,0,3);
		assertTrue(retVal);
	}
	
	@Test
	public void isWinningDisc_firstDiagonalDown() {
		
		_b.putDiscInColumn(0, MyDisc.newDisc(1));
		_b.putDiscInColumn(0, MyDisc.newDisc(1));
		_b.putDiscInColumn(0, MyDisc.newDisc(1));
		_b.putDiscInColumn(0, MyDisc.newDisc(0));
		
		_b.putDiscInColumn(1, MyDisc.newDisc(1));
		_b.putDiscInColumn(1, MyDisc.newDisc(1));
		_b.putDiscInColumn(1, MyDisc.newDisc(0));
		
		_b.putDiscInColumn(2, MyDisc.newDisc(1));
		_b.putDiscInColumn(2, MyDisc.newDisc(0));
		
		_b.putDiscInColumn(3, MyDisc.newDisc(0));
		
		boolean retVal = BoardAnalyzer.isWinningDisc(_b,3,0);
		assertTrue(retVal);
	}
	
	@Test
	public void isWinningDisc_secondDiagonalUp() {
		
		_b.putDiscInColumn(0, MyDisc.newDisc(0));
		
		_b.putDiscInColumn(1, MyDisc.newDisc(1));
		_b.putDiscInColumn(1, MyDisc.newDisc(0));
		
		_b.putDiscInColumn(2, MyDisc.newDisc(1));
		_b.putDiscInColumn(2, MyDisc.newDisc(1));
		_b.putDiscInColumn(2, MyDisc.newDisc(0));
		
		_b.putDiscInColumn(3, MyDisc.newDisc(1));
		_b.putDiscInColumn(3, MyDisc.newDisc(1));
		_b.putDiscInColumn(3, MyDisc.newDisc(1));
		_b.putDiscInColumn(3, MyDisc.newDisc(0));
		
		boolean retVal = BoardAnalyzer.isWinningDisc(_b,0,0);
		assertTrue(retVal);
	}
	
	@Test
	public void isWinningDisc_secondDiagonalDown() {
		
		_b.putDiscInColumn(0, MyDisc.newDisc(0));
		
		_b.putDiscInColumn(1, MyDisc.newDisc(1));
		_b.putDiscInColumn(1, MyDisc.newDisc(0));
		
		_b.putDiscInColumn(2, MyDisc.newDisc(1));
		_b.putDiscInColumn(2, MyDisc.newDisc(1));
		_b.putDiscInColumn(2, MyDisc.newDisc(0));
		
		_b.putDiscInColumn(3, MyDisc.newDisc(1));
		_b.putDiscInColumn(3, MyDisc.newDisc(1));
		_b.putDiscInColumn(3, MyDisc.newDisc(1));
		_b.putDiscInColumn(3, MyDisc.newDisc(0));
		
		boolean retVal = BoardAnalyzer.isWinningDisc(_b,3,3);
		assertTrue(retVal);
	}

}
