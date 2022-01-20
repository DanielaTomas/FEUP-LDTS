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
                if((land.getType(i,j) == 0)) {
                    if (insideGrass(i,j) && verifyWolves(i, j)) boundaryFill(i, j);
                }
            }
        }
    }
    @Override
    public boolean insideGrass(int x, int y) {
        boolean UP = false, DOWN = false, LEFT = false, RIGHT = false;
        for(int down = y; down < height-2 || x == 2 || x == width-3; down++)
            if(land.getType(x,down) == 1) { DOWN = true; break; }

        for(int up = y; up > 2 || x == 2 || x == width-3; up--)
            if(land.getType(x,up) == 1) { UP = true; break; }

        for(int left = x; left > 2 || y == 2 || y == height-3; left--)
            if(land.getType(left,y) == 1) { LEFT = true; break; }

        for(int right = x; right < width-2 || y == 2 || y == height-3; right++)
            if(land.getType(right,y) == 1) { RIGHT = true; break; }

        return (UP && ((DOWN && LEFT) || (DOWN && RIGHT) || (RIGHT && LEFT))) || (LEFT && DOWN && RIGHT);
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
