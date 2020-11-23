package starter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.graphics.GRect;

public class PieceShopPane extends GraphicsPane implements ActionListener {
private MainApplication program;

//Location and size of buttons are currently just a placeholder; fix later
private GButton P1 = new GButton("P1 Done", 700, 700, 150 , 80);
private GButton P2 = new GButton("P2 Done", 700, 700, 150 , 80);
//buy pieces button
private final GButton Bp = new GButton("Buy", 820, 80, 70, 30);
private final GButton BK = new GButton("Buy", 820, 120, 70, 30);
private final GButton Br = new GButton("Buy", 820, 160, 70, 30);
private final GButton Bb = new GButton("Buy", 820, 200, 70, 30);
private final GButton Bq = new GButton("Buy", 820, 240, 70, 30);
//detect player 1 or 2
private int player = 1;
public static final int SPACE_SIZE = 77;
public static final int BOARD_SHIFT = 50;
public static final double SIZE_MOD = 0.75;
public static final double PAWN_SIZE_MOD = 0.55;
public static final String LABEL_FONT = "Arial-Bold-22";
public static final Color LABEL_COLOR = Color.red;

private Board p = new Board();

private int clickX, clickY, lastX, lastY;
private int startX;
private int startY;
private GObject toDrag;
private int pieceT;
GImage toAdd;
String filePath;

public PieceShopPane(MainApplication app) {
		program = app;
	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		//Board
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
				toAdd.setSize(SPACE_SIZE * PAWN_SIZE_MOD, SPACE_SIZE * PAWN_SIZE_MOD);
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
		
		//Shop
		GLabel tital = new GLabel("Piece Shop:",680, 20);
		tital.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
		tital.setColor(Color.red);
		GLabel Name = new GLabel("Piece Name", 680, 50);
		Name.setFont(new Font("TimesNewRoman", Font.BOLD, 15));
		Name.setColor(Color.BLACK);
		GLabel Cost = new GLabel("Piece Cost", 780, 50);
		Cost.setFont(new Font("TimesNewRoman", Font.BOLD, 15));
		Cost.setColor(Color.BLACK);
		GLabel pawn = new GLabel("Pawn", 680, 100);
		pawn.setFont(new Font("TimesNewRoman", 15, 15));
		pawn.setColor(Color.BLACK);
		GLabel pawnC = new GLabel("1", 780, 100);
		pawnC.setFont(new Font("TimesNewRoman", 15, 15));
		pawnC.setColor(Color.BLACK);
		
		GLabel knight = new GLabel("Knight", 680, 140);
		knight.setFont(new Font("TimesNewRoman", 15, 15));
		knight.setColor(Color.BLACK);
		GLabel KnightC = new GLabel("3", 780, 140);
		KnightC.setFont(new Font("TimesNewRoman", 15, 15));
		KnightC.setColor(Color.BLACK);
		
		GLabel Bishop = new GLabel("Bishop", 680, 180);
		Bishop.setFont(new Font("TimesNewRoman", 15, 15));
		Bishop.setColor(Color.BLACK);
		GLabel BishopC = new GLabel("3", 780, 180);
		BishopC.setFont(new Font("TimesNewRoman", 15, 15));
		BishopC.setColor(Color.BLACK);
		
		GLabel Rook = new GLabel("Rook", 680, 220);
		Rook.setFont(new Font("TimesNewRoman", 15, 15));
		Rook.setColor(Color.BLACK);
		GLabel RookC = new GLabel("4", 780, 220);
		RookC.setFont(new Font("TimesNewRoman", 15, 15));
		RookC.setColor(Color.BLACK);
		
		GLabel Queen = new GLabel("Queen", 680,260);
		Queen.setFont(new Font("TimesNewRoman", 15, 15));
		Queen.setColor(Color.BLACK);
		GLabel QueenC = new GLabel("7", 780,260);
		QueenC.setFont(new Font("TimesNewRoman", 15, 15));
		QueenC.setColor(Color.BLACK);
		
		
		program.add(tital);
		program.add(Name);
		program.add(Cost);
		program.add(pawn);
		program.add(pawnC);
		program.add(knight);
		program.add(KnightC);
		program.add(Bishop);
		program.add(BishopC);
		program.add(Rook);
		program.add(RookC);
		program.add(Queen);
		program.add(QueenC);
		
		program.add(P1);
		program.add(Bp);
		program.add(BK);
		program.add(Br);
		program.add(Bb);
		program.add(Bq);
	}
	
