package starter;

public enum PieceType {
	PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING;
	
	public String toString() {
		switch(this) {
			case PAWN: return "pawn";
			case KNIGHT: return "knight";
			case BISHOP: return "bishop";
			case ROOK: return "rook";
			case QUEEN: return "queen";
			case KING: return "king";
		}
		return "n/a";
	}
}