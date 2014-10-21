package gamecomponents;

public class BoardAnalyzer {
	private static final int WIN = 4;
	
	public static boolean isWinningDisc(Board board, int col, int row){
		int COLUMNS = board.getWidth(),
				ROWS = board.getHeight();
		
		Disc d = board.getDisc(col,row);
		Disc n;// neighbor
		int count = 1;
		

		// horizontal right
		for (int i=col+1; i < COLUMNS; i++) {
			n = board.getDisc(i,row); 
			if (discsMatch(n, d))
				count++;
			else break;
		}
		if (count >= WIN) return true; // won horizontally
		// keep counting horizontal left
		for (int i=col-1; i >=0; i--) {
			n = board.getDisc(i,row);
			if (discsMatch(n,d)) 
				count++;
			else break;
		}
		if (count >= WIN) return true; // won horizontally

		count = 1;
		// vertical down
		for (int i=row+1; i < ROWS; i++) {
			n = board.getDisc(col,i);
			if (discsMatch(n,d)) 
				count++;
			else break;
		}
		if (count >= WIN) return true; // won vertical
		// keep counting vertical up
		for (int i=row-1; i >=0; i--) {
			n = board.getDisc(col,i);
			if (discsMatch(n,d)) 
				count++;
			else
				break;
		}
		if (count >= WIN) return true; // won vertical

		// first diagonal:  /  ARNON: seems like this diagonal here \
		count = 1;
		// up
		int kol = col+1;
		for (int i=row-1; i >= 0; i--) {
			if (kol>=COLUMNS) break; // we reached the end of the board right side
			n = board.getDisc(kol,i);
			if (discsMatch(n,d)) 
				count++;
			else 
				break;
			kol++;
		}
		if (count >= WIN) return true;
		// keep counting down
		kol = col-1;
		for (int i=row+1; i < ROWS; i++) {
			if (kol<0) break; // we reached the end of the board left side
			n = board.getDisc(kol,i);
			if (discsMatch(n,d)) 
				count++;
			else
				break;
			kol--;
		}
		if (count >= WIN) return true; // won diagonal "/"

		// second diagonal : \
		count = 1;
		// up
		kol = col-1;
		for (int i=row-1; i >= 0; i--) {
			if (kol<0) break; // we reached the end of the board left side
			n = board.getDisc(kol,i);
			if (discsMatch(n,d)) 
				count++;
			else 
				break;
			kol--;
		}
		if (count >= WIN) return true; // won diagonal "\"
		// keep counting down
		kol = col+1;
		for (int i=row+1; i < ROWS; i++) {
			if (kol>=COLUMNS) break; // we reached the end of the board right side
			n = board.getDisc(kol,i);
			if (discsMatch(n,d)) 
				count++;
			else
				break;
			kol++;
		}
		if (count >= WIN) return true; // won diagonal "\"

		return false;
	}
	
	private static boolean discsMatch(Disc d1, Disc d2) {
		return d1 != null && d1.equals(d2);
	}
}
