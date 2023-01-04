import processing.core.*;

public class Gameplay extends PApplet {
    private Shapes shapes = new Shapes();
    private PApplet papplet;
    private Menu menu;
    private Playfield pf;

    Gameplay(Menu menu, PApplet papplet) {
        this.papplet = papplet;
        this.menu = menu;
        pf = new Playfield(menu, papplet);

        float cellSize = menu.getCellSize();
        shapes.add(new Shape(new int[][]{{0, 1, 0, 0},
                                         {0, 1, 1, 1}}, new int[]{5, 41, 185}, cellSize, papplet));

        shapes.add(new Shape(new int[][]{{0, 0, 0, 0},
                                         {1, 1, 1, 1}}, new int[]{0, 255, 255}, cellSize, papplet));

        shapes.add(new Shape(new int[][]{{0, 0, 0, 1},
                                         {0, 1, 1, 1}}, new int[]{255, 165, 0}, cellSize, papplet));

        shapes.add(new Shape(new int[][]{{0, 1, 1, 0},
                                         {0, 1, 1, 0}}, new int[]{255, 255, 0}, cellSize, papplet));

        shapes.add(new Shape(new int[][]{{0, 1, 1, 0},
                                         {0, 0, 1, 1}}, new int[]{255, 0, 0}, cellSize, papplet));

        shapes.add(new Shape(new int[][]{{0, 0, 1, 0},
                                         {0, 1, 1, 1}}, new int[]{128, 0, 128}, cellSize, papplet));

        shapes.add(new Shape(new int[][]{{0, 0, 1, 1},
                                         {0, 1, 1, 0}}, new int[]{0, 255, 0}, cellSize, papplet));
        pf.addShape(shapes.getRandomShape());
    }

    public void draw() {
        if (pf.lastShapeHasSurfaceBelow() && papplet.frameCount % 15 == 0) {
            pf.addShape(shapes.getRandomShape());
        } else if (papplet.frameCount % 30 == 0) {
            pf.moveDown();
        }
        pf.drawPlayfield();
    }

    public void keyPressed() {
        if (papplet.key == 'a' || papplet.key == 'A') {
            pf.moveLeft();
        }
        if (papplet.key == 'd' || papplet.key == 'D') {
            pf.moveRight();
        }
        if (papplet.key == 's' || papplet.key == 'S') {
            if(pf.lastShapeHasSurfaceBelow()) return;
            pf.moveDown();
        }
        if (papplet.key == ' ') {
            pf.drop();
        }
        if (papplet.key == 'r' || papplet.key == 'R') {
            pf.restart(shapes);
        }
    }
}