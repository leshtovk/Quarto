package logic;

public enum Player {
	
	Player1,
	Player2;

	
	public Player change() {
		if (this == Player1) return Player2;
		else return Player1;
	}
}
