import java.awt.Graphics;
import java.util.LinkedList;

public class Platform {
	
	protected int plx = 1275, ply = 175;

	LinkedList<Piece> onPlatform = new LinkedList<Piece>();
	
	public void draw(Graphics g) {
		for (Piece piece : onPlatform) {
			piece.draw(g);
		}
	}
	
	public void addPiece(Piece piece) {
		this.onPlatform.add(piece);
	}
	
	public void removePiece(Piece piece) {
		this.onPlatform.remove(piece); 
	}
	
}
