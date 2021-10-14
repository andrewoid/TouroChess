package touro.chess;


import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class BoardTest {

    @Test
    public void setUpBoard()
    {
        //given
        Board board = new Board();
        Location blackPawnLocation = new Location(6, 5);
        Location whitePawnLocation = new Location(1, 5);
        Location blackKingLocation = new Location(7, 3);
        Location whiteKingLocation = new Location(0, 3);
        Location emptyLocation = new Location(5, 2);

        //when
        board.setUpBoard();
        AbstractPiece blackPawn = board.getPiece(blackPawnLocation);
        AbstractPiece whitePawn = board.getPiece(whitePawnLocation);
        AbstractPiece blackKing = board.getPiece(blackKingLocation);
        AbstractPiece whiteKing = board.getPiece(whiteKingLocation);
        AbstractPiece empty = board.getPiece(emptyLocation);

        //then
        assertEquals(blackPawn.getColor(), PieceColor.Black);
        assertEquals(blackPawn.getClass(), PawnPiece.class);
        assertEquals(whitePawn.getColor(), PieceColor.White);
        assertEquals(whitePawn.getClass(), PawnPiece.class);
        assertEquals(blackKing.getColor(), PieceColor.Black);
        assertEquals(blackKing.getClass(), KingPiece.class);
        assertEquals(whiteKing.getColor(), PieceColor.White);
        assertEquals(whiteKing.getClass(), KingPiece.class);
        assertNull(empty);
    }

    @Test
    public void isLegal_validVerticalMove(){
        //given
        Board board = new Board();
        AbstractPiece piece = mock(AbstractPiece.class);
        Location currentLocation = new Location(2,1);
        Location destinationLocation = new Location(4,1);
        board.setPiece(currentLocation, piece);
        Move move = new Move(currentLocation,destinationLocation,false);

        //when
        board.isLegal(move);

        //then
        Assert.assertTrue(board.isLegal(move));
    }

    @Test
    public void isLegal_validHorizontalMove(){
        //given
        Board board = new Board();
        AbstractPiece piece = mock(AbstractPiece.class);
        Location currentLocation = new Location(4,1);
        Location destinationLocation = new Location(4,6);
        board.setPiece(currentLocation, piece);
        Move move = new Move(currentLocation,destinationLocation,false);

        //when
        board.isLegal(move);

        //then
        Assert.assertTrue(board.isLegal(move));
    }

    @Test
    public void isLegal_validDiagonalMove(){
        //given
        Board board = new Board();
        AbstractPiece piece = mock(AbstractPiece.class);
        Location currentLocation = new Location(2,1);
        Location destinationLocation = new Location(4,3);
        board.setPiece(currentLocation, piece);
        Move move = new Move(currentLocation,destinationLocation,false);

        //when
        board.isLegal(move);

        //then
        Assert.assertTrue(board.isLegal(move));
    }

    @Test
    public void isLegal_validJumpMove(){
        //given
        Board board = new Board();
        AbstractPiece piece = mock(AbstractPiece.class);
        Location currentLocation = new Location(1, 2);
        Location destinationLocation = new Location(3,1);
        board.setPiece(currentLocation, piece);
        Move move = new Move(currentLocation,destinationLocation,true);

        //when
        board.isLegal(move);

        //then
        Assert.assertTrue(board.isLegal(move));
    }

    @Test
    public void isLegal_invalidMoveSameColor(){
        //given
        Board board = new Board();
        AbstractPiece piece = mock(AbstractPiece.class);
        doReturn(PieceColor.White).when(piece).getColor();
        Location currentLocation = new Location(2,1);
        Location destinationLocation = new Location(4,1);
        board.setPiece(currentLocation, piece);
        board.setPiece(destinationLocation,piece);
        Move move = new Move(currentLocation,destinationLocation,false);

        //when
        board.isLegal(move);

        //then
        Assert.assertFalse(board.isLegal(move));
    }

    @Test
    public void isLegal_invalidMoveBlockedPath(){
        //given
        Board board = new Board();
        AbstractPiece piece = mock(AbstractPiece.class);
        Location currentLocation = new Location(2,1);
        Location destinationLocation = new Location(2,5);
        Location middleLocation = new Location(2,3);
        board.setPiece(currentLocation, piece);
        board.setPiece(middleLocation,piece);
        Move move = new Move(currentLocation,destinationLocation,false);

        //when
        board.isLegal(move);

        //then
        Assert.assertFalse(board.isLegal(move));
    }

    @Test
    public void isLegal_invalidMoveOffBoard(){
        //given
        Board board = new Board();
        Location currentLocation = new Location(12,1);
        Location destinationLocation = new Location(4,1);
        Move move = new Move(currentLocation,destinationLocation,false);

        //when
        board.isLegal(move);

        //then
        Assert.assertFalse(board.isLegal(move));
    }

    @Test
    public void isLegal_invalidMoveSameSquare(){
        //given
        Board board = new Board();
        AbstractPiece piece = mock(AbstractPiece.class);
        Location currentLocation = new Location(4,1);
        Location destinationLocation = new Location(4,1);
        board.setPiece(currentLocation, piece);
        Move move = new Move(currentLocation,destinationLocation,false);

        //when
        board.isLegal(move);

        //then
        Assert.assertFalse(board.isLegal(move));
    }

    @Test
    public void equals_true()
    {
        //given
        Board board = new Board();
        board.setUpBoard();
        Board board2 = new Board();
        board2.setUpBoard();

        //when
        boolean same = board.equals(board);
        boolean equals = board.equals(board2);

        //then
        assertTrue(same);
        assertTrue(equals);
    }

    @Test
    public void equals_false()
    {
        //given
        Board board = new Board();
        board.setUpBoard();
        Board board2 = new Board();

        //when
        boolean equals = board.equals(board2);

        //then
        assertFalse(equals);
    }

    @Test
    public void makeMove()
    {
        //given
        Board board = new Board();
        board.setUpBoard();
        Location from = new Location(6,3);
        Location to = new Location(5,3);
        Square originalSquare = board.getSquare(from);
        Square destinationSquare = board.getSquare(to);
        AbstractPiece piece = originalSquare.getPiece();

        //when
        board.makeMove(new Move(from, to, false, false));

        //then
        assertNull(originalSquare.getPiece());
        assertEquals(destinationSquare.getPiece(), piece);
    }

    @Test
    public void copyBoard()
    {
        //given
        Board board = new Board();
        board.setUpBoard();

        //when
        Board copy = board.copyBoard();

        //then
        assertTrue(board.equals(copy));
    }

        @Test
    public void isCheckmate_true()
    {
        //given
        Board board = new Board();

        Location kingsLocation = new Location(7,4);
        KingPiece king = new KingPiece(kingsLocation, PieceColor.Black);
        board.setPiece(kingsLocation, king);

        Location queensLocation = new Location(7,3);
        QueenPiece queen = new QueenPiece(queensLocation, PieceColor.White);
        board.setPiece(queensLocation, queen);

        //when
        boolean checkmate = board.isCheckmate(PieceColor.Black);

        //then
        Assert.assertTrue(checkmate);
    }

    @Test
    public void isCheckmate_false()
    {
        //given
        Board board = new Board();
        board.setUpBoard();

        //when
        boolean checkmateBlack = board.isCheckmate(PieceColor.Black);
        boolean checkmateWhite = board.isCheckmate(PieceColor.White);

        //then
        Assert.assertFalse(checkmateBlack);
        Assert.assertFalse(checkmateWhite);
    }
}

