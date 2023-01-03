import processing.core.PApplet;

public class Playfield {
    private boolean[][] playfield;
    private int[][][] colorOfCell;
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
        colorOfCell = new int[menu.getACTUAL_HEIGHT_CELLS()][menu.WIDTH_CELLS][3];
    }

    public void addShape(Shape shape) {
        lastShape = shape;
        for (int i = 0; i < shape.getSHAPE_HEIGHT(); i++) {
            for (int j = 0; j < shape.getSHAPE_WIDTH(); j++) {
                playfield[i][3 + j] = shape.getShape()[i][j];
                colorOfCell[i][3 + j] = shape.getColor();
            }
        }
//        papplet.pushMatrix();
        xLastShape = menu.getXPlayfield() + 3 * menu.getCellSize();
        lastShape.xOnPlayfield = 3;
        yLastShape = menu.getYPlayfieldActual();
        lastShape.yOnPlayfield = 0;
//        papplet.translate(xLastShape, yLastShape);
//        shape.drawShape(shape.getColor());
//        papplet.popMatrix();
    }

    public void drawPlayfield() {
        for(int i = 0; i < menu.getACTUAL_HEIGHT_CELLS(); i++) {
            for (int j = 0; j < menu.WIDTH_CELLS; j++) {
                if(playfield[i][j]) {
                    papplet.pushMatrix();
                    int[] rgb = colorOfCell[i][j];
                    papplet.fill(rgb[0], rgb[1], rgb[2]);
                    papplet.translate(menu.getXPlayfield(), menu.getYPlayfieldActual());
                    float cellSize = menu.getCellSize();
                    papplet.rect(cellSize * j, cellSize * i, cellSize, cellSize);
                    papplet.popMatrix();
                }
            }
        }
    }

    public void moveDown() {
        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            for (int j = 0; j < lastShape.getSHAPE_WIDTH(); j++) {
                if(lastShape.getShape()[i][j]) {
                    playfield[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j] = false;
                    colorOfCell[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j] = new int[]{0, 0, 0};
                }
            }
        }
        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            for (int j = 0; j < lastShape.getSHAPE_WIDTH(); j++) {
                if(lastShape.getShape()[i][j]) {
                    playfield[lastShape.yOnPlayfield + i + 1][lastShape.xOnPlayfield + j] = true;
                    colorOfCell[lastShape.yOnPlayfield + i + 1][lastShape.xOnPlayfield + j] = lastShape.getColor();
                }
            }
        }
//        papplet.fill(255);
//        papplet.rect(xLastShape, yLastShape, menu.getCellSize() * 4f, menu.getCellSize() * 2);

//        papplet.pushMatrix();
        yLastShape += menu.getCellSize();
        lastShape.yOnPlayfield++;
//        papplet.translate(xLastShape, yLastShape);
//        lastShape.drawShape(lastShape.getColor());
//        papplet.popMatrix();
    }

    public void drawLastShape() {
        papplet.fill(255);
        papplet.rect(xLastShape, yLastShape, menu.getCellSize() * 4f, menu.getCellSize() * 2);
        papplet.pushMatrix();
        papplet.translate(xLastShape, yLastShape);
        lastShape.drawShape(lastShape.getColor());
        papplet.popMatrix();
    }

    public boolean lastShapeHasSurfaceBelow() {
        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            for (int j = 0; j <= lastShape.mostRightPointOnBottom; j++) {
                if (lastShape.getShape()[i][j]) {
                    if (lastShape.yOnPlayfield + 2 >= menu.getACTUAL_HEIGHT_CELLS() ||
                            (playfield[lastShape.yOnPlayfield + 1][lastShape.xOnPlayfield + j] &&
                                    i != 1 && !lastShape.getShape()[i + 1][j]) ||
                            (playfield[lastShape.yOnPlayfield + 2][lastShape.xOnPlayfield + j])) {
                        return true;
                    }
                }
            }
        }
        return false;
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

    public void moveLeft() {
        if (lastShape.xOnPlayfield + lastShape.mostLeftPointOnTop <= 0 ||
                lastShape.xOnPlayfield + lastShape.mostLeftPointOnBottom <= 0 ||
                playfield[lastShape.yOnPlayfield][lastShape.xOnPlayfield + lastShape.mostLeftPointOnTop - 1] ||
                playfield[lastShape.yOnPlayfield + 1][lastShape.xOnPlayfield + lastShape.mostLeftPointOnBottom - 1]) {
            return;
        }

        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            for (int j = 0; j <= lastShape.mostRightPointOnBottom || j <= lastShape.mostRightPointOnTop; j++) {
                if(lastShape.getShape()[i][j]) {
                    playfield[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j] = false;
                    colorOfCell[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j] = new int[]{0, 0, 0};
                }
            }
        }
        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            for (int j = 0; j <= lastShape.mostRightPointOnBottom || j <= lastShape.mostRightPointOnTop; j++) {
                if(lastShape.getShape()[i][j]) {
                    playfield[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j - 1] = true;
                    colorOfCell[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j - 1] = lastShape.getColor();
                }
            }
        }

        lastShape.xOnPlayfield--;
        xLastShape -= menu.getCellSize();
    }

    public void moveRight() {
        if (lastShape.xOnPlayfield + lastShape.mostRightPointOnBottom >= menu.WIDTH_CELLS - 1 ||
                lastShape.xOnPlayfield + lastShape.mostRightPointOnTop >= menu.WIDTH_CELLS - 1 ||
                playfield[lastShape.yOnPlayfield][lastShape.xOnPlayfield + lastShape.mostRightPointOnTop + 1] ||
                playfield[lastShape.yOnPlayfield + 1][lastShape.xOnPlayfield + lastShape.mostRightPointOnBottom + 1]) {
            return;
        }
        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            for (int j = 0; j <= lastShape.mostRightPointOnBottom || j <= lastShape.mostRightPointOnTop; j++) {
                if(lastShape.getShape()[i][j]) {
                    playfield[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j] = false;
                    colorOfCell[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j] = new int[]{0, 0, 0};
                }
            }
        }
        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            for (int j = 0; j <= lastShape.mostRightPointOnBottom || j <= lastShape.mostRightPointOnTop; j++) {
                if(lastShape.getShape()[i][j]) {
                    playfield[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j + 1] = true;
                    colorOfCell[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j + 1] = lastShape.getColor();
                }
            }
        }
        lastShape.xOnPlayfield++;
        xLastShape += menu.getCellSize();

    }
}
