package log;

import gamecomponents.Board;
import gamecomponents.Game;
import gamecomponents.IObservableBoard;
import main.BoardDisplay;
import players.IMyPlayer;
import players.Player;

import java.lang.reflect.Proxy;

public class LogProxyFactory {
	
	private static Class<?> myInterfaces[] = {
			Player.class,
			IMyPlayer.class,
			Board.class,
			IObservableBoard.class,
			Game.class,
			BoardDisplay.class
			
	};
	

	public static Object newLogProxy(Object o) {
		Object proxy =  Proxy.newProxyInstance(	o.getClass().getClassLoader(),
								myInterfaces, new LogProxyHandler(o));
		
		return proxy;
	}

}
