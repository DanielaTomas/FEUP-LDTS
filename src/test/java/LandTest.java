import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LandTest {
    @Test
    public void landTest() {
        Land land = new Land(new int[60][30]);
        int[][] temp = new int[60][30];
        int[][] matrix = land.getMatrix();
        for(int i = 0; i < 60; i++)
            for(int j = 0; j < 30; j++)
                assertEquals(temp[i][j],matrix[i][j]);

        temp[1][5] = 1;
        land.setType(1,2,1);

        assertEquals(1,land.getType(1,2));

        temp[2][2] = 2;
        land.setType(2,2,2);
        temp[1][2] = 3;
        land.setType(1,2,3);

        assertEquals(2,land.getType(2,2));
        assertEquals(3,land.getType(1,2));
    }
}
