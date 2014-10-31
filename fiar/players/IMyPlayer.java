package fiar.players;

import fiar.gamecomponents.Board;
import fiar.gamecomponents.Disc;

/**
 * made especially for dynamic proxy. NOT included in the uml.
 * @author agvania
 *
 */
public interface IMyPlayer extends Player {

	/* (non-Javadoc)
	 * @see fiar.players.Player#chooseColumn(fiar.gamecomponents.Board, fiar.gamecomponents.Disc, fiar.gamecomponents.Disc)
	 */
	public abstract int chooseColumn(Board b, Disc myDisc, Disc hisDisc);

	/**
	 * Every player has a number, given to it upon creation. this method returns that number.
	 * @return
	 */
	public abstract int getNumber();

	/* (non-Javadoc)
	 * @see fiar.players.Player#isComp()
	 */
	public abstract boolean isComp();

}