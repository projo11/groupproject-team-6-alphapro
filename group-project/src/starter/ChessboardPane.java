package starter;

import java.awt.Color;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import acm.graphics.GImage;
import acm.graphics.GRect;

public class ChessboardPane extends GraphicsPane implements ActionListener {
private MainApplication program;
public static final int SPACE_WIDTH = 75;
public static final int SPACE_HEIGHT = 75;
public static final int BOARD_SHIFT = 100;
	
public ChessboardPane(MainApplication app) {
		program = app;
	}
public void printBoard() {
	int[][] pboard= new int[8][8];
	int x = 50;
	int y = 50;
	for (int i = 0; i < pboard.length; i++) {//print the board
		if(i < 7) {
		for (int j = 0; j < pboard[i].length; j++) {
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
		x = 50; 
		y = y + 100;
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
			GLabel eight = new GLabel("8", 50, 50);
			program.add(eight);
			GLabel seven = new GLabel("7", 50, 150);
			program.add(seven);
			GLabel six = new GLabel("6", 50, 250);
			program.add(six);
			GLabel five = new GLabel("5", 50, 350);
			program.add(five);
			GLabel four = new GLabel("4", 50, 450);
			program.add(four);
			GLabel three = new GLabel("3", 50, 550);
			program.add(three);
			GLabel two = new GLabel("2", 50, 650);
			program.add(two);
			GLabel one = new GLabel("1", 50, 750);
			program.add(one);
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
	if (temp.getType() == PieceType.PAWN) {
		toAdd = new GImage(filePath, temp.getCol()*SPACE_WIDTH + BOARD_SHIFT + 15, temp.getRow()*SPACE_HEIGHT + BOARD_SHIFT + 15);
		toAdd.setSize(SPACE_WIDTH-30, SPACE_HEIGHT-30);
	}
	else {
		toAdd = new GImage(filePath, temp.getCol()*SPACE_WIDTH + BOARD_SHIFT + 10, temp.getRow()*SPACE_HEIGHT + BOARD_SHIFT + 10);
		toAdd.setSize(SPACE_WIDTH-20, SPACE_HEIGHT-20);
	}
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}