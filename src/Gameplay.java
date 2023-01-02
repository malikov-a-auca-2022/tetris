import processing.core.*;

public class Gameplay {
    private Shapes shapes = new Shapes();
    private PApplet papplet;
    private Menu menu;
    private Playfield pf;

    Gameplay(Menu menu, PApplet papplet) {
        this.papplet = papplet;
        this.menu = menu;
        pf = new Playfield(menu, papplet);

        float cellSize = menu.getCellSize();
        shapes.add(new Shape(new int[][]{{1, 0, 0, 0},
                                         {1, 1, 1, 0}}, new int[] {5, 41, 185}, cellSize, papplet));
        shapes.add(new Shape(new int[][]{{0, 0, 0, 0},
                                         {1, 1, 1, 1}}, new int[] {0, 255, 255}, cellSize, papplet));
        shapes.add(new Shape(new int[][]{{0, 0, 1, 0},
                                         {1, 1, 1, 0}}, new int[] {255, 165, 0}, cellSize, papplet));
        shapes.add(new Shape(new int[][]{{1, 1, 0, 0},
                                         {1, 1, 0, 0}}, new int[] {255, 255, 0}, cellSize, papplet));
    }

    public void draw() {
        Shape sh = shapes.getRandomShape();
        pf.addShape(sh);
    }
}