package fiar.gamecomponents;

import gameexceptions.ColumnFullException;
import gameexceptions.IllegalColumnNumberException;
import gameexceptions.NotObservableException;
import players.Player;

import java.util.Observer;

/**
 * Representing a game of a Four In a Line.
 * A Game is defined by its type of players and its board, which are all given to it in the ctor.
 * @author Shlomi
 *
 */
public class MyGame implements Game {

	protected Player player1, player2, currentPlayer, currentRival, winner;
	protected Board board;
	private boolean gameOver = false;
	private static final int	PLAYER1_NUM = 1,
								PLAYER2_NUM = 2;
	
	public MyGame(Player p1, Player p2, Board b) {
		player1 = p1;
		player2 = p2;
		currentPlayer = p1;
		currentRival = p2;
		winner = null;
		board = b;
	}
	
	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Game#runGameSession()
	 */
	@Override
	public void runGameSession() {
		while(!gameOver) { runTurn(); }
	}
	
	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Game#runTurn()
	 */
	@Override
	public int runTurn() throws ColumnFullException, IllegalColumnNumberException{
		if (gameOver) return -1;
		
		int choice;
        choice = currentPlayer.chooseColumn(board.clone(),
                MyDisc.newDisc(getNum(currentPlayer)),
                MyDisc.newDisc(getNum(currentRival)));

        if (!validChoice(choice)) {
            throw new IllegalColumnNumberException("" + choice);
        }

        Disc newDisc = MyDisc.newDisc(getNum(currentPlayer));
		board.columnNotFull(choice);
		board.putDiscInColumn(choice, newDisc);
		board.notifyObservers(choice, board.getTopIndex(choice), newDisc);
		if (BoardAnalyzer.isWinningDisc(board, choice, board.getTopIndex(choice))) {
			gameOver = true;
			winner = currentPlayer;
		}
		changePlayer();
		return choice;
	}
	
	private boolean validChoice(int choice) {
		return choice >= 0 && choice < board.getWidth();
	}

	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Game#gameOver()
	 */
	@Override
	public boolean gameOver() {
		return gameOver;
	}
	
	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Game#winner()
	 */
	@Override
	public Player winner() {
		return winner;
	}
	
	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Game#player1()
	 */
	@Override
	public Player player1() {
		return player1;
	}

	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Game#player2()
	 */
	@Override
	public Player player2() {
		return player2;
	}

	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Game#currentPlayer()
	 */
	@Override
	public Player currentPlayer() {
		return currentPlayer;
	}

	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Game#currentRival()
	 */
	@Override
	public Player currentRival() {
		return currentRival;
	}
	
	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Game#getBoard()
	 */
	@Override
	public Board getBoard() {
		return board;
	}
	
	/**
	 * if the board is an ObservableBoard, delegate observer adding to the board.
	 * @param o
	 * @throws NotObservableException if the board is not an ObservableBoard
	 */
	public void addBoardObserver(Observer o) throws NotObservableException{
		try {
			((IObservableBoard) board).addObserver(o);
		} catch (ClassCastException e) {
			throw new NotObservableException();
		}
	}
	
	public void addBoardObservers(Observer obs[]) {
		for (Observer o : obs) {
			addBoardObserver(o);
		}
	}
	
	private void changePlayer() {
		if (currentPlayer == player1) {
			currentPlayer = player2;
			currentRival = player1;
		} else {
			currentPlayer = player1;
			currentRival = player2;
		}
	}
	
	public int getNum(Player p) {
		if (p != player1 && p != player2) return -1; 
		return p == player1 ? PLAYER1_NUM : PLAYER2_NUM;
	}
}
