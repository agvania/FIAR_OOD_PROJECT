package players;

import gamecomponents.Board;
import gamecomponents.Disc;

public abstract class MyPlayer implements IMyPlayer {

	int pNum;

	public MyPlayer(int num) {
		pNum = num;
	}
	
	/* (non-Javadoc)
	 * @see players.Player#chooseColumn(gamecomponents.Board, gamecomponents.Disc, gamecomponents.Disc)
	 */
	@Override
	public abstract int chooseColumn(Board b, Disc myDisc, Disc hisDisc);
	
	
	/* (non-Javadoc)
	 * @see players.IMyPlayer#getNumber()
	 */
	@Override
	public int getNumber() {
		return pNum;
	}
	
	/* (non-Javadoc)
	 * @see players.Player#isComp()
	 */
	@Override
	public abstract boolean isComp();
	
}