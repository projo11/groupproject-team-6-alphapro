package starter;

public enum PieceType {
	PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING;
	
	public String toString() {
		switch(this) {
			case PAWN: return "P";
			case KNIGHT: return "Knight";
			case BISHOP: return "Bishop";
			case ROOK: return "Rook";
			case QUEEN: return "Queen";
			case KING: return "King";
		}
		return "n/a";
	}
}