/*import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Backup {
    import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

    public class Field implements Command {
        private int width;
        private int height;
        private Sheep sheep;
        private List<Wolf> wolves;
        private Land land;
        private List<LandTransformer> transformers;
        private Position last;
        private Position lastSheepPosition;


        public Field(int width, int height) {
            this.width = width;
            this.height = height;
            sheep = new Sheep(1, 1);
            wolves = createWolves();
            land = new Land(new int[60][30]);
            last = new Position(0,0); // last trail position
            lastSheepPosition = new Position(0,0);
            transformers = new ArrayList<>();
        }
        @Override
        public void processKey(KeyStroke key, TextGraphics graphics) {
            switch (key.getKeyType()) {
                case ArrowUp:
                    lastSheepPosition.setX(sheep.getPosition().getX());
                    lastSheepPosition.setY(sheep.getPosition().getY());
                    moveSheep(sheep.moveUp(), 1);
                    moveWolves();
                    break;
                case ArrowDown:
                    lastSheepPosition.setX(sheep.getPosition().getX());
                    lastSheepPosition.setY(sheep.getPosition().getY());
                    moveSheep(sheep.moveDown(), 2);
                    moveWolves();
                    break;
                case ArrowLeft:
                    lastSheepPosition.setX(sheep.getPosition().getX());
                    lastSheepPosition.setY(sheep.getPosition().getY());
                    moveSheep(sheep.moveLeft(), 3);
                    moveWolves();
                    break;
                case ArrowRight:
                    lastSheepPosition.setX(sheep.getPosition().getX());
                    lastSheepPosition.setY(sheep.getPosition().getY());
                    moveSheep(sheep.moveRight(), 4);
                    moveWolves();
                    break;
                default:
                    break;
            }
            createTypes();
            draw(graphics);
        }
        public void createTypes() {
            LandCreate create = new LandCreate(transformers);
            if(last.getX() != 0 && last.getY() != 0) {
                createMix(create);
                if(last.getX() != -1 && last.getY() != -1) { //if is the last trail
                    if (verifyPosition(last.getX() + 1, last.getY()) && verifyWolves(last.getX() + 1, last.getY())) {
                        boundaryFill(last.getX() + 1, last.getY());
                    }
                    else if (verifyPosition(last.getX(), last.getY() + 1) && verifyWolves(last.getX(), last.getY() + 1)) {
                        boundaryFill(last.getX(), last.getY() + 1);
                    }
                    else if (verifyPosition(last.getX() - 1, last.getY()) && verifyWolves(last.getX() - 1, last.getY())) {
                        boundaryFill(last.getX() - 1, last.getY());
                    }
                    else if (verifyPosition(last.getX(), last.getY() - 1) && verifyWolves(last.getX(), last.getY() - 1)) {
                        boundaryFill(last.getX(), last.getY() - 1);
                    }
                    setLast(-1,-1);
                    create.mix(land);
                    clearAnomalies(2,1);
                    insideGrass();
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
        public void clearAnomalies(int a, int b) {
            for(int i = 2; i < width-2; i++)
                for(int j = 2; j < height-2; j++)
                    if((land.getType(i,j) == a)) land.setType(i,j,b);
        }
        public void insideGrass(){
            for(int i = 0; i < width-1; i++) {
                for(int j = 0; j < height-1; j++) {
                    if((land.getType(i,j) == 0))
                        if (insideGrass(i,j) && verifyWolves(i,j)) boundaryFill(i, j);
                }
            }
        }
        public boolean insideGrass(int x, int y) {
            int down = y, up = y, left = x, right = x;
            boolean UP = false, DOWN = false, LEFT = false, RIGHT = false;
            while(up > 1 || down < height-1 || right < width-1 || left > 1) {
                if(land.getType(x,down) == 1 && (down+1 < height-2 || (x==2 || x == width-3))) DOWN = true;
                if(land.getType(x,up) == 1 && (up-1 > 1 || (x == 2 || x == width-3)))  UP = true;
                if(land.getType(left,y) == 1 && (left-1 > 1 || (y == 2 || y == height-3))) LEFT = true;
                if(land.getType(right,y) == 1 && (right+1 < width-2 || (y == 2 || y == height-3))) RIGHT = true;
                if(down < height-1) down++;
                if(up > 1) up--;
                if(right < width-1) right ++;
                if(left > 1) left--;
            }
            if((UP && DOWN && LEFT) || (UP && DOWN && RIGHT) || (UP && RIGHT && LEFT)) return true;
            else return LEFT && DOWN && RIGHT;
        }
        public boolean verifyWolves(int x, int y) {
            boolean res = verifyWolvesRec(x,y);
            clearAnomalies(4,0);
            return res;
        }
        public boolean verifyWolvesRec(int x, int y) {
            if (x > width - 1 || y > height - 1 || x < 0 || y < 0 || land.getType(x, y) == 2 || land.getType(x, y) == 1 || land.getType(x, y) == 4) return true;
            for (Wolf wolf : wolves)
                if(wolf.getPosition().getX() == x && wolf.getPosition().getY() == y) return false;
            land.setType(x, y, 4);
            return verifyWolvesRec(x + 1, y) && verifyWolvesRec(x, y + 1) && verifyWolvesRec(x - 1, y) && verifyWolvesRec(x, y - 1);
        }
        public void boundaryFill(int x, int y) {
            boundaryFillRec(x, y);
            clearAnomalies(4,0);
        }
        public void boundaryFillRec(int x, int y) {
            if(x > width-1 || y > height-1 || x < 0 || y < 0 || land.getType(x, y) == 2 || land.getType(x, y) == 1) return;
            land.setType(x, y, 1);
            boundaryFillRec(x + 1, y);
            boundaryFillRec(x, y + 1);
            boundaryFillRec(x - 1, y);
            boundaryFillRec(x, y - 1);
        }
        public boolean verifyPosition(int x, int y) {
            if(x > width-1 || y > height-1 || x < 0 || y < 0 || land.getType(x, y) == 2 || land.getType(x, y) == 1) return false;
            for (Wolf wolf : wolves)
                if(wolf.getPosition().getX() == x && wolf.getPosition().getY() == y) return false;
            return true;
        }
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
        public void createTrail(LandCreate create, int px, int py) {
            if(land.getType(px,py) != 1) {
                transformers.add(new Trail(px,py));
                create.mix(land);
            }
            if(land.getType(sheep.getPosition().getX(),sheep.getPosition().getY()) == 1 && land.getType(lastSheepPosition.getX(),lastSheepPosition.getY()) == 2) {
                setLast(lastSheepPosition.getX(),lastSheepPosition.getY());
            }
        }
        public void createFirst() {
            for (int c = 0; c < width; c++) {
                transformers.add(new Fence(c, 0));
                transformers.add(new Fence(c, height - 1));
                if(c != 0 || c < width - 1) {
                    transformers.add(new Grass(c, 1));
                    transformers.add(new Grass(c, height - 2));
                }
            }
            for (int r = 1; r < height - 1; r++) {
                transformers.add(new Fence(0, r));
                transformers.add(new Fence(width - 1, r));
                if(r < width - 2) {
                    transformers.add(new Grass(1, r));
                    transformers.add(new Grass(width - 2, r));
                }
            }
        }
        public void moveSheep(Position position, int direction) {
            if (canSheepMove(position)) {
                sheep.setPosition(position);
                sheep.setDirection(direction);
            }
        }
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
        public void draw(TextGraphics graphics) {
            for(int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if(land.getType(i,j) == 3) {
                        Fence f = new Fence(i,j);
                        f.draw(graphics);
                    }
                    else if(land.getType(i,j) == 1) {
                        Grass g = new Grass(i,j);
                        g.draw(graphics);
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
        }
        public List<Wolf> createWolves() {
            Random random = new Random();
            ArrayList<Wolf> wolves = new ArrayList<>();
            for (int i = 0; i < 5; i++)
                wolves.add(new Wolf(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
            return wolves;
        }
        public boolean canSheepMove(Position position) {
            if (position.getX() >= 0 && position.getX() <= width-1 && position.getY() >= 0 && position.getY() <= height-1)
                return land.getType(position.getX(), position.getY()) != 3;
            return false;
        }
        public boolean canWolvesMove(Position position) {
            if (position.getX() >= 0 && position.getX() <= width-1 && position.getY() >= 0 && position.getY() <= height-1)
                return land.getType(position.getX(), position.getY()) == 0 || land.getType(position.getX(), position.getY()) == 2;
            return false;
        }
        public Sheep getSheep() {
            return this.sheep;
        }
        public List<Wolf> getWolves() {
            return this.wolves;
        }
        public Land getLand() {
            return this.land;
        }
        public void setLast(int x, int y) {
            last.setX(x);
            last.setY(y);
        }
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
        public boolean win() {
            int filledArea = 0;
            for(int i = 2; i < width-2; i++)
                for(int j = 2; j < height-2; j++)
                    if(land.getType(i,j) == 1) filledArea++;
            return ((width-2) * (height-2) * 0.60) <= filledArea;
        }
    }

}
*/