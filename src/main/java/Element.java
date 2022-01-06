import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {
    private Position position;

    Element(int x, int y) {
        this.position = new Position(x,y);
    }

    public abstract void draw(TextGraphics graphics);

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }
}
