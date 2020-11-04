package starter;
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

	private int x = 8;
	private int y = 8;
	public static final int PROGRAM_WIDTH = 900;
	public static final int PROGRAM_HEIGHT = 900;
	final JFrame parent = new JFrame();
	public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
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
		add(Background);*/
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
		System.out.println("Movement is required. If a playerÅLs turn is to move, he is not in check but has no legal moves");
		System.out.println("this situation is called ÅgStalemateÅh and it ends the game in a draw.");
		System.out.println("Each type of piece has its own method of movement.");
		System.out.println("A piece may be moved to another position or may capture an opponentÅLs piece,");
		System.out.println("replacing on its square (en passant being the only exception).");
		System.out.println("With the exception of the knight, a piece may not move over or through any of the other pieces.");
		System.out.println("When a king is threatened with capture (but can protect himself or escape), itÅLs called check.");
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
		System.out.println("Thus itsÅL theoretically possible having up to nine queens or up to ten rooks, bishops, or knights if all pawns are promoted.");
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
		shop.setSize(400, 600);
		JPanel firstPanel = new JPanel();
		firstPanel.setBackground(Color.lightGray);
		JLabel tital = new JLabel("Piece Shop");
		tital.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
		tital.setForeground(Color.BLACK);
		tital.setBounds(100, -100, 300, 300);
		JLabel Name = new JLabel("Piece Name");
		Name.setFont(new Font("TimesNewRoman", 15, 15));
		Name.setForeground(Color.BLACK);
		Name.setBounds(0, 600, 300, 300);
		JLabel Cost = new JLabel("Piece Cost");
		Cost.setFont(new Font("TimesNewRoman", 15, 15));
		Cost.setForeground(Color.BLACK);
		Cost.setBounds(200, 300, 300, 300);
		firstPanel.add(tital);
		firstPanel.add(Name);
		//firstPanel.add(Cost);
		shop.setContentPane(firstPanel);
		shop.setVisible(true);
    }
    @Override
    public void mousePressed(MouseEvent e) {
    	
	}
    @Override
    public void mouseDragged(MouseEvent e) {
    
    }
    @Override
	public void mouseReleased(MouseEvent e) {
    	
    }
   
}
