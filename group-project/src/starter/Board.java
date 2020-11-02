package starter;

import java.util.*;
import javafx.util.Pair;

public class Board {
private Piece board[][];
private Piece temp[][];

private boolean attackedByWhite[][];
private boolean attackedByBlack[][];
private ArrayList<Piece> pieces;
private boolean isBoardFlipped = false;
//NOTE: Flipped means playing from black's perspective instead of white.


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
		removePiece(new Space(start.getRow()+r, start.getCol()+c));
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
	pieces.add(piece);
	updateAttackLists();
}

public boolean canMoveNumSpaces(Space start, int r, int c) {
	//Returns true if the location at start, translated down by r and to the right by c, is null, or an enemy piece.
	//Contains special instructions for the movement of pawns.
	boolean moveValid = false;
	for (Pair<Integer, Integer> temp : getPiece(start).getPossibleMoves()) {
		if (temp == new Pair<Integer, Integer>(r, c)) {
			moveValid = true;
		}
	}
	if (!moveValid) {
		return false;
	}
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
	//TODO: Require line of sight for non-knights and pawns.
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
	if (isBoardFlipped) {
		isBoardFlipped = false;
	}
	else {
		isBoardFlipped = true;
	}
}

public void updateAttackLists() {
	//updates the attackedByWhite and attackedByBlack arrays to reflect the current board state.
	for(int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			attackedByWhite[i][j] = false;
			attackedByBlack[i][j] = false;
		}
	}
	for (Piece temp : pieces) {
		if (temp.getColor()) {
			//For white pieces
			if(temp.getType() == PieceType.PAWN) {
				if (!isBoardFlipped) {
					//TODO
				}
				else {
					//TODO
				}
				//attackedByWhite[temp.getRow()-1][temp.getCol()+1] = true;
			}
		}
		else {
			//for black pieces
			if(temp.getType() == PieceType.PAWN) {
				if (!isBoardFlipped) {
					//TODO
				}
				else {
					//TODO
				}
			}
		}
	}
}

public void removePiece(Space s) {
	//removes a piece from the board, as well as from the ArrayList of all pieces.
	if (board[s.getRow()][s.getCol()] != null) {
		for (Piece temp : pieces) {
			if (temp == board[s.getRow()][s.getCol()]) {
				pieces.remove(temp);
			}
		}
		board[s.getRow()][s.getCol()] = null;
	}
}

public boolean hasLineOfSight(Space start, int r, int c) {
	if ((Math.abs(r) != Math.abs(c) && r != 0 && c != 0) || isOutOfBounds(new Space(r, c))) {
		return false;
	}
	for (int i = 0; i < 8; i++) {
		//TODO: Figure out how to check every value from start to the end
	}
	return false;
}

public boolean checkmate(boolean isTeamWhite) {
	//checks a team's king to see if it is in check. If so, looks to see if the king can move to safety. 
	//If they cannot, looks to see if any of your pieces can block the danger.
	return false;
}


}
