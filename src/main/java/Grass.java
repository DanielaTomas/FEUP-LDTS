import com.googlecode.lanterna.graphics.TextGraphics;

public class Grass extends Element implements LandTransformer{
        int x;
        int y;
        Grass(int x, int y){
             super(x,y);
             this.x = x;
             this.y = y;
        }

        @Override
        public void draw (TextGraphics graphics){

        }

        @Override
        public void execute(Land land){
            land.setType(this.x,this.y,1);
        }
    }

