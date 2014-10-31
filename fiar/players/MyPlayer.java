package fiar.players;

import fiar.gamecomponents.Board;
import fiar.gamecomponents.Disc;

public abstract class MyPlayer implements IMyPlayer {

	int pNum;

	public MyPlayer(int num) {
		pNum = num;
	}
	
	/* (non-Javadoc)
	 * @see fiar.players.Player#chooseColumn(fiar.gamecomponents.Board, fiar.gamecomponents.Disc, fiar.gamecomponents.Disc)
	 */
	@Override
	public abstract int chooseColumn(Board b, Disc myDisc, Disc hisDisc);
	
	
	/* (non-Javadoc)
	 * @see fiar.players.IMyPlayer#getNumber()
	 */
	@Override
	public int getNumber() {
		return pNum;
	}

    @Override
    public String id() {
        return "" + getNumber();
    }
	
	/* (non-Javadoc)
	 * @see fiar.players.Player#isComp()
	 */
	@Override
	public abstract boolean isComp();
	
}