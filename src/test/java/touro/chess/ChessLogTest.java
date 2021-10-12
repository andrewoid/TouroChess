package touro.chess;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ChessLogTest {

    @Test
    public void addBlackMove(){
        // given
        Location startPosition = new Location(4,4);
        KingPiece king = new KingPiece(startPosition, PieceColor.Black);
        Move move = new Move(startPosition,new Location(5,4),false);
        ChessLog chessLog = new ChessLog();

        // when
        chessLog.addMoveToLog(move, Piece.King, king.getColor());

        // then
        assertEquals(chessLog.getBlackMoves().get(0), "Kf4");
        Assert.assertTrue(chessLog.getWhiteMoves().isEmpty());
        Assert.assertTrue(chessLog.getBlackMoves().size() == 1);
    }

    @Test
    public void addWhiteMove() {
        // given
        Location startPosition = new Location(4,4);
        PawnPiece pawn = new PawnPiece(new Location(5, 5), PieceColor.White);
        Move move = pawn.getMoves().get(0);
        ChessLog chessLog = new ChessLog();

        // when
        chessLog.addMoveToLog(move, Piece.Pawn, pawn.getColor());

        // then
        assertEquals(chessLog.getWhiteMoves().get(0), "e5");
        Assert.assertTrue(chessLog.getBlackMoves().isEmpty());
        Assert.assertEquals(chessLog.getWhiteMoves().size(),  1);
        Assert.assertTrue(chessLog.getMoveNumbers().size() == 1);
    }

    @Test
    public void addMultipleMoves() {
        // given
        Location startPosition = new Location(4,4);
        PawnPiece pawn = new PawnPiece(new Location(5, 5), PieceColor.White);
        Move pawnMove = pawn.getMoves().get(0);
        KingPiece king = new KingPiece(startPosition, PieceColor.Black);
        Move kingMove = new Move(startPosition,new Location(5,4),false);
        ChessLog chessLog = new ChessLog();

        // when
        chessLog.addMoveToLog(pawnMove, Piece.Pawn, pawn.getColor());
        chessLog.addMoveToLog(kingMove, Piece.King, king.getColor());

        // then
        Assert.assertTrue(chessLog.getBlackMoves().size() == 1);
        Assert.assertTrue(chessLog.getWhiteMoves().size() == 1);
        Assert.assertTrue(chessLog.getMoveNumbers().size() == 1);
    }
}