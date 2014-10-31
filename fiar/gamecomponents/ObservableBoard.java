package fiar.gamecomponents;

import java.util.Observable;

public abstract class ObservableBoard extends Observable implements IObservableBoard {

	/* (non-Javadoc)
	 * @see fiar.gamecomponents.IObservableBoard#clone()
	 */
	@Override
	public abstract Board clone();
}
