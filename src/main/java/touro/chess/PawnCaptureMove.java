package touro.chess;

public class PawnCaptureMove extends Move {

    private boolean capture;
    private Location from, to;

    public PawnCaptureMove(Location from, Location to) {
        super(from, to);
        this.from = from;
        this.to = to;
    }

    public boolean canCapture(){
        return from.getColumn() != to.getColumn();
    }
}