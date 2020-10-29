package starter;

public class Board {
private Piece board[][];


public boolean isOutOfBounds(Space s) {
	//Returns true if the Space given has coordinates that are not on the board.
	if (s.getRow() < 0 || s.getRow() > 7 || s.getCol() < 0 || s.getCol() > 7) {
		return true;
	}
	return false;
}

public boolean moveNumSpaces(Space start, int r, int c) {
	//Moves the piece at the given space down by r and to the right by c.Returns false if the space given is null.
	return true;
}

public void addPiece(int r, int c, PieceType type, boolean isWhite) {
	//Creates a Piece object using the arguments and adds it to the board at the coordinates given.
	//Be careful calling this function, because it will override any piece that is currently at that location.
	//If desired, could be modified to return false if the location given is not null.
	if (board[r][c] != null) {
		return;
	}
	board[r][c] = new Piece(r, c, type, isWhite);
}

public boolean canMoveNumSpaces(Space start, int r, int c) {
	//Returns true if the location at start, translated down by r and to the right by c, is null.
	return (board[start.getCol()+r][start.getRow()+c] == null);
}

public Piece getPiece(Space s) {
	//Returns the piece located at the given space.
	return board[s.getRow()][s.getCol()];
}

public void swapPieces(Space s1, Space s2) {
	//Switches the location of the two pieces given. Should only be called when setting up the board pre-game, 
	// and only if both spaces contain pieces of the same color.
	Piece temp = board[s1.getRow()][s1.getCol()];
	board[s1.getRow()][s1.getCol()] = board[s2.getRow()][s2.getCol()];
	board[s2.getRow()][s2.getCol()] = temp;
}

public void flipBoard() {
	//Reverses the 2-d array board. Used to simulate looking at the board from the opposite perspective.
}

}
