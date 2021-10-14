package touro.chess;

import java.util.List;
import java.util.Objects;

/**
 * Super class to all Chess pieces.
 */
public abstract class AbstractPiece {

    private Location location;
    private final PieceColor color;

    // How should we represent the piece's image?

    public AbstractPiece(Location location, PieceColor color) {
        this.location = location;
        this.color = color;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public PieceColor getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || (this.getClass() != o.getClass()))
        {
            return false;
        }
        AbstractPiece otherPiece = (AbstractPiece) o;
        return this.location.getColumn() == otherPiece.location.getColumn()
                && this.location.getRow() == otherPiece.location.getRow()
                && this.color == otherPiece.color;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(location, color);
    }

    /**
     * @return a copy of the piece.
     */
    public abstract AbstractPiece copy();

    /**
     * @return a List of all valid moves that this piece can make from this position.
     */
    public abstract List<Move> getMoves();

}
