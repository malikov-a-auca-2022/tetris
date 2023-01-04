public class Rotation {
    public static int[][] rotateLeft(int[][] oldShape) {
//    public Shape rotateLeft(Shape oldShape) {
        int[][] newShape = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newShape[3 - j][i] = oldShape[i][j];
            }
        }
        return newShape;
//        return new Shape(newShape, oldShape.getColor(), oldShape.cellSize, oldShape.papplet);
    }
    public static int[][] rotateRight(int[][] oldShape) {
        int[][] newShape = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newShape[j][3 - i] = oldShape[i][j];
            }
        }
        return newShape;
    }
}
