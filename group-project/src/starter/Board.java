package starter;

import java.util.*;
import javafx.util.Pair;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
 import javax.sound.sampled.Clip;

public class Board {
private Piece board[][] = new Piece[8][8];

private boolean attackedByWhite[][] = new boolean[8][8];
private boolean attackedByBlack[][] = new boolean[8][8];
private ArrayList<Piece> pieces = new ArrayList<Piece>();
private boolean isBoardFlipped = false;
//NOTE: Flipped means playing from black's perspective instead of white.

public Board() {
	addPiece(7, 4, PieceType.KING, true);
	addPiece(0, 4, PieceType.KING, false);
	addPiece(6, 4, PieceType.PAWN, true);
	addPiece(1, 4, PieceType.PAWN, false);
	addPiece(7, 3, PieceType.QUEEN, true);
	addPiece(0, 3, PieceType.QUEEN, false);
	addPiece(7, 2, PieceType.BISHOP, true);
	addPiece(0, 2, PieceType.BISHOP, false);
	addPiece(7, 1, PieceType.KNIGHT, true);
	addPiece(0, 1, PieceType.KNIGHT, false);
	addPiece(7, 0, PieceType.ROOK, true);
	addPiece(0, 0, PieceType.ROOK, false);
}


public boolean isOutOfBounds(Space s) {
	//Returns true if the Space given has coordinates that are not on the board.
	if (s.getRow() < 0 || s.getRow() > 7 || s.getCol() < 0 || s.getCol() > 7) {
		try{
		      AudioInputStream movepiece =AudioSystem.getAudioInputStream(this.getClass().getResource("piecemovesoundeffect.wav"));
		     Clip clip = AudioSystem.getClip();
		     clip.open(movepiece);
		     clip.start( );
		    }
		   catch(Exception ex)
		   {  }
		return true;
	}
	return false;
}

public boolean moveNumSpaces(Space start, int r, int c) {
	//Moves the piece at the given space down by r and to the right by c. Returns false if the space given is null.
	if (canMoveNumSpaces(start, r, c)) {
		getPiece(start).setHasMoved(true);
		getPiece(start).setRow(getPiece(start).getRow()+r);
		getPiece(start).setCol(getPiece(start).getCol()+c);
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
		System.out.println("ERROR: Could not add piece.");
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
	Pair<Integer, Integer> desiredMove = new Pair<Integer, Integer>(r, c);
	for (Pair<Integer, Integer> temp : getPiece(start).getPossibleMoves()) {
		if (temp.getKey() == desiredMove.getKey()) {
			if (temp.getValue() == desiredMove.getValue()) {
				moveValid = true;
			}
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
		if (board[start.getCol()+r][start.getCol()+c] == null || board[start.getCol()+r][start.getCol()+c].getColor() != getPiece(start).getColor()) {
			if (getPiece(start).getColor()) {
				return !attackedByBlack[start.getCol()+r][start.getCol()+c];
			}
			else {
				return !attackedByWhite[start.getCol()+r][start.getCol()+c];
			}
		}
		else {
			return false;
		}
	}
	if (getPiece(start).getType() == PieceType.KNIGHT) {
		if (isOutOfBounds(new Space(start.getCol()+r, start.getRow()+c))) {
			return false;
		}
		return (board[start.getCol()+r][start.getRow()+c] == null) || (isOppositeTeam(start, new Space(start.getCol()+r, start.getRow()+c)));
	}
	if (isOutOfBounds(new Space(start.getCol()+r, start.getRow()+c))) {
		return false;
	}
	return (hasLineOfSight(start, r, c) && ((board[start.getCol()+r][start.getRow()+c] == null) || (isOppositeTeam(start, new Space(start.getCol()+r, start.getRow()+c)))));
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
	//NOTE: Flipped means playing from black's perspective instead of white.
	Piece temp[][] = new Piece[8][8];
	for (int i = 0; i < 8; i++) {
		for(int j = 0; j < 8; j++) {
			temp[i][j] = board[7-i][7-j];
			if (temp[i][j] != null) {
				temp[i][j].setRow(i);
				temp[i][j].setCol(j);
			}
		}
	}
	for (int i = 0; i < 8; i++) {
		for(int j = 0; j < 8; j++) {
			 board[i][j] = temp[i][j];
		}
	}
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
			switch(temp.getType()) {
			case PAWN: 
				if (!isBoardFlipped) {
					attackedByWhite[temp.getRow()-1][temp.getCol()+1] = true;
					attackedByWhite[temp.getRow()-1][temp.getCol()-1] = true;
				}
				else {
					attackedByWhite[temp.getRow()+1][temp.getCol()+1] = true;
					attackedByWhite[temp.getRow()+1][temp.getCol()-1] = true;
				}
				break;
			case KNIGHT:
				for (Pair<Integer, Integer> pair : temp.getPossibleMoves()) {
					if(!isOutOfBounds(new Space(temp.getRow()+pair.getKey(), temp.getCol()+pair.getValue()))) { 
						attackedByWhite[temp.getRow()+pair.getKey()][temp.getCol()+pair.getValue()] = true;
					}
				}
				break;
			case KING:
				//TODO: Figure out a better way to optimise this. 
				//Currently the script ignores kings until after this part is done, so that
				//the king will not be allowed to enter an attacked space.
				break;
			default:
				for (Pair<Integer, Integer> pair : temp.getPossibleMoves()) {
					if(!isOutOfBounds(new Space(temp.getRow()+pair.getKey(), temp.getCol()+pair.getValue())) && hasLineOfSight(new Space(temp.getRow(), temp.getCol()), pair.getKey(), pair.getValue())) { 
						attackedByWhite[temp.getRow()+pair.getKey()][temp.getCol()+pair.getValue()] = true; //TODO: Could be severly optimised
					}
				}
			}
		}
		else {
			//for black pieces
			switch(temp.getType()) {
			case PAWN: 
				if (!isBoardFlipped) {
					attackedByBlack[temp.getRow()+1][temp.getCol()+1] = true;
					attackedByBlack[temp.getRow()+1][temp.getCol()-1] = true;
				}
				else {
					attackedByBlack[temp.getRow()-1][temp.getCol()+1] = true;
					attackedByBlack[temp.getRow()-1][temp.getCol()-1] = true;
				}
				break;
			case KNIGHT:
				for (Pair<Integer, Integer> pair : temp.getPossibleMoves()) {
					if(!isOutOfBounds(new Space(temp.getRow()+pair.getKey(), temp.getRow()+pair.getKey()))) {
						attackedByBlack[temp.getRow()+pair.getKey()][temp.getRow()+pair.getKey()] = true;
					}
				}
				break;
			case KING:
				//Figure out a better way to optimise this. 
				//Currently the script ignores kings until after this part is done, so that
				//the king will not be allowed to enter an attacked space.
				break;
				default:
					for (Pair<Integer, Integer> pair : temp.getPossibleMoves()) {
						if(!isOutOfBounds(new Space(temp.getRow()+pair.getKey(), temp.getRow()+pair.getKey())) && hasLineOfSight(new Space(temp.getRow(), temp.getCol()), pair.getKey(), pair.getValue())) { 
							attackedByBlack[temp.getRow()+pair.getKey()][temp.getRow()+pair.getKey()] = true; //TODO: Could be severly optimised
					}
				}
			}
		}
	}
	for (Piece temp : pieces) {
		if (temp.getType() == PieceType.KING) {
			if (temp.getColor()) {
				for (Pair<Integer, Integer> pair : temp.getPossibleMoves()) {
					if(!isOutOfBounds(new Space(temp.getRow()+pair.getKey(), temp.getCol()+pair.getValue())) && !attackedByBlack[temp.getRow()+pair.getKey()][temp.getCol()+pair.getValue()]) { 
						attackedByWhite[temp.getRow()+pair.getKey()][temp.getCol()+pair.getValue()] = true; 
					}
				}
			}
			else {
				for (Pair<Integer, Integer> pair : temp.getPossibleMoves()) {
					if(!isOutOfBounds(new Space(temp.getRow()+pair.getKey(), temp.getCol()+pair.getValue())) && !attackedByWhite[temp.getRow()+pair.getKey()][temp.getCol()+pair.getValue()]) { 
						attackedByBlack[temp.getRow()+pair.getKey()][temp.getCol()+pair.getValue()] = true; 
					}
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
				//String pop = "takepiecesoundeffect.wav";
				//Media hit = new Media(new File(pop).toURI().toString());
				//MediaPlayer mediaPlayer = new MediaPlayer(hit);
				//mediaPlayer.play();
				pieces.remove(temp);
				break;
			}
		}
		board[s.getRow()][s.getCol()] = null;
	}
}

public boolean hasLineOfSight(Space start, int r, int c) {
	//returns true if the piece at start has line of sight to the space translated by r and c.
	if ((Math.abs(r) != Math.abs(c) && r != 0 && c != 0) || isOutOfBounds(new Space(r, c))) {
		return false;
	}
	int greater;
	if (r > c) {
		greater = r;
	}
	else {
		greater = c;
	}
	for (int i = 0; i < Math.abs(greater)-1; i++) {//TODO: Determine if greater-1 is working as intended
		if (start.getRow()+((i+1)*Integer.signum(r)) < 7 && start.getCol()+((i+1)*Integer.signum(c)) < 7 && start.getRow()+((i+1)*Integer.signum(r)) > 0 && start.getCol()+((i+1)*Integer.signum(c)) > 0) {
			if (board[start.getRow()+((i+1)*Integer.signum(r))][start.getCol()+((i+1)*Integer.signum(c))] != null) {
				return false;
			}
		}
	}
	return true;
}

public boolean[][] getAttackList(boolean isTeamWhite) {
	//Returns the 2d attacked-by array for the given team color.
	if (isTeamWhite) {
		return attackedByWhite;
	}
	else {
		return attackedByBlack;
	}
}

public boolean checkmate(boolean isTeamWhite) {
	//Returns true if the given team has been checkmated.
	
	//checks a team's king to see if it is in check. If so, looks to see if the king can move to safety. 
	//If they cannot, looks to see if any of your pieces can block the danger or kill the attacker.
	/*\
	 * STEP 0: Locate King
	 * STEP 1: Is king in check?
	 * STEP 2: Can the king move to safety?
	 * STEP 3: Determine sources of danger
	 * STEP 3.a: Check Ls for knights
	 * STEP 3.b: Check diagonals for bishops or rooks
	 * STEP 3.c: Check straights for rooks or queens
	 * STEP 4: Determine if the danger can be eliminated
	 * STEP 5: Determine if line-of-sight to the attacker can be intercepted
	\*/
	//STEP 0
	Space kingLoc = null;
	Piece attacker = null;
	Space toCheck = null;
	boolean oppositeTeam;
	if (isTeamWhite) {
		oppositeTeam = false;
	}
	else {
		oppositeTeam = true;
	}
	for (Piece temp : pieces) {
		if (temp.getColor() == isTeamWhite && temp.getType() == PieceType.KING) {
			kingLoc = new Space(temp.getRow(), temp.getCol());
		}
	}
		//WHITE TEAM
		//STEP 1
		if (!getAttackList(oppositeTeam)[kingLoc.getRow()][kingLoc.getCol()]) {
			return false;
		}
		//STEP 2
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (!isOutOfBounds(new Space(kingLoc.getRow()+i, kingLoc.getCol()+i))) {
					if (!getAttackList(oppositeTeam)[kingLoc.getRow()+i][kingLoc.getCol()+i]) {
						return false;
					}
				}
			}
		}
		//STEP 3
		//check for knights
		for (int i = -1; i < 2; i+=2) {
			for (int j = -1; j < 2; j+=2) {
				toCheck = new Space(kingLoc.getRow()+(2*i), kingLoc.getCol()+j);
				if (!isOutOfBounds(toCheck)) {
					if (getPiece(toCheck).getType() == PieceType.KNIGHT && !getPiece(toCheck).getColor()) {
						if (attacker != null) {
							return true;
						}
						else {
							attacker = getPiece(toCheck);
						}
					}
				}
				toCheck = new Space(kingLoc.getRow()+i, kingLoc.getCol()+(2*j));
				if (!isOutOfBounds(toCheck)) {
					if (getPiece(toCheck).getType() == PieceType.KNIGHT && !getPiece(toCheck).getColor()) {
						if (attacker != null) {
							return true;
						}
						else {
							attacker = getPiece(toCheck);
						}
					}
				}//TODO: Optimize in the same fashion as the diag function
			}
		}
		//check diagonals
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < 9; j++) {
				switch(i) {
				case 0: 
					toCheck = new Space(kingLoc.getRow()-i, kingLoc.getCol()+i);//Up-right
					break;
				case 1: 
					toCheck = new Space(kingLoc.getRow()+i, kingLoc.getCol()+i);//Down-right
					break;
				case 2: 
					toCheck = new Space(kingLoc.getRow()+i, kingLoc.getCol()-i);//Down-left
					break;
				case 3:
					toCheck = new Space(kingLoc.getRow()-i, kingLoc.getCol()-i);//Up-left
					break;
				}
				if (!isOutOfBounds(toCheck)) {
					if ((getPiece(toCheck).getType() == PieceType.BISHOP || getPiece(toCheck).getType() == PieceType.QUEEN) && !getPiece(toCheck).getColor()) {
						if (attacker != null) {
							return true;
						}
						else {
							attacker = getPiece(toCheck);
						}
					}
				}
			}
		}
		//check straights
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < 9; j++) {
				switch(i) {
				case 0: 
					toCheck = new Space(kingLoc.getRow(), kingLoc.getCol()+i);//Right
					break;
				case 1: 
					toCheck = new Space(kingLoc.getRow()+i, kingLoc.getCol());//Down
					break;
				case 2: 
					toCheck = new Space(kingLoc.getRow(), kingLoc.getCol()-i);//Left
					break;
				case 3:
					toCheck = new Space(kingLoc.getRow()-i, kingLoc.getCol());//Up
					break;
				}
				if (!isOutOfBounds(toCheck)) {
					if ((getPiece(toCheck).getType() == PieceType.ROOK || getPiece(toCheck).getType() == PieceType.QUEEN) && !getPiece(toCheck).getColor()) {
						if (attacker != null) {
							return true;
						}
						else {
							attacker = getPiece(toCheck);
						}
					}
				}
			}
		}
		//STEP 4
		if (getAttackList(isTeamWhite)[attacker.getRow()][attacker.getCol()]) { 
			return false;
		}
		if (attacker.getType() == PieceType.KNIGHT) {
			return true;
		}
		//STEP 5
		ArrayList<Space> blockLocations = new ArrayList<Space>();
		int r = attacker.getRow() - kingLoc.getRow();
		int c = attacker.getCol() - kingLoc.getCol();
		if (r == 1 || c == 1) {
			return true;
		}
		c += Integer.signum(c)*-1;
		r += Integer.signum(r)*-1;
		int distance = 0;
		if (Math.abs(r) > Math.abs(c)) {
			distance = Math.abs(r);
		}
		else {
			distance = Math.abs(c);
		}
		for (int i = 0; i < distance; i++) {
			blockLocations.add(new Space (kingLoc.getRow()+(i*Integer.signum(r)), kingLoc.getCol()+(i*Integer.signum(c))));
		}
		for (Space temp : blockLocations) {
			if (getAttackList(isTeamWhite)[temp.getRow()][temp.getCol()]) {
				return false;
			}
		}
	return true;
}

public ArrayList<Piece>getPieces() {
	return pieces;
}


}
