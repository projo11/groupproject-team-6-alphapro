package starter;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class MainMenuPane extends GraphicsPane {
private MainApplication program;

private GButton start = new GButton("Start Battle", 335, 450, 200 , 100);
private GButton rules = new GButton("View Rules",335, 600, 200, 100);
	
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
		program.add(start);
		program.add(rules);
	}

	@Override
	public void hideContents() {
		program.removeAll();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == start)
		{
			program.switchToPieceShop();
		}
		if(obj == rules)
		{
			program.switchToRules();
		}
	}
}
