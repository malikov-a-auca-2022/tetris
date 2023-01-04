public class Rotation {

    public static Shape rotateLeft(Shape oldShape) {
        int[][] newShape = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newShape[3 - j][i] = oldShape.getShape()[i][j] ? 1: 0;
            }
        }
        return new Shape(newShape, oldShape.getColor(), oldShape.cellSize, oldShape.papplet);
    }

    public static Shape rotateRight(Shape oldShape) {
        int[][] newShape = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newShape[j][3 - i] = oldShape.getShape()[i][j] ? 1 : 0;
            }
        }
        return new Shape(newShape, oldShape.getColor(), oldShape.cellSize, oldShape.papplet);
    }
    public static Shape rotate180(Shape oldShape) {
        return rotateRight(rotateRight(oldShape));
    }
}
