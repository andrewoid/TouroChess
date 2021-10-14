package touro.chess;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class KnightPieceTest {

    @Test
    public void copy()
    {
        //given
        Location location = new Location(3,4);
        KnightPiece knight = new KnightPiece(location, PieceColor.Black);

        //when
        KnightPiece copy = (KnightPiece)knight.copy();

        //then
        assertEquals(knight.getLocation().getColumn(), copy.getLocation().getColumn());
        assertEquals(knight.getLocation().getRow(), copy.getLocation().getRow());
        assertEquals(knight.getColor(), copy.getColor());
    }

    @Test
    public void getMoves(){
        //given
        Location testLocation = new Location(4,3);
        KnightPiece knightPiece = new KnightPiece(testLocation, PieceColor.Black);
        List<Move> testMove = new ArrayList<>();
        testMove.add(new Move(knightPiece.getLocation(), new Location(5,5),true));
        testMove.add(new Move(knightPiece.getLocation(), new Location(3,5),true));
        testMove.add(new Move(knightPiece.getLocation(), new Location(5,1),true));
        testMove.add(new Move(knightPiece.getLocation(), new Location(3,1),true));
        testMove.add(new Move(knightPiece.getLocation(), new Location(6,2),true));
        testMove.add(new Move(knightPiece.getLocation(), new Location(6,4),true));
        testMove.add(new Move(knightPiece.getLocation(), new Location(2,2),true));
        testMove.add(new Move(knightPiece.getLocation(), new Location(2,4),true));

        //when
        List<Move> listMoves = knightPiece.getMoves();

        //then
        assertEquals(listMoves, testMove);
    }
}
