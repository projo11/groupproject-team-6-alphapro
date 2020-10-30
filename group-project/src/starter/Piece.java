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
		//TO DO
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
