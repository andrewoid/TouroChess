package touro.chess;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Square {

    private final Location location;
    private @Nullable AbstractPiece piece;

    public Square(Location location) {
        this.location = location;
    }


    public Location getLocation() {
        return location;
    }

    public @Nullable AbstractPiece getPiece() {
        return piece;
    }

    public void setPiece(@Nullable AbstractPiece piece) {
        this.piece = piece;
    }

    public boolean hasPiece() {
        return piece != null;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || this.getClass() != o.getClass())
        {
            return false;
        }
        Square square = (Square)o;
        if(this.piece == null && square.piece == null)
        {
            return this.location.getColumn() == square.location.getColumn()
                    && this.location.getRow() == square.location.getRow();
        }
        if(this.piece == null || square.piece == null)
        {
            return false;
        }
        return this.location.getColumn() == square.location.getColumn()
                && this.location.getRow() == square.location.getRow()
                && this.piece.equals(square.piece);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(location, piece);
    }
}
