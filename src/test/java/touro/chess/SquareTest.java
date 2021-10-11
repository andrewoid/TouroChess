package touro.chess;

import org.junit.Test;

import static org.junit.Assert.*;
import static touro.chess.PieceColor.Black;
import static touro.chess.PieceColor.White;

public class SquareTest
{
    @Test
    public void equals_true()
    {
        //given
        Location location = new Location(1,4);
        KingPiece king = new KingPiece(location, White);
        Square square = new Square(location);
        Square square2 = new Square(location);
        Square square3 = new Square(location);
        Square square4 = new Square(location);
        square3.setPiece(king);
        square4.setPiece(king);

        //when
        boolean same = square.equals(square);
        boolean noPiece = square.equals(square2);
        boolean hasPiece = square3.equals(square4);

        //then
        assertTrue(same);
        assertTrue(noPiece);
        assertTrue(hasPiece);
    }

    @Test
    public void equals_false()
    {
        //given
        Location location = new Location(1,4);
        Location location2 = new Location(2,4);
        Square square = new Square(location);
        Square square2 = new Square(location2);
        Square square3 = new Square(location2);
        Square square4 = new Square(location2);
        KingPiece king = new KingPiece(location, White);
        KingPiece king2 = new KingPiece(location, Black);
        square3.setPiece(king);
        square4.setPiece(king2);

        //when
        boolean locations = square.equals(square2);
        boolean pieces = square3.equals(square4);
        boolean onePiece = square.equals(square4);

        //then
        assertFalse(locations);
        assertFalse(pieces);
        assertFalse(onePiece);
    }

}