import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FieldTest {
    private static Terminal terminal;
    private static TextGraphics graphics;
    private static Screen screen;

    @BeforeAll
    public static void start() throws IOException {
        TerminalSize terminalSize = new TerminalSize(0, 0);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        graphics = screen.newTextGraphics();
    }

    @Test
    public void processArrowUp() {
        Field field = new Field(60, 30);
        KeyStroke key = new KeyStroke(KeyType.ArrowUp);
        field.processKey(key, graphics);

        assertEquals(1, field.getSheep().getDirection());
    }

    @Test
    public void processArrowDown() {
        Field field = new Field(60, 30);
        KeyStroke key = new KeyStroke(KeyType.ArrowDown);
        field.processKey(key, graphics);

        assertEquals(2, field.getSheep().getDirection());
    }

    @Test
    public void processLeft() {
        Field field = new Field(60, 30);
        KeyStroke key = new KeyStroke(KeyType.ArrowLeft);
        field.processKey(key, graphics);

        assertEquals(3, field.getSheep().getDirection());
    }

    @Test
    public void processRight() {
        Field field = new Field(60, 30);
        KeyStroke key = new KeyStroke(KeyType.ArrowRight);
        field.processKey(key, graphics);

        assertEquals(4, field.getSheep().getDirection());
    }

    @Test
    public void canSheepMove() {
        Field field = new Field(60, 30);
        assertFalse(field.canSheepMove(new Position(100, 50)));
        assertFalse(field.canSheepMove(new Position(20, 50)));
        assertFalse(field.canSheepMove(new Position(100, 10)));
        assertFalse(field.canSheepMove(new Position(-7, 0)));
        assertFalse(field.canSheepMove(new Position(14, -23)));
        assertFalse(field.canSheepMove(new Position(-4, -3)));

        for (int i = 0; i < 60; i++) {
            for (int j = 0; j < 30; j++) {
                if (i < 10 || j > 20) {
                    field.getLand().setType(i, j, 3);
                    assertFalse(field.canSheepMove(new Position(i, j)));
                } else {
                    assertTrue(field.canSheepMove(new Position(i, j)));
                }
            }
        }
    }

    @Test
    public void moveSheep() {
        Field field = new Field(60, 30);
        Position p1 = new Position(10, 10);
        p1.setX(1);
        p1.setY(2);
        field.moveSheep(p1, 2);

        assertEquals(2, field.getSheep().getDirection());
        assertEquals(p1, field.getSheep().getPosition());

        field.moveSheep(new Position(90, 20), 3);

        assertEquals(2, field.getSheep().getDirection());
        assertEquals(p1, field.getSheep().getPosition());
    }

    @Test
    public void canWolvesMove() {
        Field field = new Field(60, 30);
        assertFalse(field.canWolvesMove(new Position(100, 50)));
        assertFalse(field.canWolvesMove(new Position(20, 50)));
        assertFalse(field.canWolvesMove(new Position(100, 10)));
        assertFalse(field.canWolvesMove(new Position(-7, 0)));
        assertFalse(field.canWolvesMove(new Position(14, -23)));
        assertFalse(field.canWolvesMove(new Position(-4, -3)));

        for (int i = 0; i < 60; i++) {
            for (int j = 0; j < 30; j++) {
                if (i < 10 || j < 10) {
                    field.getLand().setType(i, j, 3);
                    assertFalse(field.canWolvesMove(new Position(i, j)));
                } else if (i > 30 || j > 20) {
                    field.getLand().setType(i, j, 1);
                    assertFalse(field.canWolvesMove(new Position(i, j)));
                } else {
                    assertTrue(field.canWolvesMove(new Position(i, j)));
                }
            }
        }
    }

    @Test
    public void createWolves() {
        Field field = new Field(60, 30);
        assertEquals(5, field.getWolves().size());
        field.getWolves().clear();
        assertEquals(5, field.createWolves().size());
    }
}