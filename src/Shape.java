import processing.core.*;

public class Shape {

    private final int SHAPE_WIDTH = 4;

    public int getSHAPE_WIDTH() {
        return SHAPE_WIDTH;
    }

    public int getSHAPE_HEIGHT() {
        return SHAPE_HEIGHT;
    }

    private final int SHAPE_HEIGHT = 2;

    private final boolean[][] SHAPE;

    public boolean[][] getShape() {
        return SHAPE;
    }

    private final int[] COLOR;

    public int[] getColor() {
        return COLOR;
    }

    private float cellSize;
    PApplet papplet;
    public int xOnPlayfield, yOnPlayfield;

//    Shape(boolean[][] shape, int[] color, float cellSize, PApplet papplet) {
//        this.papplet = papplet;
//        this.cellSize = cellSize;
//        if (shape.length != 2 || shape[0].length != 4) {
//            throw new RuntimeException("Wrong size of shape: size must be [2][4]");
//        }
//        SHAPE = shape;
//        if (color.length != 3) {
//            throw new RuntimeException("Wrong size of color array: size must be 3");
//        }
//        COLOR = color;
//    }

    Shape(int[][] shape, int[] color, float cellSize, PApplet papplet) {
        this.papplet = papplet;
        this.cellSize = cellSize;
        if (shape.length != 2 || shape[0].length != 4) {
            throw new RuntimeException("Wrong size of shape: size must be [2][4]");
        }
        SHAPE = new boolean[2][4];
        for (int i = 0; i < SHAPE_HEIGHT; i++) {
            for (int j = 0; j < SHAPE_WIDTH; j++) {
                if(shape[i][j] == 1) {
                    SHAPE[i][j] = true;
                } else {
                    SHAPE[i][j] = false;
                }
            }
        }
        if (color.length != 3) {
            throw new RuntimeException("Wrong size of color array: size must be 3");
        }
        COLOR = color;

        mostLeftPointOnBottom = mostLeftPointOnBottom();
        mostLeftPoint = mostLeftPoint();
        mostRightPointOnBottom = mostRightPointOnBottom();
        mostRightPoint = mostRightPoint();

        mostRightPointOnTop = mostRightPointOnTop();
        mostLeftPointOnTop = mostLeftPointOnTop();
    }

    public void drawShape(int[] color) {
        papplet.fill(color[0], color[1], color[2]);
        papplet.noStroke();
        for (int i = 0; i < SHAPE_HEIGHT; i++) {
            for (int j = 0; j < SHAPE_WIDTH; j++) {
                if (SHAPE[i][j]) {
                    papplet.rect(cellSize * j, cellSize * i, cellSize, cellSize);
                }
            }
        }
    }

    public int mostLeftPointOnBottom, mostRightPointOnBottom;
    public int mostLeftPointOnTop, mostRightPointOnTop;
    public int mostLeftPoint, mostRightPoint;

    private int mostLeftPointOnBottom() {
        for(int i = 0; i < SHAPE_WIDTH; i++) {
            if(SHAPE[1][i]) return i;
        }
        return -1;
    }
    private int mostRightPointOnBottom() {
        for(int i = SHAPE_WIDTH - 1; i >= 0; i--) {
            if(SHAPE[1][i]) return i;
        }
        return -1;
    }
    private int mostLeftPointOnTop() {
        for (int i = 0; i < SHAPE_WIDTH; i++) {
            if(SHAPE[0][i]) return i;
        }
        return 0;
    }
    private int mostRightPointOnTop() {
        for (int i = SHAPE_WIDTH - 1; i >= 0; i--) {
            if(SHAPE[0][i]) return i;
        }
        return -1;
    }
    private int mostLeftPoint() {
        int min = 5;
        for (int i = 0; i < SHAPE_HEIGHT; i++) {
            for(int j = 0; j < SHAPE_WIDTH; j++) {
                if(SHAPE[i][j]) {
                    min = Math.min(min, j);
                }
            }
        }
        return min;
    }
    private int mostRightPoint() {
        int min = SHAPE_WIDTH;
        for (int i = 0; i < SHAPE_HEIGHT; i++) {
            for (int j = SHAPE_WIDTH - 1; j >= 0; j--) {
                if(SHAPE[i][j]) {
                    min = Math.min(min, j);
                }
            }
        }
        return min;
    }
}