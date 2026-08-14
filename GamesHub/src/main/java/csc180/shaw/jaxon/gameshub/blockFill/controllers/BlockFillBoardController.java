package csc180.shaw.jaxon.gameshub.blockFill.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.util.Random;

import static csc180.shaw.jaxon.gameshub.HelloController.getT;

public class BlockFillBoardController {
    private Random rand = new Random();
    private Color color;
    private int rowNumber;
    private int colNumber;

    public int getRowNumber() { return rowNumber; }
    public void setRowNumber(int rowNum) { this.rowNumber = rowNum; }
    public int getColNumber() { return colNumber; }
    public void setColNumber(int colNum) { this.colNumber = colNum; }

    @FXML
    private AnchorPane bufferScreen;

    @FXML
    private Button bufferButton;

    @FXML
    private GridPane gridBoardDisplay;

    @FXML
    public void initialize() {

    }

    public void createBoard(int rowNum, int colNum) {
        if (gridBoardDisplay != null) {
            gridBoardDisplay.getChildren().clear();
            for (int row = 0; row < rowNum; row++) {
                for (int col = 0; col < colNum; col++) {

                    Rectangle cell = new Rectangle(100, 100);
                    cell.setFill(Color.rgb(83, 104, 120));
                    cell.setStroke(Color.rgb(54, 69, 79));

                    cell.setId(row + " " + col);
                    cell.setOnMousePressed(this::boardCellClicked);

                    gridBoardDisplay.add(cell, col, row);
                }
            }
        }
    }

    @FXML
    public void loadBoard() {
        createBoard(getRowNumber(), getColNumber());
        bufferButton.setVisible(false);
        bufferScreen.setVisible(false);
    }

    public Color getCellColor() {
        if (color == null){
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

    @FXML
    private void boardCellClicked(MouseEvent event) {
        String target = ((Node) event.getSource()).getId();
        int row = Integer.parseInt(String.valueOf(target.charAt(0)));
        int col = Integer.parseInt(String.valueOf(target.charAt(2)));

        gridStuffTest(col, row, getCellColor());
    }

    public void gridStuffTest(int col, int row, Color color) {
        for (javafx.scene.Node node : gridBoardDisplay.getChildren()) {
            Integer columnIndex = GridPane.getColumnIndex(node);
            Integer rowIndex = GridPane.getRowIndex(node);

            int colIndex = (columnIndex == null) ? 0 : columnIndex;
            int rowIdx = (rowIndex == null) ? 0 : rowIndex;

            if (colIndex == col && rowIdx == row) {
                ((Rectangle) node).setFill(color);
            }
        }
    }

    public <T> T changeScene(String viewName, String title, boolean maximized) throws IOException {
        return getT(viewName, title, maximized);
    }
}