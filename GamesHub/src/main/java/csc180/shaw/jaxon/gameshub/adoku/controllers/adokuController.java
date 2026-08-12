package csc180.shaw.jaxon.gameshub.adoku.controllers;

import csc180.shaw.jaxon.gameshub.adoku.views.JavaFXDisplay;
import csc180.shaw.jaxon.gameshub.adoku.models.BoardChecker;
import csc180.shaw.jaxon.gameshub.adoku.models.BoardGenerator;
import csc180.shaw.jaxon.gameshub.adoku.models.interfaces.GameBoard;
import csc180.shaw.jaxon.gameshub.adoku.models.boards.StandardBoard;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.io.IOException;

import static csc180.shaw.jaxon.gameshub.HelloController.getT;

public class adokuController {

    @FXML
    private Label statusLabel;

    @FXML
    private JavaFXDisplay JavaFXDisplay;

    private GameBoard board;

    @FXML
    public void initialize() {
        board = BoardGenerator.generate(new StandardBoard(), 40); // 40 blanks ~ medium
        JavaFXDisplay.render(board);

    }

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
