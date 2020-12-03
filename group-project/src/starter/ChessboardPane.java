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

public static final int SPACE_SIZE = 100;
public static final int BOARD_SHIFT = 50;
public static final double SIZE_MOD = 0.75;
public static final double PAWN_SIZE_MOD = 0.55;
public static final String LABEL_FONT = "Arial-Bold-22";
public static final Color LABEL_COLOR = Color.red;
private int clickX, clickY, lastX, lastY;
private GObject toDrag;
private boolean isWhiteTurn = true; //Checks to see who's turn it is and which pieces can be moved; White ALWAYS moves first
	
public ChessboardPane(MainApplication app) {
		program = app;
	}
public void printBoard() {
	int x = BOARD_SHIFT;
	int y = BOARD_SHIFT;
	for (int i = 0; i < 8; i++) {//print the board
		for (int j = 0; j < 8; j++) {
				GRect block = new GRect(x, y, SPACE_SIZE, SPACE_SIZE);
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
					x += SPACE_SIZE;
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
					x += SPACE_SIZE;
				}
			}
		x = BOARD_SHIFT; 
		y += SPACE_SIZE;
		}
	
	/*TESTER CODE FOR VISUALIZING ATTACKED SQUARES
	x = BOARD_SHIFT;
	y = BOARD_SHIFT;
	for (int i = 0; i < 8; i++) {//print the board
		for (int j = 0; j < 8; j++) {
				GRect block = new GRect(x, y, SPACE_SIZE, SPACE_SIZE);
				program.add(block);
					if(program.getBoard().getAttackList(true)[i][j]) {
						block.setFillColor(Color.green);
						block.setFilled(true);
					}
					else if (program.getBoard().getAttackList(false)[i][j]) {
						block.setFillColor(Color.blue);
						block.setFilled(true);
					}
					x += SPACE_SIZE;
				}
		x = BOARD_SHIFT; 
		y += SPACE_SIZE;
			}
	
	*/
	for (Piece temp : program.getBoard().getPieces()) {
		GImage toAdd;
		String filePath;
		if (temp.getColor()) {
			filePath = new String("White_" + temp.getType().toString() + ".png");
		}
		else {
			filePath = new String("Black_" + temp.getType().toString() + ".png");
		}
		if (temp.getType() == PieceType.PAWN) {
			toAdd = new GImage(filePath, temp.getCol()*SPACE_SIZE + BOARD_SHIFT + (((1-PAWN_SIZE_MOD)*SPACE_SIZE)/2), temp.getRow()*SPACE_SIZE + BOARD_SHIFT + (((1-PAWN_SIZE_MOD)*SPACE_SIZE)/2));
			toAdd.setSize(SPACE_SIZE * PAWN_SIZE_MOD, SPACE_SIZE * PAWN_SIZE_MOD);
		}
		else {
			toAdd = new GImage(filePath, temp.getCol()*SPACE_SIZE + BOARD_SHIFT + (((1-SIZE_MOD)*SPACE_SIZE)/2), temp.getRow()*SPACE_SIZE + BOARD_SHIFT + (((1-SIZE_MOD)*SPACE_SIZE)/2));
			toAdd.setSize(SPACE_SIZE*SIZE_MOD, SPACE_SIZE*SIZE_MOD);
		}
		program.add(toAdd);
		}
	String labelName = new String();
	GLabel toAdd;
	for (int i = 0; i < 8; i++) {
		switch(i) {
		case 0: 
			labelName = "A";
			break;
		case 1: 
			labelName = "B";
			break;
		case 2: 
			labelName = "C";
			break;
		case 3: 
			labelName = "D";
			break;
		case 4: 
			labelName = "E";
			break;
		case 5: 
			labelName = "F";
			break;
		case 6: 
			labelName = "G";
			break;
		case 7: 
			labelName = "H";
			break;
		}
		toAdd = new GLabel(labelName, BOARD_SHIFT+(i+1)*SPACE_SIZE-15, BOARD_SHIFT+8*SPACE_SIZE);
		toAdd.setFont(LABEL_FONT);
		toAdd.setColor(LABEL_COLOR);
		program.add(toAdd);
	}
	for (int i = 0; i < 8; i++) {
		toAdd = new GLabel(Integer.toString(8-i), BOARD_SHIFT, BOARD_SHIFT+i*SPACE_SIZE+18);
		toAdd.setFont(LABEL_FONT);
		toAdd.setColor(LABEL_COLOR);
		program.add(toAdd);
	}
}


	@Override
	public void showContents() {
		printBoard();
		isWhiteTurn = true;
	}

	@Override
	public void hideContents() {
		program.removeAll();
	}
	
	@Override
    public void mousePressed(MouseEvent e) {
		Piece piece;
		if(program.getElementAt(e.getX(), e.getY()) != null) //If you did not click out of bounds, continue to the code below
		{
			GObject object = program.getElementAt(e.getX(), e.getY());

			if(object.getWidth() != SPACE_SIZE) //If the object you clicked on is a space on the chess board, you cannot drag it
			{
				piece = getPieceFromXY(e.getX(), e.getY());
				if(isWhiteTurn == piece.getColor())
				{
					toDrag = program.getElementAt(e.getX(), e.getY());
				}
			}
			else
			{
				toDrag = null;
			}
		}
		else
		{
			toDrag = null;
		}

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
    	if(toDrag != null) //If the object you initially clicked and dragged was a piece, continue to the piece movement code below
    	{
        	Space space = convertXYToSpace(clickX, clickY);
        	Piece piece;
    		if(space.getRow() != -1 && space.getCol() != -1)
        	{
        		piece = getPieceFromXY(clickX, clickY);
        		if(piece != null)
               	{
        			
        			if(isWhiteTurn != piece.getColor()) {
        				hideContents();
                   		printBoard(); 
        			}
        			else  {
        				program.quicksave();
        				if (program.getBoard().moveNumSpaces(space, calculateRowsMoved(), calculateColsMoved())) {
        					if (program.getBoard().checkmate(program.getBoard().isBoardFlipped())) {
            					hideContents();	
            					program.switchToVic();
            				}
        					else if (program.getBoard().isKingInCheck(isWhiteTurn)) {
        						program.quickload();
        						hideContents();
                           		printBoard();
        					}
            				else {
            					isWhiteTurn = !isWhiteTurn;
            					program.getBoard().flipBoard();
            					hideContents();
            					printBoard(); 
            				}
            			}
            			else {
            				hideContents();
                       		printBoard(); 
            			}
        			}
               	}
        	}
    	}
    }
	    
    //Below are functions used to help with the mouse listener functions
    //Code that converts XY coordinates into a Space
    private Space convertXYToSpace(double x, double y)
    {
    	if(x < BOARD_SHIFT || x > BOARD_SHIFT+(SPACE_SIZE*8) || y < BOARD_SHIFT || y > BOARD_SHIFT+(SPACE_SIZE*8))
    	{
    		return new Space(-1, -1);
    	}
    	else
    	{
        	Space space = new Space((int)((y-BOARD_SHIFT)/SPACE_SIZE), (int)((x-BOARD_SHIFT)/SPACE_SIZE)); 
        	return space;
    	}
    }
	    
    //Code that returns the Piece in a given space with the provided XY coordinates
    private Piece getPieceFromXY(double x, double y)
    {
    	Space space = convertXYToSpace(x, y);
    	return program.getBoard().getPiece(space);
    }
	    
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