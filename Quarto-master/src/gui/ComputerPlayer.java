package gui;

import javax.swing.SwingWorker;

import intelligence.ArbitraryChoices;
import intelligence.ArbitraryMoves;
import intelligence.MinMax;
import logic.*;

public class ComputerPlayer extends PlayerObject{
	
	private Window main;
	private Player player;
	private SwingWorker<Choice,Object> mindForChoosing;
	private SwingWorker<Result, Object> mindForPlacement;
	
	public ComputerPlayer(Window main, Player moi) {
		this.main = main;
		this.player = moi;
	}

	@Override
	public void current() {
	
	}

	@Override
	public void stop() {
		if (mindForChoosing != null) {
			mindForChoosing.cancel(true);
		}
		
		if (mindForPlacement != null) {
			mindForPlacement.cancel(true);
		}
		
	}

	@Override
	public void choose(Piece p) {
		/*mindForPlacement = new ArbitraryMoves(main);
		mindForPlacement.execute();*/
	}

	@Override
	public void placeOn(Tile t) {
		mindForPlacement = new ArbitraryMoves(main);
		mindForPlacement.execute();
	}
}
