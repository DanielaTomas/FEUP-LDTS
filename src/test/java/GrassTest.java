import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

public class GrassTest {
    @Test
    public void grassTest() {
        Land land = new Land(new int[60][30]);
        Grass grass = new Grass(1,1);
        grass.execute(land);
        assertEquals(1,land.getType(1,1));
    }
    @Test
    public void drawTest(){
        TextGraphics mockGraphics = Mockito.mock(TextGraphics.class);
        Grass grass = new Grass(25,10);
        grass.draw(mockGraphics);
        Mockito.verify(mockGraphics,times(1)).setForegroundColor(TextColor.Factory.fromString("#006400"));
        Mockito.verify(mockGraphics,times(1)).enableModifiers(SGR.BOLD);
        Mockito.verify(mockGraphics,times(1)).putString(new TerminalPosition(25, 10),"~");
    }
}
