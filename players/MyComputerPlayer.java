package players;

import gamecomponents.Board;
import gamecomponents.Disc;

/**
 * @author Shlomi
 *
 */
public class MyComputerPlayer extends ComputerPlayer {

	/**
	 * @param num
	 */
	public MyComputerPlayer(int num) {
		super(num);
	}

	/* (non-Javadoc)
	 * @see ComputerPlayer#chooseColumn(Board, Disc, Disc)
	 */
	@Override
	public int chooseColumn(Board board, Disc myDisc, Disc hisDisc) {
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

		// if other player wins no matter what or will not win next turn, pick up last non full column
		for (int i = COLS-1; i>=0; i--) {
			if (board.columnNotFull(i)){ 
				return i;
			}
		}
		
		// nothing of the above - return -1
		return -1; 
	}

}
