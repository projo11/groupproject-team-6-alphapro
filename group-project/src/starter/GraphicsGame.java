package starter;
import acm.graphics.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

public class GraphicsGame extends GraphicsProgram implements ActionListener{
	private Board board;
	private int clickX, clickY, lastX, lastY;
	private GObject toDrag;
	private boolean isPlayingMatch; //Boolean variable to indicate if a chess match is being played
	private int x = 8; //Chess board width dimension
	private int y = 8; //Chess board height dimension
	public static final int SPACE_WIDTH = 40;
	public static final int SPACE_HEIGHT = 40;
	public static final int PROGRAM_WIDTH = 900;
	public static final int PROGRAM_HEIGHT = 900;
	public static final int BOARD_SHIFT = 100;
	final JFrame parent = new JFrame();
	public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
	}
	public void run() {
		printTitleScreen();
		addMouseListeners();
		printBoard();
	}
	public void printTitleScreen() {
		isPlayingMatch = false; //NOTE: Added so that the code knows a match is currently not being played
		final JFrame mainM = new JFrame("Main Menu");
		mainM.setSize(900, 900);
		JLabel Title = new JLabel("Custom Chess");
		Title.setForeground(Color.red);
		Title.setFont(new Font("TimesNewRoman", Font.BOLD, 30));
		Title.setBounds(345, -100, 300, 300);
		JPanel firstPanel = new JPanel();
		firstPanel.setBackground(Color.white);
		firstPanel.setLayout(null);
		
		ImageIcon Background = new ImageIcon("Background.png");
		JLabel label = new JLabel(Background);
		label.setBounds(0, 0, 900, 900);
		//add background image
		JButton start = new JButton("Start Battle");
		start.setBounds(335, 450, 200, 100);
		start.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){  
				mainM.setVisible(false);
	            printPieceShop();
			}
		});
		JButton rules = new JButton("View Rules");
		rules.setBounds(335, 600, 200, 100);
		rules.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
		rules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){  
	            printRules();
			}
		});
		firstPanel.add(Title);
		firstPanel.add(start);
		firstPanel.add(rules);
		firstPanel.add(label);
		mainM.setContentPane(firstPanel);
		mainM.setVisible(true);
	}
	
	public void printRules() {
		final JFrame rules = new JFrame("Rules1");
		rules.setSize(800, 800);
		JPanel firstPanel = new JPanel();
		firstPanel.setBackground(Color.white);
		firstPanel.setLayout(null);
		JLabel Title1 = new JLabel("Basic Rules:");
		Title1.setForeground(Color.red);
		Title1.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
		Title1.setBounds(0, -140, 300, 300);
		JLabel Text1 = new JLabel("The player with the white pieces always moves first.");
		Text1.setForeground(Color.black);
		Text1.setFont(new Font("TimesNewRoman", 15, 15));
		Text1.setBounds(0, -120, 500, 300);
		JLabel Text2 = new JLabel("Players take turns alternately moving one piece at a time.");
		Text2.setForeground(Color.black);
		Text2.setFont(new Font("TimesNewRoman", 15, 15));
		Text2.setBounds(0, -100, 500, 300);
		JLabel Text3 = new JLabel("Movement is required. If a player's turn is to move, he is not in check but has no legal moves");
		Text3.setForeground(Color.black);
		Text3.setFont(new Font("TimesNewRoman", 15, 15));
		Text3.setBounds(0, -80, 700, 300);
		JLabel Text4 = new JLabel("this situation is called 'Stalemate' and it ends the game in a draw.");
		Text4.setForeground(Color.black);
		Text4.setFont(new Font("TimesNewRoman", 15, 15));
		Text4.setBounds(0, -60, 500, 300);
		JLabel Text5 = new JLabel("Each type of piece has its own method of movement.");
		Text5.setForeground(Color.black);
		Text5.setFont(new Font("TimesNewRoman", 15, 15));
		Text5.setBounds(0, -40, 500, 300);
		JLabel Text6 = new JLabel("A piece may be moved to another position or may capture an opponent's piece,");
		Text6.setForeground(Color.black);
		Text6.setFont(new Font("TimesNewRoman", 15, 15));
		Text6.setBounds(0, -20, 700, 300);
		JLabel Text7 = new JLabel("replacing on its square (en passant being the only exception).");
		Text7.setForeground(Color.black);
		Text7.setFont(new Font("TimesNewRoman", 15, 15));
		Text7.setBounds(0, 0, 500, 300);
		JLabel Text8 = new JLabel("With the exception of the knight, a piece may not move over or through any of the other pieces.");
		Text8.setForeground(Color.black);
		Text8.setFont(new Font("TimesNewRoman", 15, 15));
		Text8.setBounds(0, 20, 700, 300);
		JLabel Text9 = new JLabel("When a king is threatened with capture (but can protect himself or escape), it's called check.");
		Text9.setForeground(Color.black);
		Text9.setFont(new Font("TimesNewRoman", 15, 15));
		Text9.setBounds(0, 40, 700, 300);
		JLabel Text10 = new JLabel("If a king is in check, then the player must make a move that eliminates the threat of capture and cannot"); 
		Text10.setForeground(Color.black);
		Text10.setFont(new Font("TimesNewRoman", 15, 15));
		Text10.setBounds(0, 60, 700, 300);
		JLabel Text11 = new JLabel("leave the king in check. Checkmate happens when a king is placed in check and there is no legal move to escape."); 
		Text11.setForeground(Color.black);
		Text11.setFont(new Font("TimesNewRoman", 15, 15));
		Text11.setBounds(0, 80, 800, 300);
		JLabel Text12 = new JLabel("Checkmate ends the game and the side whose king was checkmated looses."); 
		Text12.setForeground(Color.black);
		Text12.setFont(new Font("TimesNewRoman", 15, 15));
		Text12.setBounds(0, 100, 700, 300);
		
		JLabel Title2 = new JLabel("Special/Move Rules:");
		Title2.setForeground(Color.red);
		Title2.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
		Title2.setBounds(0, 140, 300, 300);
		JLabel Text13 = new JLabel("Each player has 35 points to spend on the pieces.");
		Text13.setForeground(Color.black);
		Text13.setFont(new Font("TimesNewRoman", 15, 15));
		Text13.setBounds(0, 160, 500, 300);
		JLabel Text14 = new JLabel("Pieces Move - King:");
		Text14.setForeground(Color.red);
		Text14.setFont(new Font("TimesNewRoman", 15, 15));
		Text14.setBounds(0, 190, 500, 300);
		JLabel Text15 = new JLabel("King can only move one square in any direction - up, down, to the sides, and diagonally.");
		Text15.setForeground(Color.black);
		Text15.setFont(new Font("TimesNewRoman", 15, 15));
		Text15.setBounds(0, 210, 700, 300);
		JLabel Text16 = new JLabel("The king may never move himself into check (where he could be captured).");
		Text16.setForeground(Color.black);
		Text16.setFont(new Font("TimesNewRoman", 15, 15));
		Text16.setBounds(0, 230, 700, 300);
		JLabel Text17 = new JLabel("Pieces Move - Queen:");
		Text17.setForeground(Color.red);
		Text17.setFont(new Font("TimesNewRoman", 15, 15));
		Text17.setBounds(0, 260, 500, 300);
		JLabel Text18 = new JLabel("Queen can move in any one straight direction - forward, backward, sideways, or diagonally.");
		Text18.setForeground(Color.black);
		Text18.setFont(new Font("TimesNewRoman", 15, 15));
		Text18.setBounds(0, 280, 700, 300);
		JLabel Text19 = new JLabel("as far as possible as long as she does not move through any of her own pieces.");
		Text19.setForeground(Color.black);
		Text19.setFont(new Font("TimesNewRoman", 15, 15));
		Text19.setBounds(0, 300, 700, 300);
		JLabel Text20 = new JLabel("Pieces Move - Rook:");
		Text20.setForeground(Color.red);
		Text20.setFont(new Font("TimesNewRoman", 15, 15));
		Text20.setBounds(0, 330, 500, 300);
		JLabel Text21 = new JLabel("Rook can move any number of vacant squares vertically or horizontally.");
		Text21.setForeground(Color.black);
		Text21.setFont(new Font("TimesNewRoman", 15, 15));
		Text21.setBounds(0, 350, 700, 300);
		JLabel Text22 = new JLabel("Pieces Move - Bishop:");
		Text22.setForeground(Color.red);
		Text22.setFont(new Font("TimesNewRoman", 15, 15));
		Text22.setBounds(0, 380, 500, 300);
		JLabel Text23 = new JLabel("Bishop can move any number of vacant squares in any diagonal direction.");
		Text23.setForeground(Color.black);
		Text23.setFont(new Font("TimesNewRoman", 15, 15));
		Text23.setBounds(0, 400, 700, 300);
		JLabel Text24 = new JLabel("Pieces Move - Knight:");
		Text24.setForeground(Color.red);
		Text24.setFont(new Font("TimesNewRoman", 15, 15));
		Text24.setBounds(0, 430, 500, 300);
		JLabel Text25 = new JLabel("Knights can going two squares in one direction, and then one more move at a 90 degree angle");
		Text25.setForeground(Color.black);
		Text25.setFont(new Font("TimesNewRoman", 15, 15));
		Text25.setBounds(0, 450, 700, 300);
		JLabel Text26 = new JLabel("Pieces Move - Pawn:");
		Text26.setForeground(Color.red);
		Text26.setFont(new Font("TimesNewRoman", 15, 15));
		Text26.setBounds(0, 480, 500, 300);
		JLabel Text27 = new JLabel("Pawns can move forward one square and capture diagonally. It never move or capture backwards");
		Text27.setForeground(Color.black);
		Text27.setFont(new Font("TimesNewRoman", 15, 15));
		Text27.setBounds(0, 500, 700, 300);
		JLabel Text28 = new JLabel("Except for their very first move where they can move forward two squares.");
		Text28.setForeground(Color.black);
		Text28.setFont(new Font("TimesNewRoman", 15, 15));
		Text28.setBounds(0, 530, 700, 300);
		JLabel Text29 = new JLabel("if a pawn reaches the other side of the board it can become any other chess piece.");
		Text29.setForeground(Color.black);
		Text29.setFont(new Font("TimesNewRoman", 15, 15));
		Text29.setBounds(0, 550, 700, 300);
		JLabel Text30 = new JLabel("Thus its theoretically possible having up to nine queens or up to ten rooks, bishops, or knights if all pawns are promoted.");
		Text30.setForeground(Color.black);
		Text30.setFont(new Font("TimesNewRoman", 15, 15));
		Text30.setBounds(0, 570, 800, 300);
		firstPanel.add(Title1);
		firstPanel.add(Title2);
		firstPanel.add(Text1);
		firstPanel.add(Text2);
		firstPanel.add(Text3);
		firstPanel.add(Text4);
		firstPanel.add(Text5);
		firstPanel.add(Text6);
		firstPanel.add(Text7);
		firstPanel.add(Text8);
		firstPanel.add(Text9);
		firstPanel.add(Text10);
		firstPanel.add(Text11);
		firstPanel.add(Text12);
		firstPanel.add(Text13);
		firstPanel.add(Text14);
		firstPanel.add(Text15);
		firstPanel.add(Text16);
		firstPanel.add(Text17);
		firstPanel.add(Text18);
		firstPanel.add(Text19);
		firstPanel.add(Text20);
		firstPanel.add(Text21);
		firstPanel.add(Text22);
		firstPanel.add(Text23);
		firstPanel.add(Text24);
		firstPanel.add(Text25);
		firstPanel.add(Text26);
		firstPanel.add(Text27);
		firstPanel.add(Text28);
		firstPanel.add(Text29);
		firstPanel.add(Text30);
		rules.setContentPane(firstPanel);
		rules.setVisible(true);
	}
	public void printBoard(Graphics g) {
		int row;
	    int col;
	    int x,y;
	    for ( row = 0; row < 8; row++ ){
	          for ( col = 0; col < 8; col++){
	               x = BOARD_SHIFT + col * 40;
	               y = BOARD_SHIFT + row * 40;
	               if ( (row % 2) == (col % 2) ) {
	                    g.setColor(Color.white);
	               }
	               else {
	                   g.setColor(Color.black);
	                   g.fillRect(x, y, 40, 40);
	               }
	           }
	     }
	    for (Piece temp : board.getPieces()) {
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
	    	add(toAdd);
	    }
	}
	public void printWinScreen() {
		isPlayingMatch = false; //NOTE: Added so that the code knows the game is over
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
    	//TODO: Figure out where to make it so that isPlayingMatch becomes true
    	
    	if(isPlayingMatch == true) //If the players are currently playing a match, mouseReleased() will behave like the below
    	{
    		if(piece != null)
        	{
        		board.moveNumSpaces(space, calculateRowsMoved(), calculateColsMoved());
        		removeAll();
        		printBoard(null); //TODO: Fix this!
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
    	else //If the players are currently just looking at the menus(i.e. title screen, rules, win screen)/setting up the board, mouseReleased() will behave like below
    	{
    		toDrag = null;
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
    	else //If no piece was selected, the amount of cols moved is 0
    	{
    		colsMoved = 0;
    	}
    	return colsMoved;
    }

}
