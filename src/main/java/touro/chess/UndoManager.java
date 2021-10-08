package touro.chess;

import java.util.Stack;
/*
    TO DO:
    clean code
    fix naming?
    private class within public?
    testing issues
    other scenarios? if a piece is changed? don't know chess well enough...

 */
public class UndoManager { //don't like naming
    Board board;
    Stack<MoveInfo> executedMoves;
    private class MoveInfo{
        private Move move;
        private boolean pieceCaptured;
        private AbstractPiece capturedPiece;
        private MoveInfo(Move move, boolean pieceCaptured, AbstractPiece capturedPiece){
            this.move = move;
            this.pieceCaptured = pieceCaptured;
            this.capturedPiece = capturedPiece;
        }
        //getters and setters?
        /**
        public Move getMove (){
            return this.move;

        }
        public boolean getPieceCaptured (){
            return this.pieceCaptured;

        }
        public AbstractPiece getCapturedPiece (){
            return this.capturedPiece;

        }
         **/
    }

    public UndoManager (Board currentBoard ){
        board = currentBoard;
        executedMoves = new Stack<>();
    }

    /**
     * method to track move played
     * called when last move did not capture a piece
     * creates MoveInfo object and adds to stack
     * @param lastMove
     * @param pieceCaptured
     */
    public void trackMove(Move lastMove, boolean pieceCaptured){
        MoveInfo latestMoveInfo = new MoveInfo(lastMove, pieceCaptured, null);
        executedMoves.add(latestMoveInfo);
    }

    /**
     * overloaded method to track move played
     * called when last move captured a piece
     * creates MoveInfo object and adds to stack
     * @param lastMove
     * @param pieceCaptured
     * @param capturedPiece
     */
    public void trackMove(Move lastMove, boolean pieceCaptured, AbstractPiece capturedPiece) {
        MoveInfo latestMoveInfo = new MoveInfo(lastMove, pieceCaptured, capturedPiece);
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
        board.setPiece(latestMoveInfo.move.getFrom(), latestPiece); //set that piece back to where it came from
        //return captured pieces to board
        if (latestMoveInfo.pieceCaptured){
            AbstractPiece capturedPiece = latestMoveInfo.capturedPiece;
            board.setPiece(capturedPiece.getLocation(), capturedPiece);
            //when a piece is taken off the board, does it still keep its last coordinates?
        }
    }

}
