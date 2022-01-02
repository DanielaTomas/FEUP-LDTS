import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private int width;
    private int height;
    private Sheep sheep;
    private List<Fence> fences;

    public Field(int width, int height) {
       this.width = width;
       this.height = height;
       sheep = new Sheep(10,10);
       fences = createFence();
    }
    void processKey(KeyStroke key, TextGraphics graphics) {
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
        draw(graphics);
    }
    void moveSheep(Position position) {
        if (canSheepMove(position))
            sheep.setPosition(position);
    }

    public void draw(TextGraphics graphics) {
        sheep.draw(graphics);
        for(Fence fence : fences)
            fence.draw(graphics);
    }

    private List<Fence> createFence() {
        List<Fence> fences = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            fences.add(new Fence(c, 0));
            fences.add(new Fence(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            fences.add(new Fence(0, r));
            fences.add(new Fence(width - 1, r));
        }
        return fences;
    }
    private boolean canSheepMove(Position position) {
        if(position.getX() >= 0 && position.getX() <= width && position.getY() >= 0 && position.getY() <= height) {
            for(Fence fence : fences) {
                if(fence.getPosition().equals(position)) return false;
            }
            return true;
        }
        return false;
    }
}
