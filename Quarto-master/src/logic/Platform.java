package logic;

import java.awt.Graphics;
import java.util.LinkedList;

public class Platform {
	
	protected int plx = 1275, ply = 175;

	private LinkedList<Piece> pieces;
	
	public Platform(LinkedList<Piece> pieces) {
		this.pieces = pieces;
	}
	
	public void stage(Piece p) {
		if (pieces.size() == 0) {
			
			Piece.SIZE pSize = p.getSize();
			Piece.SHAPE pShape = p.getShape();
			Piece.COLOR pColor = p.getColor();
			Piece.LOOP pLoop = p.getLoop();
			
			pieces.add(new Piece(1275, 175, pSize, pColor, pShape, pLoop));
			
		}
	}
	
	public Piece get() {
		if (pieces.size() == 1) return pieces.get(0);
		else return null;
	}
	
	public boolean ready() {
		if (pieces.size() == 1) return true;
		else return false;
	}
	
	public void clear() {
		pieces = new LinkedList<Piece>();
	}
	
	public void draw(Graphics g) {
		for (Piece piece : pieces) {
			piece.draw(g);
		}
	}
	
/*
	
	public void draw(Graphics g) {
		
		Font custom = new Font("arial", 1, 30);
		Font custom2 = new Font("arial", 1, 50);
		g.setFont(custom);
		
		if (game.getState() == Game.STATE.P1_Choose) {

			
			g.setColor(Color.WHITE);
			g.drawString("Player 1, choose a piece", 1180, 70);
			
		}
		
		else if (game.getState() == Game.STATE.P2_Choose) {

			g.setColor(Color.WHITE);
			g.drawString("Player 2, choose a piece", 1180, 70);
			
		} 
		
		else if (game.getState() == Game.STATE.P1_Placement) {

			g.setColor(Color.WHITE);
			g.drawString("Player 1, place your piece", 1170, 70);
			
		} 
		
		else if (game.getState() == Game.STATE.P2_Placement) {

			g.setColor(Color.WHITE);
			g.drawString("Player 2, place your piece", 1170, 70);
			
		} 
		
		else if (game.getState() == Game.STATE.P1_Wins) {
			
			g.setFont(custom2);
			g.setColor(Color.WHITE);
			g.drawString("Player 1 wins!", 1180, 150);
			
			g.setFont(custom);
			g.setColor(Color.WHITE);
			g.drawRect(1275, 265, 150, 50);
			g.drawString("Replay", 1300, 300);
		
		}
		
		else if (game.getState() == Game.STATE.P2_Wins) {
			
			g.setFont(custom2);
			g.setColor(Color.WHITE);
			g.drawString("Player 2 wins!", 1180, 150);
			
			g.setFont(custom);
			g.setColor(Color.WHITE);
			g.drawRect(1275, 265, 150, 50);
			g.drawString("Replay", 1300, 300);
			
		}
		
	}
 */
}
