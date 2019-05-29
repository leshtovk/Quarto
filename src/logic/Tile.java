package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Tile {
	public enum STATE {
		empty,
		taken;
	}
	
	protected int x, y; 
	public STATE state;
	LinkedList<Piece> onTile = new LinkedList<Piece>();
	
	public Tile(int x, int y, STATE state, LinkedList<Piece> onTile) {
		this.x = x; 
		this.y = y; 
		this.state = state;
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
	public void place (Piece p) {
		
		Piece.SIZE pSize = p.getSize();
		Piece.SHAPE pShape = p.getShape();
		Piece.COLOR pColor = p.getColor();
		Piece.LOOP pLoop = p.getLoop();
		
		onTile.add(new Piece(this.getX(), this.getY(), pSize, pColor, pShape, pLoop));
		this.state = STATE.taken;
	}

	public void invalidatePlace(){
		onTile = new LinkedList<Piece>();
		this.state = STATE.empty;
	}
}











































