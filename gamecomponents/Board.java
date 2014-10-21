package gamecomponents;

import gameexceptions.ColumnFullException;

/**
 * This class represents a board in the game of "four in a row"
 * Slots of the board are not allowed to be set directly. Only columns can be inserted or extracted a disc.
 * The board also gives all information about it's current state
 * @author Shlomi
 *
 */
public interface Board extends Cloneable{

	/**
	 * 
	 * @param col
	 * @param d
	 * @throws ColumnFullException in case the column is full...
	 */
	public void putDiscInColumn(int col, Disc d) throws ColumnFullException;

	/**
	 * 
	 * @param col
	 * @return index of the top disc on the column col. -1 if column is empty.
	 */
	public int getTopIndex(int col);

	/**
	 * dosen't take out the top disc, just returns it
	 * @param col
	 * @return the disc on top of the column, if column not empty. null if column is empty
	 */
	public Disc getTop(int col);

	/**
	 * takes out the disc and returns it
	 * @param col
	 * @return
	 */
	public Disc extractTop(int col);

	/**
	 * 
	 * @param col
	 * @return lowest available index in the given column. -1 if the column is full
	 */
	public int lowestAvailable(int col);

	/**
	 * 
	 * @param col
	 * @return
	 */
	public boolean columnFull(int col);

	/**
	 * 
	 * @param col
	 * @return
	 */
	public boolean columnNotFull(int col);

	/**
	 * 
	 * @param x
	 * @param y
	 * @return the disc in slot (x,y) of the board. null if the slot is empty.
	 */
	public Disc getDisc(int x, int y);

	public int getWidth();

	public int getHeight();

	/**
	 * can be invoked by anyone in order to notify observers that the slot (x,y) has been set to hold the Disc d.
	 * 
	 * @param x
	 * @param y
	 * @param d the Disc put in that slot. null if a disc was taken out of the slot.
	 */
	public void notifyObservers(int x, int y, Disc d);

	/**
	 * performs deep copy of this board
	 */
	public Board clone();

}