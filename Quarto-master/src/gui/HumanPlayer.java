package gui;

import logic.Choice;
import logic.Move;
import logic.Piece;
import logic.Player;
import logic.Tile;

public class HumanPlayer extends PlayerObject{

	private Window main;
	private Player player;
	
	public HumanPlayer(Window main, Player moi) {
		this.main = main;
		this.player = moi;
	}
	
	@Override
	public void current() {		
	}

	@Override
	public void stop() {
	}

	public void choose(Piece p) {
		main.make(new Choice(p));
		main.humanTurn = false;
	}

	@Override
	public void placeOn(Tile t) {
	  Piece ready = main.getPlatform().get();
	  if (ready != null) main.make(new Move(ready, t));
	}
}
