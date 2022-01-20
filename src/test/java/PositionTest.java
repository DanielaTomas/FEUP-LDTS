import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {
    @Test
    public void testEquals1() {
        Position p1 = new Position(10,16);
        Position p2 = new Position(10,16);
        assertTrue(p1.equals(p2));
        assertTrue(p1.hashCode() == p2.hashCode());
    }

    @Test
    public void testEquals2() {
        Position p1 = new Position(10,16);
        Position p2 = new Position(20, 5);
        assertFalse(p1.equals(p2));
        assertFalse(p1.hashCode() == p2.hashCode());
    }
}
