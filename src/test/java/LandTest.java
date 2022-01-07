import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LandTest {
    @Test
    public void landTest() {
        Land land = new Land(10,10);
        assertEquals(new Position(10,10), land.getPosition());
        
        land.setPosition(new Position(10,10));
        assertEquals(new Position(10,10), land.getPosition());
    }
}
