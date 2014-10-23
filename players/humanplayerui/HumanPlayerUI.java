package players.humanplayerui;

/**
 * Created by agvania on 21/10/14.
 */
public interface HumanPlayerUI {
    int getUserChoice() throws NumberFormatException;
    void message(String msg);
}
