import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Sheep {
    private Position position;

    public Sheep(int x, int y) {
        this.position = new Position(x,y);
    }
    public Position moveUp(){
        return new Position(position.getX(), position.getY() - 1);
    }
    public Position moveRight(){
        return new Position(position.getX() + 1, position.getY());
    }
    public Position moveLeft(){
        return new Position(position.getX() - 1, position.getY());
    }
    public Position moveDown(){
        return new Position(position.getX(), position.getY() + 1);
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public Position getPosition() {
        return position;
    }

   public void draw(TextGraphics graphics) {
       graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
       graphics.enableModifiers(SGR.BOLD);
       graphics.putString(new TerminalPosition(position.getX(), position.getY()),"O");
   }
}
