package fiar.gamecomponents;

import gameexceptions.ColumnFullException;


public class MyBoard extends ObservableBoard {

	public static final int DEFAULT_HEIGHT = 6;
	public static final int DEFAULT_WIDTH = 7;

	private final int height, width;

	protected Disc boardArr [][];

	/**
	 * Creates a new Board with the given width and height
	 * @param height
	 * @param width
	 */
	public MyBoard(int width, int height) {
		this.height = height;
		this.width = width;
		initBoard();
	}

	/**
	 *	empty board with default height and width
	 */
	public MyBoard() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	private void initBoard() {
		boardArr = new Disc[width][height];
	}


	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Board#putDiscInColumn(int, fiar.gamecomponents.Disc)
	 */
	@Override
	public void putDiscInColumn(int col, Disc d) throws ColumnFullException{
		if (columnNotFull(col)) {
			int lowest = lowestAvailable(col);
			boardArr[col][lowest] = d;
			setChanged();
		} else {
			throw new ColumnFullException("" + col);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Board#getTopIndex(int)
	 */
	@Override
	public int getTopIndex(int col) {
		int a = lowestAvailable(col);
		if (a == -1)//column full
			return height-1;
		else if (a == 0)//column empty
			return -1;
		else//column partially full
			return a-1;
	}
	
	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Board#getTop(int)
	 */
	@Override
	public Disc getTop(int col) {
		int i = getTopIndex(col);
		if (i==-1) return null;//column empty
		return getDisc(col, i);
	}
	
	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Board#extractTop(int)
	 */
	@Override
	public Disc extractTop(int col) {
		int topIndex = getTopIndex(col);
		if (topIndex==-1) return null;//column empty
		Disc top = getDisc(col,topIndex);
		boardArr[col][topIndex] = null;
		setChanged();
		return top;
	}
	
	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Board#lowestAvailable(int)
	 */
	@Override
	public int lowestAvailable(int col) {
		if (columnFull(col)) return -1;
		
		int lowest = 0;
		while(boardArr[col][lowest] != null) { lowest++; }
		return lowest;
	}

	
	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Board#columnFull(int)
	 */
	@Override
	public boolean columnFull(int col) {
		return !columnNotFull(col);
	}
	
	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Board#columnNotFull(int)
	 */
	@Override
	public boolean columnNotFull(int col) {
		return boardArr[col][height-1] == null;
	}

	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Board#getDisc(int, int)
	 */
	@Override
	public Disc getDisc(int x, int y) {
		return boardArr[x][y];
	}

	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Board#getWidth()
	 */
	@Override
	public int getWidth() {
		return width;
	}

	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Board#getHeight()
	 */
	@Override
	public int getHeight() {
		return height;
	}
	
	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Board#notifyObservers(int, int, fiar.gamecomponents.Disc)
	 */
	@Override
	public void notifyObservers(int x, int y, Disc d) {
		Object data[] = {x,y,d};
		notifyObservers(data);
	}
	

	/* (non-Javadoc)
	 * @see fiar.gamecomponents.Board#clone()
	 */
	@Override
	public Board clone() {
		MyBoard cloneBoard = new MyBoard(width, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Disc d = boardArr[i][j];
				if (d != null) 
					d = d.clone();
				
				cloneBoard.boardArr[i][j] = d;
			}
		}
		
		return cloneBoard;
	}
	
}