package starter;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

public class GraphicsGame extends GraphicsProgram{
	private Board board;
	private int clickX, clickY, releaseX, releaseY, lastX, lastY;
	private int x = 8;
	private int y = 8;
	public static final int PROGRAM_WIDTH = 800;
	public static final int PROGRAM_HEIGHT = 600;
	final JFrame parent = new JFrame();
	
	
	public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
	}

	public void printTitleScreen() {
		setSize(PROGRAM_WIDTH/2, PROGRAM_HEIGHT/2);
		GLabel Title = new GLabel("Custom Chess", 200, 100);
		Title.setColor(Color.RED);
		Title.setFont("TimesNewRoman-30");
		add(Title);
		/*GImage Background = new GImage("Background.png", 205, 200);
		Background.sendToBack();
		Background.setSize(300, 300);
		add(Background);*/
		//add background image
		JButton start = new JButton("Start Battle");
		start.setLocation(200, 400);
		JButton rules = new JButton("View Rule");
		start.setLocation(250, 400);
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
	public void printBoard() {
		//print board
		//board = new Board(x,y);
		//board.printB();
	}
	public void printPieceShop() {
		
	}
    public void printWinScreen() {
    	setSize(PROGRAM_WIDTH/2, PROGRAM_HEIGHT/2);
    	GLabel v = new GLabel("VICTORY", 200, 100);
		v.setColor(Color.RED);
		v.setFont("TimesNewRoman-40");
		add(v);
		/*GImage Background = new GImage("Background.png", 205, 200);
		Background.sendToBack();
		Background.setSize(300, 300);
		add(Background);*/
		//add background image
		JButton rematch = new JButton("Rematch");
		rematch.setLocation(200, 400);
		JButton repay = new JButton("Recreate your chess Group on the board");
		repay.setLocation(250, 400);
		JButton Return = new JButton("Recreate your chess Group on the board");
		Return.setLocation(300, 400);
		
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
