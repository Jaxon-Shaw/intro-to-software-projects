package csc180.shaw.jaxon.gameshub.adoku.models;

import csc180.shaw.jaxon.gameshub.adoku.models.interfaces.GameBoard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BoardGenerator {
    private static final Random rng = new Random();

    private static void fillDiagonal(int[][] grid, int size, int boxSize) {
        for (int i = 0; i < size; i += boxSize) {
            fillBox(grid, size, boxSize, i, i);
        }
    }

    private static void fillBox(int[][] grid, int size, int boxSize, int rowStart, int colStart) {
        List<Integer> nums = new ArrayList<>();
        for (int n = 1; n <= size; n++) {
            nums.add(n);
        }
        Collections.shuffle(nums, rng);

        int idx = 0;
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                grid[rowStart + i][colStart + j] = nums.get(idx++);
            }
        }
    }

    private static boolean fillRemaining(int[][] grid, int size, int boxSize, int row, int col) {

        if (row == size)
            return true;

        if (col == size)
            return fillRemaining(grid, size, boxSize, row + 1, 0);

        if (grid[row][col] != 0)
            return fillRemaining(grid, size, boxSize, row, col + 1);

        List<Integer> possibleNums = new ArrayList<>();
        for (int num = 1; num <= size; num++) {
            possibleNums.add(num);
        }
        Collections.shuffle(possibleNums, rng);

        for (int num : possibleNums) {
            if (isSafe(grid, size, boxSize, row, col, num)) {
                grid[row][col] = num;
                if (fillRemaining(grid, size, boxSize, row, col + 1))
                    return true;
                grid[row][col] = 0;
            }
        }

        return false;
    }

    private static boolean isSafe(int[][] grid, int size, int boxSize, int row, int col, int num) {
        return unusedInRow(grid, size, row, num)
                && unusedInCol(grid, size, col, num)
                && unusedInBox(grid, boxSize, row - row % boxSize, col - col % boxSize, num);
    }

    private static boolean unusedInRow(int[][] grid, int size, int row, int num) {
        for (int col = 0; col < size; col++) {
            if (grid[row][col] == num)
                return false;
        }
        return true;
    }

    private static boolean unusedInCol(int[][] grid, int size, int col, int num) {
        for (int row = 0; row < size; row++) {
            if (grid[row][col] == num)
                return false;
        }
        return true;
    }

    private static boolean unusedInBox(int[][] grid, int boxSize, int rowStart, int colStart, int num) {
        for (int r = 0; r < boxSize; r++) {
            for (int c = 0; c < boxSize; c++) {
                if (grid[rowStart + r][colStart + c] == num)
                    return false;
            }
        }
        return true;
    }

    private static void removeDigits(int[][] grid, int size, int emptyCells) {

        emptyCells = Math.min(emptyCells, size * size);


        List<Integer> positions = new ArrayList<>();

        for (int i = 0; i < size * size; i++) {
            positions.add(i);
        }

        Collections.shuffle(positions, rng);

        for (int i = 0; i < emptyCells; i++) {
            int cellId = positions.get(i);
            int row = cellId / size;
            int col = cellId % size;

            grid[row][col] = 0;
        }

    }

    public static GameBoard generate(GameBoard board, int emptyCells) {

        int size = board.getSize();
        int boxSize = (int) Math.sqrt(size);

        int[][] grid = new int[size][size];
        boolean solved = false;
        int maxAttempts = 100;

        for (int attempt = 0; attempt < maxAttempts && !solved; attempt++) {
            grid = new int[size][size];
            fillDiagonal(grid, size, boxSize);
            solved = fillRemaining(grid, size, boxSize, 0, 0);
        }

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board.setSolutionValue(row, col, grid[row][col]);
            }
        }

        removeDigits(grid, size, emptyCells);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board.setValue(row, col, grid[row][col]);
                board.getCell(row, col).setFixed(grid[row][col] != 0);
            }
        }

        return board;
    }
}

