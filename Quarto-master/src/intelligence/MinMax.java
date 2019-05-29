package intelligence;

import gui.Window;
import logic.*;

import java.util.LinkedList;

public class MinMax {
    private Window main;

    private int maxDepth;
    private Tile resultTile;
    protected Piece choosenPiece = null;

    public MinMax(Window window, int depth) {
        this.main = window;
        this.maxDepth = depth;
    }

    public Result getNextMoveAI(Piece p){
        System.out.println("zakaj");
        int result = max(maxDepth, p);

        System.out.println("Computer finished");

        return new Result(resultTile, p, result, choosenPiece);
    }

    // computer turn
    private int max(int depth, Piece p){
        if(depth == 0)
            return getBoardValue(main.game);

        int maximumValue = -1;

        //System.out.println("max");

        TileArray tiles = main.getTiles();
        LinkedList<Tile> emptyTiles = tiles.freeTiles();

        for (int i = 0; i < emptyTiles.size(); i++) {
            // place a piece
            emptyTiles.get(i).place(p);

            if(isGameOver(main.game) == TileArray.STATE.Winning_Combo){
                // depth == maxDepth si shrani potezo
                maximumValue = 100;
                if(maxDepth == depth){
                    resultTile = emptyTiles.get(i);
                }
            }else{
                FreePieces possiblePieces = main.game.freePieces();
                for(int j = 0; j < possiblePieces.size(); j++){
                    // choose a piece
                    Piece choosen = possiblePieces.get(j);

                    // recursion
                    int rekValue = min(depth - 1, choosen);

                    if(rekValue > maximumValue){
                        // depth == maxDepth si shrani potezo
                        maximumValue = rekValue;
                        if(maxDepth == depth){
                            resultTile = emptyTiles.get(i);
                            choosenPiece = choosen;
                        }
                    }
                }
            }

            // undo place
            emptyTiles.get(i).invalidatePlace();
            main.game.tiles().state = TileArray.STATE.No_Winning_Combo;
        }

        return maximumValue;
    }

    // player turn
    private int min(int depth, Piece p){
        if(depth == 0)
            return getBoardValue(main.game);


        int minimumValue = 100;

        //System.out.println("min");

        TileArray tiles = main.getTiles();
        LinkedList<Tile> emptyTiles = tiles.freeTiles();

        for (int i = 0; i < emptyTiles.size(); i++) {
            // place a piece
            emptyTiles.get(i).place(p);

            if(isGameOver(main.game) == TileArray.STATE.Winning_Combo){
                minimumValue = -1;
            }else{
                FreePieces possiblePieces = main.game.freePieces();
                for(int j = 0; j < possiblePieces.size(); j++){
                    // choose a piece
                    Piece choosen = possiblePieces.get(j);

                    // recursion
                    int rekValue = max(depth - 1, choosen);

                    if(rekValue < minimumValue){
                        minimumValue = rekValue;
                    }
                }
            }

            // undo place
            emptyTiles.get(i).invalidatePlace();
            main.game.tiles().state = TileArray.STATE.No_Winning_Combo;
        }

        return minimumValue;
    }

    public TileArray.STATE isGameOver(Game game){
        return game.tiles().stateOfBoard();
    }

    private int getBoardValue(Game game){
        TileArray tA = game.tiles();
        int value = 0;

        for(int i = 0; i < 4; i++){
            Tile[] line = new Tile[4];
            Tile[] lineInvert = new Tile[4];

            for (int j = 0; j < 4; j++) {
                line[j] = tA.getTile(i, j);
                lineInvert[j] = tA.getTile(j, i);
            }
            value += hasThreeCharacteristics(line);
            value += hasThreeCharacteristics(lineInvert);
        }

        Tile[] d1 = {tA.getTile(0,0), tA.getTile(1,1), tA.getTile(2,2), tA.getTile(3,3)}; // diagonala a00 -> a11 -> a22 -> a33
        value += hasThreeCharacteristics(d1);

        Tile[] d2 = {tA.getTile(3,0), tA.getTile(2,1), tA.getTile(1,2), tA.getTile(0,3)}; // diagonala a30 -> a21 -> a12 -> a03
        value += hasThreeCharacteristics(d2);

        return value;
    }

    private int hasThreeCharacteristics(Tile[] line){

        Piece.COLOR color = null; int nColor = 1;
        Piece.SIZE size = null; int nSize = 1;
        Piece.SHAPE shape = null; int nShape = 1;
        Piece.LOOP loop = null; int nLoop = 1;

        boolean isFirst = true;

        for (int i = 0; i < 4; i++) {
            if(isFirst && line[i].state != Tile.STATE.empty) {
                color = line[i].getPiece().getColor();
                size = line[i].getPiece().getSize();
                shape = line[i].getPiece().getShape();
                loop = line[i].getPiece().getLoop();

                isFirst = false;
            }else if(line[i].state != Tile.STATE.empty){
                if(color == line[i].getPiece().getColor()) nColor++; else color = null;
                if(size == line[i].getPiece().getSize()) nSize++; else size = null;
                if(shape == line[i].getPiece().getShape()) nShape++; else shape = null;
                if(loop == line[i].getPiece().getLoop()) nLoop++; else loop = null;
            }
        }

        int boardValue = 0;

        if(nColor == 3) boardValue+= 1; else if(nColor == 4) boardValue+= 100;
        if(nSize  == 3) boardValue+= 1; else if(nSize  == 4) boardValue+= 100;
        if(nShape == 3) boardValue+= 1; else if(nShape == 4) boardValue+= 100;
        if(nLoop  == 3) boardValue+= 1; else if(nLoop  == 4) boardValue+= 100;

        return boardValue;
    }
}
