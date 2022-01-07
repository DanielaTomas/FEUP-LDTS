import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FenceTest {
    @Test
    public void trailTest() {
        Land land = new Land(new int[60][30]);
        Fence fence = new Fence(3,3);
        fence.execute(land);
        assertEquals(3,land.getType(3,3));
    }
}
