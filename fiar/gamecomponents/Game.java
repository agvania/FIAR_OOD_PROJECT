package fiar.gamecomponents;

import gameexceptions.ColumnFullException;
import gameexceptions.IllegalColumnNumberException;
import players.Player;

public interface Game {

	public void runGameSession();

	public int runTurn() throws ColumnFullException,
			IllegalColumnNumberException;

	public boolean gameOver();

	public Player winner();

	public Player player1();

	public Player player2();

	public Player currentPlayer();

	public Player currentRival();

	public Board getBoard();
	
	/**
	 * @param p
	 * @return the number of the player in the game. -1 if the player is not part of the game.
	 */
	public int getNum(Player p);

}