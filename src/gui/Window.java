package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import logic.Choice;
import logic.FreePieces;
import logic.Game;
import logic.GameState;
import logic.Move;
import logic.Piece;
import logic.Platform;
import logic.Player;
import logic.Tile;
import logic.TileArray;

@SuppressWarnings("serial")
public class Window extends JFrame implements ActionListener{
	
	public Game game;
	private GameBoard board;
	private PlayerObject Player1;
	private PlayerObject Player2;
	private JLabel status;
	private JMenuItem newGame;
	
	// continue adding mouse event shit
	
	
	public Window() {
		
		this.setTitle("Quarto");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());	
		
		board = new GameBoard(this);
		
		JMenuBar menu_bar = new JMenuBar();
		this.setJMenuBar(menu_bar);
		JMenu game_menu = new JMenu("Game");
		menu_bar.add(game_menu);
		newGame = new JMenuItem("New Game");
		game_menu.add(newGame);
		newGame.addActionListener(this);
		
		status = new JLabel();
		status.setFont(new Font(status.getFont().getName(),
						   status.getFont().getStyle(),20));
		status.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(board, BorderLayout.CENTER);
		add(status, BorderLayout.SOUTH); 
		
		newGame(new HumanPlayer(this, Player.Player1), new ComputerPlayer(this, Player.Player2));
	
	}
	
	public void newGame(PlayerObject P1, PlayerObject P2) {
		
		this.game = new Game();
		
		Player1 = P1;
		Player2 = P2;
		tick();
		
		repaint();
	}
	
	public GameState getSTate(){
		return game.state();
		}
	
	public TileArray getTiles() {
		if (game == null) return null;
		else return game.tiles();
	}
	
	public Platform getPlatform() {
		if (game == null) return null;
		else return game.platform();
	}
	
	public FreePieces getPieces() {
		if (game == null) return null;
		else return game.freePieces();
	}
	
	public void make(Choice c) {
		game.choose(c);
		board.repaint();
		tick();
		
	}
	
	public void make(Move m) {
		game.execute(m);
		board.repaint();
		tick();
	}
	
	public void tick() {
		System.out.println("tick " + game.state());
		if (game == null) {
			status.setText("No games are running on your PC.");
		}
		else {
			switch(game.state()) {
			case P1_Choose:
			    status.setText("Player 1, choose a piece");
                if (Player1 instanceof HumanPlayer)
                    humanTurn = true;
                else
                    Player1.choose(null);
                break;
			case P2_Choose:
			    status.setText("Player 2, choose a piece");
				if (Player2 instanceof HumanPlayer)
				    humanTurn = true;
				else
				    Player2.choose(null);
				break;
			case P1_Placement:
			    status.setText("Player 1, place your piece");
                if (Player1 instanceof HumanPlayer)
                    humanTurn = true;
                else
                    Player1.placeOn(null);
                break;
			case P2_Placement:
			    status.setText("Player 2, place your piece");
                if (Player2 instanceof HumanPlayer)
                    humanTurn = true;
                else
                    Player2.placeOn(null);
                break;
			case P1_Wins:
			    status.setText("Player 1 wins!");
			    break;
			case P2_Wins:
			    status.setText("Player 2 wins!");
			    break;
			}
		}
	}
	
	public void clickPiece(Piece p) {
		if (game != null) {
			switch (game.state()) {
				case P1_Choose:
					Player1.choose(p);
					break;
				case P2_Choose:
					Player2.choose(p);
					break;
				default:
					break;
			}
		}
	}
	
	public void clickTile(Tile t) {
		System.out.println("clickTile");
		if (game != null) {
			switch (game.state()) {
			case P1_Placement : Player1.placeOn(t); break;
			case P2_Placement : Player2.placeOn(t); break;
			default : break;
			}
		}
	}

	
	public Game copyGame() {
		return new Game(game);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newGame) {
			
			if (this.Player1 != null) this.Player1.stop();
			if (this.Player2 != null) this.Player2.stop();
			
			newGame(new HumanPlayer(this, Player.Player1),
					  new ComputerPlayer(this, Player.Player1));
		}
	}
	
	public boolean humanTurn = false;
}