package logic;

import java.awt.Color;
import java.util.LinkedList;

public class Game{

	private Player current;
	private Platform platform;
	private TileArray tiles; 
	private FreePieces freePieces; 
	
	// background:
	public static Color boardColor = new Color(100, 30, 0);
	
	public Game() {
		
		current = Player.Player1;
		platform = new Platform(new LinkedList<Piece>()); 
		tiles = new TileArray(TileArray.STATE.No_Winning_Combo);
		freePieces = new FreePieces();
		
		// initial state of the tiles:
		tiles.initialize();
		
		// initial state of the game pieces:
		freePieces.initialize();
		
	}

	// return all the possible pieces we can choose
	public LinkedList<Choice> possibleChoices(){
		
		LinkedList<Choice> choices = new LinkedList<Choice>();
		for (int i = 0; i < freePieces.size(); i++) {
			choices.add(new Choice(freePieces.get(i)));
		}
		
		return choices;
	}
	
	// return all the places we can place a specific piece
	public LinkedList<Move> possibleMoves(Piece piece){
		
		LinkedList<Move> moves = new LinkedList<Move>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (tiles.getTile(i, j).state == Tile.STATE.empty) {
					moves.add(new Move(piece, tiles.getTile(i, j)));
				}
			}
		}
		
		return moves;
	}
	
	public void restart() {
		current = Player.Player1;
		tiles.clear();
		freePieces.clear();
		tiles.initialize();
		freePieces.initialize();
		platform.clear();
	}
	
	// make a copy of the current game:
	public Game(Game game) {
		current = game.current;
		platform = game.platform;
		tiles = game.tiles; 
		freePieces = game.freePieces;
	}
	
	public FreePieces freePieces() {
		return this.freePieces;
	}
	
	public Platform platform() {
		return this.platform;
	}
	
	public TileArray tiles() {
		return this.tiles;
	}
	
	public GameState state() {
		
		// check for a winner:
		if (tiles.stateOfBoard() == TileArray.STATE.Winning_Combo) {
			switch(this.current) {
			case Player1 : return GameState.P1_Wins;
			case Player2 : return GameState.P2_Wins;
			}
			
		}
		
		// mid-game:
		if (current == Player.Player1 && this.platform.ready() == false) return GameState.P1_Choose; 
		else if (current == Player.Player1 && this.platform.ready() == true) return GameState.P1_Placement; 
		else if (current == Player.Player2 && this.platform.ready() == false) return GameState.P2_Choose; 
		else if (current == Player.Player2 && this.platform.ready() == true) return GameState.P2_Placement; 
		
		else return null; 
		
	}
	
	// choose a piece and return true if the choice was successful
	public boolean choose(Choice c) {
		Piece p = c.getPiece();
		if (freePieces.contains(p)) {
			platform.stage(p);
			freePieces.remove(p);
			current = current.change();
			
			return true;
		}
		
		else return false;
	}
	
	// execute a move (Place a piece on a tile)
	public boolean execute(Move m) {
		Tile t = m.getTile();
		Piece p = m.getPiece();
		if (t.state == Tile.STATE.empty) {
			t.place(p);
			platform.clear();
			
			return true;
		}
		
		else return false;
	}
	
}