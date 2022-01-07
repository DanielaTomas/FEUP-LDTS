import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

public class LandTest {
    @Test
    public void landTest() {

        Land land = new Land(new int[60][30]);
        int[][] temp = new int[60][30];
        int[][] matrix = land.getMatrix();
        for(int i = 0; i < 60; i++)
            for(int j = 0; j < 30; j++)
                assertEquals(temp[i][j],matrix[i][j]);

        temp[2][2] = 2;
        land.setType(2,2,2);
        assertEquals(temp[2][2],land.getType(2,2));

    }
}
