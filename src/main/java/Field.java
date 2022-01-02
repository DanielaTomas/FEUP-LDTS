import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

public class Field {
    private Sheep sheep;

    public Field() {
       sheep = new Sheep(10,10);
    }

    void processKey(KeyStroke key, TextGraphics graphics) {
        draw(graphics);
        switch(key.getKeyType()) {
            case ArrowUp:
                moveSheep(sheep.moveUp());
                break;
            case ArrowDown:
                moveSheep(sheep.moveDown());
                break;
            case ArrowRight:
                moveSheep(sheep.moveRight());
                break;
            case ArrowLeft:
                moveSheep(sheep.moveLeft());
                break;
        }
    }

    void moveSheep(Position position) {
         sheep.setPosition(position);
    }

    public void draw(TextGraphics graphics) {
        sheep.draw(graphics);
    }
}
