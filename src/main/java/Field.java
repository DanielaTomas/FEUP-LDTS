import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Field implements Command, FieldInterface {
    private int width;
    private int height;
    private Sheep sheep;
    private List<Wolf> wolves;
    private Land land;
    private List<LandTransformer> transformers;
    private Position last;
    private Position lastSheepPosition;
    private Fill fill;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        sheep = new Sheep(1, 1);
        wolves = createWolves();
        land = new Land(new int[60][30]);
        last = new Position(0,0); // last trail position
        lastSheepPosition = new Position(0,0);
        transformers = new ArrayList<>();
        fill = new Fill(width,height,land,wolves);
    }
    @Override
    public void processKey(KeyStroke key, TextGraphics graphics) {
        setLastSheep(sheep.getPosition().getX(),sheep.getPosition().getY());
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
        createTypes();
        draw(graphics);
    }
    @Override
    public void createTypes() {
        LandCreate create = new LandCreate(transformers);
        if(last.getX() != 0 && last.getY() != 0) {
            createMix(create);
            if(last.getX() != -1 && last.getY() != -1) { //if is the last trail
                if (fill.verifyPosition(last.getX() + 1, last.getY()) && fill.verifyWolves(last.getX() + 1, last.getY())) {
                    fill.boundaryFill(last.getX() + 1, last.getY());
                }
                else if (fill.verifyPosition(last.getX(), last.getY() + 1) && fill.verifyWolves(last.getX(), last.getY() + 1)) {
                    fill.boundaryFill(last.getX(), last.getY() + 1);
                }
                else if (fill.verifyPosition(last.getX() - 1, last.getY()) && fill.verifyWolves(last.getX() - 1, last.getY())) {
                    fill.boundaryFill(last.getX() - 1, last.getY());
                }
                else if (fill.verifyPosition(last.getX(), last.getY() - 1) && fill.verifyWolves(last.getX(), last.getY() - 1)) {
                    fill.boundaryFill(last.getX(), last.getY() - 1);
                }
                setLast(-1,-1);
                create.mix(land);
                fill.clearAnomalies(2,1);
                fill.insideGrass();
                transformers.clear();
            }
        }
        else {
            createFirst();
            last.setX(-1);
            last.setY(-1);
            create.mix(land);
            transformers.clear();
        }
    }
    @Override
    public void createMix(LandCreate create) {
        switch(sheep.getDirection()) {
            case 1:
                createTrail(create, sheep.moveDown().getX(), sheep.moveDown().getY());
                break;
            case 2:
                createTrail(create, sheep.moveUp().getX(), sheep.moveUp().getY());
                break;
            case 3:
                createTrail(create, sheep.moveRight().getX(), sheep.moveRight().getY());
                break;
            case 4:
                createTrail(create, sheep.moveLeft().getX(), sheep.moveLeft().getY());
                break;
        }
    }
    @Override
    public void createTrail(LandCreate create, int px, int py) {
        if(land.getType(px,py) != 1) {
            transformers.add(new Trail(px,py));
            create.mix(land);
        }
        if(land.getType(sheep.getPosition().getX(),sheep.getPosition().getY()) == 1 && land.getType(lastSheepPosition.getX(),lastSheepPosition.getY()) == 2) {
            setLast(lastSheepPosition.getX(),lastSheepPosition.getY());
        }
    }
    @Override
    public void createFirst() {
        for (int c = 0; c < width; c++) {
            transformers.add(new Fence(c, 0));
            transformers.add(new Fence(c, height - 1));
            transformers.add(new Grass(c, 1));
            transformers.add(new Grass(c, height - 2));
        }
        for (int r = 1; r < height - 1; r++) {
            transformers.add(new Fence(0, r));
            transformers.add(new Fence(width - 1, r));
            transformers.add(new Grass(1, r));
            transformers.add(new Grass(width - 2, r));
        }
    }
    @Override
    public void moveSheep(Position position, int direction) {
        if (canSheepMove(position)) {
            sheep.setPosition(position);
            sheep.setDirection(direction);
        }
    }
    @Override
    public void moveWolves() {
        for (Wolf wolf : wolves) {
            if(!checkWolfStuck(wolf)) {
                Position p = wolf.move();
                while (!canWolvesMove(p)) {
                    p = wolf.move();
                }
                wolf.setPosition(p);
            }
        }
    }
    @Override
    public boolean checkWolfStuck(Wolf wolf) {
        return land.getType(wolf.getPosition().getX()+1,wolf.getPosition().getY()+1) == 1
                && land.getType(wolf.getPosition().getX()+1,wolf.getPosition().getY()) == 1
                && land.getType(wolf.getPosition().getX(),wolf.getPosition().getY()+1) == 1
                && land.getType(wolf.getPosition().getX()+1,wolf.getPosition().getY()-1) == 1
                && land.getType(wolf.getPosition().getX()-1,wolf.getPosition().getY()-1) == 1
                && land.getType(wolf.getPosition().getX()-1,wolf.getPosition().getY()) == 1
                && land.getType(wolf.getPosition().getX(),wolf.getPosition().getY()-1) == 1
                && land.getType(wolf.getPosition().getX()-1,wolf.getPosition().getY()+1) == 1;
    }
    @Override
    public void draw(TextGraphics graphics) {
        double filledArea = 0;
        for(int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(land.getType(i,j) == 3) {
                    Fence f = new Fence(i,j);
                    f.draw(graphics);
                }
                else if(land.getType(i,j) == 1) {
                    Grass g = new Grass(i,j);
                    g.draw(graphics);
                    if(i >= 2 && j >= 2 && i < width-2 && j < height-2) filledArea++;
                }
                else if(land.getType(i,j) == 2) {
                    Trail t = new Trail(i,j);
                    t.draw(graphics);
                }
            }
        }
        for (Wolf wolf : wolves)
            wolf.draw(graphics);
        sheep.draw(graphics);
        graphics.putString(new TerminalPosition(2, 31), "MOVE:arrow keys  PAUSE:'p'  RESTART:'r'  AREA: " + ((int)(filledArea*100)/((width-2) * (height-2))) + "% " + "/ 80%");
    }
    @Override
    public List<Wolf> createWolves() {
        Random random = new Random();
        ArrayList<Wolf> wolves = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            wolves.add(new Wolf(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return wolves;
    }
    @Override
    public boolean canSheepMove(Position position) {
        if (position.getX() >= 0 && position.getX() <= width-1 && position.getY() >= 0 && position.getY() <= height-1)
            return land.getType(position.getX(), position.getY()) != 3;
        return false;
    }
    @Override
    public boolean canWolvesMove(Position position) {
        if (position.getX() >= 0 && position.getX() <= width-1 && position.getY() >= 0 && position.getY() <= height-1)
            return land.getType(position.getX(), position.getY()) == 0 || land.getType(position.getX(), position.getY()) == 2;
        return false;
    }
    @Override
    public Sheep getSheep() {
        return this.sheep;
    }
    @Override
    public List<Wolf> getWolves() {
        return this.wolves;
    }
    @Override
    public void setLast(int x, int y) {
        last.setX(x);
        last.setY(y);
    }
    @Override
    public Land getLand() {
        return this.land;
    }
    @Override
    public void setLastSheep(int x, int y) {
        lastSheepPosition.setX(x);
        lastSheepPosition.setY(y);
    }
    @Override
    public boolean gameOver() {
        for(Wolf wolf : wolves) {
            if((sheep.getPosition().getX() == wolf.getPosition().getX() && sheep.getPosition().getY() == wolf.getPosition().getY())
                    || (land.getType(wolf.getPosition().getX(),wolf.getPosition().getY()) == 2)
                    || (land.getType(sheep.getPosition().getX(),sheep.getPosition().getY()) == 2)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean win() {
        int filledArea = 0;
        for(int i = 2; i < width-2; i++)
            for(int j = 2; j < height-2; j++)
                if(land.getType(i,j) == 1) filledArea++;
        return ((width-2) * (height-2) * 0.80) <= filledArea;
    }
}
