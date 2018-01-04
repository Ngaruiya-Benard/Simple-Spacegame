package Hw2;


/**
 * pauseButton is a class that extends JButton and it executes the pause and play functions
 * Benard Ngaruiya comp 86 
 * Benard.Ngaruiya@tufts.edu
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class pauseButton extends JButton implements ActionListener{
	protected boolean playing;//to affect playing and pausing of the app
	private Map canvas;
	public pauseButton(String title, Map canvas){
		this.canvas = canvas;
		setText(title);
		addActionListener(this);
		playing = true;
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("We will Pause the simulation");
		if (playing  == true){
			canvas.setZoom(true);
			playing = false;
		}
		else {
			playing = true;
			canvas.setZoom(false);
		}
	}

	public boolean isPlaying() {
		return playing;
	}
	public void setPlaying(boolean set){
		playing = set;
	}

}
