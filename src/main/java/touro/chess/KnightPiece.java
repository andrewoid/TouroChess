package touro.chess;

import java.util.ArrayList;
import java.util.List;

public class KnightPiece extends AbstractPiece {

    public KnightPiece(Location location, PieceColor color) {
        super(location, color);
    }

    @Override
    public AbstractPiece copy()
    {
        return new KnightPiece(this.getLocation(), this.getColor());
    }

    @Override
    public List<Move> getMoves() {
        Location currentLocation = getLocation();
        int row = currentLocation.getRow();
        int col = currentLocation.getColumn();

        List<Move> moves = new ArrayList<Move>();
        moves.add( new Move(getLocation(), new Location(row + 1, col + 2), true));
        moves.add( new Move(getLocation(), new Location(row - 1, col + 2), true));
        moves.add( new Move(getLocation(), new Location(row + 1, col - 2), true));
        moves.add( new Move(getLocation(), new Location(row - 1, col - 2), true));
        moves.add( new Move(getLocation(), new Location(row + 2, col - 1), true));
        moves.add( new Move(getLocation(), new Location(row + 2, col + 1), true));
        moves.add( new Move(getLocation(), new Location(row - 2, col - 1), true));
        moves.add( new Move(getLocation(), new Location(row - 2, col + 1), true));

        return moves;
    }
}
