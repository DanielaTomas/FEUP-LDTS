import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Sheep extends Element{

    public Sheep(int x, int y) {
        super(x,y);
    }
    public Position moveUp(){
        return new Position(getPosition().getX(), getPosition().getY() - 1);
    }
    public Position moveRight(){
        return new Position(getPosition().getX() + 1, getPosition().getY());
    }
    public Position moveLeft(){
        return new Position(getPosition().getX() - 1, getPosition().getY());
    }
    public Position moveDown(){
        return new Position(getPosition().getX(), getPosition().getY() + 1);
    }

    @Override
   public void draw(TextGraphics graphics) {
       graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
       graphics.enableModifiers(SGR.BOLD);
       graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()),"O");
   }
}
