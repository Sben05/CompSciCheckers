/**
 * 
 * @author Shreeniket Bendre
 * Project: CompSciCheckers
 * File: CompSciCheckerBoard.java
 *
 */
/*
 * A CompSciCheckerBoard represents a playing board for a 
 * CompSciChecker game.  In this game a board is 8x8 and starts 
 * with black and white CompSciChecker pieces.  The fields
 * numWhitePieces and numBlackPieces track the appropriate 
 * number of pieces for either side.  There are 12 white 
 * pieces starting at the top left square and fill every other space
 * on the board for the first three rows.  No two white pieces 
 * should be directly adjacent to start.  The same is true of the
 * 12 black pieces but they start at the bottom right and fill the
 * bottom three rows.  the pieces may move one place in any
 * direction, forwards, backwards, left, right or diagonal.  The
 * pieces may "jump" an opposing player in any direction as well.
 * 
 */
public class CompSciCheckerBoard 
{

	private CompSciChecker[][] board;
	private int numWhitePieces;
	private int numBlackPieces;

	/*
	 * Create a Constructor for the CompSciCheckerBoard
	 * Once created the board should look like this:
	 * 
	 *  _ _ _ _ _ _ _ _
	 * |W   W   W   W  |
	 * |  W   W   W   W|
	 * |W   W   W   W  |
	 * |               |
	 * |               |
	 * |  B   B   B   B|
	 * |B   B   B   B  |
	 * |_ B _ B _ B _ B|
	 * 
	 * Empty Spaces have a value of null
	 * 
	 * 
	 */

	public CompSciCheckerBoard()
	{
		board = new CompSciChecker[8][8];

		//White piece loop
		for (int i = 0; i<3; i++) {
			for (int j = 0; j<8; j+=2) {
				if (i == 0 || i == 2) {
					CompSciChecker newChecker = new CompSciChecker("W", j, i);
					board[i][j] = newChecker;
				}
				else {
					CompSciChecker newChecker = new CompSciChecker("W", j+1, i);
					board[i][j+1] = newChecker;
				}
			}
		}
		//Black piece loop
		for (int i = 7; i>4; i--) {
			for (int j = 0; j<8; j+=2) {
				if (i == 7 || i == 5) {
					CompSciChecker newChecker = new CompSciChecker("B", j+1, i);
					board[i][j+1] = newChecker;
				}
				else {
					CompSciChecker newChecker = new CompSciChecker("B", j, i);
					board[i][j] = newChecker;
				}
			}
		}
		numWhitePieces = 12;
		numBlackPieces = 12;

	}



	/*
	 * Next you will write the method isValidMove which will
	 * take a CompSciChecker piece and a new x and y value
	 * and determine if the move to the new space is legal.
	 * 
	 * A CompSciChecker piece can move one space in any direction
	 * including diagonally, so long as the space is not occuppied.
	 * 
	 * A CompSciChecker piece can move two spaces in any direction
	 * ONLY IF there is an opposing piece being "jumped" and the new
	 * space is not occupied.
	 * 
	 * write the method isValidMove.  return true if the desired move
	 * is valid and false if it is not.
	 */

	public boolean isValidMove(CompSciChecker piece, int newX, int newY)
	{
		int x = piece.getX();
		int y = piece.getY();
		
		if (newX<0 || newY<0 || newX>7 || newY>7) {
			return false;
		}
		if (newX == x && newY == y) {
			return false;
		}
		
		String colorPiece = piece.getColor();
		
		int absX = (x+newX)/2;
		int absY = (y+newY)/2;
		
		char resultPos = position(x, y, newX, newY);
		
		if (getPiece(newX, newY)!=null) {
			return false;
		}
		if (resultPos != 'n' && getPiece(newX, newY) == null) {
			return true;
		}
		else if (!(Math.abs(x-newX)>2 || Math.abs(y-newY)>2) && (Math.abs(x-newX)!=1 && Math.abs(y-newY)!=1)) {
			
			if (board[absY][absX]==null) {
				return false;
			}
			if (board[absY][absX]!=null) {
				if ((getPiece(absX, absY).getColor().contentEquals(colorPiece))) {
					return false;
				}
			}
			
			return true;
		}
		//		else if (getPiece(newX, newY)!= null && resultPos!='n') {
		//			if (resultPos == 'x') {
		//				newX = newX+(-1*(x-newX));
		//				newY = newY+(-1*(y-newY));
		//			}
		//			else if (resultPos == 'u') {newY++;}
		//			else if (resultPos == 'd') {newY--;}
		//			else if (resultPos == 'r') {newX++;}
		//			else {newX--;}
		//			
		//			if (getPiece(newX, newY) == null) {return true;}
		//		}
		return false;
	}


