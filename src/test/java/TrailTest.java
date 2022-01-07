import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrailTest {
    @Test
    public void trailTest() {
        Land land = new Land(new int[60][30]);
        Trail trail = new Trail(2,2);
        trail.execute(land);
        assertEquals(2,land.getType(2,2));
    }
}
