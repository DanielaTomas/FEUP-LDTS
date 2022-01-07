import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LandCreateTest {
    @Test
    public void landCreate() {
        Land land = new Land(new int[60][30]);
        Grass g = new Grass(1,1);
        Trail t = new Trail(2,2);
        Fence f = new Fence(3, 3);
        List<LandTransformer> transformers = new ArrayList<>();
        transformers.add(g);
        transformers.add(t);
        transformers.add(f);
        LandCreate create = new LandCreate(transformers);
        create.mix(land);
        assertEquals(1, land.getType(1,1));
        assertEquals(2, land.getType(2,2));
        assertEquals(3, land.getType(3,3));
    }
}
