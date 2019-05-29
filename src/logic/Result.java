package logic;

public class Result {
    private Tile resultTile;
    private Piece p;
    private int result;
    private Piece choosenPiece;

    public Piece getChoosenPiece() {
        return choosenPiece;
    }

    public void setChoosenPiece(Piece choosenPiece) {
        this.choosenPiece = choosenPiece;
    }

    public Piece getP() {
        return p;
    }

    public void setP(Piece p) {
        this.p = p;
    }

    public Tile getResultTile() {
        return resultTile;
    }

    public void setResultTile(Tile resultTile) {
        this.resultTile = resultTile;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Result(Tile resultTile, Piece p, int result, Piece choosenPiece){
        this.resultTile = resultTile;
        this.p = p;
        this.result = result;
        this.choosenPiece = choosenPiece;
    }
}
