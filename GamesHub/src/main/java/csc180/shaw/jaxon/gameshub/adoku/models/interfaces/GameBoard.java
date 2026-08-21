package csc180.shaw.jaxon.gameshub.adoku.models.interfaces;

import csc180.shaw.jaxon.gameshub.adoku.models.Cell;

public interface GameBoard {
    int getSize();
    Cell getCell(int row, int col);
    void setValue(int row, int col, int value);
    boolean isComplete();

    int getSolutionValue(int row, int col);
    void setSolutionValue(int row, int col, int value);
}
