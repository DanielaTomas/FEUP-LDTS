import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

public class GameTest {
    @Test
    public void processKeyMenu1() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        Game game = new Game();
        KeyStroke key = new KeyStroke(KeyType.ArrowDown);
        game.processKeyMenu(key);

        assertEquals(1,game.getMenu().getSelected());
    }
    @Test
    public void processKeyMenu2() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        Game game = new Game();
        game.getMenu().setSelected(2);
        KeyStroke key = new KeyStroke(KeyType.ArrowDown);
        game.processKeyMenu(key);

        assertEquals(2,game.getMenu().getSelected());
    }
    @Test
    public void goToMenu() throws IOException, URISyntaxException, FontFormatException {
        Game game = new Game();
        game.getMenu().setIsSelected(false);
        game.goToMenu();
        assertTrue(game.getMenu().isSelected());
    }
}
