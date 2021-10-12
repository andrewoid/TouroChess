package touro.chess;

import java.util.ArrayList;
import java.util.List;

public class ChessLog {

    private ArrayList <Integer> moveNumbers;
    private ArrayList <String> blackMoves;
    private ArrayList <String> whiteMoves;

    public ChessLog () {
        moveNumbers = new ArrayList<Integer>();
        blackMoves = new ArrayList<String>();
        whiteMoves = new ArrayList<String>();
    }

    public void addMoveToLog(Move move, Piece piece, PieceColor pieceColor){
        switch (pieceColor){
            case White:
                whiteMoves.add(moveAsString(move, piece));
                break;
            case Black:
                blackMoves.add(moveAsString(move, piece));
                break;
        }
    }

    private String moveAsString(Move move, Piece piece){
        String returnString = "";
        switch (piece.toString()){
            case "Bishop":
                returnString += 'B';
                break;
            case "King":
                returnString += 'K';
                break;
            case "Knight":
                returnString += 'N';
                break;
            case "Pawn":
                break;
            case "Queen":
                returnString += 'Q';
                break;
            case "Rook":
                returnString += 'R';
                break;
        }
        return returnString;
    }

    public List<String> getBlackMoves(){
        return this.blackMoves;
    }

    public List<String> getWhiteMoves(){
        return this.whiteMoves;
    }

}
