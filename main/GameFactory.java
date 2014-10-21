package main;

import gamecomponents.Board;
import gamecomponents.MyBoard;
import gamecomponents.MyGame;
import players.HumanPlayer;
import players.Player;

import static log.LogProxyFactory.newLogProxy;

/**
 * This class implements the factory pattern:
 * Its creation method is getting a number - the type of the game required,
 * as specified in the static public constants, and creates the game according to the type
 */
public class GameFactory {

	//game types:
	public static final int TWO_HUMANS = 1,
							HUMAN_COMPUTER = 2;
	
	
	public static final int 
			YOUR_COMP = 1,
			MY_COMP = 2,
			RAND = 3;
	
	private static int huristics = YOUR_COMP;	// by default
	
	public static MyGame createGame(int type) {
		Board b = new MyBoard();
		b = (Board) newLogProxy(b);// PROXY
		
		switch(type) {
		case TWO_HUMANS:
			//create game for two human players
			Player h1 = newHumanPlayer(1);
			Player h2 = newHumanPlayer(2);
			
			//PROXY
			h1 = (Player) newLogProxy(h1);
			h2 = (Player) newLogProxy(h2);
			
			return new MyGame(h1, h2, b);
			
		case HUMAN_COMPUTER:
			//create game for human against computer
			
			Player h = newHumanPlayer(1);
			Player c = ComputerPlayerFactory.newComputerPlayer(2, huristics);
			
			//PROXY
			h = (Player) newLogProxy(h);
			c = (Player) newLogProxy(c);
			return new MyGame(h, c, b);
		default:
			return null;
		}
		
	}
	
	
	public static void configureHuristics (int huristics) {
		if (huristics >= YOUR_COMP && huristics <= RAND)
			GameFactory.huristics = huristics;
	}

	private static HumanPlayer newHumanPlayer(int pNum) {
		return new HumanPlayer(System.in, System.out, pNum);
	}
}
