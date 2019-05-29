package gui;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import logic.FreePieces;
import logic.Game;
import logic.TileArray;

public class MouseInput extends MouseAdapter {

	// create methods to register clicks on game pieces and tiles
	
	private Game game;
	private FreePieces freePieces;
	private Platform platform; 
	private TileArray tiles = new TileArray(game, platform);
	
	public MouseInput(Game game, FreePieces freePieces, Platform platform, TileArray tiles) {
		this.game = game;
		this.freePieces = freePieces; 
		this.platform = platform;
		this.tiles = tiles;
	}  
	
	public static boolean inBounds1(int mx, int my, int ox, int oy, int xLen, int yLen) {
		// static to access it in other classes
		
		if (mx > ox && mx < ox + xLen) {
			if (my > oy && my < oy + yLen) {
				return true; 
			}
			else return false; 
		}
		else return false; 
	}
	
	public static boolean inBounds2(int mx, int my, int cx, int cy, int radius) {
		// for circles
		
		if (((mx - cx) * (mx - cx)) + ((my - cy) * (my - cy)) <=  (radius * radius)) return true;
		else return false; 
	}
		
	public void mouseClicked (MouseEvent e) {		
		int mouseX = e.getX();
		int mouseY = e.getY(); 
	//	System.out.println("X :" + mouseX + " Y: " + mouseY); 
		if ((game.getState() == Game.STATE.P1_Choose || game.getState() == Game.STATE.P2_Choose)
				&& !platform.ready()) freePieces.removeOnClick(mouseX, mouseY);
		else if ((game.getState() == Game.STATE.P1_Placement || game.getState() == Game.STATE.P2_Placement)
				&& platform.ready()) tiles.addOnClick(mouseX, mouseY);
		if (game.getState() == Game.STATE.P1_Wins || game.getState() == Game.STATE.P2_Wins) {
			if (inBounds1 (mouseX, mouseY, 1275, 265, 150, 50)) game.restart();
		}
	//	System.out.println("" + Game.gameState);
		}

} 
	
	

	

