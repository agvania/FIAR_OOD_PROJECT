package players;

import gamecomponents.Board;
import gamecomponents.BoardAnalyzer;
import gamecomponents.Disc;
import gameexceptions.ColumnFullException;

public abstract class ComputerPlayer extends MyPlayer {

	public ComputerPlayer(int num) {
		super(num);
	}

	public abstract int chooseColumn(Board board, Disc myDisc, Disc hisDisc);

	/**
	 * if the game can be won in one move - retrun the number of the column where insertion will lead to winning.
	 * if the game cannot be won - return -1;
	 * @param board
	 * @param myDisc
	 * @return
	 */
	protected static int tryToWin(Board board, Disc myDisc) {
		int emptyrow= 0;
		final int COLS = board.getWidth();

		for (int i=0; i<COLS; i++) {
			if (board.columnNotFull(i)) {
				emptyrow = board.lowestAvailable(i);
				try {
					board.putDiscInColumn(i, myDisc);
					if (BoardAnalyzer.isWinningDisc(board, i, emptyrow)) {
						board.extractTop(i); // reset
						return i;
					}
					board.extractTop(i); // reset
				} catch (ColumnFullException e) {

				}
			}
		}

		//no winning column founds
		return -1;
	}

	/**
	 * look ahead <B>one</B> turn and check for rival's winning options:
	 * if only one option exists for the rival to win - return the index to prevent his winning.
	 * if more than one option or no options - return -1
	 * @param board
	 * @param hisDisc
	 * @return
	 */
	protected static int preventRivalWin(Board board, Disc hisDisc) {
		int emptyrow= 0;
		final int COLS = board.getWidth();

		int counter = 0; // i count other player possible winnings
		int chosenrow = 0; 
		for (int i=0; i<COLS; i++) {
			if (board.columnNotFull(i)) {
				emptyrow = board.lowestAvailable(i);
				try {
					board.putDiscInColumn(i, hisDisc);// assume the other player does this
					if (BoardAnalyzer.isWinningDisc(board, i, emptyrow)) {
						board.extractTop(i); // reset
						counter++; // we found a winning disc
						chosenrow = i; // remember the row
					}
					board.extractTop(i); // reset
				} catch (ColumnFullException e) {
					continue;
				}
			}
		}

		// we block the player if there is exactly one winning disc 
		if (counter==1) return chosenrow;

		// else - either the rival will not win or will win anyway
		return -1;
	}

	public boolean isComp() {
		return true;
	}
}
