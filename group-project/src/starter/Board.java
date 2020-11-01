package starter;

import java.util.*;

public class Board {
private Piece board[][];
private Piece temp[][];

private boolean attackedByWhite[][];
private boolean attackedByBlack[][];
private ArrayList<Piece> Pieces;


public boolean isOutOfBounds(Space s) {
	//Returns true if the Space given has coordinates that are not on the board.
	if (s.getRow() < 0 || s.getRow() > 7 || s.getCol() < 0 || s.getCol() > 7) {
		return true;
	}
	return false;
}

public boolean moveNumSpaces(Space start, int r, int c) {
	//Moves the piece at the given space down by r and to the right by c. Returns false if the space given is null.
	if (canMoveNumSpaces(start, r, c)) {
		getPiece(start).setHasMoved(true);
		board[start.getRow()+r][start.getCol()+c] = getPiece(start);
		board[start.getRow()][start.getCol()] = null;
		return true;
	}
	return false;
}

public void addPiece(int r, int c, PieceType type, boolean isWhite) {
	//Creates a Piece object using the arguments and adds it to the board at the coordinates given.
	//Be careful calling this function, because it will override any piece that is currently at that location.
	//If desired, could be modified to return false if the location given is not null.
	if (board[r][c] != null) {
		return;
	}
	Piece piece = new Piece(r, c, type, isWhite);
	board[r][c] = piece;
	Pieces.add(piece);
	//TODO: update attackedbyblack and attackedbywhite
}

public boolean canMoveNumSpaces(Space start, int r, int c) {
	//Returns true if the location at start, translated down by r and to the right by c, is null, or an enemy piece.
	//Contains special instructions for the movement of pawns.
	
	//TODO: check if the move is listed in the movesList of the piece.
	if(getPiece(start).getType() == PieceType.PAWN) {
		if (r == -2 && c == 0) {
			return (board[start.getRow()-1][start.getCol()] == null) && (board[start.getRow()-2][start.getCol()] == null) && (getPiece(start).getHasMoved() == false);
		}
		else if (r == -1 && c == 0) {
			return (board[start.getCol()+r][start.getRow()+c] == null);
		}
		else if (c == 1 || c == -1 && r == -1) {
			return isOppositeTeam(start, new Space(start.getCol()+r, start.getRow()+c));
		}
		return false;
	}
	if (getPiece(start).getType() == PieceType.KING) {
		if (getPiece(start).getColor()) {
			return !attackedByBlack[r][c];
		}
		else {
			return !attackedByWhite[r][c];
		}
	}
	return ((board[start.getCol()+r][start.getRow()+c] == null) || (isOppositeTeam(start, new Space(start.getCol()+r, start.getRow()+c))));
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

public boolean isOppositeTeam(Space s1, Space s2) {
	//returns true if the pieces at the spaces given are of opposite color.
	//returns false if one or more of the given spaces are null.
	if (s1 != null && s2 != null) {
		return (getPiece(s1).getColor() != getPiece(s2).getColor());
	}
	return false;
}

public void promotePawn(Space s, PieceType type) {
	//replaces a pawn on the board with either a knight, rook, bishop, or queen.
	board[s.getRow()][s.getCol()] = new Piece(s.getRow(), s.getCol(), type, getPiece(s).getColor());
}

public void flipBoard() {
	//Reverses the 2-d array board. Used to simulate looking at the board from the opposite perspective.
	for (int i = 0; i < 8; i++) {
		for(int j = 0; j < 8; j++) {
			temp[i][j] = board[7-i][7-j];
		}
	}
	board = temp;
}

public void updateAttackLists() {
	for(int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			attackedByWhite[i][j] = false;
			attackedByBlack[i][j] = false;
		}
	}
	
}

public boolean checkmate(boolean isTeamWhite) {
	//checks a team's king to see if it is in check. If so, looks to see if the king can move to safety. 
	//If they cannot, looks to see if any of your pieces can block the danger.
	return false;
}


}
