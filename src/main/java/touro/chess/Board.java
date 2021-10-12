package touro.chess;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import static touro.chess.PieceColor.Black;
import static touro.chess.PieceColor.White;

public class Board {

    private static final int COLUMNS = 8;
    private static final int ROWS = 8;
    private Square[][] squares = new Square[COLUMNS][ROWS];

    /**
     * Creates an empty board
     */

    public Board() {
        for (int column = 0; column < COLUMNS; column++) {
            for (int row = 0; row < ROWS; row++) {
                squares[column][row] = new Square(new Location(row, column));
            }
        }
    }


    /**
     * Sets up the board at the beginning of the game
     * Black is rows 0-1 and White is rows 6-7
     *
     */

    public void setUpBoard()
    {
        //pawns
        for(int column = 0; column < COLUMNS; column++)
        {
            int rowWhite = 6;
            int rowBlack = 1;
            squares[column][rowWhite].setPiece(new PawnPiece(new Location(rowWhite, column), White));
            squares[column][rowBlack].setPiece(new PawnPiece(new Location(rowBlack, column), Black));
        }
        int rowWhite = 7;
        int rowBlack = 0;
        //rooks
        squares[0][rowWhite].setPiece(new RookPiece(new Location(rowWhite, 0), White));
        squares[7][rowWhite].setPiece(new RookPiece(new Location(rowWhite, 7), White));
        squares[0][rowBlack].setPiece(new RookPiece(new Location(rowBlack, 0), Black));
        squares[7][rowBlack].setPiece(new RookPiece(new Location(rowBlack, 7), White));
        //knights
        squares[1][rowWhite].setPiece(new KnightPiece(new Location(rowWhite, 1), White));
        squares[6][rowWhite].setPiece(new KnightPiece(new Location(rowWhite, 6), White));
        squares[1][rowBlack].setPiece(new KnightPiece(new Location(rowBlack, 1), Black));
        squares[6][rowBlack].setPiece(new KnightPiece(new Location(rowBlack, 6), White));
        //bishop
        squares[2][rowWhite].setPiece(new BishopPiece(new Location(rowWhite, 2), White));
        squares[5][rowWhite].setPiece(new BishopPiece(new Location(rowWhite, 5), White));
        squares[2][rowBlack].setPiece(new BishopPiece(new Location(rowBlack, 2), Black));
        squares[5][rowBlack].setPiece(new BishopPiece(new Location(rowBlack, 5), White));
        //king
        squares[3][rowWhite].setPiece(new KingPiece(new Location(rowWhite, 3), White));
        squares[3][rowBlack].setPiece(new KingPiece(new Location(rowBlack, 3), Black));
        //queen
        squares[4][rowWhite].setPiece(new QueenPiece(new Location(rowWhite, 4), White));
        squares[4][rowBlack].setPiece(new QueenPiece(new Location(rowBlack, 4), Black));

    }

    /**
     * Returns the piece at a given location
     * @param location
     * @return AbstractPiece if there is a piece there, otherwise null
     */
    public @Nullable AbstractPiece getPiece(Location location)
    {
        return squares[location.getColumn()][location.getRow()].getPiece();
    }

    public Square getSquare(Location location) {
        return squares[location.getColumn()][location.getRow()];
    }

    public boolean isOnBoard(Move move) {
        return isOnBoard(move.getFrom()) && isOnBoard(move.getTo());
    }

    public boolean isOnBoard(Location location) {
        return location.getRow() > -1 && location.getRow() < 8
                && location.getColumn() > 0 && location.getColumn() < 8;
    }

    public void setPiece(Location location, AbstractPiece piece) {
        squares[location.getColumn()][location.getRow()].setPiece(piece);
    }


    /**
     * Verify that a Move is legal on this Board.
     *
     * @param move
     * @return true if the Move is legal, otherwise false.
     */
    public boolean isLegal(Move move) {
        int currentRow = move.getFrom().getRow();
        int currentColumn = move.getFrom().getColumn();
        int destinationRow = move.getTo().getRow();
        int destinationColumn = move.getTo().getColumn();

        if (!isOnBoard(move)) {
            return false;
        }

        Square currentSquare = getSquare(move.getFrom());
        Square destinationSquare = getSquare(move.getTo());
        AbstractPiece currentPiece = currentSquare.getPiece();
        AbstractPiece destinationPiece = destinationSquare.getPiece();

        if (currentPiece == null) {
            return false;
        } else if (currentSquare == destinationSquare) {
            return false;
        } else if (destinationPiece != null) {
            if (destinationSquare.hasPiece() && destinationPiece.getColor() == currentPiece.getColor()) {
                return false;
            }
        } else {   //if destination is empty or contains opposing color piece
            if (move.isJump()) {
                return true;
            } else {
                while (currentRow != destinationRow || currentColumn != destinationColumn) {
                    if (currentRow < destinationRow) {
                        currentRow++;
                    }
                    else if (currentRow > destinationRow){
                        currentRow--;
                    }
                    if (currentColumn < destinationColumn) {
                        currentColumn++;
                    } else if (currentColumn > destinationColumn) {
                        currentColumn--;
                    }
                    if(squares[currentColumn][currentRow].hasPiece()){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * method detects if it is a check for the king piece that is being passed
     * @param king
     * @return true or alse
     */
    public boolean isCheck(KingPiece king)
    {
        Location kingLocation = king.getLocation();
        PieceColor kingColor = king.getColor();
        for (int column = 0; column < squares.length; column++) {
            for (int row = 0; row < squares[column].length; row++) {
                Square thisSquare = squares[column][row];
                if (thisSquare.hasPiece() && (thisSquare.getPiece().getColor() != kingColor)) {
                    List<Move> moves = thisSquare.getPiece().getMoves();
                    for (Move move : moves)
                    {
                        if (isLegal(move) &&
                                move.getTo().getColumn() == kingLocation.getColumn() &&
                                move.getTo().getRow() == kingLocation.getRow())
                        {
                            return true;
                        }
                    }

                }
            }
        }
      return false;
    }
}