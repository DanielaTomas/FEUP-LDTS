import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Fence extends Element implements LandTransformer{
    int x;
    int y;
    Fence(int x, int y) {
        super(x,y);
        this.x=x;
        this.y=y;
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#336699"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()),"#");
    }

    @Override
    public void execute(Land land) {
        land.setType(this.x,this.y,3);
    }
}
