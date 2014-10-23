package main;

import gamecomponents.Game;
import gamecomponents.MyGame;
import gameexceptions.ColumnFullException;
import gameexceptions.IllegalColumnNumberException;
import players.Player;

@traceable class TerminalGameRunner {

	/**
	 * runs the complete game and displays it to the terminal 
	 * @param game
	 */
	public static void runGameAndDisplay(MyGame game) {
		BoardDisplay bd = new BoardTerminalDisplay(game.getBoard());
		game.addBoardObserver(bd);
		bd.displayBoard();
		while(!game.gameOver()) {
			runGameTurnAndDisplay(game);
			bd.displayBoard();
		}

		showWinner(game);
	}
	
	/**
	 * runs a single turn of the game
	 * @param game
	 */
	private static void runGameTurnAndDisplay(Game game) {
		Player current = game.currentPlayer();
		try {
			int choice = game.runTurn();
			if (current.isComp()) { // computer
				System.out.println("Computer put a disc in column " + (choice+1) );
				System.out.println();
			}
		} catch (ColumnFullException e) {
			if (current.isComp()) {
				System.out.println("Computer chose a full column - " + e.getMessage());
				System.exit(1);
			}
			System.out.println("Column is full. please choose again.");
			//try again
			runGameTurnAndDisplay(game);
		} catch (IllegalColumnNumberException e) {
			if (!current.isComp()) {// a human
				System.out.println("Illegal column number, choose again");
			}
			// try again
			runGameTurnAndDisplay(game);
		}
	}

	public static void showWinner(Game game) {
		Player winner = game.winner();
		if (winner == null)
			System.out.print("Board is full! game has ended with a tie!");
		else 
			if (winner.isComp()) System.out.println("Game has ended! The computer won!");
			else System.out.println("Game has ended! Player " + game.getNum(winner) + " won!");
		System.out.println();
	}
}