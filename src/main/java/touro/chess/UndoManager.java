package touro.chess;
import java.util.Stack;
/*
    TO DO:
    fix naming?
    private class within public?
    testing issues
    other scenarios? if a piece is changed? don't know chess well enough...

 */
public class UndoManager {
    private Board board;
    private Stack<MoveInfo> executedMoves;
    private MoveInfo latestMoveInfo; //better like this or go back?

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

   /* public class MoveInfo{  //public class within a class?
        private Move move;
        private AbstractPiece capturedPiece;
        private MoveInfo(Move move, AbstractPiece capturedPiece){
            this.move = move;
            this.capturedPiece = capturedPiece;
        }
        //getters and setters?
        public Move getMove(){
            return this.move;
        }
        public AbstractPiece getCapturedPiece(){
            return this.capturedPiece;
        }
    }
    */

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
