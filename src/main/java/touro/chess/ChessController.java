package touro.chess;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class ChessController {

    @FXML
    ArrayList<Label> squares;

    public void initialize() {
        squares = new ArrayList<>();
        setupInitialBoard();
    }

    private void setupInitialBoard() {
        squares.get(0).getStyleClass().add("BlackRook");
        squares.get(1).getStyleClass().add("BlackKnight");
        squares.get(2).getStyleClass().add("BlackBishop");
        squares.get(3).getStyleClass().add("BlackQueen");
        squares.get(4).getStyleClass().add("BlackKing");
        squares.get(5).getStyleClass().add("BlackBishop");
        squares.get(6).getStyleClass().add("BlackKnight");
        squares.get(7).getStyleClass().add("BlackRook");

        for (int sq = 8; sq <= 15; sq++){
            squares.get(sq).getStyleClass().add("BlackPawn");
        }

        for (int sq = 48; sq <= 55; sq++){
            squares.get(sq).getStyleClass().add("WhitePawn");
        }

        squares.get(56).getStyleClass().add("WhiteRook");
        squares.get(57).getStyleClass().add("WhiteKnight");
        squares.get(58).getStyleClass().add("WhiteBishop");
        squares.get(59).getStyleClass().add("WhiteQueen");
        squares.get(60).getStyleClass().add("WhiteKing");
        squares.get(61).getStyleClass().add("WhiteBishop");
        squares.get(62).getStyleClass().add("WhiteKnight");
        squares.get(63).getStyleClass().add("WhiteRook");
    }

    public void updateImage(PieceColor color, int originSquare, int destinationSquare, Piece piece){
        String movingPiece = color.toString() + "" + piece.toString();
        squares.get(originSquare).getStyleClass().remove(movingPiece);
        squares.get(originSquare).getStyleClass().add("Empty");
        squares.get(destinationSquare).getStyleClass().removeAll();
        squares.get(destinationSquare).getStyleClass().add(movingPiece);
    }

    public int convertOrigin (Move move){
        return move.getFrom().getRow() * 8 + move.getFrom().getColumn();
    }

    public int convertDestination (Move move){
        return move.getTo().getRow() * 8 + move.getTo().getColumn();

    }
    public void updateMoveImage(PieceColor color, Move move, Piece piece){
        updateImage(color, convertOrigin(move), convertDestination(move), piece);
    }

//private void updateImage(PieceColor color, Move move, Piece piece){
//        int numOriginSquare = move.getFrom().getRow() * 8 + move.getFrom().getColumn();
//        int numDestinationSSquare = move.getTo().getRow() * 8 + move.getTo().getColumn();
//
//        String movingPiece = color.toString() + "" + piece.toString();
//        squares.get(numOriginSquare).getStyleClass().remove(movingPiece);
//        squares.get(numOriginSquare).getStyleClass().add("Empty");
//        squares.get(numDestinationSSquare).getStyleClass().removeAll();
//        squares.get(numDestinationSSquare).getStyleClass().add(movingPiece);
//}

    public enum Piece {
        Rook,
        Knight,
        Bishop,
        Queen,
        King,
        Pawn
    }
}
