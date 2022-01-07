import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrassTest {
    @Test
    public void grassTest() {
        Land land = new Land(new int[60][30]);
        Grass grass = new Grass(1,1);
        grass.execute(land);
        assertEquals(1,land.getType(1,1));
    }
}
