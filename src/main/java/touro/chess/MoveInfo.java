package touro.chess;

public class MoveInfo {
    private Move move;
    private AbstractPiece capturedPiece;
    public MoveInfo(Move move, AbstractPiece capturedPiece){
        this.move = move;
        this.capturedPiece = capturedPiece;
    }
    public Move getMove(){
        return this.move;
    }

    public AbstractPiece getCapturedPiece(){
        return this.capturedPiece;
    }
}
