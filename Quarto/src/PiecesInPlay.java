
import java.awt.Graphics;
import java.util.LinkedList;

public class PiecesInPlay {
	
	public LinkedList<Piece> pieces = new LinkedList<Piece>();  

	public void draw(Graphics g) {
		for (Piece piece : pieces) {
			piece.draw(g);
		}
	}
	
	public void addPiece(Piece piece) {
		this.pieces.add(piece);
	}
	
	public void removePiece(Piece piece) {
		this.pieces.remove(piece); 
	}
}