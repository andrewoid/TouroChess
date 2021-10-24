package touro.chess;

public class PawnCaptureMove extends Move {

    private boolean capture;

    public PawnCaptureMove(Location from, Location to) {
        super(from, to);
        capture = true;
    }
}

