import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Land extends Element{

    Land(int x, int y) {
        super(x,y);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#336699"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()),"#");
    }
}