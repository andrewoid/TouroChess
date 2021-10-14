package touro.chess;

import org.junit.Test;

import static org.junit.Assert.*;
import static touro.chess.PieceColor.*;

public class AbstractPieceTest
{

    @Test
    public void equals_true()
    {
        //given
        Location location = new Location(1,4);
        KingPiece king = new KingPiece(location, Black);
        KingPiece king2 = new KingPiece(location, Black);

        //when
        boolean equals = king.equals(king2);

        //then
        assertTrue(equals);
    }

    @Test
    public void equals_false()
    {
        //given
        Location location = new Location(1,4);
        Location location2 = new Location(1,3);
        KingPiece king = new KingPiece(location, Black);
        KingPiece king2 = new KingPiece(location2, Black);
        KingPiece king3 = new KingPiece(location2, White);
        QueenPiece queen = new QueenPiece(location, Black);

        //when
        boolean locations = king.equals(king2);
        boolean colors = king.equals(king3);
        boolean pieces = king.equals(queen);

        //then
        assertFalse(locations);
        assertFalse(colors);
        assertFalse(pieces);
    }
}