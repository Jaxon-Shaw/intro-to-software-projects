package csc180.shaw.jaxon.gameshub.blockFill.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import static csc180.shaw.jaxon.gameshub.HelloController.getT;

public class BlockFillBoardController {
    private Random rand = new Random();
    private Color color;
    private final Color fillColor = Color.rgb(83, 104, 120);
    private final Color backgroundColor = Color.rgb(54, 69, 79);
    private int rowNumber;
    private int colNumber;
    private int boardNum;

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

                    cell.setOnMousePressed(event -> {
                        dragging = true;
                        if (!(cell.getFill() == backgroundColor)) {
                            updateDisablingForCellPlus(cell, gridPane);
                            cell.setFill(color);
                        }
                    });

                    cell.setOnDragDetected(event -> {
                        cell.startFullDrag();
                        event.consume();
                    });

                    cell.setOnMouseDragEntered(event -> {
                        if (dragging && !(cell.getFill() == backgroundColor)) {
                            updateDisablingForCellPlus(cell, gridPane);
//                            if (!(cell.getFill() == color)) {
                                cell.setFill(color);
//                            } else if (cell.getFill() == fillColor) {
//                                cell.setFill(fillColor);
//                            } else {
//                                cell.setFill(backgroundColor);
//                            }
                        }
                    });

                    cell.setOnMouseReleased(event -> dragging = false);


                    gridPane.add(cell, col, row);
                }
            }
        }
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
    protected void onRestartButtonClick() {
        loadBoard();
    }

    @FXML
    protected void onExitButtonClick() {
        try {
            changeScene("menu-view.fxml", "Main Menu", false);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void onDifficultySelectClick() {
        try {
            changeScene("blockFillViews/block-fill-view.fxml", "Block Fill", false);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    ///TODO Very big honkagolongaloos switch statement for each board that disables squares
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
                extraditeSquare(0, 4);
                extraditeSquare(3, 4);
                setStartingSquare(1, 2);
                break;
            case 2: // Hard Level 5 ---- easy2.fxml

                break;
            case 3: // Hard Level 6 ---- easy3.fxml

                break;
            case 4: // Hard Level 7 ---- easy4.fxml

                break;
            case 5: // Hard Level 15 ---- easy5.fxml

                break;
            case 6: // Hard Level 16 ---- easy6.fxml

                break;
            case 7: // Hard Level 17 ---- easy7.fxml

                break;
            case 8: // Hard Level 18 ---- easy8.fxml

                break;
            case 9: // Hard Level 19 ---- easy9.fxml

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

                break;
            case 13: // Extra Hard Level 25 ---- hard3.fxml

                break;
            case 14: // Extra Hard Level 23 ---- hard4.fxml

                break;
            case 15: // Extra Hard Level 22 ---- hard5.fxml

                break;
            case 16: // Extra Hard Level 21 ---- hard6.fxml

                break;
            case 17: // Extra Hard Level 15 ---- hard7.fxml

                break;
            case 18: // Extra Hard Level 41 ---- hard8.fxml

                break;
            case 19: // Extra Hard Level 28 ---- hard9.fxml

                break;
        }
    }

    private boolean hasAdjacentSameColor(Node node, GridPane gridPane) {
        Integer colIndex = GridPane.getColumnIndex(node);
        Integer rowIndex = GridPane.getRowIndex(node);

        int nodeCol = (colIndex == null) ? 0 : colIndex;
        int nodeRow = (rowIndex == null) ? 0 : rowIndex;

        if (!(node instanceof Rectangle rect)) return false;


        int[][] adjacentCoords = {
                {nodeCol, nodeRow - 1},
                {nodeCol, nodeRow + 1},
                {nodeCol - 1, nodeRow},
                {nodeCol + 1, nodeRow}
        };
        for (int[] coord : adjacentCoords) {
            Node adjacentNode = getSpecificNode(coord[0], coord[1], gridPane);
            if (adjacentNode instanceof Rectangle adjacentRect) {
                Paint adjacentColor = adjacentRect.getFill();
                if (adjacentColor.equals(color)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void updateDisablingForCellPlus(Node node, GridPane gridPane) {
        if (!(node instanceof Rectangle rect)) return;

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
//                if (!hasAdjacentSameColor(checkNode, gridPane)) {
//                    if (Objects.equals(checkRect.getFill(), fillColor )) {
//                        checkRect.setDisable(true);
//                        checkRect.setFill(color);
//                    }
//
//                } else {
//                    checkRect.setDisable(false);
//                    if (checkRect.getFill() != backgroundColor && checkRect.getFill() != color){
//                        checkRect.setFill(fillColor);
//                    }
//                }
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
        }
    }

    private Node getSpecificNode(int col, int row, GridPane gridPane) {
        for (Node node : gridPane.getChildren()){
            Integer colIndex = GridPane.getColumnIndex(node);
            Integer rowIndex = GridPane.getRowIndex(node);

            int nodeCol = (colIndex == null) ? 0 : colIndex;
            int nodeRow = (rowIndex == null) ? 0 : rowIndex;

            if (nodeCol == col && nodeRow == row) {
                return node;
            }
        }
        return null;
    }

    public <T> T changeScene(String viewName, String title, boolean maximized) throws IOException {
        return getT(viewName, title, maximized);
    }
}