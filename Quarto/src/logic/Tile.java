package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import gui.Platform;

public class Tile {
	
	public enum STATE {
		empty,
		taken;
	}
	
	protected int x, y; 
	protected STATE state;
	private Platform platform;
	LinkedList<Piece> onTile = new LinkedList<Piece>();
	
	public Tile(int x, int y, STATE state, Platform platform, LinkedList<Piece> onTile) {
		this.x = x; 
		this.y = y; 
		this.state = state;
		this.platform = platform;
		this.onTile = onTile;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public Piece getPiece(){
		return this.onTile.get(0);
	}

	public void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.drawOval(x, y, 150, 150);
		if (this.state == STATE.taken) {
			this.getPiece().draw(g);
		}
		
	}
	
	// place the piece on the platform on the tile: 
	public void place () {
		Piece beingPlayed = platform.getPiece();
		beingPlayed.setX(this.getX()); 
		beingPlayed.setY(this.getY());
		this.onTile.add(beingPlayed);
		this.state = STATE.taken;
	} 
	
}











































