package touro.chess;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {

    @Test
    public void toChessNotation() {
        Location loc = new Location(0, 5);
        assertEquals("a5", loc.toChessNotation());

        Location loc2 = new Location(6, 7);
        assertEquals("g7", loc2.toChessNotation());
    }
}
