package csc180.shaw.jaxon.gameshub.blockFill.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.util.Random;

import static csc180.shaw.jaxon.gameshub.HelloController.getT;

public class BlockFillBoardController {
    private final Random rand = new Random();
    private Color color;
    private final Color fillColor = Color.rgb(83, 104, 120);
    private final Color backgroundColor = Color.rgb(54, 69, 79);
    private int rowNumber;
    private int colNumber;
    private int boardNum;
    private Rectangle currentCell;

    public int getRowNumber() { return rowNumber; }
    public void setRowNumber(int rowNum) { this.rowNumber = rowNum; }
    public int getColNumber() { return colNumber; }
    public void setColNumber(int colNum) { this.colNumber = colNum; }
    public int getBoardNum() { return boardNum; }
    public void setBoardNum(int boardNum) { this.boardNum = boardNum; }
    public Color getColor() { return color; }

    @FXML
    private AnchorPane bufferScreen;

    @FXML
    private Button bufferButton;

    @FXML
    private GridPane gridBoardDisplay;

    private boolean dragging = false;

    public void createBoard(int rowNum, int colNum, GridPane gridPane) {
        if (gridPane != null) {
            gridPane.getChildren().clear();
            color = setCellColor();
            for (int row = 0; row < rowNum; row++) {
                for (int col = 0; col < colNum; col++) {


                    Rectangle cell = new Rectangle(100, 100);
                    cell.setDisable(true);
                    cell.setFill(fillColor);
                    cell.setStroke(backgroundColor);
                    cell.setId(row + " " + col);

                    cell.setOnDragDetected(event -> {
                        if (cell == currentCell) {
                            dragging = true;
                            cell.startFullDrag();
                        }
                        event.consume();
                    });

                    cell.setOnMouseDragEntered(event -> {
                        if (dragging && cell.getFill() == fillColor && isAdjacentToColor(cell)) {
                            updateDisablingForCellPlus(cell, gridPane);
                            cell.setFill(color);
                            currentCell = cell;
                        }
                        if (gameIsDone(gridBoardDisplay)) {
                            try{
                                changeScene("blockFillViews/block-fill-win-view.fxml", "Block Fill", true, true);
                            } catch (IOException ioe) {
                                ioe.printStackTrace();
                            }
                        }
                    });

                    cell.setOnMouseReleased(event -> dragging = false);


                    gridPane.add(cell, col, row);
                }
            }
        }
    }

