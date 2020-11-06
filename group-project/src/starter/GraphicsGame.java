package starter;
import acm.graphics.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

public class GraphicsGame extends GraphicsProgram implements ActionListener{
	private Board board;
	private int clickX, clickY, releaseX, releaseY, lastX, lastY;
	private GObject toDrag;

	private int x = 8; //Chess board width dimension
	private int y = 8; //Chess board height dimension
	public static final int PROGRAM_WIDTH = 900;
	public static final int PROGRAM_HEIGHT = 900;
	final JFrame parent = new JFrame();
	public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
	}
	public void run() {
		printTitleScreen();
	}
	public void printTitleScreen() {
		final JFrame mainM = new JFrame("Main Menu");
		mainM.setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
		JLabel Title = new JLabel("Custom Chess");
		Title.setForeground(Color.red);
		Title.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
		Title.setBounds(375, -100, 300, 300);
		JPanel firstPanel = new JPanel();
		firstPanel.setBackground(Color.white);
		firstPanel.setLayout(null);
		/*GImage Background = new GImage("Background.png", 205, 200);
		Background.sendToBack();
		Background.setSize(300, 300);
		add(Background); */
		//add background image
		JButton start = new JButton("Start Battle");
		start.setBounds(335, 450, 200, 100);
		start.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){  
	            printPieceShop();
			}
		});
		JButton rules = new JButton("View Rule");
		rules.setBounds(335, 600, 200, 100);
		rules.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
		rules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){  
	            printRules1();
			}
		});
		firstPanel.add(start);
		firstPanel.add(rules);
		firstPanel.add(Title);
		mainM.setContentPane(firstPanel);
		mainM.setVisible(true);
	}
	
	public void printRules1() {
		System.out.println("Basic Rules:");
		System.out.println("The player with the white pieces always moves first.");
		System.out.println("Players take turns alternately moving one piece at a time.");
		System.out.println("Movement is required. If a player�Ls turn is to move, he is not in check but has no legal moves");
		System.out.println("this situation is called �gStalemate�h and it ends the game in a draw.");
		System.out.println("Each type of piece has its own method of movement.");
		System.out.println("A piece may be moved to another position or may capture an opponent�Ls piece,");
		System.out.println("replacing on its square (en passant being the only exception).");
		System.out.println("With the exception of the knight, a piece may not move over or through any of the other pieces.");
		System.out.println("When a king is threatened with capture (but can protect himself or escape), it�Ls called check.");
		System.out.println("If a king is in check, then the player must make a move that eliminates the threat of capture and cannot"); 
		System.out.println("leave the king in check. Checkmate happens when a king is placed in check and there is no legal move to escape."); 
		System.out.println("Checkmate ends the game and the side whose king was checkmated looses.");  
		printRules2();
	}
	public void printRules2() {
		System.out.println("Special Rules:");
		System.out.println("Each player has 35 points to spend on the pieces.");
		System.out.println("Pieces Move - King:");
		System.out.println("King can only move one square in any direction - up, down, to the sides, and diagonally.");
		System.out.println("The king may never move himself into check (where he could be captured).");
		System.out.println("Pieces Move - Queen:");
		System.out.println("Queen can move in any one straight direction - forward, backward, sideways, or diagonally.");
		System.out.println("as far as possible as long as she does not move through any of her own pieces.");
		System.out.println("Pieces Move - Rook:");
		System.out.println("Rook can move any number of vacant squares vertically or horizontally.");
		System.out.println("Pieces Move - Bishop:");
		System.out.println("Bishop can move any number of vacant squares in any diagonal direction.");
		System.out.println("Pieces Move - Knight:");
		System.out.println("Knights can going two squares in one direction, and then one more move at a 90 degree angle");
		System.out.println("Pieces Move - Pawn:");
		System.out.println("Pawns can move forward one square and capture diagonally. It never move or capture backwards");
		System.out.println("Except for their very first move where they can move forward two squares.");
		System.out.println("if a pawn reaches the other side of the board it can become any other chess piece.");
		System.out.println("Thus its theoretically possible having up to nine queens or up to ten rooks, bishops, or knights if all pawns are promoted.");
	}
	public void printBoard(Graphics g) {
		int row;
	    int col;
	    int x,y;
	    for ( row = 0; row < 8; row++ ){
	          for ( col = 0; col < 8; col++){
	               x = col * 40;
	               y = row * 40;
	               if ( (row % 2) == (col % 2) ) {
	                    g.setColor(Color.white);
	               }
	               else {
	                   g.setColor(Color.black);
	                   g.fillRect(x, y, 40, 40);
	               }
	           }
	     }
	}
	public void printWinScreen() {
		JLabel v = new JLabel("Victory");
		final JFrame rules1 = new JFrame("WinScreen");
		JPanel firstPanel = new JPanel();
		firstPanel.setBackground(Color.cyan);
		firstPanel.setLayout(null);
		rules1.setSize(PROGRAM_WIDTH/2, PROGRAM_HEIGHT/2);
		v.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
		v.setForeground(Color.red);
		v.setBounds(155, -100, 300, 300);
		JButton rematch = new JButton("Rematch.");
		rematch.setBounds(135, 80, 100, 50);
		rematch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ //one click, reprint board
	            printBoard(null);
			}
		});
		JButton repay = new JButton("Recreate your chess Group on the board.");
		repay.setBounds(85, 140, 200, 50);
		repay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ //one click, go back to piece shop
	            printPieceShop();
			}
		});
		JButton Return = new JButton("Return to the main Screen.");
		Return.setBounds(85, 200, 200, 50);
		Return.addActionListener(new ActionListener() {//one click, go back to main screen
			public void actionPerformed(ActionEvent e){  
	            printTitleScreen();
			}
		});
		firstPanel.add(rematch);
		firstPanel.add(repay);
		firstPanel.add(Return);
		firstPanel.add(v);
		rules1.setContentPane(firstPanel);
		rules1.setVisible(true);
	}
    public void printPieceShop() {
    	final JFrame shop = new JFrame("Piece Shop");
		shop.setSize(400, 400);
		JPanel firstPanel = new JPanel();
		firstPanel.setBackground(Color.lightGray);
		firstPanel.setLayout(null);
		JLabel tital = new JLabel("Piece Shop");
		tital.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
		tital.setForeground(Color.BLACK);
		tital.setBounds(130, -100, 300, 300);
		JLabel Name = new JLabel("Piece Name");
		Name.setFont(new Font("TimesNewRoman", Font.BOLD, 15));
		Name.setForeground(Color.BLACK);
		Name.setBounds(0, 50, 100, 100);
		JLabel Cost = new JLabel("Piece Cost");
		Cost.setFont(new Font("TimesNewRoman", Font.BOLD, 15));
		Cost.setForeground(Color.BLACK);
		Cost.setBounds(130, 50, 100, 100);
		JLabel pawn = new JLabel("Pawn");
		pawn.setFont(new Font("TimesNewRoman", 15, 15));
		pawn.setForeground(Color.BLACK);
		pawn.setBounds(0, 80, 100, 100);
		JLabel pawnC = new JLabel("1");
		pawnC.setFont(new Font("TimesNewRoman", 15, 15));
		pawnC.setForeground(Color.BLACK);
		pawnC.setBounds(130, 80, 100, 100);
		JButton Bp = new JButton("Buy");
		Bp.setBounds(260, 80, 100, 50);
		//Bp.addActionListener(new ActionListener() {
			//public void actionPerformed(ActionEvent e){ //one click, reprint board
	            //Piece.Piece()
			//}
		//);
		JLabel knight = new JLabel("Knight");
		knight.setFont(new Font("TimesNewRoman", 15, 15));
		knight.setForeground(Color.BLACK);
		knight.setBounds(0, 120, 100, 100);
		JLabel KnightC = new JLabel("3");
		KnightC.setFont(new Font("TimesNewRoman", 15, 15));
		KnightC.setForeground(Color.BLACK);
		KnightC.setBounds(130, 120, 100, 100);
		JButton BK = new JButton("Buy");
		BK.setBounds(260, 120, 100, 50);
		//BK.addActionListener(new ActionListener() {
			//public void actionPerformed(ActionEvent e){ //one click, reprint board
	            //Piece.Piece()
			//}
		//);
		JLabel Bishop = new JLabel("Bishop");
		Bishop.setFont(new Font("TimesNewRoman", 15, 15));
		Bishop.setForeground(Color.BLACK);
		Bishop.setBounds(0, 160, 100, 100);
		JLabel BishopC = new JLabel("3");
		BishopC.setFont(new Font("TimesNewRoman", 15, 15));
		BishopC.setForeground(Color.BLACK);
		BishopC.setBounds(130, 160, 100, 100);
		JButton BI = new JButton("Buy");
		BI.setBounds(260, 160, 100, 50);
		//BI.addActionListener(new ActionListener() {
			//public void actionPerformed(ActionEvent e){ //one click, reprint board
	            //Piece.Piece()
			//}
		//);
		JLabel Rook = new JLabel("Rook");
		Rook.setFont(new Font("TimesNewRoman", 15, 15));
		Rook.setForeground(Color.BLACK);
		Rook.setBounds(0, 200, 100, 100);
		JLabel RookC = new JLabel("4");
		RookC.setFont(new Font("TimesNewRoman", 15, 15));
		RookC.setForeground(Color.BLACK);
		RookC.setBounds(130, 200, 100, 100);
		JButton BR = new JButton("Buy");
		BR.setBounds(260, 160, 100, 50);
		//BR.addActionListener(new ActionListener() {
			//public void actionPerformed(ActionEvent e){ //one click, reprint board
	            //Piece.Piece()
			//}
		//);
		JLabel Queen = new JLabel("Queen");
		Queen.setFont(new Font("TimesNewRoman", 15, 15));
		Queen.setForeground(Color.BLACK);
		Queen.setBounds(0, 240, 100, 100);
		JLabel QueenC = new JLabel("7");
		QueenC.setFont(new Font("TimesNewRoman", 15, 15));
		QueenC.setForeground(Color.BLACK);
		QueenC.setBounds(130, 240, 100, 100);
		JButton BQ = new JButton("Buy");
		BQ.setBounds(260, 160, 100, 50);
		//BQ.addActionListener(new ActionListener() {
			//public void actionPerformed(ActionEvent e){ //one click, reprint board
	            //Piece.Piece()
			//}
		//);
		firstPanel.add(tital);
		firstPanel.add(Name);
		firstPanel.add(Cost);
		firstPanel.add(pawn);
		firstPanel.add(pawnC);
		firstPanel.add(knight);
		firstPanel.add(KnightC);
		firstPanel.add(Bishop);
		firstPanel.add(BishopC);
		firstPanel.add(Rook);
		firstPanel.add(RookC);
		firstPanel.add(Queen);
		firstPanel.add(QueenC);
		shop.setContentPane(firstPanel);
		shop.setVisible(true);
		printBoard(null);
    }
    @Override
    public void mousePressed(MouseEvent e) {
    	toDrag = getElementAt(e.getX(), e.getY());
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
    	if(piece != null)
    	{
    		board.moveNumSpaces(space, calculateRowsMoved(), calculateColsMoved());
    		removeAll();
    		//printBoard(); NOTE: find out what kind of Graphic object to put in parameters
    	}
    	if(piece.getColor() == true) //true = white -> piece is white
    	{
    		if(board.checkmate(piece.getColor()) == true) //if a white piece puts the opponent in checkmate
    		{
    			removeAll();
    			printWinScreen();
    		}
    	}
    	else //false = black -> piece is black
    	{
    		if(board.checkmate(piece.getColor()) == true) //if a black piece puts the opponent in checkmate
    		{
    			removeAll();
    			printWinScreen();
    		}
    	}
    }
    
    //Below are functions used to help with the mouse listener functions
    //Code that converts XY coordinates into a Space
    private Space convertXYToSpace(double x, double y)
    {
    	Space space = new Space((int)(y/spaceHeight()), (int)(x/spaceWidth())); 
    	return space;
    }
    
    //Code that returns the Piece in a given space with the provided XY coordinates
    private Piece getPieceFromXY(double x, double y)
    {
    	Space space = convertXYToSpace(x, y);
    	return board.getPiece(space);
    }
    
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
    	else //If no piece was selected, the amount of rows moved is 0
    	{
    		colsMoved = 0;
    	}
    	return colsMoved;
    }
    
    //Code that returns the width of the spaces
    private double spaceWidth() 
    {
		return PROGRAM_WIDTH/x;
	}

    //Code that returns the height of the spaces
	private double spaceHeight() 
	{
		return PROGRAM_HEIGHT/y;
	}
   
}
