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
        assertEquals(chessLog.getBlackMoves().get(0), "K");
        Assert.assertTrue(chessLog.getWhiteMoves().isEmpty());
        Assert.assertTrue(chessLog.getBlackMoves().size() == 1);
    }
    
}