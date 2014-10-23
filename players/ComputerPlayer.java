package players;

import gamecomponents.Board;
import gamecomponents.Disc;
import players.compstartegies.ComputerStrategy;
import players.compstartegies.MyStrategy;
import players.compstartegies.YourStrategy;

public class ComputerPlayer extends MyPlayer {

    private static final ComputerStrategy YOUR_STRATEGY =
            new YourStrategy();

    protected ComputerStrategy strategy;

    public ComputerPlayer(int num, ComputerStrategy strategy) {
        super(num);
        this.strategy = strategy;
    }

    /**
     * by default, use YourStrategy.
     * @param num player number
     */
	public ComputerPlayer(int num) {
		this(num, YOUR_STRATEGY);
	}

	public int chooseColumn(Board board, Disc myDisc, Disc hisDisc) {
        return strategy.chooseColumn(board, myDisc, hisDisc);
    }


	public boolean isComp() {
		return true;
	}

    @Override
    public void informInvalidChoice() {

    }

    @Override
    public void informInvalidChoice(String msg) {

    }

    public void setStrategy(ComputerStrategy newStrategy) {
        this.strategy = newStrategy;
    }

}
