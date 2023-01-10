import processing.core.*;

public class Shape implements Cloneable {

    private final int SHAPE_WIDTH;
    private final int SHAPE_HEIGHT;

    public int getSHAPE_WIDTH() {
        return SHAPE_WIDTH;
    }

    public int getSHAPE_HEIGHT() {
        return SHAPE_HEIGHT;
    }

    public boolean[][] shape;

    public boolean[][] getShape() {
        return shape;
    }

    private final int[] COLOR;

    public int[] getColor() {
        return COLOR;
    }

    public float cellSize;
    public PApplet papplet;
    public int xOnPlayfield, yOnPlayfield;

    public int mostLeftPoint, mostRightPoint;

    Shape(int[][] shape, int[] color, float cellSize, PApplet papplet) {
        this.papplet = papplet;

        this.cellSize = cellSize;
        if (shape.length != shape[0].length) {
            throw new RuntimeException("Wrong size of shape: array must be square");
        }

        SHAPE_HEIGHT = shape.length;
        SHAPE_WIDTH = shape[0].length;
        this.shape = intArrToBoolArr(shape);

        if (color.length != 3) throw new RuntimeException("Wrong size of color array: size must be 3");
        COLOR = color;

        mostRightPoint = -1;
        mostLeftPoint = SHAPE_WIDTH;
        for (int i = 0; i < SHAPE_HEIGHT; i++) {
            if (!hasCellInRow(i)) continue;
            for (int j = 0; j < SHAPE_WIDTH; j++) {
                if (this.shape[i][j]) {
                    mostRightPoint = Math.max(mostRightPoint, j);
                    mostLeftPoint = Math.min(mostLeftPoint, j);
                }
            }
        }
    }

    public Object clone()  {
        try {
            return super.clone();
        } catch (CloneNotSupportedException exception) {
            throw new RuntimeException("Clone Exception");
        }
    }

    public boolean[][] intArrToBoolArr(int[][] arr) {
        boolean[][] newArr = new boolean[SHAPE_HEIGHT][SHAPE_WIDTH];
        for (int i = 0; i < SHAPE_HEIGHT; i++) {
            for (int j = 0; j < SHAPE_WIDTH; j++) {
                newArr[i][j] = arr[i][j] == 1;
            }
        }
        return newArr;
    }

    public boolean hasCellInRow(int x) {
        if (x != 0 && x != 1 && x != 2 && x != 3) {
            throw new RuntimeException("Invalid row number: it should be number from 0...3");
        }
        for (int i = 0; i < SHAPE_WIDTH; i++) {
            if(shape[x][i]) return true;
        }
        return false;
    }

    public int lowestRow() {
        int lowestRow = 0;
        for (int i = 0; i < SHAPE_HEIGHT; i++) {
            for (int j = 0; j <= mostRightPoint; j++) {
                if(shape[i][j]) {
                    lowestRow = Math.max(lowestRow, i);
                    break;
                }
            }
        }
        return lowestRow;
    }

    public int mostLeftPointOnRow(int x) {
        for (int i = 0; i < SHAPE_WIDTH; i++) {
            if (shape[x][i]) return i;
        }
        return -1;
    }

    public int mostRightPointOnRow(int x){
        for(int i = SHAPE_WIDTH - 1; i >= 0; i--) {
            if (shape[x][i]) return i;
        }
        return -1;
    }

}