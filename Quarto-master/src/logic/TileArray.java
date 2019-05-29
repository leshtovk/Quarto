package logic;
import java.awt.Graphics;
import java.util.LinkedList;

import gui.GameBoard;

public class TileArray {
	
	public enum STATE {
		Winning_Combo,
		No_Winning_Combo;
	}

	// tile coordinates:
	private int A00x = 475, A00y = 50,  A01x = 595, A01y = 170, A02x = 715, A02y = 290, A03x = 835, A03y = 410;
	private int A10x = 355, A10y = 170, A11x = 475, A11y = 290, A12x = 595, A12y = 410, A13x = 715, A13y = 530;
	private int A20x = 235, A20y = 290, A21x = 355, A21y = 410, A22x = 475, A22y = 530, A23x = 595, A23y = 650;
	private int A30x = 115, A30y = 410, A31x = 235, A31y = 530, A32x = 355, A32y = 650, A33x = 475, A33y = 770;
	
	// tile lists:
	private LinkedList<Piece> tile00; 
	private LinkedList<Piece> tile01; 
	private LinkedList<Piece> tile02; 
	private LinkedList<Piece> tile03; 
	private LinkedList<Piece> tile10; 
	private LinkedList<Piece> tile11; 
	private LinkedList<Piece> tile12; 
	private LinkedList<Piece> tile13; 
	private LinkedList<Piece> tile20; 
	private LinkedList<Piece> tile21; 
	private LinkedList<Piece> tile22; 
	private LinkedList<Piece> tile23;
	private LinkedList<Piece> tile30; 
	private LinkedList<Piece> tile31; 
	private LinkedList<Piece> tile32; 
	private LinkedList<Piece> tile33;   
	
	private Tile[][] tiles = new Tile[4][4];	
	public STATE state;
	
	public TileArray (STATE state) {
		this.state = state;
	}
	
