import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import java.util.List;
  interface FieldInterface {
      void processKey(KeyStroke key, TextGraphics graphics);
      void createTypes();
      void createMix(LandCreate create);
      void createTrail(LandCreate create, int px, int py);
      void createFirst();
      void moveSheep(Position position, int direction);
      void moveWolves();
      boolean checkWolfStuck(Wolf wolf);
      void draw(TextGraphics graphics);
      List<Wolf> createWolves();
      boolean canSheepMove(Position position);
      boolean canWolvesMove(Position position);
      Sheep getSheep();
      List<Wolf> getWolves();
      Land getLand();
      void setLast(int x, int y);
      void setLastSheep(int x, int y);
      boolean gameOver();
      boolean win();
}

