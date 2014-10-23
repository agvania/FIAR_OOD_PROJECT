package players.compstartegies;

import gamecomponents.Board;
import gamecomponents.Disc;

import java.util.Random;

/**
 * Created by agvania on 21/10/14.
 */
public class RandomStrategy extends ComputerStrategy {

    @Override
    public int chooseColumn(Board board, Disc myDisc, Disc hisDisc) {
        final int COLS = board.getWidth();

        // first check if a move can win
        int win = tryToWin(board, myDisc);
        if (win >= 0) {
            return win;
        }

        // otherwise then pick up any move that will prevent other player to win
        // in case there is a win on next turn
        int prevent = preventRivalWin(board, hisDisc);
        if (prevent >= 0) {
            return prevent;
        }

        // if other player wins no matter what or will not win next turn, pick up a random column
        // try COLS*2 times to find a non-empty random column, after that give up and find the first non-empty column
        Random r = new Random();
        for (int i=0; i < 2*COLS; ++i) {
            int col = r.nextInt(COLS);
            if (board.columnNotFull(col)) return col;
        }

        // find the first non-empty column
        for (int i=0; i<COLS; i++)
            if (board.columnNotFull(i)){
                return i;
            }

        // nothing of the above - return -1
        return -1;
    }
}
