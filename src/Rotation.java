public class Rotation {

    public static void rotateLeft(Shape oldShape, Menu menu, Playfield pf) {
        int size = oldShape.getSHAPE_HEIGHT();
        int[][] newShape = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newShape[size - 1 - j][i] = oldShape.getShape()[i][j] ? 1 : 0;
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (newShape[i][j] == 1) {
                    if (oldShape.yOnPlayfield + i >= menu.getACTUAL_HEIGHT_CELLS() - 1 ||
                                oldShape.yOnPlayfield + i <= 0 ||
                                oldShape.xOnPlayfield  + j >= menu.WIDTH_CELLS - 1 ||
                                oldShape.xOnPlayfield + j <= 0) {
                        return;
                    }
                    if (pf.playfield[oldShape.yOnPlayfield + i][oldShape.xOnPlayfield + j]) {
                        return;
                    }
                }
            }
        }

        oldShape.shape = oldShape.intArrToBoolArr(newShape);
    }

    public static void rotateRight(Shape oldShape, Menu menu, Playfield pf) {
        int size = oldShape.getSHAPE_HEIGHT();
        int[][] newShape = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newShape[j][size - 1 - i] = oldShape.getShape()[i][j] ? 1 : 0;
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (newShape[i][j] == 1) {
                    if (oldShape.yOnPlayfield + i >= menu.getACTUAL_HEIGHT_CELLS() - 1 ||
                            oldShape.yOnPlayfield + i <= 0 ||
                            oldShape.xOnPlayfield  + j >= menu.WIDTH_CELLS - 1 ||
                            oldShape.xOnPlayfield + j <= 0) {
                        return;
                    }
                    if (pf.playfield[oldShape.yOnPlayfield + i][oldShape.xOnPlayfield + j]) {
                        return;
                    }
                }
            }
        }
        oldShape.shape = oldShape.intArrToBoolArr(newShape);
    }
    public static void rotate180(Shape oldShape, Menu menu, Playfield pf) {
        rotateRight(oldShape, menu, pf);
        rotateRight(oldShape, menu, pf);
    }
}
