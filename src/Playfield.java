import processing.core.PApplet;

public class Playfield {
    public boolean[][] playfield;
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

    public void addShape(Shape shape)  {
        lastShape = (Shape) shape.clone();
        for (int i = 0; i < shape.getSHAPE_HEIGHT(); i++) {
            for (int j = 0; j < shape.getSHAPE_WIDTH(); j++) {
                playfield[i][3 + j] = shape.getShape()[i][j];
                colorOfCell[i][3 + j] = shape.getColor();
            }
        }
        xLastShape = menu.getXPlayfield() + 3 * menu.getCellSize();
        lastShape.xOnPlayfield = 3;
        yLastShape = menu.getYPlayfieldActual();
        lastShape.yOnPlayfield = 0; //change it to 1
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
            if(!lastShape.hasCellInRow(i)) continue;
            for (int j = 0; j <= lastShape.mostRightPointOnRow(i); j++) {
                if(lastShape.getShape()[i][j]) {
                    playfield[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j] = false;
                    colorOfCell[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j] = new int[]{0, 0, 0};
                }
            }
        }
        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            if(!lastShape.hasCellInRow(i)) continue;
            for (int j = 0; j <= lastShape.mostRightPointOnRow(i); j++) {
                if(lastShape.getShape()[i][j]) {
                    playfield[lastShape.yOnPlayfield + i + 1][lastShape.xOnPlayfield + j] = true;
                    colorOfCell[lastShape.yOnPlayfield + i + 1][lastShape.xOnPlayfield + j] = lastShape.getColor();
                }
            }
        }
        yLastShape += menu.getCellSize();
        lastShape.yOnPlayfield++;
    }

    public boolean lastShapeHasSurfaceBelow() {
        if (lastShape.yOnPlayfield + lastShape.lowestRow() >= menu.getACTUAL_HEIGHT_CELLS() - 1) {
            return true;
        }
        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            if(!lastShape.hasCellInRow(i)) continue;
            for (int j = 0; j <= lastShape.mostRightPointOnRow(i); j++) {
                if (lastShape.getShape()[i][j]) {
                    if (i != lastShape.getSHAPE_HEIGHT() - 1 && !lastShape.getShape()[i + 1][j] &&
                            playfield[lastShape.yOnPlayfield + i + 1][lastShape.xOnPlayfield + j]) {
                        return true;
                    }
                    if (i == lastShape.getSHAPE_HEIGHT() - 1 &&
                            playfield[lastShape.yOnPlayfield + i + 1][lastShape.xOnPlayfield + j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void rotateLeft() {
        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            if(!lastShape.hasCellInRow(i)) continue;
            for (int j = 0; j <= lastShape.mostRightPointOnRow(i); j++) {
                if(lastShape.getShape()[i][j]) {
                    playfield[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j] = false;
                    colorOfCell[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j] = new int[]{0, 0, 0};
                }
            }
        }
//        int oldXOnPlayfield = lastShape.xOnPlayfield;
//        int oldYOnPlayfield = lastShape.yOnPlayfield;
        Rotation.rotateLeft(lastShape, menu, this);
//        lastShape.xOnPlayfield = oldXOnPlayfield;
//        lastShape.yOnPlayfield = oldYOnPlayfield;
//        drawPlayfield();
    }

    public void rotateRight() {
        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            if(!lastShape.hasCellInRow(i)) continue;
            for (int j = 0; j <= lastShape.mostRightPointOnRow(i); j++) {
                if(lastShape.getShape()[i][j]) {
                    playfield[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j] = false;
                    colorOfCell[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j] = new int[]{0, 0, 0};
                }
            }
        }
//        int oldXOnPlayfield = lastShape.xOnPlayfield;
//        int oldYOnPlayfield = lastShape.yOnPlayfield;
        Rotation.rotateRight(lastShape, menu, this);
//        lastShape.xOnPlayfield = oldXOnPlayfield;
//        lastShape.yOnPlayfield = oldYOnPlayfield;
//        drawPlayfield();
    }

    public void rotate180() {
        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            if(!lastShape.hasCellInRow(i)) continue;
            for (int j = 0; j <= lastShape.mostRightPointOnRow(i); j++) {
                if(lastShape.getShape()[i][j]) {
                    playfield[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j] = false;
                    colorOfCell[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j] = new int[]{0, 0, 0};
                }
            }
        }
//        int oldXOnPlayfield = lastShape.xOnPlayfield;
//        int oldYOnPlayfield = lastShape.yOnPlayfield;
        Rotation.rotate180(lastShape, menu, this);
//        lastShape.xOnPlayfield = oldXOnPlayfield;
//        lastShape.yOnPlayfield = oldYOnPlayfield;
//        drawPlayfield();
    }

    public int highestPointAtX(int x) {
        for (int i = 0; i < menu.getACTUAL_HEIGHT_CELLS(); i++) {
            if(playfield[x][i]) return i;
        }
        return -1;
    }

    public void shadow() {

    }

    public void drop() {

    }

    private void lose() {
        throw new RuntimeException("You lost: shapes got too high");
    }

    public void moveLeft() {
        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            if (!lastShape.hasCellInRow(i)){
                continue;
            }
            if (lastShape.xOnPlayfield + lastShape.mostLeftPointOnRow(i) <= 0) {
                return;
            }
            if (playfield[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + lastShape.mostLeftPointOnRow(i) - 1]) {
                return;
            }
        }

        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            if(!lastShape.hasCellInRow(i)) continue;
            for (int j = 0; j <= lastShape.mostRightPoint; j++) {
                if(lastShape.getShape()[i][j]) {
                    playfield[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j] = false;
                    colorOfCell[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j] = new int[]{0, 0, 0};
                }
            }
        }
        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            if(!lastShape.hasCellInRow(i)) continue;
            for (int j = 0; j <= lastShape.mostRightPoint; j++) {
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
        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            if (!lastShape.hasCellInRow(i)) {
                continue;
            }
            if (lastShape.xOnPlayfield + getLastShape().mostRightPointOnRow(i) >= menu.WIDTH_CELLS - 1) {
                return;
            }
            if (playfield[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + lastShape.mostRightPointOnRow(i) + 1]) {
                return;
            }
        }

        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            if(!lastShape.hasCellInRow(i)) continue;
            for (int j = 0; j <= lastShape.mostRightPoint; j++) {
                if(lastShape.getShape()[i][j]) {
                    playfield[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j] = false;
                    colorOfCell[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j] = new int[]{0, 0, 0};
                }
            }
        }
        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            if(!lastShape.hasCellInRow(i)) continue;
            for (int j = 0; j <= lastShape.mostRightPoint; j++) {
                if(lastShape.getShape()[i][j]) {
                    playfield[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j + 1] = true;
                    colorOfCell[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j + 1] = lastShape.getColor();
                }
            }
        }
        lastShape.xOnPlayfield++;
        xLastShape += menu.getCellSize();

    }

    public void restart(Shapes shapes)  {
        for (int i = 0; i < menu.getACTUAL_HEIGHT_CELLS(); i++) {
            for (int j = 0; j < menu.WIDTH_CELLS; j++) {
                playfield[i][j] = false;
                colorOfCell[i][j] = new int[] {0, 0, 0};
            }
        }
        addShape(shapes.getRandomShape());
    }
}
