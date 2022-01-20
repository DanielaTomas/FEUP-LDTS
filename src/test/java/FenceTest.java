import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

public class FenceTest {
    @Test
    public void trailTest() {
        Land land = new Land(new int[60][30]);
        Fence fence = new Fence(3,3);
        fence.execute(land);
        assertEquals(3,land.getType(3,3));
    }
    @Test
    public void drawTest(){
        TextGraphics mockGraphics = Mockito.mock(TextGraphics.class);
        Fence fence = new Fence(0,0);
        fence.draw(mockGraphics);
        Mockito.verify(mockGraphics,times(1)).setForegroundColor(TextColor.Factory.fromString("#A0522D"));
        Mockito.verify(mockGraphics,times(1)).enableModifiers(SGR.BOLD);
        Mockito.verify(mockGraphics,times(1)).putString(new TerminalPosition(0, 0),"#");
    }
}
