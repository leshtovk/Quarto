package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import logic.FreePieces;
import logic.Piece;
import logic.Platform;
import logic.Tile;
import logic.TileArray;

@SuppressWarnings("serial")
public class GameBoard extends JPanel implements MouseListener{

	private Window main;
	
	public static final int width = 1600; 
	public static final int height = 950; 
	
	// background:
	public static Color boardColor = new Color(100, 30, 0);
	
	
	public GameBoard(Window main) {
		super();
		setBackground(boardColor);
		this.main = main;
		this.addMouseListener(this);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
	
	@Override 
	protected void paintComponent(Graphics g){
		
		Rectangle r = this.getBounds();
		int newHeight = r.height;
		int newWidth = r.width;
		
		// fill the background
		super.paintComponent(g);
		
		// draw the circle around the tiles
		g.setColor(Color.yellow);
		g.drawOval(width, height, 880, 880);
		
		// Separate the tiles from the pieces 
		g.drawLine(1100, 1000, 1100, 0);
		
	//	g.setColor(Color.yellow);
	//	g.drawOval(newWidth/15, newHeight/21, (int)(newWidth/1.8), (int)(newWidth/1.8));
		
		// Separate the tiles from the pieces 
	//	g.drawLine((newWidth/25)*17, 1000, (newWidth/25)*17, 0);
		
		// begin changing coordinates:
	//	g.drawLine((newWidth/27)*17, 1000, (newWidth/27)*17, 0);
	//	g.drawLine(1600, (newHeight/21), 0, (newHeight/21));
		
		// draw the staged piece if there is one 
		Platform platform = main.getPlatform();
		platform.draw(g);
		
		// draw the free pieces
		FreePieces pieces = main.getPieces();
		pieces.draw(g);
				
		// draw the tiles
		TileArray tiles = main.getTiles();
		tiles.draw(g);
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
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (main.humanTurn == false) return;
		
		FreePieces pieces = main.getPieces();
		TileArray tiles = main.getTiles();
		
		int mouseX = e.getX();
		int mouseY = e.getY(); 
		
		Piece p = pieces.clickPiece(mouseX, mouseY);
		if (p != null) {
			System.out.println("in pieces");
			main.clickPiece(p);
		}
		
		Tile t = tiles.clickTile(mouseX, mouseY);
		if (t != null) {
			System.out.println("in tile");
			main.clickTile(t);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
