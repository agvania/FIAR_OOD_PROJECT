package main;

import players.ComputerPlayer;
import players.compstartegies.ComputerStrategy;
import players.compstartegies.MyStrategy;
import players.compstartegies.RandomStrategy;
import players.compstartegies.YourStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * This factory class is meant to utilize the creation of computer players, in order to have them as flyweights.
 * For every player number, there's at most one instance from each of ComputerPlayer's subclasses.
 * @author Shlomi
 *
 */
public class ComputerPlayerFactory {

	public static final int 
	YOUR_COMP = 0,
	MY_COMP = 1,
	RAND = 2,
	STRATS_SIZE = 3;


    private static ComputerStrategy [] strategies = new ComputerStrategy[STRATS_SIZE];
    static { // init
        strategies [YOUR_COMP]  = new YourStrategy();
        strategies [MY_COMP]    = new MyStrategy();
        strategies [RAND]       = new RandomStrategy();
    }

    private static Map<Integer, ComputerPlayer> [] playerMaps = new Map[STRATS_SIZE];
    static { // init
        playerMaps [YOUR_COMP]  = new HashMap<Integer, ComputerPlayer>();
        playerMaps [MY_COMP]    = new HashMap<Integer, ComputerPlayer>();
        playerMaps [RAND]       = new HashMap<Integer, ComputerPlayer>();
    }

	/**
	 * 
	 * @param pNum the player number of the requested player
	 * @param heuristicsNum the playing heuristicsNum of the player
	 * @return
	 */
	public static ComputerPlayer newComputerPlayer(int pNum, int heuristicsNum) {
		Map<Integer, ComputerPlayer> hmap = getPlayerMap(heuristicsNum); // get the map of the heuristicsNum chosen
		if (hmap.containsKey(pNum)) { // there's an instance
			return hmap.get(pNum);
		} else { // create the instance
			ComputerPlayer cp = new ComputerPlayer(pNum, getStrategy(heuristicsNum));
			hmap.put(pNum, cp);
			return cp;
		}
		
	}

    private static Map<Integer, ComputerPlayer> getPlayerMap(int i) {
        return playerMaps[i-1];
    }

    private static ComputerStrategy getStrategy(int i) {
        return strategies[i-1];
    }

}
