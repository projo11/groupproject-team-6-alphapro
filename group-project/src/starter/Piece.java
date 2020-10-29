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
	
	//Constructor to create a Piece object with the given parameters.
	public Piece(int row, int col, PieceType type, boolean color)
	{
		this.row = row;
		this.col = col;
		this.type = type;
		this.color = color;
	}
	
	public void setRow(int row)
	{
		this.row = row;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public void setCol(int col)
	{
		this.col = col;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public void setType(PieceType type)
	{
		this.type = type;
	}
	
	public PieceType getType()
	{
		return type;
	}
	
	public void setPossibleMoves(PieceType type)
	{
		//TO DO
	}
	
	public int getPossibleMoves()
	{
		return 0;
	}
	
	public void setColor(boolean color)
	{
		this.color = color;
	}
	
	public boolean getColor()
	{
		return color;
	}
	
	public void setCost(int cost)
	{
		this.cost = cost;
	}
	
	public int getCost()
	{
		return cost;
	}
	
	public Space ifIWereToMove()
	{
		return null;
	}

}
