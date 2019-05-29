package intelligence;

import java.util.LinkedList;
import java.util.Random;

import javax.swing.SwingWorker;

import gui.Window;
import logic.*;

public class ArbitraryMoves extends SwingWorker<Result, Object>{
private Window main;
	
	public ArbitraryMoves(Window main) {
		this.main = main;
	}
	
	@Override
	protected Result doInBackground() throws Exception {
		Game game = main.game;

		MinMax computerTurn = new MinMax(main, 2); // sod depth
		Result r = computerTurn.getNextMoveAI(game.platform().get());

		return r;
	}
	
	@Override
	public void done() {
		try {
			Result r = this.get();
			if (r != null){
				main.game.tiles().state = TileArray.STATE.No_Winning_Combo;
				main.make(new Move(r.getP(), r.getResultTile()));
				main.make(new Choice(r.getChoosenPiece()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
