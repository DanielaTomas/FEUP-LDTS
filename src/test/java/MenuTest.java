import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {
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
    public void processArrowDown() {
        Menu menu = new Menu(graphics);
        KeyStroke key = new KeyStroke(KeyType.ArrowDown);
        menu.processKey(key,graphics);

        assertEquals(2,menu.getSelected());
        assertTrue(menu.isSelected());

        menu.processKey(key,graphics);
        assertEquals(2,menu.getSelected());

        assertTrue(menu.isSelected());
    }
    @Test
    public void processArrowUp() {
        Menu menu = new Menu(graphics);
        KeyStroke key = new KeyStroke(KeyType.ArrowUp);

        menu.processKey(key, graphics);
        assertEquals(1, menu.getSelected());

        menu.setSelected(2);
        menu.processKey(key, graphics);
        assertEquals(1, menu.getSelected());

        assertTrue(menu.isSelected());
    }
    @Test
    public void processEnter() {
        Menu menu = new Menu(graphics);
        KeyStroke key = new KeyStroke(KeyType.Enter);
        menu.processKey(key, graphics);

        assertFalse(menu.isSelected());
    }
}
