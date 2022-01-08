import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FieldTest {
    private static Terminal terminal;
    private static TextGraphics graphics;
    private static Screen screen;

    @BeforeAll
    public static void start() throws IOException {
        TerminalSize terminalSize = new TerminalSize(0, 0);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        graphics = screen.newTextGraphics();
    }
    @Test
    public void processArrowUp() {
        Field field = new Field(60,30);
        KeyStroke key = new KeyStroke(KeyType.ArrowUp);
        field.processKey(key, graphics);

        assertEquals(1,field.getSheep().getDirection());
    }
    @Test
    public void processArrowDown() {
        Field field = new Field(60,30);
        KeyStroke key = new KeyStroke(KeyType.ArrowDown);
        field.processKey(key, graphics);

        assertEquals(2,field.getSheep().getDirection());
    }
    @Test
    public void processLeft() {
        Field field = new Field(60,30);
        KeyStroke key = new KeyStroke(KeyType.ArrowLeft);
        field.processKey(key, graphics);

        assertEquals(3,field.getSheep().getDirection());
    }
    @Test
    public void processRight() {
        Field field = new Field(60,30);
        KeyStroke key = new KeyStroke(KeyType.ArrowRight);
        field.processKey(key, graphics);

        assertEquals(4,field.getSheep().getDirection());
    }
    @Test
    public void canSheepMoveOutOfScreen() {
        Field field = new Field(60,30);
        field.getSheep().setPosition(new Position(100,50));
        assertFalse(field.canMove(new Position(100,50)));
    }

}
