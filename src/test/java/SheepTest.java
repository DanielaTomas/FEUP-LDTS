import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

public class SheepTest {
    @Test
    public void sheepMoveUp() {
        Sheep sheep = new Sheep(10, 10);
        Position position = sheep.moveUp();

        assertEquals(10, position.getX());
        assertEquals(9, position.getY());

    }
    @Test
    public void sheepMoveDown() {
        Sheep sheep = new Sheep(10, 10);
        Position position = sheep.moveDown();

        assertEquals(10, position.getX());
        assertEquals(11, position.getY());
    }
    @Test
    public void sheepMoveLeft() {
        Sheep sheep = new Sheep(10, 10);
        Position position = sheep.moveLeft();

        assertEquals(9, position.getX());
        assertEquals(10, position.getY());
    }
    @Test
    public void sheepMoveRight() {
        Sheep sheep = new Sheep(10,10);
        Position position = sheep.moveRight();

        assertEquals(11,position.getX());
        assertEquals(10,position.getY());
    }
    @Test
    public void drawTest(){
        TextGraphics mockGraphics = Mockito.mock(TextGraphics.class);
        Sheep sheep = new Sheep(10,10);
        sheep.draw(mockGraphics);
        Mockito.verify(mockGraphics,times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFAFA"));
        Mockito.verify(mockGraphics,times(1)).disableModifiers(SGR.BOLD);
        Mockito.verify(mockGraphics,times(1)).putString(new TerminalPosition(10, 10),"@");
    }
}
