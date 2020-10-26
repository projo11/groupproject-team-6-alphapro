package starter;

public class Board {
private Space board[][];

//Returns true if the Space given has coordinates that are not on the board.
public boolean isOutOfBounds(Space s) {
	if (s.getRow() < 0 || s.getRow() > 7 || s.getCol() < 0 || s.getCol() > 7) {
		return true;
	}
	return false;
}


}
