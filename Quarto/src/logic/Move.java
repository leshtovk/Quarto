package logic;

public class Move {

	Piece piece;
	Tile tile;
	
	public Move(Piece piece, Tile tile) {
		this.piece = piece;
		this.tile = tile;
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	
	public Tile getTile() {
		return this.tile;
	}
	
}
