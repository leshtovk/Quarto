import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -1169216312278450738L;

	public static final int width = 1600; 
	public static final int height = 1000; 
	
	private Thread thread; 
	private boolean running = false; 
	
	private TileArray tiles; 
	private FreePieces freePieces; 
	private PiecesInPlay piecesInPlay;
	private Platform platform;
//	private MouseInput mouse; 
	
	// tile coordinates:
	private int A00x = 475, A00y = 50,  A01x = 595, A01y = 170, A02x = 715, A02y = 290, A03x = 835, A03y = 410;
	private int A10x = 355, A10y = 170, A11x = 475, A11y = 290, A12x = 595, A12y = 410, A13x = 715, A13y = 530;
	private int A20x = 235, A20y = 290, A21x = 355, A21y = 410, A22x = 475, A22y = 530, A23x = 595, A23y = 650;
	private int A30x = 115, A30y = 410, A31x = 235, A31y = 530, A32x = 355, A32y = 650, A33x = 475, A33y = 770;
	
	// free pieces coordinates:
	public int c1 = 805, c2 = 665, c3 = 540, c4 = 430;
	public int r1 = 1095, r2 = 1215, r3 = 1335, r4 = 1455; 
	
	// background:
	private final Color boardColor = new Color(100, 30, 0);
	
	public enum STATE{
		P1_Choose,
		P2_Choose,
		P1_Placement,
		P2_Placement,
		P1_Wins,
		P2_Wins; 
	}
	
	public Color getBoardColor () {
 		return this.boardColor; 
	}
	
	
	public Game() {
		
		tiles = new TileArray();
		platform = new Platform(); 
		freePieces = new FreePieces(platform);
		piecesInPlay = new PiecesInPlay();
		
		new Window (width, height, "Quarto", this);
		
	//	mouse = new MouseInput();
		this.addMouseListener(new MouseInput(freePieces)); 
		
		// Initial state of the tiles:
		tiles.addTile(0, 0, new Tile(A00x, A00y, false));
		tiles.addTile(0, 1, new Tile(A01x, A01y, false));
		tiles.addTile(0, 2, new Tile(A02x, A02y, false));
		tiles.addTile(0, 3, new Tile(A03x, A03y, false));
		tiles.addTile(1, 0, new Tile(A10x, A10y, false));
		tiles.addTile(1, 1, new Tile(A11x, A11y, false));
		tiles.addTile(1, 2, new Tile(A12x, A12y, false));
		tiles.addTile(1, 3, new Tile(A13x, A13y, false));
		tiles.addTile(2, 0, new Tile(A20x, A20y, false));
		tiles.addTile(2, 1, new Tile(A21x, A21y, false));
		tiles.addTile(2, 2, new Tile(A22x, A22y, false));
		tiles.addTile(2, 3, new Tile(A23x, A23y, false));
		tiles.addTile(3, 0, new Tile(A30x, A30y, false));
		tiles.addTile(3, 1, new Tile(A31x, A31y, false));
		tiles.addTile(3, 2, new Tile(A32x, A32y, false));
		tiles.addTile(3, 3, new Tile(A33x, A33y, false));
		
		// Initial state of the game pieces:
		freePieces.addPiece(new Piece(r1, c1, Piece.SIZE.big, Piece.COLOR.white, Piece.SHAPE.square, Piece.LOOP.without));
		freePieces.addPiece(new Piece(r2, c1, Piece.SIZE.big, Piece.COLOR.white, Piece.SHAPE.square, Piece.LOOP.with));
		freePieces.addPiece(new Piece(r3, c1, Piece.SIZE.big, Piece.COLOR.black, Piece.SHAPE.square, Piece.LOOP.without));
		freePieces.addPiece(new Piece(r4, c1, Piece.SIZE.big, Piece.COLOR.black, Piece.SHAPE.square, Piece.LOOP.with));
		freePieces.addPiece(new Piece(r1, c2, Piece.SIZE.big, Piece.COLOR.white, Piece.SHAPE.circle, Piece.LOOP.without));
		freePieces.addPiece(new Piece(r2, c2, Piece.SIZE.big, Piece.COLOR.white, Piece.SHAPE.circle, Piece.LOOP.with));
		freePieces.addPiece(new Piece(r3, c2, Piece.SIZE.big, Piece.COLOR.black, Piece.SHAPE.circle, Piece.LOOP.without));
		freePieces.addPiece(new Piece(r4, c2, Piece.SIZE.big, Piece.COLOR.black, Piece.SHAPE.circle, Piece.LOOP.with));
		freePieces.addPiece(new Piece(r1, c3, Piece.SIZE.small, Piece.COLOR.white, Piece.SHAPE.square, Piece.LOOP.without));
		freePieces.addPiece(new Piece(r2, c3, Piece.SIZE.small, Piece.COLOR.white, Piece.SHAPE.square, Piece.LOOP.with));
		freePieces.addPiece(new Piece(r3, c3, Piece.SIZE.small, Piece.COLOR.black, Piece.SHAPE.square, Piece.LOOP.without));
		freePieces.addPiece(new Piece(r4, c3, Piece.SIZE.small, Piece.COLOR.black, Piece.SHAPE.square, Piece.LOOP.with));
		freePieces.addPiece(new Piece(r1, c4, Piece.SIZE.small, Piece.COLOR.white, Piece.SHAPE.circle, Piece.LOOP.without));
		freePieces.addPiece(new Piece(r2, c4, Piece.SIZE.small, Piece.COLOR.white, Piece.SHAPE.circle, Piece.LOOP.with));
		freePieces.addPiece(new Piece(r3, c4, Piece.SIZE.small, Piece.COLOR.black, Piece.SHAPE.circle, Piece.LOOP.without));
		freePieces.addPiece(new Piece(r4, c4, Piece.SIZE.small, Piece.COLOR.black, Piece.SHAPE.circle, Piece.LOOP.with));
		
	}

	public synchronized void start() {
		thread = new Thread(this); 
		thread.start(); 
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join(); 
			running = false; 
		}
		catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	
		// game loop: 
		public void run() {
			this.requestFocus(); // we won't need to click the window every time we open a new game
			long lastTime = System.nanoTime(); 
			double amountOfTricks = 60.0;
			double ns = 1000000000 / amountOfTricks; 
			double delta = 0; 
			while(running) {
				long now = System.nanoTime(); 
				delta += (now - lastTime) / ns; 
				lastTime = now; 
				while(delta >= 1) {
					tick(); 
					delta--; 
				}
				if(running)
					draw(); 
			}
			stop(); 
		}

	private void tick() {
	//	freePieces.tick();
	}
		
	private void draw() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3); 
			return; 
		}
		Graphics g = bs.getDrawGraphics(); 
		
		// Draw the game board:
		g.setColor(boardColor);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.yellow);
		g.drawOval(110, 45, 880, 880);
		// Separate the tiles from the side bar: 
		g.drawLine(1100, 1000, 1100, 0);		
		//Draw the tiles:
		tiles.draw(g);
		// Draw the piece to be placed: 
		platform.draw(g);
		// Draw the free pieces:
		freePieces.draw(g);
		// Draw the pieces in play:
		piecesInPlay.draw(g);
		
		g.setColor(Color.white);
		g.drawRect(1300, 200, 100, 100);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) {
		new Game(); 
	}
}