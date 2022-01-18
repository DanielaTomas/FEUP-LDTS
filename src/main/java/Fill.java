import java.util.ArrayList;
import java.util.List;

public class Fill implements FillInterface {
    private int width;
    private int height;
    private Land land;
    private List<Wolf> wolves;
    Fill(int width, int height, Land land, List<Wolf> wolves) {
        this.width = width;
        this.height = height;
        this.land = land;
        this.wolves = wolves;
    }
    @Override
    public void clearAnomalies(int a, int b) {
        for(int i = 2; i < width-2; i++)
            for(int j = 2; j < height-2; j++)
                if((land.getType(i,j) == a)) land.setType(i,j,b);
    }
    @Override
    public void insideGrass(){
        for(int i = 0; i < width-1; i++) {
            for(int j = 0; j < height-1; j++) {
                if((land.getType(i,j) == 0))
                    if (insideGrass(i,j) && verifyWolves(i,j)) boundaryFill(i, j);
            }
        }
    }
    @Override
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
    @Override
    public boolean verifyWolves(int x, int y) {
        boolean res = verifyWolvesRec(x,y);
        clearAnomalies(4,0);
        return res;
    }
    @Override
    public boolean verifyWolvesRec(int x, int y) {
        if (x > width - 1 || y > height - 1 || x < 0 || y < 0 || land.getType(x, y) == 2 || land.getType(x, y) == 1 || land.getType(x, y) == 4) return true;
        for (Wolf wolf : wolves)
            if(wolf.getPosition().getX() == x && wolf.getPosition().getY() == y) return false;
        land.setType(x, y, 4);
        return verifyWolvesRec(x + 1, y) && verifyWolvesRec(x, y + 1) && verifyWolvesRec(x - 1, y) && verifyWolvesRec(x, y - 1);
    }
    @Override
    public void boundaryFill(int x, int y) {
        boundaryFillRec(x, y);
        clearAnomalies(4,0);
    }
    @Override
    public void boundaryFillRec(int x, int y) {
        if(x > width-1 || y > height-1 || x < 0 || y < 0 || land.getType(x, y) == 2 || land.getType(x, y) == 1) return;
        land.setType(x, y, 1);
        boundaryFillRec(x + 1, y);
        boundaryFillRec(x, y + 1);
        boundaryFillRec(x - 1, y);
        boundaryFillRec(x, y - 1);
    }
    @Override
    public boolean verifyPosition(int x, int y) {
        if(x > width-1 || y > height-1 || x < 0 || y < 0 || land.getType(x, y) == 2 || land.getType(x, y) == 1) return false;
        for (Wolf wolf : wolves)
            if(wolf.getPosition().getX() == x && wolf.getPosition().getY() == y) return false;
        return true;
    }
}