    private boolean gameIsDone(GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            if (((Rectangle)node).getFill() == fillColor) {
                return false;
            }
        }
        return true;
    }

    private boolean isAdjacentToColor(Node node) {
        if (currentCell == null) return false;

        Integer nodeColIdx = GridPane.getColumnIndex(node);
        Integer nodeRowIdx = GridPane.getRowIndex(node);
        Integer curColIdx = GridPane.getColumnIndex(currentCell);
        Integer curRowIdx = GridPane.getRowIndex(currentCell);

        int nodeCol = (nodeColIdx == null) ? 0 : nodeColIdx;
        int nodeRow = (nodeRowIdx == null) ? 0 : nodeRowIdx;
        int curCol = (curColIdx == null) ? 0 : curColIdx;
        int curRow = (curRowIdx == null) ? 0 : curRowIdx;

        int dx = Math.abs(nodeCol - curCol);
        int dy = Math.abs(nodeRow - curRow);

        return (dx == 1 && dy == 0) || (dx == 0 && dy == 1);
    }

    @FXML
    public void loadBoard() {
        createBoard(getRowNumber(), getColNumber(), gridBoardDisplay);
        createBoardShape(getBoardNum());
        bufferButton.setVisible(false);
        bufferScreen.setVisible(false);
    }

    public Color setCellColor() {
        if (getColor() == null){
            switch (rand.nextInt(10)){
                case 0 -> color = Color.AQUAMARINE;
                case 1 -> color = Color.BLUE;
                case 2 -> color = Color.LIGHTGREEN;
                case 3 -> color = Color.BEIGE;
                case 4 -> color = Color.PURPLE;
                case 5 -> color = Color.ORANGE;
                case 6 -> color = Color.PINK;
                case 7 -> color = Color.RED;
                case 8 -> color = Color.YELLOW;
                case 9 -> color = Color.DARKRED;
            }
        }
        return color;
    }

    @FXML
    protected void onRestartButtonClick() { loadBoard(); }

    @FXML
    protected void onExitButtonClick() {
        try {
            changeScene("menu-view.fxml", "Main Menu", false, true);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void onDifficultySelectClick() {
        try {
            changeScene("blockFillViews/block-fill-view.fxml", "Block Fill", false, false);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void createBoardShape(int boardNum) {
        switch (boardNum) {
            case 0: // Hard Level 8 ---- easy0.fxml
                extraditeSquare(1, 0);
                extraditeSquare(2, 0);
                extraditeSquare(5, 0);
                extraditeSquare(5, 1);
                extraditeSquare(0, 2);
                extraditeSquare(1, 2);
                extraditeSquare(0, 3);
                extraditeSquare(3, 3);
                extraditeSquare(4, 3);
                extraditeSquare(4, 5);
                extraditeSquare(5, 5);
                setStartingSquare(2, 4);
                break;
            case 1: // Hard Level 4 ---- easy1.fxml
                extraditeSquare(1, 0);
                extraditeSquare(2, 0);
                extraditeSquare(3, 0);
                extraditeSquare(3, 1);
                extraditeSquare(0, 5);
                extraditeSquare(3, 5);
                setStartingSquare(2, 1);
                break;
            case 2: // Hard Level 5 ---- easy2.fxml
                extraditeSquare(0, 1);
                extraditeSquare(0, 2);
                extraditeSquare(1, 2);
                extraditeSquare(0, 3);
                extraditeSquare(3, 3);
                extraditeSquare(0, 4);
                extraditeSquare(3, 4);
                extraditeSquare(0, 5);
                extraditeSquare(1, 5);
                extraditeSquare(3, 5);
                extraditeSquare(0, 6);
                extraditeSquare(1, 6);
                setStartingSquare(4, 0);
                break;
            case 3: // Hard Level 6 ---- easy3.fxml
                extraditeSquare(0, 0);
                extraditeSquare(2, 1);
                extraditeSquare(3, 1);
                extraditeSquare(1, 2);
                extraditeSquare(4, 3);
                extraditeSquare(2, 4);
                extraditeSquare(4, 4);
                extraditeSquare(1, 5);
                extraditeSquare(2, 5);
                extraditeSquare(0, 6);
                extraditeSquare(1, 6);
                extraditeSquare(2, 6);
                extraditeSquare(5, 6);
                setStartingSquare(3, 2);
                break;
            case 4: // Hard Level 7 ---- easy4.fxml
                extraditeSquare(0, 2);
                extraditeSquare(3, 2);
                extraditeSquare(0, 4);
                extraditeSquare(3, 4);
                extraditeSquare(4, 4);
                setStartingSquare(2, 0);
                break;
            case 5: // Hard Level 15 ---- easy5.fxml
                extraditeSquare(4, 0);
                extraditeSquare(2, 1);
                extraditeSquare(4, 1);
                extraditeSquare(3, 2);
                extraditeSquare(4, 2);
                extraditeSquare(4, 3);
                extraditeSquare(2, 4);
                extraditeSquare(4, 4);
                extraditeSquare(0, 6);
                extraditeSquare(1, 6);
                setStartingSquare(2, 2);
                break;
            case 6: // Hard Level 16 ---- easy6.fxml
                extraditeSquare(3, 0);
                extraditeSquare(4, 0);
                extraditeSquare(5, 0);
                extraditeSquare(1, 1);
                extraditeSquare(3, 1);
                extraditeSquare(4, 1);
                extraditeSquare(1, 2);
                extraditeSquare(4, 2);
                extraditeSquare(2, 4);
                extraditeSquare(3, 4);
                extraditeSquare(5, 4);
                extraditeSquare(5, 5);
                extraditeSquare(5, 6);
                setStartingSquare(0, 2);
                break;
            case 7: // Hard Level 17 ---- easy7.fxml
                extraditeSquare(0, 0);
                extraditeSquare(3, 0);
                extraditeSquare(4, 0);
                extraditeSquare(4, 2);
                extraditeSquare(0, 3);
                extraditeSquare(1, 3);
                extraditeSquare(4, 3);
                extraditeSquare(4, 4);
                extraditeSquare(4, 5);
                extraditeSquare(2, 6);
                extraditeSquare(3, 6);
                extraditeSquare(4, 6);
                setStartingSquare(1, 6);
                break;
            case 8: // Hard Level 18 ---- easy8.fxml
                extraditeSquare(0, 0);
                extraditeSquare(5, 0);
                extraditeSquare(0, 1);
                extraditeSquare(2, 1);
                extraditeSquare(3, 1);
                extraditeSquare(2, 2);
                extraditeSquare(1, 3);
                extraditeSquare(4, 4);
                extraditeSquare(5, 4);
                extraditeSquare(2, 5);
                extraditeSquare(0, 6);
                extraditeSquare(1, 6);
                extraditeSquare(4, 6);
                setStartingSquare(0, 5);
                break;
            case 9: // Hard Level 19 ---- easy9.fxml
                extraditeSquare(0, 0);
                extraditeSquare(3, 0);
                extraditeSquare(4, 0);
                extraditeSquare(5, 0);
                extraditeSquare(5, 1);
                extraditeSquare(3, 3);
                extraditeSquare(4, 3);
                extraditeSquare(5, 3);
                extraditeSquare(1, 4);
                extraditeSquare(5, 4);
                extraditeSquare(5, 5);
                setStartingSquare(1, 2);
                break;
            case 10: // Extra Hard Level 32 ---- hard0.fxml
                extraditeSquare(2, 0);
                extraditeSquare(5, 1);
                extraditeSquare(6, 1);
                extraditeSquare(5, 2);
                extraditeSquare(6, 2);
                extraditeSquare(0, 3);
                extraditeSquare(4, 3);
                extraditeSquare(3, 4);
                extraditeSquare(1, 5);
                extraditeSquare(5, 5);
                extraditeSquare(3, 6);
                extraditeSquare(4, 6);
                extraditeSquare(6, 7);
                setStartingSquare(3, 3);
                break;
            case 11: // Extra Hard Level 30 ---- hard1.fxml
                extraditeSquare(2, 0);
                extraditeSquare(1, 2);
                extraditeSquare(3, 2);
                extraditeSquare(6, 2);
                extraditeSquare(1, 3);
                extraditeSquare(6, 3);
                extraditeSquare(0, 4);
                extraditeSquare(3, 4);
                extraditeSquare(5, 5);
                setStartingSquare(2, 2);
                break;
            case 12: // Extra Hard Level 29 ---- hard2.fxml
                extraditeSquare(3, 0);
                extraditeSquare(2, 4);
                extraditeSquare(2, 6);
                setStartingSquare(0, 5);
                break;
            case 13: // Extra Hard Level 25 ---- hard3.fxml
                extraditeSquare(3, 0);
                extraditeSquare(1, 1);
                extraditeSquare(3, 1);
                extraditeSquare(3, 2);
                extraditeSquare(4, 2);
                extraditeSquare(3, 4);
                extraditeSquare(2, 5);
                extraditeSquare(5, 5);
                extraditeSquare(2, 6);
                extraditeSquare(3, 6);
                setStartingSquare(1, 3);
                break;
            case 14: // Extra Hard Level 23 ---- hard4.fxml
                extraditeSquare(3, 0);
                extraditeSquare(1, 1);
                extraditeSquare(2, 2);
                extraditeSquare(3, 2);
                extraditeSquare(0, 3);
                extraditeSquare(4, 4);
                extraditeSquare(1, 6);
                extraditeSquare(4, 6);
                extraditeSquare(0, 7);
                extraditeSquare(1, 7);
                setStartingSquare(5, 6);
                break;
            case 15: // Extra Hard Level 22 ---- hard5.fxml
                extraditeSquare(0, 0);
                extraditeSquare(0, 1);
                extraditeSquare(1, 1);
                extraditeSquare(0, 4);
                extraditeSquare(2, 4);
                extraditeSquare(3, 4);
                extraditeSquare(0, 5);
                setStartingSquare(5, 2);
                break;
            case 16: // Extra Hard Level 21 ---- hard6.fxml
                extraditeSquare(3, 0);
                extraditeSquare(5, 1);
                extraditeSquare(2, 2);
                extraditeSquare(5, 2);
                extraditeSquare(6, 3);
                extraditeSquare(2, 4);
                extraditeSquare(1, 5);
                extraditeSquare(2, 5);
                extraditeSquare(4, 6);
                extraditeSquare(5, 6);
                extraditeSquare(6, 6);
                setStartingSquare(0, 3);
                break;
            case 17: // Extra Hard Level 15 ---- hard7.fxml
                extraditeSquare(0, 0);
                extraditeSquare(4, 0);
                extraditeSquare(2, 1);
                extraditeSquare(1, 2);
                extraditeSquare(1, 3);
                extraditeSquare(4, 3);
                extraditeSquare(1, 4);
                extraditeSquare(0, 6);
                extraditeSquare(0, 7);
                extraditeSquare(1, 7);
                extraditeSquare(2, 7);
                extraditeSquare(3, 7);
                extraditeSquare(5, 7);
                setStartingSquare(4, 7);
                break;
            case 18: // Extra Hard Level 41 ---- hard8.fxml
                extraditeSquare(3, 1);
                extraditeSquare(4, 1);
                extraditeSquare(5, 1);
                extraditeSquare(1, 2);
                extraditeSquare(4, 2);
                extraditeSquare(0, 4);
                extraditeSquare(0, 5);
                extraditeSquare(2, 5);
                extraditeSquare(5, 5);
                extraditeSquare(0, 6);
                setStartingSquare(1, 3);
                break;
            case 19: // Extra Hard Level 28 ---- hard9.fxml
                extraditeSquare(2, 0);
                extraditeSquare(5, 0);
                extraditeSquare(0, 1);
                extraditeSquare(2, 3);
                extraditeSquare(2, 4);
                extraditeSquare(4, 4);
                extraditeSquare(5, 4);
                extraditeSquare(4, 5);
                extraditeSquare(5, 5);
                extraditeSquare(4, 6);
                extraditeSquare(5, 6);
                setStartingSquare(0, 2);
                break;
        }
    }

    private void updateDisablingForCellPlus(Node node, GridPane gridPane) {
//        if (!(node instanceof Rectangle rect)) return;

        Integer colIndex = GridPane.getColumnIndex(node);
        Integer rowIndex = GridPane.getRowIndex(node);

        int nodeCol = (colIndex == null) ? 0 : colIndex;
        int nodeRow = (rowIndex == null) ? 0 : rowIndex;
        int[][] coordsToCheck = {
                {nodeCol, nodeRow - 1},
                {nodeCol, nodeRow + 1},
                {nodeCol - 1, nodeRow},
                {nodeCol + 1, nodeRow}
        };

        for (int[] coord : coordsToCheck) {
            Node checkNode = getSpecificNode(coord[0], coord[1], gridPane);
            if (checkNode instanceof Rectangle checkRect) {
                if (checkRect.getFill() == backgroundColor) {
                    checkRect.setDisable(true);
                } else if (checkRect.getFill() == color) {
                    checkRect.setDisable(true);
                } else if (checkRect.getFill() == fillColor) {
                    checkRect.setDisable(false);
                }
            }
        }
    }

    private void extraditeSquare(int col, int row) {
        Node square = getSpecificNode(col, row, gridBoardDisplay);
        if (square instanceof Rectangle rect) {
            rect.setFill(backgroundColor);
            rect.setDisable(true);
        }
    }

    private void setStartingSquare(int col, int row) {
        Node square = getSpecificNode(col, row, gridBoardDisplay);
        if (square instanceof Rectangle rect) {
            rect.setFill(color);
            rect.setDisable(false);
            currentCell = rect;
            updateDisablingForCellPlus(rect, gridBoardDisplay);
        }
    }

    private Node getSpecificNode(int col, int row, GridPane gridPane) {
        for (Node node : gridPane.getChildren()){
            Integer colIndex = GridPane.getColumnIndex(node);
            Integer rowIndex = GridPane.getRowIndex(node);

            int nodeCol = (colIndex == null) ? 0 : colIndex;
            int nodeRow = (rowIndex == null) ? 0 : rowIndex;

            if (nodeCol == col && nodeRow == row) { return node; }
        }
        return null;
    }

    public <T> T changeScene(String viewName, String title, boolean maximized, boolean centered) throws IOException {
        return getT(viewName, title, maximized, centered);
    }
}