package csc180.shaw.jaxon.gameshub.battleship.controllers;

import csc180.shaw.jaxon.gameshub.battleship.models.Game;
import javafx.fxml.FXML;

import java.io.IOException;

import static csc180.shaw.jaxon.gameshub.battleship.controllers.StartController.changeScene;

public class GameEndController {
    private Game game;

    protected void setGame(Game game) {
        this.game = game;
    }

    @FXML
    protected void onExitButtonClick() throws IOException {
        try {
            changeScene("menu-view.fxml", "Main Menu", false);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
