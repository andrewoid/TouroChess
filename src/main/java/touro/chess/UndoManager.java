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
    public UndoManager (Board currentBoard ){
        board = currentBoard;
        executedMoves = new Stack<>();
    }
    public Board getBoard(){
        return board;
    }
    public Stack<MoveInfo> getExecutedMoves(){
        return executedMoves;
    }

    private class MoveInfo{
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

    /**
     * method to track move played
     * creates MoveInfo object and adds to stack
     * @param lastMove
     * @param capturedPiece
     */
    public void trackMove(Move lastMove, AbstractPiece capturedPiece) {
        MoveInfo latestMoveInfo = new MoveInfo(lastMove,capturedPiece);
        executedMoves.add(latestMoveInfo);
    }

    /**
     * method to undo move
     * returns piece to previous position
     * returns captured piece to the board
     */
    public void undoMove (){
        MoveInfo latestMoveInfo = executedMoves.pop();
        AbstractPiece latestPiece = board.getPiece(latestMoveInfo.move.getTo()); //get the last piece moved
        board.setPiece(latestMoveInfo.move.getFrom(), latestPiece); //set piece back to where it came from
        latestPiece.setLocation(latestMoveInfo.move.getFrom()); //update piece's location
        //return captured pieces to board
        if (latestMoveInfo.capturedPiece != null){
            AbstractPiece capturedPiece = latestMoveInfo.capturedPiece;
            board.setPiece(latestMoveInfo.move.getTo(), capturedPiece);
        }
    }

}
