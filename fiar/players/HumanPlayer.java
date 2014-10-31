package fiar.players;

import fiar.gamecomponents.Board;
import fiar.gamecomponents.Disc;
import main.traceable;
import fiar.players.humanplayerui.HumanPlayerTerminalUI;
import fiar.players.humanplayerui.HumanPlayerUI;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represents a human player.
 * a human player instance has an input stream in order to receive information from the human player represented by it,
 * and an output stream to send info to that player.
 * @author Shlomi
 *
 */
@traceable
public class HumanPlayer extends MyPlayer {


    protected HumanPlayerUI ui;

    public HumanPlayer(int num, HumanPlayerUI ui) {
        super(num);
        setUI(ui);
    }

    /**
     * uses plain terminal as the UI.
     * @param num
     * @param input
     * @param output
     */
    public HumanPlayer(int num, InputStream input, OutputStream output) {
        this(num, new HumanPlayerTerminalUI(num, input, output));
    }

    /**
     * uses standard output as the player's output stream
     * @param num
     * @param in
     */
	public HumanPlayer(int num, InputStream in) {
		this(num, in, System.out);
	}

    /**
     * uses standard input as the player's input stream
     * @param num
     */
    public HumanPlayer(int num) {
        this(num, System.in);
    }

	public boolean isComp() {
		return false;
	}




    /**
	 * this method returns iff the player entered a valid number.
	 * returns the choice that the player enters in the input stream.
	 */
	public int chooseColumn(Board b, Disc myDisc, Disc hisDisc) {
		try {
			int choice = ui.getUserChoice();
			return choice - 1;//values begin from 0
		} catch (NumberFormatException e) {
            informInvalidChoice("This is not a number!");
			//try again:
			return chooseColumn(b, myDisc, hisDisc);
		}
	}

    @Override
    public void informInvalidChoice() {
        ui.message("Please enter a valid choice.");
    }

    @Override
    public void informInvalidChoice(String msg) {
        ui.message(msg);
        informInvalidChoice();
    }

    public void setUI(HumanPlayerUI ui) {
        this.ui = ui;
    }
	
}