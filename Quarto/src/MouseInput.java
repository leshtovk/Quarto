import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

	// create methods to register clicks on game pieces and tiles
	
	private FreePieces freePieces;
	
	public MouseInput(FreePieces freePieces) {
		this.freePieces = freePieces; 
	}  
		
	public void mouseClicked (MouseEvent e) {		
		int mouseX = e.getX();
		int mouseY = e.getY(); 
		System.out.println("X :" + mouseX + " Y: " + mouseY); 
		freePieces.removeOnClick(mouseX, mouseY);
		}
	// add placement on a tile
} 
	
	

	

