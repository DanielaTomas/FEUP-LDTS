import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Field {
    private int width;
    private int height;
    private Sheep sheep;
    private List<Fence> fences;
    private List<Wolf> wolves;

    public Field(int width, int height) {
       this.width = width;
       this.height = height;
       sheep = new Sheep(10,10);
       fences = createFences();
       wolves = createWolves();
    }
    void processKey(KeyStroke key, TextGraphics graphics) {
        switch(key.getKeyType()) {
            case ArrowUp:
                moveSheep(sheep.moveUp());
                moveWolves();
                break;
            case ArrowDown:
                moveSheep(sheep.moveDown());
                moveWolves();
                break;
            case ArrowRight:
                moveSheep(sheep.moveRight());
                moveWolves();
                break;
            case ArrowLeft:
                moveSheep(sheep.moveLeft());
                moveWolves();
                break;
        }
        draw(graphics);
    }
    void moveSheep(Position position) {
        if (canSheepMove(position))
            sheep.setPosition(position);
    }
    private void moveWolves() {
        for(Wolf wolves : wolves) {
            Position p = wolves.move();
            while(!canWolvesMove(p)) {
                p = wolves.move();
            }
            wolves.setPosition(p);
        }
    }

    public void draw(TextGraphics graphics) {
        sheep.draw(graphics);
        for(Fence fence : fences)
            fence.draw(graphics);
        for(Wolf wolf : wolves)
            wolf.draw(graphics);
    }

    private List<Fence> createFences() {
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
    private List<Wolf> createWolves() {
            Random random = new Random();
            ArrayList<Wolf> wolves = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                wolves.add(new Wolf(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
            }
            return wolves;

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
    private boolean canWolvesMove(Position position) {
        if(position.getX() >= 0 && position.getX() <= width && position.getY() >= 0 && position.getY() <= height) {
            for(Fence fence : fences) {
                if(fence.getPosition().equals(position)) return false;
            }
            return true;
        }
        return false;
    }
}
