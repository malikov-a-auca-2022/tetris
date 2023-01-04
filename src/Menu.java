import processing.core.*;

class Menu {
    private PApplet papplet;
    public final int WIDTH_CELLS = 10;
    public final int HEIGHT_CELLS = 20;

    public int getACTUAL_HEIGHT_CELLS() {
        return ACTUAL_HEIGHT_CELLS;
    }
    private final int ACTUAL_HEIGHT_CELLS = 24;

    private float cellSize;

    public float getCellSize() {
        return cellSize;
    }

    private float playfieldWidth, playfieldHeight;
    private float playfieldActualHeight;

    public float getPlayfieldWidth() {
        return playfieldWidth;
    }

    public float getPlayfieldHeight() {
        return playfieldHeight;
    }

    public float getXPlayfield() {
        return xPlayfield;
    }

    public float getYPlayfield() {
        return yPlayfield;
    }

    private float xPlayfield;
    private float yPlayfield;

    public float getYPlayfieldActual() {
        return yPlayfieldActual;
    }

    private float yPlayfieldActual;
    private float stroke;

    Menu(PApplet papplet) {
        this.papplet = papplet;
        playfieldHeight = papplet.height * 0.8f;
        cellSize = playfieldHeight / HEIGHT_CELLS;
        playfieldActualHeight = playfieldHeight + (ACTUAL_HEIGHT_CELLS - HEIGHT_CELLS) * cellSize;
        playfieldWidth = cellSize * WIDTH_CELLS;
        xPlayfield = (papplet.width - playfieldWidth) / 2f;
        yPlayfield = (papplet.height - playfieldHeight) / 2f;
        yPlayfieldActual = yPlayfield - (ACTUAL_HEIGHT_CELLS - HEIGHT_CELLS) * cellSize;
        stroke = cellSize / 20f;
    }

    public void drawMenu() {
//        papplet.fill(20, 20, 20);
//        papplet.rect(xPlayfield, yPlayfield, playfieldWidth, playfieldHeight);

        papplet.stroke(255);
        papplet.strokeWeight(stroke);

        papplet.pushMatrix();
        papplet.translate(xPlayfield, yPlayfield);
        for (int i = 0; i <= WIDTH_CELLS; i++) {
            papplet.line(0, 0, 0, playfieldHeight);
            papplet.translate(cellSize, 0);
        }
        papplet.popMatrix();

        papplet.pushMatrix();
        papplet.translate(xPlayfield, yPlayfield);
        for (int i = 0; i <= HEIGHT_CELLS; i++) {
            papplet.line(0, 0, playfieldWidth, 0);
            papplet.translate(0, cellSize);
        }
        papplet.popMatrix();
    }
}
