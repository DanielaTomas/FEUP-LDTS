import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Sheep extends Element{
    private int direction;

    public Sheep(int x, int y) {
        super(x,y);
        direction = 0;
    }
    public Position moveUp(){
        return new Position(getPosition().getX(), getPosition().getY() - 1);
    }
    public Position moveLeft(){
        return new Position(getPosition().getX() - 1, getPosition().getY());
    }
    public Position moveRight(){
        return new Position(getPosition().getX() + 1, getPosition().getY());
    }
    public Position moveDown(){
        return new Position(getPosition().getX(), getPosition().getY() + 1);
    }
    @Override
    public void draw(TextGraphics graphics) {
       graphics.setForegroundColor(TextColor.Factory.fromString("#FFFAFA"));
       graphics.disableModifiers(SGR.BOLD);
       graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()),"@");
   }
    public int getDirection() {
        return direction;
    }
    public void setDirection(int direction) {
        this.direction = direction;
   }
}
