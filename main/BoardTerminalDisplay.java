package main;

import gamecomponents.Board;
import gamecomponents.Disc;

import java.util.Observable;

@traceable
public class BoardTerminalDisplay implements BoardDisplay{
	
	protected final static char OPLAYER = 'O';
	protected final static char XPLAYER = 'X';
	protected final static char EMPTY = ' ';

	private Disc firstPlayerDisc = null;
	
	protected Board board;
	protected char charBoard[][];
	
	private final int ROWS;
	private final int COLS;
	
	public BoardTerminalDisplay(Board b){
		board = b;
		COLS = b.getWidth();
		ROWS = b.getHeight();
		initCharBoard();
		setFirstPlayerDisc();
	}
	
	private void initCharBoard() {
		charBoard = new char[COLS][ROWS];
		for (int i=0; i < COLS; i++) {
			for (int j = 0; j < ROWS; j++) {
				Disc d = board.getDisc(i, j);
				charBoard[i][j] = discDisplay(d);
			}
		}
	}

	private void setFirstPlayerDisc() {
		for (int i = 0; i < board.getWidth(); i++) {
			Disc t = board.getTop(i);
			if (t != null) {
				firstPlayerDisc = t;
				return;
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		Object data[] = (Object[])arg;
		int col = (Integer) data[0];
		int row = (Integer) data[1];
		Disc d = (Disc)data[2];

		//in case the first player number disc not yet been set(cause board was empty)
		if (firstPlayerDisc == null && d != null) {
			setFirstPlayerDisc();
		}

		charBoard[col][row] = discDisplay(d);
	}

	public void displayBoard() {
		// copied from the school solution
		System.out.println("Printing board:");
		System.out.println();
		for (int j = ROWS-1; j >= 0; j--) {
			System.out.print("|");
			for (int k = 0; k < COLS; k++)
				System.out.print(charBoard[k][j] + "|");
			System.out.println();
		}
		for (int k = 0; k < 2*COLS+1; k++)
			System.out.print("-");
		System.out.println();
		System.out.println();
	}

	private char discDisplay(Disc disc) {
		if (disc == null) return EMPTY;
		return disc.equals(firstPlayerDisc) ? OPLAYER : XPLAYER;
	}
}