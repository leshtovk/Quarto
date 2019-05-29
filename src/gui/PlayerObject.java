package gui;

import logic.Piece;
import logic.Tile;

public abstract class PlayerObject {

	public abstract void current();
	
	public abstract void stop();
	
	public abstract void choose(Piece p);
	
	public abstract void placeOn(Tile t);
	
}
