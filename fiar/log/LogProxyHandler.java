package fiar.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LogProxyHandler implements InvocationHandler {
	
	private static FileOutputStream out;
	
	static {
		try {
			out = new FileOutputStream(new File("GameLog.log"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	Object myObject;

	public LogProxyHandler(Object o) {
		myObject = o;
	}

	@Override
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
		try {
			log("Invoked " + m.getName() + " on " + myObject.getClass().getName());
			if (args == null) {
				logln(" with no arguments.");
			} else {
				log(" with arguments: ");
				for (Object arg : args) {
					String v = arg == null ? "null" : arg.toString();
					log(v + ", ");
				}
				logln(".");
			}
			
			return m.invoke(myObject, args);
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
	}

	public static void closeLogStream() throws IOException {
		out.close();
	}
	
	private static void log(String s) throws IOException {
		out.write(s.getBytes());
	}
	
	private static void logln() throws IOException {
		out.write("\r\n".getBytes());
	}
	
	private static void logln(String s) throws IOException {
		log(s);
		logln();
	}
}
