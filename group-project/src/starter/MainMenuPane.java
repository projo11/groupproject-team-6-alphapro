package starter;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import acm.graphics.GImage;
import acm.graphics.GLabel;

public class MainMenuPane extends GraphicsPane implements ActionListener {
private MainApplication program;
	
public MainMenuPane(MainApplication app) {
		program = app;

	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		GImage image = new GImage("Background.png");
		program.add(image,0,0);
		GLabel Title = new GLabel("Custom Chess",335, 450);
		Title.setColor(Color.red);
		Title.setFont("TimesNewRoman-24");
		program.add(Title);
		//add background image
		GButton start = new GButton("Start Battle",335,450,200,100);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){  
	            PieceShopPane p = null;
	            p.showContents();
			}
		});
		GButton rules = new GButton("View Rules",335, 600, 200, 100);
		rules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){  
				RulesPane p = null;
	            p.showContents();
			}
		});
		program.add(start);
		program.add(rules);
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
