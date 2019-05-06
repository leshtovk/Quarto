
import java.awt.Color;
import java.awt.Graphics;

public class Tile {
	
	protected int x, y; 
	protected boolean state; 
	
	public Tile(int x, int y, Boolean state) {
		this.x = x; 
		this.y = y; 
		this.state = state;
	}

	public void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.drawOval(x, y, 150, 150);
		
	}

}