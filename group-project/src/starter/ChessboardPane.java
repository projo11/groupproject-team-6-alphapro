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
private Board board;
public static final int SPACE_WIDTH = 40;
public static final int SPACE_HEIGHT = 40;
	
public ChessboardPane(MainApplication app) {
		program = app;
	}
public void printBoard() {
	int[][] pboard= new int[9][9];
	int x = 50;
	int y = 50;
	for (int i = 0; i < pboard.length; i++) {
		if(i < 9) {
		for (int j = 0; j < pboard[i].length; j++) {
			if(i != 0) {
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
				switch(i) {
				case 0: 
					System.out.print("8");
					break;
				case 1: 
					System.out.print("7");
					break;
				case 2: 
					System.out.print("6");
					break;
				case 3: 
					System.out.print("5");
					break;
				case 4: 
					System.out.print("4");
					break;
				case 5: 
					System.out.print("3");
					break;
				case 6: 
					System.out.print("2");
					break;
				case 7: 
					System.out.print("1");
					break;
				case 8: 
					break;
				}
					
			}
	x = 50;
	y = y + 100;
}
		}
		else {
			System.out.print("abcdefgh");
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
	program.add(toAdd);
	}
	}
}


	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		
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