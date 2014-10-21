package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



public aspect TraceAspect {
	
	private static FileOutputStream out;
	
	static {
		try {
			out = new FileOutputStream(new File("TraceScreen.log"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private long beforeChooseTime;
	
	pointcut callChooseColumn() :
		call (public int Player.chooseColumn(..));
	

	before() : callChooseColumn(){
		try {
			logln(thisJoinPoint.getTarget().getClass().getName() + " was asked to choose a column");
			beforeChooseTime = System.currentTimeMillis();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	after() : callChooseColumn() {
		try {
			logln(thisJoinPoint.getTarget().getClass().getName() + " chose a column");
			long msa = System.currentTimeMillis();
			logln("time of choice: " + (msa - beforeChooseTime));
			logln();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	pointcut sysPrint() :
		call (public void java.io.PrintStream.print*(..));// || call (public void java.io.OutputStream.write*(..));
	
	before() : sysPrint() {
		try {
			if (thisJoinPoint.getArgs().length > 0) {
				log((String)thisJoinPoint.getArgs()[0]);
			}
			if (thisJoinPoint.getSignature().getName().endsWith("ln")) {
				logln();
			}
//			logln("before print");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	after() : sysPrint() {
//		try {
////			logln("after print");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	pointcut outWrite() :
		call (public void java.io.OutputStream.write*(..));
	
	before() : outWrite() && (!within(TraceAspect)){
		
		try {
			if (thisJoinPoint.getArgs().length > 0) {
				log(new String((byte[])(thisJoinPoint.getArgs()[0])));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
