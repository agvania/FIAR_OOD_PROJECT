package gamecomponents;

import java.util.Observer;

/**
 * made especially for dynamic proxy. NOT included in the uml.
 * @author agvania
 *
 */
public interface IObservableBoard extends Board {

	public Board clone();
	public void notifyObservers();
	public void notifyObservers(Object arg);
	public void addObserver(Observer o);
//	void setChanged();

}