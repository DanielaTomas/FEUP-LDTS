import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

public interface Command {
    void processKey(KeyStroke key, TextGraphics graphics);
}
