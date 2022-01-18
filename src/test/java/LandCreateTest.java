import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

public class LandCreateTest {
    @Test
    public void landCreate() {
        Land mockLand = Mockito.mock(Land.class);
        List<LandTransformer> transformers = new ArrayList<>();
        transformers.add(new Grass(1,1));
        transformers.add(new Trail(2,2));
        transformers.add(new Grass(1,5));
        transformers.add(new Fence(3, 3));
        LandCreate create = new LandCreate(transformers);

        create.mix(mockLand);

        Mockito.verify(mockLand,times(1)).setType(1,1,1);
        Mockito.verify(mockLand,times(1)).setType(2,2,2);
        Mockito.verify(mockLand,times(1)).setType(1,5,1);
        Mockito.verify(mockLand,times(1)).setType(3,3,3);
    }
}
