package csc180.shaw.jaxon.gameshub.adoku.models.boards;

import csc180.shaw.jaxon.gameshub.adoku.models.Cell;
import csc180.shaw.jaxon.gameshub.adoku.models.interfaces.GameBoard;

import java.util.ArrayList;

public class Board extends Cell implements GameBoard {
    private final int size;
    private final Cell[][] cells;

    protected Board(int size) {
        this.size = size;
        this.cells = new Cell[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                cells[row][col] = new Cell();
            }
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    @Override
    public void setValue(int row, int col, int value) {
        cells[row][col].setValue(value);
    }

    @Override
    public boolean isComplete() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (cells[row][col].getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