	public void draw(Graphics g) {
		if (tiles != null) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					Tile tile = tiles[i][j]; 
					tile.draw(g); 
				}
			}
		}
	}
	
	public void addTile(int i, int j, Tile tile) {
		this.tiles[i][j] = tile;
	}
	
	public Tile getTile(int i, int j) {
		return tiles[i][j];
	}
	
	public LinkedList<Tile> freeTiles() {
		LinkedList<Tile> freeTiles = new LinkedList<Tile>();
		for (int i = 0; i < 4; i++) {
			for (Tile tile : tiles[i]) {
				if (tile.state == Tile.STATE.empty) freeTiles.add(tile);
			}
		}
		
		return freeTiles;
	}
	
	public Tile clickTile (int mx, int my) {

		LinkedList<Tile> candidates = new LinkedList<Tile>();
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Tile tile = tiles[i][j]; 
				int tx = tile.getX();
				int ty = tile.getY();
				if (GameBoard.inBounds2(mx, my, tx + 75, ty + 75, 75) && (tile.state == Tile.STATE.empty)) {
					candidates.add(tile);
				 }
			}
		}
		
		if (candidates.size() == 1) return candidates.get(0);
		else return null;
		
	}   
	
	public void checkTiles(Tile tile1, Tile tile2, Tile tile3, Tile tile4) {
		
		if (tile1.state == Tile.STATE.taken && tile2.state == Tile.STATE.taken 
				&& tile3.state == Tile.STATE.taken && tile4.state == Tile.STATE.taken) {
			
			Piece p1 = tile1.getPiece();
			Piece p2 = tile2.getPiece();
			Piece p3 = tile3.getPiece();
			Piece p4 = tile4.getPiece();
			
			if ((p1.size == p2.size && p1.size == p3.size && p1.size == p4.size) || 
				(p1.shape == p2.shape && p1.shape == p3.shape && p1.shape == p4.shape) ||
				(p1.color == p2.color && p1.color == p3.color && p1.color == p4.color) ||
				(p1.loop == p2.loop && p1.loop == p3.loop && p1.loop == p4.loop)) {
				
					state = STATE.Winning_Combo;
			}
		}
	}
	
	
	public STATE stateOfBoard() {
			
			Tile tile00 = tiles[0][0];
			Tile tile11 = tiles[1][1];
			Tile tile22 = tiles[2][2];
			Tile tile33 = tiles[3][3];
		
			this.checkTiles(tile00, tile11, tile22, tile33);
			
			Tile tile03 = tiles[0][3];
			Tile tile12 = tiles[1][2];
			Tile tile21 = tiles[2][1];
			Tile tile30 = tiles[3][0];
		
			this.checkTiles(tile03, tile12, tile21, tile30);
			
			for (int i = 0; i < 4; i++) {
				Tile tile_i0 = tiles[i][0];
				Tile tile_i1 = tiles[i][1];
				Tile tile_i2 = tiles[i][2];
				Tile tile_i3 = tiles[i][3];
			
				this.checkTiles(tile_i0, tile_i1, tile_i2, tile_i3);
			}
			
			for (int i = 0; i < 4; i++) {
				Tile tile_0i = tiles[0][i];
				Tile tile_1i = tiles[1][i];
				Tile tile_2i = tiles[2][i];
				Tile tile_3i = tiles[3][i];
			
				this.checkTiles(tile_0i, tile_1i, tile_2i, tile_3i);
			}
		
		return state;
	}	
	
	public void clear() {
		tiles = new Tile[4][4];
	}
	
	public void initialize() {
		
		tile00 = new LinkedList<Piece>(); 
		tile01 = new LinkedList<Piece>(); 
		tile02 = new LinkedList<Piece>(); 
		tile03 = new LinkedList<Piece>(); 
		tile10 = new LinkedList<Piece>(); 
		tile11 = new LinkedList<Piece>(); 
		tile12 = new LinkedList<Piece>(); 
		tile13 = new LinkedList<Piece>(); 
		tile20 = new LinkedList<Piece>(); 
		tile21 = new LinkedList<Piece>(); 
		tile22 = new LinkedList<Piece>(); 
		tile23 = new LinkedList<Piece>();
		tile30 = new LinkedList<Piece>(); 
		tile31 = new LinkedList<Piece>(); 
		tile32 = new LinkedList<Piece>(); 
		tile33 = new LinkedList<Piece>();  
		
		this.addTile(0, 0, new Tile(A00x, A00y, Tile.STATE.empty, tile00));
		this.addTile(0, 1, new Tile(A01x, A01y, Tile.STATE.empty, tile01));
		this.addTile(0, 2, new Tile(A02x, A02y, Tile.STATE.empty, tile02));
		this.addTile(0, 3, new Tile(A03x, A03y, Tile.STATE.empty, tile03));
		this.addTile(1, 0, new Tile(A10x, A10y, Tile.STATE.empty, tile10));
		this.addTile(1, 1, new Tile(A11x, A11y, Tile.STATE.empty, tile11));
		this.addTile(1, 2, new Tile(A12x, A12y, Tile.STATE.empty, tile12));
		this.addTile(1, 3, new Tile(A13x, A13y, Tile.STATE.empty, tile13));
		this.addTile(2, 0, new Tile(A20x, A20y, Tile.STATE.empty, tile20));
		this.addTile(2, 1, new Tile(A21x, A21y, Tile.STATE.empty, tile21));
		this.addTile(2, 2, new Tile(A22x, A22y, Tile.STATE.empty, tile22));
		this.addTile(2, 3, new Tile(A23x, A23y, Tile.STATE.empty, tile23));
		this.addTile(3, 0, new Tile(A30x, A30y, Tile.STATE.empty, tile30));
		this.addTile(3, 1, new Tile(A31x, A31y, Tile.STATE.empty, tile31));
		this.addTile(3, 2, new Tile(A32x, A32y, Tile.STATE.empty, tile32));
		this.addTile(3, 3, new Tile(A33x, A33y, Tile.STATE.empty, tile33));
	}
	
}
























