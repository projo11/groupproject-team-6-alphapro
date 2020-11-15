package starter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import acm.graphics.GObject;

public class PieceShopPane extends GraphicsPane implements ActionListener {
private MainApplication program;

//Location and size of buttons are currently just a placeholder; fix later
private GButton P1 = new GButton("P1 Done", 335, 450, 200 , 100);
private GButton P2 = new GButton("P2 Done", 335, 450, 200 , 100);
	
public PieceShopPane(MainApplication app) {
		program = app;
	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(P1);
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
			program.remove(P1);
			program.add(P2);
		}
		if(obj == P2) //After Player 2 is done setting up their board, game goes into a Chess match
		{
			program.switchToGame();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}