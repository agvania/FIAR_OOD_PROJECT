package players.humanplayerui;

import players.humanplayerui.HumanPlayerUI;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * Created by agvania on 21/10/14.
 */
public class HumanPlayerTerminalUI implements HumanPlayerUI {
    private final String playerId;
    Scanner in;
    OutputStream out;


    public HumanPlayerTerminalUI(String playerId, InputStream in, OutputStream out) {
        this.playerId = playerId;
        this.in = new Scanner(in);
        this.out = out;
    }

    public HumanPlayerTerminalUI(int playerNumber, InputStream in, OutputStream out) {
        this ("" + playerNumber, in, out);
    }

    @Override
    public int getUserChoice() throws NumberFormatException{
        write("Player " + playerId + ", choose a column: ");
        int choice = Integer.parseInt(in.nextLine());
        writeln();
        return choice;
    }

    @Override
    public void message(String msg) {
        writeln(msg);
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
