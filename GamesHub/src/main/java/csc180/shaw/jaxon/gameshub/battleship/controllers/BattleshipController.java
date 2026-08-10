package csc180.shaw.jaxon.gameshub.battleship.controllers;

import csc180.shaw.jaxon.gameshub.battleship.models.Game;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

import static csc180.shaw.jaxon.gameshub.HelloController.getT;

public class BattleshipController {
    public Game game = new Game();

    @FXML
    protected void onExitButtonClick() {
        try {
            changeScene("menu-view.fxml", "Main Menu", false);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    private GridPane gameBoard;

    @FXML
    private GridPane fogBoard;

    @FXML
    public void initialize() {
        createBoard(gameBoard);
        createBoard(fogBoard);
        game.start();
    }

    private void createBoard(GridPane board) {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {

                Rectangle cell = new Rectangle();
                cell.setWidth(70);
                cell.setHeight(70);
                cell.setFill(Color.DODGERBLUE);
                cell.setStroke(Color.BLACK);

                board.add(cell, col, row);
            }
        }
    }

    public <T> T changeScene(String viewName, String title, boolean maximized) throws IOException {
        return getT(viewName, title, maximized);
    }
}
