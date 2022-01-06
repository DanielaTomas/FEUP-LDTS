import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {

    @Test
    public void processKey() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        Game game = new Game();
        KeyStroke key = new KeyStroke(KeyType.ArrowDown);
        game.processKey(key);
        assertTrue(game.getMenu().isSelected());

    }
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
}
