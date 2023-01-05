public class Rotation {

    public static void rotateLeft(Shape oldShape, Menu menu, Playfield pf) {
        int[][] newShape = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newShape[3 - j][i] = oldShape.getShape()[i][j] ? 1: 0;
            }
        }

        for (int i = 0; i < oldShape.getSHAPE_HEIGHT(); i++) {
            for (int j = 0; j < oldShape.getSHAPE_WIDTH(); j++) {
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

        oldShape.SHAPE = oldShape.intArrToBoolArr(newShape);
    }

    public static void rotateRight(Shape oldShape, Menu menu, Playfield pf) {
        int[][] newShape = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newShape[j][3 - i] = oldShape.getShape()[i][j] ? 1 : 0;
            }
        }
        for (int i = 0; i < oldShape.getSHAPE_HEIGHT(); i++) {
            for (int j = 0; j < oldShape.getSHAPE_WIDTH(); j++) {
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
        oldShape.SHAPE = oldShape.intArrToBoolArr(newShape);
    }
    public static void rotate180(Shape oldShape, Menu menu, Playfield pf) {
        rotateRight(oldShape, menu, pf);
        rotateRight(oldShape, menu, pf);
    }
}
