import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Field implements Command {
    private int width;
    private int height;
    private Sheep sheep;
    private List<Wolf> wolves;
    private Land land;
    private List<LandTransformer> transformer;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        sheep = new Sheep(1, 1);
        wolves = createWolves();
        transformer = createTypes();
        int[][] map = new int[60][30];
        land = new Land(map);
    }

    private List<LandTransformer> createTypes() {
        List<LandTransformer> t = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            t.add(new Fence(c, 0));
            t.add(new Fence(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            t.add(new Fence(0, r));
            t.add(new Fence(width - 1, r));
        }
        //continuar para grass e trail
        return t;
    }

    public void processKey(KeyStroke key, TextGraphics graphics) {
        switch (key.getKeyType()) {
            case ArrowUp:
                moveSheep(sheep.moveUp(), 1);
                moveWolves();
                break;
            case ArrowDown:
                moveSheep(sheep.moveDown(), 2);
                moveWolves();
                break;
            case ArrowLeft:
                moveSheep(sheep.moveLeft(), 3);
                moveWolves();
                break;
            case ArrowRight:
                moveSheep(sheep.moveRight(), 4);
                moveWolves();
                break;
            default:
                break;
        }
        draw(graphics);
    }

    public void moveSheep(Position position, int direction) {
        if (canMove(position)) {
            sheep.setPosition(position);
            sheep.setDirection(direction);
        }
    }

    private void moveWolves() {
        for (Wolf wolves : wolves) {
            Position p = wolves.move();
            while (!canMove(p)) {
                p = wolves.move();
            }
            wolves.setPosition(p);
        }
    }

    public void draw(TextGraphics graphics) {
        sheep.draw(graphics);
      /*  for (Fence fence : fences)
            fence.draw(graphics); */
        for (Wolf wolf : wolves)
            wolf.draw(graphics);

        sheep.draw(graphics);
    }
    private List<Wolf> createWolves() {
        Random random = new Random();
        ArrayList<Wolf> wolves = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            wolves.add(new Wolf(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        }
        return wolves;

    }
    public boolean canMove(Position position) {
        if (position.getX() >= 0 && position.getX() <= width-1 && position.getY() >= 0 && position.getY() <= height-1) {
          /*  for (Fence fence : fences)
                if (fence.getPosition().equals(position)) return false;*/
            return true;
        }
        return false;
    }

    public Sheep getSheep() {
        return this.sheep;
    }
}
