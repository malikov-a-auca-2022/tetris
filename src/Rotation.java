public class Rotation {

    public static void rotateLeft(Shape oldShape) {
        int[][] newShape = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newShape[3 - j][i] = oldShape.getShape()[i][j] ? 1: 0;
            }
        }
        oldShape.SHAPE = oldShape.intArrToBoolArr(newShape);
        oldShape.lowestRow = oldShape.lowestRow();
    }

    public static void rotateRight(Shape oldShape) {
        int[][] newShape = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newShape[j][3 - i] = oldShape.getShape()[i][j] ? 1 : 0;
            }
        }
        oldShape.SHAPE = oldShape.intArrToBoolArr(newShape);
        oldShape.lowestRow = oldShape.lowestRow();
    }
    public static void rotate180(Shape oldShape) {
        rotateRight(oldShape);
        rotateRight(oldShape);
    }
}
