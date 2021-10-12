package touro.chess;

import java.util.ArrayList;
import java.util.List;

public class ChessLog {

    private ArrayList <Integer> moveNumbers;
    private ArrayList <String> blackMoves;
    private ArrayList <String> whiteMoves;

    public ChessLog () {
        moveNumbers = new ArrayList<>();
        blackMoves = new ArrayList<>();
        whiteMoves = new ArrayList<>();
    }

    public void addMoveToLog(Move move, Piece piece, PieceColor pieceColor){
        switch (pieceColor){
            case White:
                whiteMoves.add(moveAsString(move, piece));
                updateMoveNumber();
                break;
            case Black:
                blackMoves.add(moveAsString(move, piece));
                break;
        }
    }

    private String moveAsString(Move move, Piece piece){
        String returnString = piece.toString().equals("Bishop") ? "B" : piece.toString().equals("King") ? "K" :
                piece.toString().equals("Knight") ? "N" : piece.toString().equals("Rook") ? "R" : piece.toString().equals("Queen") ? "Q" : "";
        returnString += move.getTo().toChessNotation();
        return returnString;
    }

    public List<String> getBlackMoves(){
        return this.blackMoves;
    }

    public List<String> getWhiteMoves(){
        return this.whiteMoves;
    }

    public List<Integer> getMoveNumbers(){
        return this.moveNumbers;
    }

    private void updateMoveNumber() {
        moveNumbers.add(moveNumbers.size() + 1);
    }
}
