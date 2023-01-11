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
        int xToBeInMiddle = (menu.WIDTH_CELLS - shape.getSHAPE_WIDTH()) / 2;
        int yToAppearAboveVisible = menu.getACTUAL_HEIGHT_CELLS() - menu.HEIGHT_CELLS - shape.getSHAPE_HEIGHT();
        for (int i = 0; i < shape.getSHAPE_HEIGHT(); i++) {
            for (int j = 0; j < shape.getSHAPE_WIDTH(); j++) {
                if(shape.getShape()[i][j]) {
                    playfield[yToAppearAboveVisible + i][xToBeInMiddle + j] = true;
                    colorOfCell[yToAppearAboveVisible + i][xToBeInMiddle + j] = shape.getColor();
                }
            }
        }
        xLastShape = menu.getXPlayfield() + xToBeInMiddle * menu.getCellSize();
        lastShape.xOnPlayfield = xToBeInMiddle;
        yLastShape = menu.getYPlayfieldActual() + yToAppearAboveVisible * menu.getCellSize();
        lastShape.yOnPlayfield = yToAppearAboveVisible;
    }

    public void drawPlayfield() {
        for(int i = menu.getACTUAL_HEIGHT_CELLS() - menu.HEIGHT_CELLS; i < menu.getACTUAL_HEIGHT_CELLS(); i++) {
            for (int j = 0; j < menu.WIDTH_CELLS; j++) {
                papplet.noStroke();
                papplet.pushMatrix();
                int[] rgb = colorOfCell[i][j];
                papplet.fill(rgb[0], rgb[1], rgb[2]);
                papplet.translate(menu.getXPlayfield(), menu.getYPlayfieldActual());
                float cellSize = menu.getCellSize();
                papplet.rect(cellSize * j, cellSize * i, cellSize, cellSize);
                papplet.popMatrix();
            }
        }
        shadow();
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
            for (int j = 0; j < lastShape.getSHAPE_WIDTH(); j++) {
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
    public boolean lastShapeHasSurfaceBelow(int y) {
        if (y + lastShape.lowestRow() >= menu.getACTUAL_HEIGHT_CELLS() - 1) {
            return true;
        }
        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            if(!lastShape.hasCellInRow(i)) continue;
            for (int j = 0; j < lastShape.getSHAPE_WIDTH(); j++) {
                if (lastShape.getShape()[i][j]) {
                    if (i != lastShape.getSHAPE_HEIGHT() - 1 && !lastShape.getShape()[i + 1][j] &&
                            playfield[y + i + 1][lastShape.xOnPlayfield + j]) {
                        return true;
                    }
                    if (i == lastShape.getSHAPE_HEIGHT() - 1 &&
                            playfield[y + i + 1][lastShape.xOnPlayfield + j]) {
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
        int yOnSurface = 0;
        for (int i = lastShape.yOnPlayfield; i < menu.getACTUAL_HEIGHT_CELLS(); i++) {
            if(lastShapeHasSurfaceBelow(i)) {
                yOnSurface = i;
                break;
            }
        }
        if(yOnSurface == lastShape.yOnPlayfield) {
            return;
        }
        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            if(!lastShape.hasCellInRow(i)) continue;
            for (int j = 0; j <= lastShape.mostRightPointOnRow(i); j++) {
                if(lastShape.getShape()[i][j]) {
                    if(playfield[yOnSurface + i][lastShape.xOnPlayfield + j]) continue;
                    colorOfCell[yOnSurface + i][lastShape.xOnPlayfield + j] = new int[] {80, 80, 80};
                }
            }
        }
    }
    public void unshadow() {
        int yOnSurface = 0;
        for (int i = lastShape.yOnPlayfield; i < menu.getACTUAL_HEIGHT_CELLS(); i++) {
            if(lastShapeHasSurfaceBelow(i)) {
                yOnSurface = i;
                break;
            }
        }
        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            if(!lastShape.hasCellInRow(i)) continue;
            for (int j = 0; j <= lastShape.mostRightPointOnRow(i); j++) {
                if(lastShape.getShape()[i][j]) {
                    colorOfCell[yOnSurface + i][lastShape.xOnPlayfield + j] = new int[] {0, 0, 0};
                }
            }
        }
    }

    public void drop() {
        int oldY = lastShape.yOnPlayfield;
        for (int i = lastShape.yOnPlayfield; i < menu.getACTUAL_HEIGHT_CELLS(); i++) {
            if(lastShapeHasSurfaceBelow(i)) {
                lastShape.yOnPlayfield = i;
                break;
            }
        }
        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            if(!lastShape.hasCellInRow(i)) continue;
            for (int j = 0; j <= lastShape.mostRightPointOnRow(i); j++) {
                if(lastShape.getShape()[i][j]) {
                    playfield[oldY + i][lastShape.xOnPlayfield + j] = false;
                    colorOfCell[oldY + i][lastShape.xOnPlayfield + j] = new int[]{0, 0, 0};
                }
            }
        }
        for (int i = 0; i < lastShape.getSHAPE_HEIGHT(); i++) {
            if(!lastShape.hasCellInRow(i)) continue;
            for (int j = 0; j <= lastShape.mostRightPointOnRow(i); j++) {
                if(lastShape.getShape()[i][j]) {
                    playfield[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j] = true;
                    colorOfCell[lastShape.yOnPlayfield + i][lastShape.xOnPlayfield + j] = lastShape.getColor();
                }
            }
        }
        yLastShape = menu.getYPlayfield() + lastShape.yOnPlayfield * menu.getCellSize();
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
        unshadow();

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
        unshadow();

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

    public void burnLines() {
        for (int i = menu.getACTUAL_HEIGHT_CELLS() - 1; i >= 0; i--) {
            boolean lineIsFull = true;
            for (int j = 0; j < menu.WIDTH_CELLS; j++) {
                if (!playfield[i][j]) {
                    lineIsFull = false;
                    break;
                }
            }
            if(lineIsFull) {
                burnLine(i);
                i++;
            }
        }
    }
    public void burnLine(int y) {
        for (int i = y; i >= menu.getACTUAL_HEIGHT_CELLS() - menu.HEIGHT_CELLS; i--) {
            for (int j = 0; j < menu.WIDTH_CELLS; j++) {
                playfield[i][j] = playfield[i - 1][j];
                colorOfCell[i][j] = colorOfCell[i - 1][j];
            }
        }
    }
}
