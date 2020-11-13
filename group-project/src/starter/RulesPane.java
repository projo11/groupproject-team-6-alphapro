package starter;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import acm.graphics.GLabel;

public class RulesPane extends GraphicsPane implements ActionListener {
private MainApplication program;
	
public RulesPane(MainApplication app) {
		program = app;
	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		GLabel Title1 = new GLabel("Basic Rules:", 0, 0);
		Title1.setColor(Color.red);
		Title1.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
		GLabel Text1 = new GLabel("The player with the white pieces always moves first.",0, 20);
		Text1.setColor(Color.black);
		Text1.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text2 = new GLabel("Players take turns alternately moving one piece at a time.",0 , 40);
		Text2.setColor(Color.black);
		Text2.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text3 = new GLabel("Movement is required. If a player's turn is to move, he is not in check but has no legal moves", 0, 60);
		Text3.setColor(Color.black);
		Text3.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text4 = new GLabel("this situation is called 'Stalemate' and it ends the game in a draw.",0,80);
		Text4.setColor(Color.black);
		Text4.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text5 = new GLabel("Each type of piece has its own method of movement.",0,100);
		Text5.setColor(Color.black);
		Text5.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text6 = new GLabel("A piece may be moved to another position or may capture an opponent's piece,",0,120);
		Text6.setColor(Color.black);
		Text6.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text7 = new GLabel("replacing on its square (en passant being the only exception).",0,140);
		Text7.setColor(Color.black);
		Text7.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text8 = new GLabel("With the exception of the knight, a piece may not move over or through any of the other pieces.",0,160);
		Text8.setColor(Color.black);
		Text8.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text9 = new GLabel("When a king is threatened with capture (but can protect himself or escape), it's called check.",0,180);
		Text9.setColor(Color.black);
		Text9.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text10 = new GLabel("If a king is in check, then the player must make a move that eliminates the threat of capture and cannot",0,200); 
		Text10.setColor(Color.black);
		Text10.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text11 = new GLabel("leave the king in check. Checkmate happens when a king is placed in check and there is no legal move to escape.",0,220); 
		Text11.setColor(Color.black);
		Text11.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text12 = new GLabel("Checkmate ends the game and the side whose king was checkmated looses.",0,240); 
		Text12.setColor(Color.black);
		Text12.setFont(new Font("TimesNewRoman", 15, 15));
		
		GLabel Title2 = new GLabel("Special/Move Rules:",0,280);
		Title2.setColor(Color.red);
		Title2.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
		GLabel Text13 = new GLabel("Each player has 35 points to spend on the pieces.",0,300);
		Text13.setColor(Color.black);
		Text13.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text14 = new GLabel("Pieces Move - King:",0,320);
		Text14.setColor(Color.red);
		Text14.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text15 = new GLabel("King can only move one square in any direction - up, down, to the sides, and diagonally.",0,340);
		Text15.setColor(Color.black);
		Text15.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text16 = new GLabel("The king may never move himself into check (where he could be captured).",0,360);
		Text16.setColor(Color.black);
		Text16.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text17 = new GLabel("Pieces Move - Queen:",0,380);
		Text17.setColor(Color.red);
		Text17.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text18 = new GLabel("Queen can move in any one straight direction - forward, backward, sideways, or diagonally.",0,400);
		Text18.setColor(Color.black);
		Text18.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text19 = new GLabel("as far as possible as long as she does not move through any of her own pieces.",0,420);
		Text19.setColor(Color.black);
		Text19.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text20 = new GLabel("Pieces Move - Rook:",0,440);
		Text20.setColor(Color.red);
		Text20.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text21 = new GLabel("Rook can move any number of vacant squares vertically or horizontally.",0,460);
		Text21.setColor(Color.black);
		Text21.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text22 = new GLabel("Pieces Move - Bishop:",0,480);
		Text22.setColor(Color.red);
		Text22.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text23 = new GLabel("Bishop can move any number of vacant squares in any diagonal direction.",0,500);
		Text23.setColor(Color.black);
		Text23.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text24 = new GLabel("Pieces Move - Knight:",0,520);
		Text24.setColor(Color.red);
		Text24.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text25 = new GLabel("Knights can going two squares in one direction, and then one more move at a 90 degree angle",0,540);
		Text25.setColor(Color.black);
		Text25.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text26 = new GLabel("Pieces Move - Pawn:",0,560);
		Text26.setColor(Color.red);
		Text26.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text27 = new GLabel("Pawns can move forward one square and capture diagonally. It never move or capture backwards",0,580);
		Text27.setColor(Color.black);
		Text27.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text28 = new GLabel("Except for their very first move where they can move forward two squares.",0,600);
		Text28.setColor(Color.black);
		Text28.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text29 = new GLabel("if a pawn reaches the other side of the board it can become any other chess piece.",0,620);
		Text29.setColor(Color.black);
		Text29.setFont(new Font("TimesNewRoman", 15, 15));
		GLabel Text30 = new GLabel("Thus its theoretically possible having up to nine queens or up to ten rooks, bishops, or knights if all pawns are promoted.",0,640);
		Text30.setColor(Color.black);
		Text30.setFont(new Font("TimesNewRoman", 15, 15));
		GButton exit = new GButton("EXIT",800,800,200,100);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){  
	           MainMenuPane m = null;
	            m.showContents();
			}
		});
		program.add(Title1);
		program.add(Title2);
		program.add(Text1);
		program.add(Text2);
		program.add(Text3);
		program.add(Text4);
		program.add(Text5);
		program.add(Text6);
		program.add(Text7);
		program.add(Text8);
		program.add(Text9);
		program.add(Text10);
		program.add(Text11);
		program.add(Text12);
		program.add(Text13);
		program.add(Text14);
		program.add(Text15);
		program.add(Text16);
		program.add(Text17);
		program.add(Text18);
		program.add(Text19);
		program.add(Text20);
		program.add(Text21);
		program.add(Text22);
		program.add(Text23);
		program.add(Text24);
		program.add(Text25);
		program.add(Text26);
		program.add(Text27);
		program.add(Text28);
		program.add(Text29);
		program.add(Text30);
		program.add(exit);
	}

	@Override
	public void hideContents() {
		program.removeAll();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}