import processing.core.*;

public class Main extends PApplet {
    public static void main(String[] args) {
        PApplet.main("Main");
//        int[][] arr = {{0, 0, 0, 0}, {0, 1, 0, 0}, {0, 1, 1, 1}, {0, 0, 0, 0}};
//        int[][] newArr = Rotation.rotateRight(arr);
//        for (int[] arrElem : newArr) {
//            for (int elem : arrElem) {
//                System.out.print(elem + ", ");
//            }
//            System.out.println();
//        }
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
        background(0);
        gameplay.draw();
        menu.drawMenu();
    }
    public void keyPressed() {
        gameplay.keyPressed();
    }
}