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
    }

    public void drawShape(int[] color) {
        papplet.fill(color[0], color[1], color[2]);
        papplet.noStroke();
        for (int i = 0; i < SHAPE_HEIGHT; i++) {
            for (int j = 0; j < SHAPE_WIDTH; j++) {
                if (SHAPE[i][j]) {
                    papplet.rect(cellSize * i, cellSize * j, cellSize, cellSize);
                }
            }
        }
    }

}