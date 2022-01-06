import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

public class Menu implements Command {
    private int selected;
    private boolean isSelected;

    Menu(TextGraphics graphics) {
        selected = 1;
        isSelected = true;
        draw(graphics);
    }
    public void processKey(KeyStroke key, TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#DAA520"));
        switch(key.getKeyType()) {
            case ArrowDown:
                if(selected == 1) selectDown(graphics);
                break;
            case ArrowUp:
                if(selected == 2) selectUp(graphics);
                break;
            case Enter:
                isSelected = false;
                break;
            default:
                break;
        }
    }
    private void selectDown(TextGraphics graphics) {
        selected = 2;
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(25, 9), new TerminalSize(7, 3), ' ');
        drawString(graphics,26,10,"Start");
        graphics.setBackgroundColor(TextColor.Factory.fromString("#EEE8AA"));
        graphics.fillRectangle(new TerminalPosition(25, 14), new TerminalSize(7, 3), ' ');
        drawString(graphics,26,15,"Exit");
    }
    private void selectUp(TextGraphics graphics) {
        selected = 1;
        graphics.setBackgroundColor(TextColor.Factory.fromString("#EEE8AA"));
        graphics.fillRectangle(new TerminalPosition(25, 9), new TerminalSize(7, 3), ' ');
        drawString(graphics,26,10,"Start");
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(25, 14), new TerminalSize(7, 3), ' ');
        drawString(graphics,26,15,"Exit");
    }
    public void draw(TextGraphics graphics) {
        graphics.fillRectangle(new TerminalPosition(25, 9), new TerminalSize(7, 3), ' ');
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#DAA520"));
        drawString(graphics,21,5,"The Sheep's Escape");
        selectUp(graphics);
    }
    private void drawString(TextGraphics graphics, int w, int h, String s) {
        graphics.putString(new TerminalPosition(w, h), s);
    }
    public int getSelected() {
        return selected;
    }
    public void setSelected(int selected) {
        this.selected = selected;
    }
    public boolean isSelected() {
        return isSelected;
    }
}

