package logic;

public enum GameState{
	P1_Choose,
	P2_Choose,
	P1_Placement,
	P2_Placement,
	P1_Wins,
	P2_Wins; 
	
	public String toString() {
		if (this == P1_Choose) return "P1_Choose";
		if (this == P2_Choose) return "P2_Choose";
		if (this == P1_Placement) return "P1_Placement";
		if (this == P2_Placement) return "P2_Placement";
		if (this == P1_Wins) return "P1_Wins";
		if (this == P2_Wins) return "P2_Wins";
		return "xxxx";
	}
}
