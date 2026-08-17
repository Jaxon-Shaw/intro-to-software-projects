package csc180.shaw.jaxon.gameshub.adoku.controllers;

import csc180.shaw.jaxon.gameshub.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static csc180.shaw.jaxon.gameshub.HelloController.getT;

public class difficultyController {



    @FXML
    protected void loadVeryEasyButton() {
        try {
            loadGame("sudokuViews/adoku-view-v2.fxml", "Adoku", true, 10);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void loadEasyButton() {
        try {
            loadGame("sudokuViews/adoku-view-v2.fxml", "Adoku", true, 15);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void loadMediumButton() {
        try {
            loadGame("sudokuViews/adoku-view-v2.fxml", "Adoku", true, 30);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void loadHardButton() {
        try {
            loadGame("sudokuViews/adoku-view-v2.fxml", "Adoku", true, 40);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void loadVeryHardButton() {
        try {
            loadGame("sudokuViews/adoku-view-v2.fxml", "Adoku", true, 50);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void loadTestButton() {
        try {
            loadGame("sudokuViews/adoku-view-v2.fxml", "Adoku", true, 1);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
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

    public <T> T loadGame(String viewName, String title, boolean maximized, int difficulty) throws IOException {
        return getGame(viewName, title, maximized, difficulty);
    }

    public static <T> T getGame(String viewName, String title, boolean maximized, int difficulty) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("/csc180/shaw/jaxon/gameshub/" + viewName));
        Parent root = loader.load();

        ((adokuController) loader.getController()).initialize(difficulty);
//        controller.initialize(difficulty);


        Stage stage = HelloApplication.primaryStage;
        Scene scene = new Scene(root);
        stage.setMaximized(maximized);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();

        return loader.getController();
    }


}
