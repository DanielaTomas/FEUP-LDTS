import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Grass extends Element implements LandTransformer{
        private int x;
        private int y;
        Grass(int x, int y){
             super(x,y);
             this.x = x;
             this.y = y;
        }

        @Override
        public void draw (TextGraphics graphics){
            graphics.setForegroundColor(TextColor.Factory.fromString("#008000"));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()),"~");
        }

        @Override
        public void execute(Land land){
            land.setType(this.x,this.y,1);
        }
    }

