import java.awt.Graphics;

public class TileArray {

	private Tile[][] tiles = new Tile[4][4];	
	
	public void draw(Graphics g) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Tile tile = tiles[i][j]; 
				tile.draw(g);
			  //g.drawString("" + i + ", " + j, tile.x + 40, tile.y + 40);
			}
		}
	}
	
	public void addTile(int i, int j, Tile tile) {
		this.tiles[i][j] = tile;
	}
}
