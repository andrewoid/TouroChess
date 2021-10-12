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

    //why is board null?
    //without captured piece
    public void testUndoMove() {
        //given
        givenUndoManager();
        latestMoveInfo = new MoveInfo (latestMove,null);
        undoManager.trackMove(latestMove,null);
        Location latestLocation = new Location(0,1);
        PawnPiece latestPiece = new PawnPiece(latestLocation,PieceColor.White);
        currentBoard.setPiece(latestLocation,latestPiece);

        //when
        undoManager.undoMove();

        //then
        //move Info popped from stack is the latest moveinfo <-- needed?
        MoveInfo actualLatestMoveInfo = undoManager.getExecutedMoves().pop();
        assertEquals(latestMove, actualLatestMoveInfo.getMove()); //won't work, move equals method not yet implemented
        assertNull(actualLatestMoveInfo.getCapturedPiece());
        // latest piece is correct
        //board.set piece is called correctly
        //latestPiece's location is reset


    }
    //with captured piece
    public void testUndoMoveWithCapture() {
        //given

        //when
        undoManager.undoMove();

        //verify
        //move popped from stack is the latest move
        // latest piece is correct
        //board.set piece is called correctly
        //latestPiece's location is reset
        //if is reached?
        // correct captured piece
        //board.setPiece is called correctly
    }

    public void givenUndoManager(){
    Board currentBoard = new Board();
    //currentBoard.setUpBoard();
    undoManager = new UndoManager(currentBoard);
    from = new Location(1,4);
    to = new Location(1,5);
    latestMove = new Move(from,to,false);
    //executedMoves = new Stack<>();
    }
}