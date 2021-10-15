package touro.chess;

import javafx.scene.control.Label;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ChessControllerTest {
//    @Test
//    public void updateMoveImage(){
//        //given
//        ChessController chessController = new ChessController();
//        chessController.squares = new ArrayList<>();
//        Location originSquare = new Location(0,0);
//        Move move = new Move(originSquare, new Location(0,1), false);
//        //chessController.squares.add();
//
//        //when
//        chessController.updateMoveImage(PieceColor.White, move, Piece.King);
//
//        //then
//
//
//    }

    @BeforeClass
    public static void beforeClass() {
        com.sun.javafx.application.PlatformImpl.startup(() -> {
        });
    }

    @Test
    public void updateImage(){
        //given
        ChessController chessController = new ChessController();
        chessController.squares = new ArrayList<>();
        //Location originSquare = new Location(0,0);
        //Move move = new Move(originSquare, new Location(0,1), false);
        Label labelTo = mock(Label.class);
        Label labelFrom = mock(Label.class);
        chessController.squares.add(labelTo);
        chessController.squares.add(labelFrom);


        //       doNothing().when(chessController.squares.get(0).getStyleClass().remove("WhiteRook"));
//        doReturn("Empty").when(chessController.squares.get(0).getStyleClass().add("Empty"));

        int from = 0;
        int to =  1;


        doNothing().when(chessController.squares.get(from)).setStyle(any());
        doNothing().when(chessController.squares.get(to)).setStyle(any());



        //when
        chessController.updateImage(PieceColor.White, from, to, Piece.Rook);

        //then
        verify(labelFrom).setStyle("WhiteRook");
        verify(labelTo).setStyle("Empty");

    }
}