	@Override
	public void hideContents() {
		program.removeAll();
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == P1) //After Player 1 is done setting up their board, screen changes so that Player 2 now gets to set up the board
		{
			program.removeAll();
			program.getBoard().flipBoard();
			showContents();
			program.add(P2);
			player++;
		}
		if(obj == P2) //After Player 2 is done setting up their board, game goes into a Chess match
		{
			program.getBoard().flipBoard();
			program.switchToGame();
		}
		if(obj == Bp) {//add pawn
			pieceT = 0;
			if(player == 2) {
				filePath = new String("Black_P.png");
				toAdd = new GImage(filePath);
				toAdd.setBounds(lastX, lastY, 42,42);
			}
			else {
				filePath = new String("White_P.png");
				toAdd = new GImage(filePath);
				toAdd.setBounds(lastX, lastY, 42,42);
			}
			program.add(toAdd);
				
		}
		if(obj == BK) {//add knight
			pieceT = 1;
			if(player == 2) {
				filePath = new String("Black_Knight.png");
				toAdd = new GImage(filePath);
				toAdd.setBounds(lastX, lastY, 42,42);
			}
			else {
				filePath = new String("White_Knight.png");
				toAdd = new GImage(filePath);
				toAdd.setBounds(lastX, lastY, 42,42);
			}
			program.add(toAdd);
		}
		if(obj == Br) {//add rook
			pieceT = 2;
			if(player == 2) {
				filePath = new String("Black_Rook.png");
				toAdd = new GImage(filePath);
				toAdd.setBounds(lastX, lastY, 42,42);
			}
			else {
				filePath = new String("White_Rook.png");
				toAdd = new GImage(filePath);
				toAdd.setBounds(lastX, lastY, 42,42);
			}
			program.add(toAdd);
		}
		if(obj == Bb) {//add bishop
			pieceT = 3;
			if(player == 2) {
				filePath = new String("Black_Bishop.png");
				toAdd = new GImage(filePath);
				toAdd.setBounds(lastX, lastY, 42,42);
			}
			else {
				filePath = new String("White_Bishop.png");
				toAdd = new GImage(filePath);
				toAdd.setBounds(lastX, lastY, 42,42);
			}
			program.add(toAdd);
		}
		if(obj == Bq) {//add queen
			pieceT = 4;
			if(player == 2) {
				filePath = new String("Black_Queen.png");
				toAdd = new GImage(filePath);
				toAdd.setBounds(lastX, lastY, 42,42);
			}
			else {
				filePath = new String("White_Queen.png");
				toAdd = new GImage(filePath);
				toAdd.setBounds(lastX, lastY, 42,42);
			}
			program.add(toAdd);
		}
		if(obj == toAdd) {
				toDrag = program.getElementAt(e.getX(), e.getY());
			    //lastX = e.getX();
			    //lastY = e.getY();
			    //add pawn
			    /*if(pieceT == 0 && player == 2){
			    	piece.addPiece(lastX,lastY,PieceType.PAWN, false);
			    }
			    if(pieceT == 0 && player == 1){
			    	piece.addPiece(lastX,lastY,PieceType.PAWN, true);
			    }
			    //add knight
			    if(pieceT == 1 && player == 2){
			    	piece.addPiece(lastX,lastY,PieceType.KNIGHT, false);
			    }
			    if(pieceT == 1 && player == 1){
			    	piece.addPiece(lastX,lastY,PieceType.KNIGHT, true);
			    }
			    //add rook
			    if(pieceT == 2 && player == 2){
			    	piece.addPiece(lastX,lastY,PieceType.ROOK, false);
			    }
			    if(pieceT == 2 && player == 1){
			    	piece.addPiece(lastX,lastY,PieceType.ROOK, true);
			    }
			    //add bishop
			    if(pieceT == 3 && player == 2){
			    	piece.addPiece(lastX,lastY,PieceType.BISHOP, false);
			    }
			    if(pieceT == 3 && player == 1){
			    	piece.addPiece(lastX,lastY,PieceType.PAWN, true);
			    }
			    //add queen
			    if(pieceT == 4 && player == 2){
			    	piece.addPiece(lastX,lastY,PieceType.QUEEN, false);
			    }
			    if(pieceT == 4 && player == 1){
			    	piece.addPiece(lastX,lastY,PieceType.QUEEN, true);
			    }*/
		}
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
	
	//TODO: Fix up the code so that when it reprint the board it doesn't go back to the original;
	//		try using the addPiece() function in Board.java for this.
	//		Fix up the check to see if the piece moves out of bounds; the movement check is also
	//		not working properly and needs to be fixed.
	//		Fix the the board so you can't drag around the square tiles

    @Override
  	public void mouseReleased(MouseEvent e) {
      	Space space = convertXYToSpace(clickX, clickY);
      	Piece piece;
      	
      	if(space.getRow() != -1 && space.getCol() != -1)
      	{
      		piece = getPieceFromXY(clickX, clickY);
      	}
      	lastX = space.getRow();
      	lastY = space.getCol();
      //add pawn
	    /*if(pieceT == 0 && player == 2){
	    	piece.addPiece(lastX,lastY,PieceType.PAWN, false);
	    }
	    if(pieceT == 0 && player == 1){
	    	piece.addPiece(lastX,lastY,PieceType.PAWN, true);
	    }
	    //add knight
	    if(pieceT == 1 && player == 2){
	    	piece.addPiece(lastX,lastY,PieceType.KNIGHT, false);
	    }
	    if(pieceT == 1 && player == 1){
	    	piece.addPiece(lastX,lastY,PieceType.KNIGHT, true);
	    }
	    //add rook
	    if(pieceT == 2 && player == 2){
	    	piece.addPiece(lastX,lastY,PieceType.ROOK, false);
	    }
	    if(pieceT == 2 && player == 1){
	    	piece.addPiece(lastX,lastY,PieceType.ROOK, true);
	    }
	    //add bishop
	    if(pieceT == 3 && player == 2){
	    	piece.addPiece(lastX,lastY,PieceType.BISHOP, false);
	    }
	    if(pieceT == 3 && player == 1){
	    	piece.addPiece(lastX,lastY,PieceType.PAWN, true);
	    }
	    //add queen
	    if(pieceT == 4 && player == 2){
	    	piece.addPiece(lastX,lastY,PieceType.QUEEN, false);
	    }
	    if(pieceT == 4 && player == 1){
	    	piece.addPiece(lastX,lastY,PieceType.QUEEN, true);
	    }*/
    }
    private Space convertXYToSpace(double x, double y)
    {
    	if(x < BOARD_SHIFT || x > BOARD_SHIFT+(100*8) || y < BOARD_SHIFT || y > BOARD_SHIFT+(100*8))
    	{
    		return new Space(-1, -1);
    	}
    	else
    	{
        	Space space = new Space((int)((y-BOARD_SHIFT)/100), (int)((x-BOARD_SHIFT)/100)); 
        	return space;
    	}
    }
    
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