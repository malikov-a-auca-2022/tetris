import processing.core.*;

public class Main extends PApplet {
    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public void settings() {
        fullScreen();
    }

    Menu menu;
    Gameplay gameplay;

    public void setup() {
        menu = new Menu(this);
        gameplay = new Gameplay(menu, this);
    }

    public void draw() {
        gameplay.draw();
        menu.drawMenu();
    }
}