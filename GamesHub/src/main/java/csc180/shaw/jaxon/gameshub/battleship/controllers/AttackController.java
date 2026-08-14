package csc180.shaw.jaxon.gameshub.battleship.controllers;

import csc180.shaw.jaxon.gameshub.battleship.models.Board;
import csc180.shaw.jaxon.gameshub.battleship.models.Coordinate;
import csc180.shaw.jaxon.gameshub.battleship.models.Game;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class AttackController {
    @FXML
    private GridPane attackBoardDisplay;
    @FXML
    private GridPane gameBoardDisplay;
    @FXML
    private AnchorPane intermissionScreen;

    private Game game;

    protected void setGame(Game game) {
        this.game = game;
    }

    @FXML
    protected void initialize() {
        intermissionScreen.setVisible(true);
    }

    @FXML
    protected void onExitButtonClick() {
        try {
            PlacementController.changeScene("menu-view.fxml", "Main Menu", false);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void resumeButtonClick() {
        intermissionScreen.setVisible(false);
        createBoard(77, attackBoardDisplay);
        createBoard(35, gameBoardDisplay);
        redrawBoard(gameBoardDisplay, game.currentPlayer.board);
        redrawBoard(attackBoardDisplay, game.currentPlayer.fog);
    }

    private void createBoard(int cellSize, GridPane board) {
        board.getChildren().clear();

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {

                Rectangle cell = new Rectangle();
                cell.setWidth(cellSize);
                cell.setHeight(cellSize);
                cell.setId(row+" "+col);
                cell.setFill(Color.DODGERBLUE);
                cell.setStroke(Color.BLACK);
                cell.setOnMousePressed(this::attackCell);

                board.add(cell, col, row);
            }
        }
    }

    protected void redrawBoard(GridPane display, Board board) {
        for (javafx.scene.Node node : display.getChildren()) {
            Integer columnIndex = GridPane.getColumnIndex(node);
            Integer rowIndex = GridPane.getRowIndex(node);

            int colIndex = (columnIndex == null) ? 0 : columnIndex;
            int rowIdx = (rowIndex == null) ? 0 : rowIndex;

            if (board.getCell(new Coordinate(rowIdx, colIndex)).hasShip()) {
                ((Rectangle) node).setFill(Color.GREEN);
            }

        }
    }

    @FXML
    protected void attackCell(MouseEvent event) {
        String target = ((Node) event.getSource()).getId();

    }
}
