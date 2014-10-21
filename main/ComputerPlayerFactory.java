package main;

import players.ComputerPlayer;
import players.MyComputerPlayer;
import players.RandomComputerPlayer;
import players.YourComputerPlayer;

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
	YOUR_COMP = 1,
	MY_COMP = 2,
	RAND = 3,
	TYPES_NUM = 3;
	
	private static boolean inited = false;
	
	private static Map<Integer, Map<Integer, ComputerPlayer>> map;
	
	/**
	 * 
	 * @param pNum the player number of the requested player
	 * @param huristics the playing huristics of the player
	 * @return
	 */
	public static ComputerPlayer newComputerPlayer(int pNum, int huristics) {
		if (!inited) {
			init();
		}
		
		Map<Integer, ComputerPlayer> hmap = map.get(huristics); // get the map of the huristics chosen
		if (hmap.containsKey(pNum)) { // there's an instance
			return hmap.get(pNum);
		} else { // create the instance
			ComputerPlayer cp = newPlayer(pNum, huristics);
			hmap.put(pNum, cp);
			return cp;
		}
		
	}
	
	private static ComputerPlayer newPlayer(int pNum, int huristics) {
		switch (huristics) {
		case YOUR_COMP:
			return new YourComputerPlayer(pNum);
		case MY_COMP:
			return new MyComputerPlayer(pNum);
		case RAND:
			return new RandomComputerPlayer(pNum);
		}

		return null;
	}



	private static void init() {
		map = new HashMap<Integer, Map<Integer,ComputerPlayer>>(TYPES_NUM);
		map.put(YOUR_COMP, new HashMap<Integer, ComputerPlayer>());
		map.put(MY_COMP, new HashMap<Integer, ComputerPlayer>());
		map.put(RAND, new HashMap<Integer, ComputerPlayer>());
	}

}
