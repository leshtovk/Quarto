
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5232343621325964612L;
	
	Game game;
	
	
public Window(int width, int height, String title, Game game) {
		
		// create the game window:
		JFrame frame = new JFrame(title); 
		
		// set dimensions:
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		// make sure X button works:
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// take away the option to resize the window: 
		frame.setResizable(false);
		
		// window starts in the middle of the screen: 
		frame.setLocationRelativeTo(null);
		
		// add the game class into the frame:
		frame.add(game); 
		
		// set the frame to visible: 
		frame.setVisible(true);
		
		// run the start method in the Game class:
		game.start(); 
	}

}