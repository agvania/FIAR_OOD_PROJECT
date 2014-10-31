package fiar.players;

import fiar.gamecomponents.Board;
import fiar.gamecomponents.Disc;

public interface Player {

	/**
	 * Gets a board at a certain state, and two discs - one that belongs to the player and one that belongs
	 * to the rival. The player can try some disc insertions on the board and make a decision about which
	 * column to insert his next disc.<BR>
	 * Note that the player may change the board as he likes, so it is recommended not to give the original
	 * board but rather a copy of it.
	 * @param b the board in certain state
	 * @param myDisc
	 * @param hisDisc
	 * @return the column to insert the disc in the next move.
	 */
	public int chooseColumn(Board b, Disc myDisc, Disc hisDisc);

    public void informInvalidChoice();
    public void informInvalidChoice(String msg);

	/**
	 * @return is this player a computer player?
	 */
	public boolean isComp();

    /**
     *
     * @return the id of this player.
     */
    public String id();
}