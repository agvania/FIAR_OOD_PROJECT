package fiar.gamecomponents;

import java.util.HashMap;
import java.util.Map;

/**
 * this class implements the flyweight pattern, in order to optimize memory usage.
 * a disc is instantiated once for each player number,
 * and that instance is returned every time that number is requested.
 * disc identifiers are numbers and not booleans in order to give the possibility to have multiple types of discs.
 * @author Shlomi
 *
 */
public class MyDisc implements Disc {

	private static Map<Integer, MyDisc> discs = new HashMap<Integer, MyDisc>();

	public static MyDisc newDisc(int playerNum) {
		if (discs.get(playerNum) != null) {
			return discs.get(playerNum);
		}
		
		MyDisc disc = new MyDisc(playerNum);
		discs.put(playerNum, disc);
		return disc;
	}

	
	private int playerNum;
	
	/**
	 * 
	 * @param playerNum number of the player who owns this disc
	 */
	private MyDisc(int playerNum) {
		this.playerNum = playerNum;
	}
	
	
	/**
	 *To what player does this disc belongs?
	 */
	public int getPlayerNum(){
		return playerNum;
	}
	
	
	//because of singleton - the next two methods are implemented accordingly.

	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Disc#equals(fiar.gamecomponents.MyDisc)
	 */
	@Override
	public boolean equals(Disc o) {
		try {
			MyDisc other = (MyDisc)o;
			return other != null && this.playerNum == other.playerNum;
		} catch (ClassCastException e) {
			return false;			
		}
	}

	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Disc#clone()
	 */
	@Override
	public Disc clone() {
		return this;
	}
}