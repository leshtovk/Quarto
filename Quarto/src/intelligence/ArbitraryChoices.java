package intelligence;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import logic.FreePieces;
import logic.Game;
import logic.Piece;
import logic.Tile;
import logic.TileArray;

public class ArbitraryChoices extends SwingWorker<Piece, Object>{

	private Game source;
	
	public ArbitraryChoices(Game source) {
		this.source = source; 
	}
	
	@Override
	protected Piece doInBackground() throws Exception {
		
		Game game = source.copyGame();
		Thread.sleep(100);
		Random r = new Random();
		FreePieces freePieces = game.freePieces();
		Piece p = freePieces.get(r.nextInt(freePieces.size()));
		
		return p;
	}
	
	@Override
	public void done() {
		
	//	if (source.getState() == Game.STATE.P2_Choose) {
			
			try {
				Piece p = this.get();
				source.freePieces().removePiece(p);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
//	}

}
