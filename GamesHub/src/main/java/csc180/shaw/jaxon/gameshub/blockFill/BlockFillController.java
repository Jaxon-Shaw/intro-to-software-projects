package csc180.shaw.jaxon.gameshub.blockFill;

import javafx.fxml.FXML;
import java.util.Random;
import java.io.IOException;

import static csc180.shaw.jaxon.gameshub.HelloController.getT;

public class BlockFillController {
    @FXML
    protected void onExitButtonClick() {
        try {
            changeScene("menu-view.fxml", "Main Menu", false);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void onEasyButtonClick() {
        try {
            changeScene("blockFillViews/block-fill-game-board-easy.fxml", "Block Fill", true);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void onHardButtonClick() {
        try {
            changeScene("blockFillViews/block-fill-game-board-hard.fxml", "Block Fill", true);
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

    public <T> T changeScene(String viewName, String title, boolean maximized) throws IOException {
        return getT(viewName, title, maximized);
    }
}
