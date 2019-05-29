
import java.awt.Graphics;
import java.util.LinkedList;

public class FreePieces {
	
	LinkedList<Piece> pieces = new LinkedList<Piece>(); 
	private Platform platform; 
	
	public FreePieces(Platform platform) {
		this.platform = platform;
	}
	
/*	public void tick() {
		
	}   */
	
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
	
/*	public Piece getPiece (int x, int y) {
		for (Piece piece : pieces) {
			if (piece.x == x && piece.y == y) {
				return piece; 
			}
		}
	}  */
	
	public boolean inBounds(int x, int y, int px, int py, int xLen, int yLen) {
		
		if (x > px && x < px + xLen) {
			if (y > py && y < py + yLen) {
				return true; 
			}
			else return false; 
		}
		else return false; 
	}
	
	public int coordError (Piece p) {
		if (p.size == Piece.SIZE.big) return 25;
		else return 40;
		
	}
	
	public int lenError (Piece p) {
		if (p.size == Piece.SIZE.big) return 100;
		else return 70;
	}
	
	public void removeOnClick (int mx, int my) {
		LinkedList<Piece> auxiliary = new LinkedList<Piece>(); 
		 for (Piece piece : pieces) {
			 int px = piece.getX();
			 int py = piece.getY();
			 int ce = coordError(piece);
			 int le = lenError(piece);
			 if (inBounds(mx, my, px + ce, py + ce, le, le)) {
				 piece.setX(1275);
				 piece.setY(175);
				 platform.addPiece(piece);
			 }
			 else auxiliary.add(piece);
		 }
		 
		 this.pieces = auxiliary; 
	 }
}




















