import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Grass extends Element implements LandTransformer{

        Grass(int x, int y){
             super(x,y);
        }

        @Override
        public void draw (TextGraphics graphics){
            graphics.setForegroundColor(TextColor.Factory.fromString("#006400"));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()),"~");
        }

        @Override
        public void execute(Land land){
            land.setType(getPosition().getX(), getPosition().getY(), 1);
        }
    }

