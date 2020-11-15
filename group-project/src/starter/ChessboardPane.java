package starter;

import java.awt.Color;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class ChessboardPane extends GraphicsPane implements ActionListener {
private MainApplication program;
public static final int SPACE_WIDTH = 40;
public static final int SPACE_HEIGHT = 40;
public static final int BOARD_SHIFT = 100;
private int clickX, clickY, lastX, lastY;
private GObject toDrag;
	
public ChessboardPane(MainApplication app) {
		program = app;
	}
public void printBoard() {
	int[][] pboard= new int[9][9];
	int x = 50;
	int y = 50;
	for (int i = 0; i < pboard.length; i++) {//print the board
		if(i < 8) {
		for (int j = 0; j < pboard[i].length; j++) {
			if(j != 0) {
				GRect block = new GRect(x, y, 100, 100);
				program.add(block);
				if(i%2 == 0) {
					if(j%2 == 0) {
						block.setFillColor(Color.black);
						block.setFilled(true);
					}
					else {
						block.setFillColor(Color.white);
						block.setFilled(true);
					}
					x = x + 100;
				}
				else {
					if(j%2 == 0) {
						block.setFillColor(Color.white);
						block.setFilled(true);
					}
					else {
						block.setFillColor(Color.black);
						block.setFilled(true);
					}
					x = x + 100;
				}
			}
			else {
				switch(i) {//print the number label at the side of the board
				case 0: 
					GLabel eight = new GLabel("8", x, y);
					program.add(eight);
					x = x + 100;
					
				case 1: 
					GLabel seven = new GLabel("7", x, y);
					program.add(seven);
					x = x + 100;
					
				case 2: 
					GLabel six = new GLabel("6", x, y);
					program.add(six);
					x = x + 100;
					
				case 3: 
					GLabel five = new GLabel("5", x, y);
					program.add(five);
					x = x + 100;
					
				case 4: 
					GLabel four = new GLabel("4", x, y);
					program.add(four);
					x = x + 100;
					
				case 5: 
					GLabel three = new GLabel("3", x, y);
					program.add(three);
					x = x + 100;
					
				case 6: 
					GLabel two = new GLabel("2", x, y);
					program.add(two);
					x = x + 100;
					
				case 7: 
					GLabel one = new GLabel("1", x, y);
					program.add(one);
					x = x + 100;
					
				case 8: 
					x = x + 100;
					
				}
			}
			y = y + 100;
			}
		x = 50; 
		}
		else {//print the alphabet label at the bottom of the board
			GLabel hlabel = new GLabel("A", 150, 850);
			program.add(hlabel);
			GLabel glabel = new GLabel("B", 250, 850);
			program.add(glabel);
			GLabel flabel = new GLabel("C", 350, 850);
			program.add(flabel);
			GLabel elabel = new GLabel("D", 450, 850);
			program.add(elabel);
			GLabel dlabel = new GLabel("E", 550, 850);
			program.add(dlabel);
			GLabel clabel = new GLabel("F", 650, 850);
			program.add(clabel);
			GLabel blabel = new GLabel("G", 750, 850);
			program.add(blabel);
			GLabel alabel = new GLabel("H", 850, 850);
			program.add(alabel);
		}
	}
	

for (Piece temp : program.getBoard().getPieces()) {
	GImage toAdd;
	String filePath;
	if (temp.getColor()) {
		filePath = new String("White_" + temp.getType().toString() + ".png");
	}
	else {
		filePath = new String("Black_" + temp.getType().toString() + ".png");
	}
	toAdd = new GImage(filePath, temp.getCol()*SPACE_WIDTH, temp.getRow()*SPACE_HEIGHT);
	toAdd.setSize(40, 40);
	program.add(toAdd);
	}
	
}


	@Override
	public void showContents() {
		printBoard();
	}

	@Override
	public void hideContents() {
		program.removeAll();
		
	}
	
	@Override
    public void mousePressed(MouseEvent e) {
		//TODO FIX THIS BELOW!!
	    //toDrag = getElementAt(e.getX(), e.getY());
	    lastX = e.getX();
	    lastY = e.getY();
	    clickX = e.getX();
	    clickY = e.getY();
	}

	@Override
    public void mouseDragged(MouseEvent e) {
	    if(toDrag != null)
	    {
	    	toDrag.move(e.getX()-lastX, e.getY()-lastY);
	    }
	    lastX = e.getX();
	    lastY = e.getY();
    }
	
    @Override
	public void mouseReleased(MouseEvent e) {
    	Space space = convertXYToSpace(clickX, clickY);
    	Piece piece = getPieceFromXY(clickX, clickY);
    	//TODO: Figure out where to make it so that isPlayingMatch becomes true
   		if(piece != null)
       	{
       		program.getBoard().moveNumSpaces(space, calculateRowsMoved(), calculateColsMoved());
       		hideContents();
       		printBoard(); 
       	}
       	if(piece.getColor() == true) //true = white -> piece is white
       	{
    		if(program.getBoard().checkmate(piece.getColor()) == true) //if a white piece puts the opponent in checkmate
       		{
       			hideContents();
       			program.switchToVic();
       		}
       	}
       	else //false = black -> piece is black
       	{
      		if(program.getBoard().checkmate(piece.getColor()) == true) //if a black piece puts the opponent in checkmate
       		{
       			hideContents();
       			program.switchToVic();
        		}
        	}
	    }
	    
	    //Below are functions used to help with the mouse listener functions
	    //Code that converts XY coordinates into a Space
	    private Space convertXYToSpace(double x, double y)
	    {
	    	Space space = new Space((int)(y/SPACE_HEIGHT), (int)(x/SPACE_WIDTH)); 
	    	return space;
	    }
	    
    //Code that returns the Piece in a given space with the provided XY coordinates
    private Piece getPieceFromXY(double x, double y)
    {
    	Space space = convertXYToSpace(x, y);
    	return program.getBoard().getPiece(space);
    }
	    
    //TODO: When calculating calculating rows moved and cols moved, must change it to account
    //		for the BOARD_SHIFT of 100 pixels; ANY pieces that are moved outside of the board
    //		is not allowed
    //Code that returns how many rows a piece has moved
    private int calculateRowsMoved()
    {
    	int rowsMoved = 0;
    	Space start = convertXYToSpace(clickX, clickY); 
    	Space end = convertXYToSpace(lastX, lastY); 
    	Piece piece = getPieceFromXY(clickX, clickY);
    	if(piece != null)
    	{
    		rowsMoved = end.getRow() - start.getRow();
    	}
    	else if(lastX <= BOARD_SHIFT) //If the place the player moved the chess piece is out of bounds, then the move is invalid the the rows moved is 0
    	{
    		//TODO: Fix code
    		rowsMoved = 0;
    	}
    	else //If no piece was selected, the amount of rows moved is 0
    	{
    		rowsMoved = 0;
    	}
    	return rowsMoved;
    }
	    
    //Code that returns how many cols a piece has moved
    private int calculateColsMoved()
    {
    	int colsMoved = 0;
    	Space start = convertXYToSpace(clickX, clickY);
    	Space end = convertXYToSpace(lastX, lastY);
    	Piece piece = getPieceFromXY(clickX, clickY);
    	if(piece != null)
    	{
    		colsMoved = end.getCol() - start.getCol();
    	}
    	else if(lastY <= BOARD_SHIFT) //If the place the player moved the chess piece is out of bounds, then the move is invalid the the cols moved is 0
    	{
    		//TODO: Fix code
    		colsMoved = 0;
    	}
    	else //If no piece was selected, the amount of cols moved is 0
    	{
    		colsMoved = 0;
    	}
    	return colsMoved;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}