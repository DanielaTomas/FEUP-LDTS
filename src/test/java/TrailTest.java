import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

public class TrailTest {
    @Test
    public void trailTest() {
        Land land = new Land(new int[60][30]);
        Trail trail = new Trail(2,2);
        trail.execute(land);
        assertEquals(2,land.getType(2,2));
    }
    @Test
    public void drawTest(){
        TextGraphics mockGraphics = Mockito.mock(TextGraphics.class);
        Trail trail = new Trail(5,10);
        trail.draw(mockGraphics);
        Mockito.verify(mockGraphics,times(1)).setForegroundColor(TextColor.Factory.fromString("#32CD32"));
        Mockito.verify(mockGraphics,times(1)).enableModifiers(SGR.BOLD);
        Mockito.verify(mockGraphics,times(1)).putString(new TerminalPosition(5, 10),"~");
    }
}