	/*
	 * Finally you will write the method makeMove.  This method will
	 * take a CompSciChecker piece and a new x and y as arguments.  
	 * move the desired piece ONLY IF the move is valid.  If an opposing
	 * piece is "jumped" remove it from the board and update the
	 * appropriate field.
	 * 
	 */

	public void makeMove(CompSciChecker piece, int x, int y)
	{

		
		if (isValidMove(piece, x, y)) {
			int oldX = piece.getX();
			int oldY = piece.getY();
			board[y][x] = piece;
			piece.setX(x);
			piece.setY(y);
			board[oldY][oldX] = null;

			boolean xJumped = false;
			boolean yJumped = false;
			
			int averageX = (oldX+x)/2;
			int averageY = (oldY+y)/2;
			
			if (Math.abs(x-oldX)==2) {
				xJumped = true;
			}
			if (Math.abs(y-oldY)==2) {
				yJumped = true;
			}
			if (xJumped || yJumped) {
				if (getPiece(averageX, averageY)!=null) {
					CompSciChecker removePiece = board[averageY][averageX];
					String pieceColor = removePiece.getColor();
					
					if (pieceColor.equals("B")){numBlackPieces--;}
					else {numWhitePieces--;}
					
					board[averageY][averageX] = null;
				}
			}
			
		}
		
	}

	//This method will return the position of a second coordinate in relation to the first. Represented by characters such
	//as x for diagonal, l for left, u for up, r for right, d for down, and n for none.
	public static char position (int x, int y, int newX, int newY) {
		if ((x-newX == 1 || x-newX == -1) && (y-newY == 1 || y-newY == -1)) {
			return 'x';
		}
		if(y-newY == 1 && x == newX) {
			return 'u';
		}
		if(y-newY == -1 && x == newX) {
			return 'd';
		}
		if(x-newX == 1 && y == newY) {
			return 'l';
		}
		if(x-newX == -1 && y == newY) {
			return 'r';
		}
		return 'n';
	}

	//Returns Number of Pieces
	
	public int getNumPieces(char color) {
		if (color == 'W') {
			return numWhitePieces;
		}
		return numBlackPieces;
	}




	/**
	 * 
	 * @param x x coordinate of board
	 * @param y y coordinate of board
	 * @return the piece at (x,y) or null if no piece
	 *         is located at (x,y)
	 */
	public CompSciChecker getPiece(int x, int y)
	{
		return board[y][x];
	}

	/**
	 * 
	 * @return the field board
	 */
	public CompSciChecker[][] getBoard()
	{
		return board;
	}


	/**
	 * returns a string representation of the board
	 */
	public String toString()
	{
		String str = "  _ _ _ _ _ _ _ _\n";
		for(int i = 0; i<board.length; i++)
		{
			str+= i + "|";
			for(int j = 0; j<board[i].length; j++)
			{
				if(board[i][j] != null)
					str+=board[i][j].getColor();
				else
				{
					if(i<board.length-1) str+=" ";
					else str+= "_";
				}
				if(j<board[i].length-1) str+= " ";
			}
			str+="|\n";
		}
		str+= "  0 1 2 3 4 5 6 7\n";
		return str;
	}






}
