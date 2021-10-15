package touro.chess;
import java.util.Stack;

public class UndoManager {
    private Board board;
    private Stack<MoveInfo> executedMoves;
    private MoveInfo latestMoveInfo;

    public UndoManager (Board currentBoard ){
        board = currentBoard;
        executedMoves = new Stack<>();
    }

    public Board getBoard(){ //remove?
        return board;
    }

    public Stack<MoveInfo> getExecutedMoves(){
        return executedMoves;
    }

    /**
     * method to track move played
     * creates MoveInfo object and adds to stack
     * @param lastMove
     * @param capturedPiece
     */
    public void trackMove(Move lastMove, AbstractPiece capturedPiece) {
        latestMoveInfo = new MoveInfo(lastMove,capturedPiece);
        executedMoves.add(latestMoveInfo);
    }

    /**
     * method to undo move
     * returns piece to previous position
     * returns captured piece to the board
     */
    public void undoMove (){
        latestMoveInfo = executedMoves.pop();
        AbstractPiece latestPiece = board.getPiece(latestMoveInfo.getMove().getTo());//get the last piece moved
        Move latestMove = latestMoveInfo.getMove();
        board.setPiece(latestMove.getFrom(),latestPiece);
        latestPiece.setLocation(latestMove.getFrom());
        if (latestMoveInfo.getCapturedPiece() != null){
            AbstractPiece capturedPiece = latestMoveInfo.getCapturedPiece();
            board.setPiece(latestMove.getTo(),capturedPiece);
        }
    }
}
