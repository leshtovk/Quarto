package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;

import logic.Game;
import logic.Piece;

public class Platform {
	
	protected int plx = 1275, ply = 175;

	Game game;
	LinkedList<Piece> pieces = new LinkedList<Piece>();
	
	public Platform(Game game) {
		this.game = game;
	}
	
	public void draw(Graphics g) {
		for (Piece piece : pieces) {
			piece.draw(g);
		}
		
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
	
	public boolean ready () {
		return (pieces.size() == 1);
	}
	
	public void addPiece(Piece piece) {
		if (!this.ready()) { 
			this.pieces.add(piece);
		}
	}
	
	public void clear() {
		this.pieces = new LinkedList<Piece>(); 
	}
	
	public Piece getPiece() {
		return pieces.get(0);   
	}
	
}
