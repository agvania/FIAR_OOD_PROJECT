package players;

import gamecomponents.Board;
import gamecomponents.Disc;

/**
 * made especially for dynamic proxy. NOT included in the uml.
 * @author agvania
 *
 */
public interface IMyPlayer extends Player {

	/* (non-Javadoc)
	 * @see players.Player#chooseColumn(gamecomponents.Board, gamecomponents.Disc, gamecomponents.Disc)
	 */
	public abstract int chooseColumn(Board b, Disc myDisc, Disc hisDisc);

	/**
	 * Every player has a number, given to it upon creation. this method returns that number.
	 * @return
	 */
	public abstract int getNumber();

	/* (non-Javadoc)
	 * @see players.Player#isComp()
	 */
	public abstract boolean isComp();

}