package csc180.shaw.jaxon.gameshub.battleship;

import javafx.fxml.FXML;

import java.io.IOException;

import static csc180.shaw.jaxon.gameshub.HelloController.getT;

public class BattleshipController {

    @FXML
    protected void onExitButtonClick() {
        try {
            changeScene("menu-view.fxml", "Main Menu", false);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public <T> T changeScene(String viewName, String title, boolean maximized) throws IOException {
        return getT(viewName, title, maximized);
    }
}
