import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Game {
    private Screen screen;
    private Field field;
    private Menu menu;
    private TextGraphics graphics;

    Game() throws IOException, URISyntaxException, FontFormatException {
        field = new Field(60,30);
        Font font = loadFont();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        DefaultTerminalFactory factory = new DefaultTerminalFactory();
        Font loadedFont = font.deriveFont(Font.PLAIN, 25);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        factory.setTerminalEmulatorFontConfiguration(fontConfig);
        factory.setForceAWTOverSwing(true);
        factory.setInitialTerminalSize(new TerminalSize(60, 33));
        Terminal terminal = factory.createTerminal();
        ((AWTTerminalFrame)terminal).addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
            }
        });
        initializeScreen(terminal);
        graphics = screen.newTextGraphics();
        screen.clear();
        menu = new Menu(graphics);
        screen.refresh();
    }
    private Font loadFont() throws IOException, FontFormatException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("square2.ttf");
        return Font.createFont(Font.TRUETYPE_FONT, new File(resource.toURI()));
    }
    private void initializeScreen(Terminal terminal) throws IOException {
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
        screen.refresh();
    }
    public void run() throws IOException, InterruptedException {
        KeyStroke key;
        KeyStroke oldKey = new KeyStroke(KeyType.ArrowRight); // first direction
        while (true) {
            if (menu.isSelected()) { //inside the menu
                key = screen.readInput();
                menu.processKey(key,graphics); //draw the menu
                if (key.getKeyType() == KeyType.Enter) processKeyMenu(key); //process menu buttons
            }
            else { //inside the game
                key = screen.pollInput();
                if (key != null && key.getKeyType() == KeyType.Character && key.getCharacter() == 'r') { //restart the game
                    goToMenu();
                    if(key.getCharacter() == 'r') field = new Field(60,30); //restart the game
                    oldKey = new KeyStroke(KeyType.ArrowRight);
                }
                else if(field.gameOver() || field.win()) {
                    if(field.gameOver()) drawEndGame("GAME OVER!");
                    else drawEndGame("YOU WON!");
                    key = screen.readInput(); // wait for a key
                    while(key.getKeyType() != KeyType.Enter) {
                        key = screen.readInput();
                    }
                    goToMenu();
                    field = new Field(60,30);
                    oldKey = new KeyStroke(KeyType.ArrowRight);
                }
                else {
                    if(key != null && (key.getKeyType() != KeyType.Character || (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'p')))) oldKey = key;
                    screen.clear();
                    field.processKey(oldKey, graphics); //process the game
                    screen.refresh();
                    TimeUnit.MILLISECONDS.sleep(245); //game speed
                }
            }
            if(key != null) {
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') screen.close();
                if (key.getKeyType() == KeyType.EOF) break;
                System.out.println(key);
                screen.refresh();
            }
        }
    }
    public void processKeyMenu(KeyStroke key) throws IOException {
        if (menu.getSelected() == 1) { //start
            field.processKey(key,graphics);
            screen.refresh();
        }
        else if (menu.getSelected() == 2) { //exit
            screen.close();
        }
    }
    public void drawEndGame(String str) throws IOException {
        screen.clear();
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#DAA520"));
        graphics.putString(new TerminalPosition(25, 10), str);
        graphics.putString(new TerminalPosition(16, 20), "<< press enter to continue >>");
        screen.refresh();
    }
    public void goToMenu() {
        menu.setIsSelected(true);
        screen.clear();
        menu = new Menu(graphics);
    }
    public Menu getMenu() {
        return this.menu;
    }
}
