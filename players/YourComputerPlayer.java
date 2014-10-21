package players;

import gamecomponents.Board;
import gamecomponents.Disc;

public class YourComputerPlayer extends ComputerPlayer {

	public YourComputerPlayer(int num) {
		super(num);
	}


	/**
	 *  returns a column number within 0...COLUMNS, -1 if board is full
	 */
	public int chooseColumn(Board board, Disc myDisc, Disc hisDisc) {
		//huristics chosen - the one from the example code
		final int COLS = board.getWidth();

		// first check if a move can win
		int win = tryToWin(board, myDisc);
		if (win >= 0) {
			return win;
		}
		
		// otherwise then pick up any move that will prevent other player to win 
		// in case there is a win on next turn
		int prevent = preventRivalWin(board, hisDisc);
		if (prevent >= 0) {
			return prevent;
		}
		
		// if other player wins no matter what or will not win next turn, pick up first non full column
		for (int i=0; i<COLS; i++) 
			if (board.columnNotFull(i)) {
				return i;
			}
		
		// nothing of the above - return -1
		return -1; 
	}
}