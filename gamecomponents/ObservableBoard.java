package gamecomponents;

import java.util.Observable;

public abstract class ObservableBoard extends Observable implements IObservableBoard {

	/* (non-Javadoc)
	 * @see gamecomponents.IObservableBoard#clone()
	 */
	@Override
	public abstract Board clone();
}
