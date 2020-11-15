package starter;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import acm.graphics.GLabel;

public class VictoryPane extends GraphicsPane implements ActionListener {
private MainApplication program;
	
public VictoryPane(MainApplication app) {
		program = app;
	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.setSize(450, 450);
		GLabel v = new GLabel("Victory", 145, 100);
		v.setFont(new Font("TimesNewRoman", Font.BOLD, 30));
		v.setColor(Color.red);
		GButton rematch = new GButton("Rematch", 95, 180, 200, 50);
		rematch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ //one click, reprint board
	            //printBoard();
			}
		});
		GButton repay = new GButton("Recreate your chess Group on the board", 95, 260, 200, 50);
		repay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ //one click, go back to piece shop
	            PieceShopPane p = null;
				p.showContents();
			}
		});
		GButton Return = new GButton("Return to the main Screen",95, 340, 200, 50);
		Return.addActionListener(new ActionListener() {//one click, go back to main screen
			public void actionPerformed(ActionEvent e){  
				MainMenuPane m = null;
	            m.showContents();
			}
		});
		program.add(rematch);
	    program.add(repay);
		program.add(Return);
		program.add(v);
		
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