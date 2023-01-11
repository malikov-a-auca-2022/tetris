import processing.core.*;

public class Gameplay  {
    private Shapes shapes = new Shapes();
    private Shapes stableShapes;
    private PApplet papplet;
    private Menu menu;
    private Playfield pf;

    Gameplay(Menu menu, PApplet papplet)  {
        this.papplet = papplet;
        this.menu = menu;
        pf = new Playfield(menu, papplet);

        float cellSize = menu.getCellSize();
        shapes.add(new Shape(new int[][]{{0, 0, 0},
                                         {1, 0, 0},
                                         {1, 1, 1}}, new int[]{5, 41, 185}, cellSize, papplet));

        shapes.add(new Shape(new int[][]{{0, 0, 0, 0},
                                         {0, 0, 0, 0},
                                         {0, 0, 0, 0},
                                         {1, 1, 1, 1}}, new int[]{0, 255, 255}, cellSize, papplet));

        shapes.add(new Shape(new int[][]{{0, 0, 0},
                                         {0, 0, 1},
                                         {1, 1, 1}}, new int[]{255, 165, 0}, cellSize, papplet));

        shapes.add(new Shape(new int[][]{{1, 1},
                                         {1, 1}}, new int[]{255, 255, 0}, cellSize, papplet));

        shapes.add(new Shape(new int[][]{{0, 0, 0},
                                         {1, 1, 0},
                                         {0, 1, 1}}, new int[]{255, 0, 0}, cellSize, papplet));

        shapes.add(new Shape(new int[][]{{0, 0, 0},
                                         {0, 1, 0},
                                         {1, 1, 1}}, new int[]{128, 0, 128}, cellSize, papplet));

        shapes.add(new Shape(new int[][]{{0, 0, 0},
                                         {0, 1, 1},
                                         {1, 1, 0}}, new int[]{0, 255, 0}, cellSize, papplet));
        stableShapes = shapes;
        pf.addShape(shapes.getRandomShape());
    }

    private int lastMovementFrameCount = 0;
    private int movementOnSurfaceCounter = 0;

    public void draw() {
        if ((pf.lastShapeHasSurfaceBelow() && papplet.frameCount - lastMovementFrameCount >= 20) || movementOnSurfaceCounter >= 10) {
            pf.burnLines();
            pf.addShape(stableShapes.getRandomShape());
            movementOnSurfaceCounter = 0;
        } else if (!pf.lastShapeHasSurfaceBelow() && papplet.frameCount % 30 == 0 &&
                (!papplet.keyPressed || (papplet.keyPressed && (papplet.key != 's' || papplet.key != 'S')))) {
            pf.moveDown();
            lastMovementFrameCount = papplet.frameCount;
            movementOnSurfaceCounter = 0;
        }
        pf.drawPlayfield();

    }

    public void keyPressed()  {
        if (pf.lastShapeHasSurfaceBelow()) {
            movementOnSurfaceCounter++;
        }
        if (papplet.key == 'a' || papplet.key == 'A') {
            pf.moveLeft();
            lastMovementFrameCount = papplet.frameCount;
        }
        if (papplet.key == 'd' || papplet.key == 'D') {
            pf.moveRight();
            lastMovementFrameCount = papplet.frameCount;
        }
        if (papplet.key == 's' || papplet.key == 'S') {
            if(pf.lastShapeHasSurfaceBelow()) return;
            pf.moveDown();
            lastMovementFrameCount = papplet.frameCount;
        }
        if (papplet.key == ' ') {
            pf.drop();
            movementOnSurfaceCounter = 10;
        }
        if (papplet.key == 'r' || papplet.key == 'R') {
            pf.restart(stableShapes);
        }
        if (papplet.keyCode == papplet.LEFT) {
            pf.unshadow();
            pf.rotateLeft();
            lastMovementFrameCount = papplet.frameCount;
        }
        if (papplet.keyCode == papplet.RIGHT) {
            pf.unshadow();
            pf.rotateRight();
            lastMovementFrameCount = papplet.frameCount;
        }
        if (papplet.keyCode == papplet.UP) {
            pf.unshadow();
            pf.rotate180();
            lastMovementFrameCount = papplet.frameCount;
        }
    }
}