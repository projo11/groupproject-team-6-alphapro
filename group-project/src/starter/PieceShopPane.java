package starter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.lang.Math;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
	private final GButton Br = new GButton("Buy", 820, 200, 70, 30);
	private final GButton Bb = new GButton("Buy", 820, 160, 70, 30);
	private final GButton Bq = new GButton("Buy", 820, 240, 70, 30);
	//total cost
	int TotalCost_p1 = 35;
	int TotalCost_p2 = 35;
	//Total cost Label
	private GLabel cost1 = new GLabel("Player1 Total Cost: " + TotalCost_p1, 0, 750);
	private GLabel cost2 = new GLabel("Player2 Total Cost: " + TotalCost_p2, 0, 750);
	
	boolean moveable = true;
	
	//detect player 1 or 2
	private int player = 1;
	
	public static final int SPACE_SIZE = 77;
	public static final int BOARD_SHIFT = 50;
	public static final double SIZE_MOD = 0.75;
	public static final double PAWN_SIZE_MOD = 0.55;
	public static final String LABEL_FONT = "Arial-Bold-22";
	public static final Color LABEL_COLOR = Color.red;
	
	private ArrayList<Piece> pieces = new ArrayList<Piece>();
	
	Point point = null;
	Board p;
	int tf = 1;
	int pieceIcon = 0;
	
	private double lastX = 0;
	private double lastY = 0;
	private GObject toDrag;
	private int pieceT = 0;
	GImage toAdd;
	String filePath;

	public PieceShopPane(MainApplication app) {
			program = app;
			p = program.getBoard();
	}
	public void printBoard() {
		int x = BOARD_SHIFT;
		int y = BOARD_SHIFT;
		pieces = program.getBoard().getPieces();//need update the new pieces
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
		
		for (Piece temp : pieces) {
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
		GLabel toAdd;
		String labelName = new String();
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
	public void ShowTotalCost() {
		cost1 = new GLabel("Player1 Total Cost: " + TotalCost_p1, 0, 750);
		cost1.setFont(new Font("TimesNewRoman", Font.BOLD, 30));
		cost1.setColor(Color.red);
		
		cost2 = new GLabel("Player2 Total Cost: " + TotalCost_p2, 0, 750);
		cost2.setFont(new Font("TimesNewRoman", Font.BOLD, 30));
		cost2.setColor(Color.red);
		
		program.add(cost1);
		if(player == 2) {
			program.remove(cost1);
			program.add(cost2);
		}
	}
	@Override
	public void showContents() {
		TotalCost_p1 = 35;
		TotalCost_p2 = 35;
		// TODO Auto-generated method stub
		//Board
		printBoard();
		//Print Total Cost
		ShowTotalCost();
		GRect bag = new GRect(0, 0, 43,43);
		bag.setColor(Color.red);
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
		program.add(bag);
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
		lastX = lastY = 0;
		pieceIcon = 0;
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == P1) //After Player 1 is done setting up their board, screen changes so that Player 2 now gets to set up the board
		{
			if (player == 1) {
				player = 2;
			}
			else {
				player = 1;
			}
			program.removeAll();
			program.getBoard().flipBoard();
			showContents();
			program.add(P2);
		}
		if(obj == P2) //After Player 2 is done setting up their board, game goes into a Chess match
		{
			program.getBoard().flipBoard();
			program.saveOriginalBoard();
			program.switchToGame();
		}
		if(player == 1) {
			if(TotalCost_p1 <= 0) {
				System.out.println("Run out of Cost.");
				
			}
			else if(TotalCost_p1 > 0){
				if(obj == Bp) {//add pawn
					pieceT = 0;
					filePath = new String("White_P.png");
					toAdd = new GImage(filePath);
					toAdd.setBounds(lastX, lastY, 42,42);
					program.add(toAdd);
				}
				if(obj == BK) {//add knight
					pieceT = 1;
					filePath = new String("White_Knight.png");
					toAdd = new GImage(filePath);
					toAdd.setBounds(lastX, lastY, 42,42);
					program.add(toAdd);
				}
				if(obj == Br) {//add rook
					pieceT = 2;
					filePath = new String("White_Rook.png");
					toAdd = new GImage(filePath);
					toAdd.setBounds(lastX, lastY, 42,42);
					program.add(toAdd);
				}
				if(obj == Bb) {//add bishop
					pieceT = 3;
					filePath = new String("White_Bishop.png");
					toAdd = new GImage(filePath);
					toAdd.setBounds(lastX, lastY, 42,42);
					program.add(toAdd);
				}
				if(obj == Bq) {//add queen
					pieceT = 4;
					filePath = new String("White_Queen.png");
					toAdd = new GImage(filePath);
					toAdd.setBounds(lastX, lastY, 42,42);
					program.add(toAdd);
				}
				if(obj == toAdd) {
					pieceIcon = 1;
					toDrag = program.getElementAt(e.getX(), e.getY());
					point = e.getPoint();
				}
			}
		}
		else if(player == 2) {
			if(TotalCost_p2 <= 0) {
				System.out.println("Run out of Cost.");
			}
			else if(TotalCost_p2 > 0){
				if(obj == Bp) {//add pawn
					
					pieceT = 0;
					filePath = new String("Black_P.png");
					toAdd = new GImage(filePath);
					toAdd.setBounds(lastX, lastY, 42,42);
					program.add(toAdd);
				}
				if(obj == BK) {//add knight
					
					pieceT = 1;
					filePath = new String("Black_Knight.png");
					toAdd = new GImage(filePath);
					toAdd.setBounds(lastX, lastY, 42,42);
					program.add(toAdd);
				}
				if(obj == Br) {//add rook
					
					pieceT = 2;
					filePath = new String("Black_Rook.png");
					toAdd = new GImage(filePath);
					toAdd.setBounds(lastX, lastY, 42,42);
					program.add(toAdd);
				}
				if(obj == Bb) {//add bishop
					pieceT = 3;
					filePath = new String("Black_Bishop.png");
					toAdd = new GImage(filePath);
					toAdd.setBounds(lastX, lastY, 42,42);
					program.add(toAdd);
				}
				if(obj == Bq) {//add queen
					pieceT = 4;
					filePath = new String("Black_Queen.png");
					toAdd = new GImage(filePath);
					toAdd.setBounds(lastX, lastY, 42,42);
					program.add(toAdd);
				}
				if(obj == toAdd) {
					pieceIcon = 1;
					toDrag = program.getElementAt(e.getX(), e.getY());
					point = e.getPoint();
					
						
				}
			}
		}
	}
	

	@Override
    public void mouseDragged(MouseEvent e) {
		if(toDrag != null )
		{
			if(pieceT >= 0 && pieceIcon == 1) {
				moveable = true;
			}
			//toDrag.move(e.getX()-lastX, e.getY()-lastY);
			double x = toDrag.getX();
			double y = toDrag.getY();
			if(point != null && moveable == true) {
				toDrag.setLocation(x + (e.getX()-point.getX()), y + (e.getY()-point.getY()));
				
			}	
	    }
		point = e.getPoint();
		lastX = e.getX();
	    lastY = e.getY();
    }

    @Override
  	public void mouseReleased(MouseEvent e) {
    	double nx = Math.round(((lastY - 60) / 80));
    	double ny = Math.round(((lastX - 60) / 80));
    	int x = (int)nx;
    	int y = (int)ny;
    	if(lastY <= 60 || lastX <= 60 ||x > 7 || y > 7) {
    		if(pieceIcon == 1) {
				toAdd.setLocation(0,0);
			}
    	}
    	else {
	    	if(x > -1 && y > -1) {
	    		if(x < 8 && y < 8) {
	    			if(pieceIcon == 1 && moveable == true) {
	    				addP(x,y,pieceT, player);
	    				pieceT = -1;
	    				moveable = false;
	    			}
	    		}
	    	}
    	}
    }
    
    public void addP(int x, int y, int pieceT, int Color) {
    	//add pawn
    	if(pieceT == 0 && player == 2){
		    int TotalCost = TotalCost_p2 - 1;
		    if(TotalCost >= 0) {
		    	p.addPiece(x, y, PieceType.PAWN, false);
		    	tf = p.a;
		    	if (tf == 0) {
		    		program.remove(toAdd);
		    	}
		    	else if(tf == 1) {
		    			TotalCost_p2 = TotalCost_p2 - 1;
		    	    	//Print Total Cost
		    			program.remove(cost2);
		    			ShowTotalCost();
		    	}
		    }
		    else if(TotalCost < 0 ){
		    	System.out.println("Cannot bought this piece.");
				program.remove(toAdd);
			}
	    	
    	}

	    if(pieceT == 0 && player == 1){
	    	int TotalCost = TotalCost_p1 - 1;
	    	if(TotalCost >= 0) {
	    		p.addPiece(x, y, PieceType.PAWN, true);
	    		tf = p.a;
	    		if (tf == 0) {
	    			program.remove(toAdd);
	    		}
	    		else if(tf == 1) {
	    				TotalCost_p1 = TotalCost_p1 - 1;
	    	    		//Print Total Cost
	    				program.remove(cost1);
	    				ShowTotalCost();
	    		}
	    	}
	    	else if(TotalCost < 0 ){
	    		System.out.println("Cannot bought this piece.");
				program.remove(toAdd);
			}
	    }
	    //add knight
	    if(pieceT == 1 && player == 2){
	    	int TotalCost = TotalCost_p2 - 3;
	    	if(TotalCost >= 0) {
	    		p.addPiece(x, y, PieceType.KNIGHT, false);
	    		tf = p.a;
	    		if (tf == 0) {
	    			program.remove(toAdd);
	    		}
	    		else if(tf == 1) {
	    				TotalCost_p2 = TotalCost_p2 - 3;
	    	    		//Print Total Cost
	    				program.remove(cost2);
	    				ShowTotalCost();
	    		}
	    	}
	    	else if(TotalCost < 0 ){
	    		System.out.println("Cannot bought this piece.");
				program.remove(toAdd);
			}
	    }
	    if(pieceT == 1 && player == 1){
	    	int TotalCost = TotalCost_p1 - 3;
	    	if(TotalCost >= 0) {
	    		p.addPiece(x, y, PieceType.KNIGHT, true);
	    		tf = p.a;
	    		if (tf == 0) {
	    			program.remove(toAdd);
	    		}
	    		else if(tf == 1) {
	    				TotalCost_p1 = TotalCost_p1 - 3;
	    	    		//Print Total Cost
	    				program.remove(cost1);
	    				ShowTotalCost();
	    		}
	    	}
	    	else if(TotalCost < 0 ){
	    		System.out.println("Cannot bought this piece.");
				program.remove(toAdd);
			}
	    }
	    //add rook
	    if(pieceT == 2 && player == 2){
	    	int TotalCost = TotalCost_p2 - 4;
	    	if(TotalCost >= 0) {
	    		p.addPiece(x, y, PieceType.ROOK, false);
	    		tf = p.a;
	    		if (tf == 0) {
	    			program.remove(toAdd);
	    		}
	    		else if(tf == 1) {
	    				TotalCost_p2 = TotalCost_p2 - 4;
	    	    		//Print Total Cost
	    				program.remove(cost2);
	    				ShowTotalCost();
	    		}
	    	}
	    	else if(TotalCost < 0 ){
	    		System.out.println("Cannot bought this piece.");
				program.remove(toAdd);
			}
	    }
	    if(pieceT == 2 && player == 1){
	    	int TotalCost = TotalCost_p1 - 4;
	    	if(TotalCost >= 0) {
	    		p.addPiece(x, y, PieceType.ROOK, true);
	    		tf = p.a;
	    		if (tf == 0) {
	    			program.remove(toAdd);
	    		}
	    		else if(tf == 1) {
	    				TotalCost_p1 = TotalCost_p1 - 4;
	    	    		//Print Total Cost
	    				program.remove(cost1);
	    				ShowTotalCost();
	    		}
	    	}
	    	else if(TotalCost < 0 ){
	    		System.out.println("Cannot bought this piece.");
				program.remove(toAdd);
			}
	    }
	    //add bishop
	    if(pieceT == 3 && player == 2){
	    	int TotalCost = TotalCost_p2 - 3;
	    	if(TotalCost >= 0) {
	    		p.addPiece(x, y, PieceType.BISHOP, false);
	    		tf = p.a;
	    		if (tf == 0) {
	    			program.remove(toAdd);
	    		}
	    		else if(tf == 1) {
	    				TotalCost_p2 = TotalCost_p2 - 3;
	    	    		//Print Total Cost
	    				program.remove(cost2);
	    				ShowTotalCost();
	    		}
	    	}
	    	else if(TotalCost < 0 ){
	    		System.out.println("Cannot bought this piece.");
				program.remove(toAdd);
			}
	    }
	    if(pieceT == 3 && player == 1){
	    	int TotalCost = TotalCost_p1 - 3;
	    	if(TotalCost >= 0) {
	    		p.addPiece(x, y, PieceType.BISHOP, true);
	    		tf = p.a;
	    		if (tf == 0) {
	    			program.remove(toAdd);
	    		}
	    		else if(tf == 1) {
	    				TotalCost_p1 = TotalCost_p1 - 3;
	    	    		//Print Total Cost
	    				program.remove(cost1);
	    				ShowTotalCost();
	    		}
	    	}
	    	else if(TotalCost < 0 ){
	    		System.out.println("Cannot bought this piece.");
				program.remove(toAdd);
			}
	    }
	    //add queen
	    if(pieceT == 4 && player == 2){
	    	int TotalCost = TotalCost_p2 - 7;
	    	if(TotalCost >= 0) {
	    		p.addPiece(x, y, PieceType.QUEEN, false);
	    		tf = p.a;
	    		if (tf == 0) {
	    			program.remove(toAdd);
	    		}
	    		else if(tf == 1) {
	    			TotalCost_p2 = TotalCost_p2 - 7;
	    	    	//Print Total Cost
	    			program.remove(cost2);
	    			ShowTotalCost();
	    			}
	    		}
	    	else if(TotalCost < 0 ){
	    		System.out.println("Cannot bought this piece.");
				program.remove(toAdd);
			}
	    	
	    }
	    if(pieceT == 4 && player == 1){
	    	int TotalCost = TotalCost_p1 - 7;
	    	if(TotalCost >= 0) {
	    		p.addPiece(x, y, PieceType.QUEEN, true);
	    		tf = p.a;
	    		if (tf == 0) {
	    			program.remove(toAdd);
	    		}
	    		else if(tf == 1) {
	    			TotalCost_p1 = TotalCost_p1 - 7;
	    	    	//Print Total Cost
	    			program.remove(cost1);
	    			ShowTotalCost();
	    			}
	    		}
	    	else if(TotalCost < 0 ){
	    		System.out.println("Cannot bought this piece.");
				program.remove(toAdd);
			}
	    }
	}
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}