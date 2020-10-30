package starter;
import java.util.*;
import javafx.util.Pair;

public class Piece {
	
	private int row;
	private int col;
	private PieceType type;
	private boolean color; //1 = White, 0 = Black
	private int cost;
	private ArrayList<Pair> PossibleMoves;
	private boolean hasMoved;
	
	//Constructor to create a Piece object with the given parameters.
	public Piece(int row, int col, PieceType type, boolean color)
	{
		this.row = row;
		this.col = col;
		this.type = type;
		this.color = color;
	}
	
	public void setRow(int row) //Sets row for Piece
	{
		this.row = row;
	}
	
	public int getRow() //Gets row for Piece
	{
		return row;
	}
	
	public void setCol(int col) //Sets Column for Piece.
	{
		this.col = col;
	}
	
	public int getCol() //Gets Column for Piece
	{
		return col;
	}
	
	public void setType(PieceType type) //Sets Piece type
	{
		this.type = type;
	}
	
	public PieceType getType() //Gets Piece type
	{
		return type;
	}
	
	public void setPossibleMoves(PieceType type) //Sets Possible Moves for each Piece
	{
		if(type == PieceType.PAWN) //Checks to see if the piece is a pawn
		{
			//Total number of moves: 4; Range: 1 space OR 2 space
			Pair<Integer, Integer> move1 = new Pair<Integer, Integer> (0, -2);
			Pair<Integer, Integer> move2 = new Pair<Integer, Integer> (0, -1);
			Pair<Integer, Integer> move3 = new Pair<Integer, Integer> (-1, -1);
			Pair<Integer, Integer> move4 = new Pair<Integer, Integer> (1, -1);
			PossibleMoves.add(move1);
			PossibleMoves.add(move2);
			PossibleMoves.add(move3);
			PossibleMoves.add(move4);
		}
		else if(type == PieceType.KNIGHT) //Checks to see if the piece is a Knight
		{
			//Total Number of moves: 8; Range: 1 space
			Pair<Integer, Integer> move1 = new Pair<Integer, Integer> (-1, -2); //Going forward 2 spaces, 1 right
			Pair<Integer, Integer> move2 = new Pair<Integer, Integer> (1, -2); //Going forward 2 spaces, 1 left
			Pair<Integer, Integer> move3 = new Pair<Integer, Integer> (-1, 2); //Going backwards 2 spaces, 1 right
			Pair<Integer, Integer> move4 = new Pair<Integer, Integer> (1, 2); //Going backwards 2 spaces, 1 left
			Pair<Integer, Integer> move5 = new Pair<Integer, Integer> (-2, -1); //Going right 2 spaces, forward 1
			Pair<Integer, Integer> move6 = new Pair<Integer, Integer> (-2, 1); //Going right 2 spaces, backwards 1
			Pair<Integer, Integer> move7 = new Pair<Integer, Integer> (2, -1); //Going left 2 spaces, forward 1
			Pair<Integer, Integer> move8 = new Pair<Integer, Integer> (2, 1); //Going left 2 spaces, backward 2
			PossibleMoves.add(move1);
			PossibleMoves.add(move2);
			PossibleMoves.add(move3);
			PossibleMoves.add(move4);
			PossibleMoves.add(move5);
			PossibleMoves.add(move6);
			PossibleMoves.add(move7);
			PossibleMoves.add(move8);
		}
		else if(type == PieceType.BISHOP) //Checks to see if the piece is a Bishop
		{
			//Total number of moves: 28; Range: 1-7 spaces
			for(int i = 1; i <= 7; i++) //Longest possible movement range is 7 in a straight line
			{
				Pair<Integer, Integer> move1 = new Pair<Integer, Integer> (-i, -i); //Going forward diagonally to the right in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> move2 = new Pair<Integer, Integer> (i, -i); //Going forward diagonally to the left in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> move3 = new Pair<Integer, Integer> (-i, i); //Going backwards diagonally to the right in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> move4 = new Pair<Integer, Integer> (i, i); //Going backwards diagonally to the left in a straight line ranging from 1-7 spaces
				PossibleMoves.add(move1);
				PossibleMoves.add(move2);
				PossibleMoves.add(move3);
				PossibleMoves.add(move4);
			}
		}
		else if(type == PieceType.ROOK) //Checks to see if the piece is a Rook
		{
			//Total number of moves: 28; Range: 1-7 spaces
			for(int i = 1; i <= 7; i++) //Longest possible movement range is 7 in a straight line
			{
				Pair<Integer, Integer> move1 = new Pair<Integer, Integer> (0, -i); //Going forward in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> move2 = new Pair<Integer, Integer> (0, i); //Going backwards in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> move3 = new Pair<Integer, Integer> (-i, 0); //Going to the right in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> move4 = new Pair<Integer, Integer> (i, 0); //Going to the left in a straight line ranging from 1-7 spaces
				PossibleMoves.add(move1);
				PossibleMoves.add(move2);
				PossibleMoves.add(move3);
				PossibleMoves.add(move4);
			}
		}
		else if(type == PieceType.QUEEN) //Checks to see if the piece is a Queen
		{
			//Total Number of moves: 56; Range: 1-7 spaces
			for(int i = 1; i <= 7; i++) //Longest possible movement range is 7 in a straight line
			{
				Pair<Integer, Integer> move1 = new Pair<Integer, Integer> (0, -i); //Going forward in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> move2 = new Pair<Integer, Integer> (0, i); //Going backwards in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> move3 = new Pair<Integer, Integer> (-i, 0); //Going to the right in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> move4 = new Pair<Integer, Integer> (i, 0); //Going to the left in a straight line ranging from 1-7 spaces
				PossibleMoves.add(move1);
				PossibleMoves.add(move2);
				PossibleMoves.add(move3);
				PossibleMoves.add(move4);
			}
			for(int i = 1; i <= 7; i++) //Longest possible movement range is 7 in a straight line
			{
				Pair<Integer, Integer> move5 = new Pair<Integer, Integer> (-i, -i); //Going forward diagonally to the right in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> move6 = new Pair<Integer, Integer> (i, -i); //Going forward diagonally to the left in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> move7 = new Pair<Integer, Integer> (-i, i); //Going backwards diagonally to the right in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> move8 = new Pair<Integer, Integer> (i, i); //Going backwards diagonally to the left in a straight line ranging from 1-7 spaces
				PossibleMoves.add(move5);
				PossibleMoves.add(move6);
				PossibleMoves.add(move7);
				PossibleMoves.add(move8);
			}
		}
		else //If the piece is none of the pieces above, the piece is a King
		{
			//Total Number of moves: 8; Range: 1 space
			Pair<Integer, Integer> move1 = new Pair<Integer, Integer> (0, -1); //Going forward 1 space
			Pair<Integer, Integer> move2 = new Pair<Integer, Integer> (0, 1); //Going backward 1 space
			Pair<Integer, Integer> move3 = new Pair<Integer, Integer> (-1, 0); //Going to the right 1 space
			Pair<Integer, Integer> move4 = new Pair<Integer, Integer> (1, 0); //Going to the left 1 space
			Pair<Integer, Integer> move5 = new Pair<Integer, Integer> (-1, -1); //Going forward diagonally to the right 1 space
			Pair<Integer, Integer> move6 = new Pair<Integer, Integer> (1, -1); //Going forward diagonally to the left 1 space
			Pair<Integer, Integer> move7 = new Pair<Integer, Integer> (-1, 1); //Going backwards diagonally to the right 1 space
			Pair<Integer, Integer> move8 = new Pair<Integer, Integer> (1, 1); //Going backwards diagonally to the left 1 space
			PossibleMoves.add(move1);
			PossibleMoves.add(move2);
			PossibleMoves.add(move3);
			PossibleMoves.add(move4);
			PossibleMoves.add(move5);
			PossibleMoves.add(move6);
			PossibleMoves.add(move7);
			PossibleMoves.add(move8);
		}
	}
	
	public int getPossibleMoves() // Gets Possible moves for each Piece
	{
		return 0;
	}
	
	public void setColor(boolean color) // Sets Color for each Piece.
	{
		this.color = color;
	}
	
	public boolean getColor() //Gets each Pieces color
	{
		return color;
	}
	
	public void setCost(int cost) //Sets cost for each Piece.
	{
		this.cost = cost;
	}
	
	public int getCost() //Gets Cost for each Piece.
	{
		return cost;
	}
	
	public void setHasMoved(boolean hasMoved)
	{
		this.hasMoved = hasMoved;
	}
	
	public boolean getHasMoved()
	{
		return hasMoved;
	}
	
	public Space ifIWereToMove()
	{
		return null;
	}

}
