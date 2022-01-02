import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {
    @Test
    public void testEquals1() {
        Position p1 = new Position(10,16);
        Position p2 = new Position(10,16);
        assertEquals(p1, p2);

    }

    @Test
    public void testEquals2() {
        Position p1 = new Position(10,16);
        Position p2 = new Position(20, 5);
        assertNotEquals(p1, p2);
    }
}
