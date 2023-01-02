import processing.core.PApplet;

public class Playfield {
    private boolean[][] playfield;
    private Menu menu;
    private PApplet papplet;

    public Shape getLastShape() {
        return lastShape;
    }

    private Shape lastShape;
    private float xLastShape;
    private float yLastShape;

    public float getXLastShape() {
        return xLastShape;
    }

    public float getYLastShape() {
        return yLastShape;
    }

    Playfield(Menu menu, PApplet papplet) {
        this.menu = menu;
        this.papplet = papplet;
        playfield = new boolean[menu.getACTUAL_HEIGHT_CELLS()][menu.WIDTH_CELLS];
    }

    public void addShape(Shape shape) {
        lastShape = shape;
        for (int i = 0; i < shape.getSHAPE_HEIGHT(); i++) {
            for (int j = 0; j < shape.getSHAPE_WIDTH(); j++) {
                playfield[i][3 + j] = shape.getShape()[i][j];
            }
        }
        papplet.pushMatrix();
        xLastShape = menu.getXPlayfield() + 3 * menu.getCellSize();
        lastShape.x = 3;
        yLastShape = menu.getYPlayfieldActual();
        lastShape.y = 0;
        papplet.translate(xLastShape, yLastShape);
        shape.drawShape(shape.getColor());
        papplet.popMatrix();
    }

    public void moveDown() {
        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            for (int j = 0; j < lastShape.getSHAPE_WIDTH(); j++) {
                playfield[lastShape.y][lastShape.x] = false;
            }
        }
        papplet.pushMatrix();
        yLastShape += menu.getCellSize();
        lastShape.y++;
        papplet.translate(xLastShape, yLastShape);
        lastShape.drawShape(lastShape.getColor());
        papplet.popMatrix();
    }
    public void drawLastShape() {
        papplet.pushMatrix();
        papplet.translate(xLastShape, yLastShape);
        lastShape.drawShape(lastShape.getColor());
        papplet.popMatrix();
    }

    public int highestPointAtX(int x) {
        for (int i = 0; i < menu.getACTUAL_HEIGHT_CELLS(); i++) {
            if(playfield[x][i]) return i;
        }
        return -1;
    }

    private void lose() {
        throw new RuntimeException("You lost: shapes got too high");
    }
}
