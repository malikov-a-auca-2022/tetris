import processing.core.PApplet;

public class Playfield {
    private boolean[][] playfield;
    private Menu menu;
    private PApplet papplet;

    Playfield(Menu menu, PApplet papplet) {
        this.menu = menu;
        this.papplet = papplet;
        playfield = new boolean[menu.getACTUAL_HEIGHT_CELLS()][menu.WIDTH_CELLS];
    }

    public void addShape(Shape shape) {
        for (int i = 0; i < shape.getSHAPE_HEIGHT(); i++) {
            for (int j = 0; j < shape.getSHAPE_WIDTH(); j++) {
                playfield[i][3 + j] = shape.getShape()[i][j];
            }
        }
        papplet.pushMatrix();
        papplet.translate(menu.getXPlayfield() + 3 * menu.getCellSize(), menu.getYPlayfieldActual());
        shape.drawShape(shape.getColor());
        papplet.popMatrix();
    }

    public void moveDown() {

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
