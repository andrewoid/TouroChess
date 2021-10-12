package touro.chess;

import junit.framework.TestCase;
import java.util.Stack;

public class UndoManagerTest extends TestCase {
    private Board currentBoard;
    private UndoManager undoManager;
    private Location from, to;
    private AbstractPiece capturedPiece;
    private Move latestMove;
    private MoveInfo latestMoveInfo;
    //private Stack<MoveInfo> executedMoves;

    //test without captured piece
    public void testTrackMove() {
        //given
        givenUndoManager();
        capturedPiece = null;

        //when
        undoManager.trackMove(latestMove,null);
        latestMoveInfo = undoManager.getExecutedMoves().peek();

        //then
        assertEquals(latestMove,latestMoveInfo.getMove());
        assertNull(latestMoveInfo.getCapturedPiece());

    }
    //test with captured piece
    public void testTestTrackMoveWithCapture() {
        givenUndoManager();
        capturedPiece = new PawnPiece(to,PieceColor.White);

        //when
        undoManager.trackMove(latestMove,capturedPiece);
        latestMoveInfo = undoManager.getExecutedMoves().peek();

        //then
        assertEquals(latestMove,latestMoveInfo.getMove());
        assertEquals(capturedPiece,latestMoveInfo.getCapturedPiece());

    }

    //without captured piece
    public void testUndoMove() {
    }
    //with captured piece
    public void testUndoMoveWithCapture() {
    }
    public void givenUndoManager(){
    Board currentBoard = new Board();
    currentBoard.setUpBoard();
    undoManager = new UndoManager(currentBoard);
    from = new Location(1,4);
    to = new Location(1,5);
    latestMove = new Move(from,to,false);
    //executedMoves = new Stack<>();
    }
}