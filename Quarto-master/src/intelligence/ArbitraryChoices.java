package intelligence;

import java.util.LinkedList;
import java.util.Random;

import javax.swing.SwingWorker;
import gui.Window;
import logic.Choice;
import logic.Game;

public class ArbitraryChoices extends SwingWorker<Choice, Object>{
	
	private Window main;
	
	public ArbitraryChoices(Window main) {
		this.main = main;
	}
	
	@Override
	protected Choice doInBackground() throws Exception {
		Game game = main.game;
		Random r = new Random();
		LinkedList<Choice> choices = game.possibleChoices();
		Choice c = choices.get(r.nextInt(choices.size()));
		Thread.sleep(1000);
		return c;
	}
	
	@Override
	public void done() {
		try {
			Choice c = this.get();
			if (c != null) { main.make(c); }
		} catch (Exception e) {
		}
	}
}
