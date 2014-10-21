package players;

import gamecomponents.Board;
import gamecomponents.Disc;
import main.traceable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * Represents a human player.
 * a human player instance has an input stream in order to receive information from the human player represented by it,
 * and an output stream to send info to that player.
 * @author Shlomi
 *
 */
@traceable public class HumanPlayer extends MyPlayer {

	Scanner in;
	OutputStream out;
	
	
	/**
	 * uses standard input as the player's input stream 
	 */
	public HumanPlayer(int num) {
		this(System.in, num);
	}
	
	/**
	 * uses standard output as the player's output stream
	 * @param input
	 */
	public HumanPlayer(InputStream input, int num) {
		this(input, System.out, num);
	}
	
	public HumanPlayer(InputStream input, OutputStream output, int num) {
		super(num);
		in = new Scanner(input);
		out = output;
	}
	
	public boolean isComp() {
		return false;
	}

	/**
	 * this method returns iff the player entered a valid number.
	 * returns the choice that the player enters in the input stream.
	 */
	public int chooseColumn(Board b, Disc myDisc, Disc hisDisc) {
		write("Player " + pNum + ", choose a column: ");
		try {
			int choice = Integer.parseInt(in.nextLine());
			writeln();
			return choice - 1;//values begin from 0
		} catch (NumberFormatException e) {
			writeln("Please enter a valid number.");
			//try again:
			return chooseColumn(b, myDisc, hisDisc);
		}
		
	}
	
	private void write(String s) {
		try {
			out.write(s.getBytes());
		} catch (IOException e) {
			System.err.println("Error, cannot write to player's output stream");
		}
	}
	
	private void writeln(String s) {
		write(s);
		writeln();
	}
	
	private void writeln() {
		write("\n");
	}
	
}