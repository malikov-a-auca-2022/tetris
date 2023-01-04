import processing.core.*;

public class Shape implements Cloneable {

    private final int SHAPE_WIDTH = 4;

    public int getSHAPE_WIDTH() {
        return SHAPE_WIDTH;
    }

    public int getSHAPE_HEIGHT() {
        return SHAPE_HEIGHT;
    }

    private final int SHAPE_HEIGHT = 4;

    public boolean[][] SHAPE;

    public boolean[][] getShape() {
        return SHAPE;
    }

    private final int[] COLOR;

    public int[] getColor() {
        return COLOR;
    }

    public float cellSize;
    PApplet papplet;
    public int xOnPlayfield, yOnPlayfield;

    public int mostLeftPoint, mostRightPoint;

    Shape(int[][] shape, int[] color, float cellSize, PApplet papplet) {
        this.papplet = papplet;
        this.cellSize = cellSize;
        if (shape.length != 4 || shape[0].length != 4) {
            throw new RuntimeException("Wrong size of shape: size must be [2][4]");
        }
        SHAPE = intArrToBoolArr(shape);
        if (color.length != 3) throw new RuntimeException("Wrong size of color array: size must be 3");

        COLOR = color;

        mostRightPoint = -1;
        mostLeftPoint = SHAPE_WIDTH;
        for (int i = 0; i < SHAPE_HEIGHT; i++) {
            if (!hasCellInRow(i)) continue;
            for (int j = 0; j < SHAPE_WIDTH; j++) {
                if (SHAPE[i][j]) {
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
        boolean[][] newArr = new boolean[4][4];
        for (int i = 0; i < SHAPE_HEIGHT; i++) {
            for (int j = 0; j < SHAPE_WIDTH; j++) {
                newArr[i][j] = arr[i][j] == 1;
            }
        }
        return newArr;
    }

    public boolean hasCellInRow(int x) {
        if(x < 0 || x > 3) throw new RuntimeException("Invalid row number: it should either 0 or 1");
        for (int i = 0; i < SHAPE_WIDTH; i++) {
            if(SHAPE[x][i]) return true;
        }
        return false;
    }

    public int lowestRow() {
        int lowestRow = 0;
        for (int i = 0; i < SHAPE_HEIGHT; i++) {
            for (int j = 0; j <= mostRightPoint; j++) {
                if(SHAPE[i][j]) {
                    lowestRow = Math.max(lowestRow, i);
                    break;
                }
            }
        }
        return lowestRow;
    }

    public int mostLeftPointOnRow(int x) {
        for (int i = 0; i < SHAPE_WIDTH; i++) {
            if (SHAPE[x][i]) return i;
        }
        return -1;
    }

    public int mostRightPointOnRow(int x){
        for(int i = SHAPE_WIDTH - 1; i >= 0; i--) {
            if (SHAPE[x][i]) return i;
        }
        return -1;
    }

}