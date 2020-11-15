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
public static final int SPACE_WIDTH = 40;
public static final int SPACE_HEIGHT = 40;
	
public ChessboardPane(MainApplication app) {
		program = app;
	}
public void printBoard() {
	int[][] pboard= new int[9][9];
	int x = 50;
	int y = 50;
	for (int i = 0; i < pboard.length; i++) {//print the board
		if(i < 9) {
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
	x = 50;
	y = y + 100;
}
		}
		else {//print the alphabet label at the bottom of the board
			GLabel hlabel = new GLabel("H", 150, 850);
			program.add(hlabel);
			GLabel glabel = new GLabel("G", 250, 850);
			program.add(glabel);
			GLabel flabel = new GLabel("F", 350, 850);
			program.add(flabel);
			GLabel elabel = new GLabel("E", 450, 850);
			program.add(elabel);
			GLabel dlabel = new GLabel("D", 550, 850);
			program.add(dlabel);
			GLabel clabel = new GLabel("C", 650, 850);
			program.add(clabel);
			GLabel blabel = new GLabel("B", 750, 850);
			program.add(blabel);
			GLabel alabel = new GLabel("A", 850, 850);
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}