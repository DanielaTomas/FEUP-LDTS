import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SheepTest {
    @Test
    public void sheepMoveUp() {
        Sheep sheep = new Sheep(10, 10);
        Position position = sheep.moveUp();

        assertEquals(10, position.getX());
        assertEquals(9, position.getY());

    }
    @Test
    public void sheepMoveDown() {
        Sheep sheep = new Sheep(10, 10);
        Position position = sheep.moveDown();

        assertEquals(10, position.getX());
        assertEquals(11, position.getY());
    }
    @Test
    public void sheepMoveLeft() {
        Sheep sheep = new Sheep(10, 10);
        Position position = sheep.moveLeft();

        assertEquals(9, position.getX());
        assertEquals(10, position.getY());
    }
    @Test
    public void sheepMoveRight() {
        Sheep sheep = new Sheep(10,10);
        Position position = sheep.moveRight();

        assertEquals(11,position.getX());
        assertEquals(10,position.getY());
    }
}
