package csc180.shaw.jaxon.gameshub.adoku.models;

import csc180.shaw.jaxon.gameshub.adoku.models.interfaces.GameBoard;

public class BoardChecker {

    public static boolean isValidMove(GameBoard board, int row, int col, int value) {

        int size = board.getSize();

        if (value < 1 || value > size) {
            return false;
        }

        if (board.getCell(row, col).isFixed()) {
            return false;
        }

        return value == board.getSolutionValue(row, col);
    }

    private static boolean isRowValid(GameBoard board, int row, int value) {
        int size = board.getSize();

        for (int col = 0; col < size; col++) {
            if (board.getCell(row, col).getValue() == value) {
                return false;
            }
        }
        return true;
    }

    private static boolean isColValid(GameBoard board, int col, int value) {
        int size = board.getSize();

        for (int row = 0; row < size; row++) {
            if (board.getCell(row, col).getValue() == value) {
                return false;
            }
        }
        return true;
    }

    private static boolean isBoxValid(GameBoard board, int row, int col, int value) {

        int size = board.getSize();
        int boxSize = (int) Math.sqrt(size);

        int boxRowStart = (row / boxSize) * boxSize;
        int boxColStart = (col / boxSize) * boxSize;

        for (int r = boxRowStart; r < boxRowStart + boxSize; r++) {
            for (int c = boxColStart; c < boxColStart + boxSize; c++) {
                if (board.getCell(r, c).getValue() == value) {
                    return false;
                }
            }
        }

        return true;
    }
}
