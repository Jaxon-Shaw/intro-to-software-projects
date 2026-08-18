package csc180.shaw.jaxon.gameshub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    protected void sodokuGameLauncher() {
        try {
            changeScene("sudokuViews/AdokuDifficulty.fxml", "Adoku Difficulty", false, true);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void battleshipGameLauncher() {
        try {
            changeScene("battleshipViews/placement-view.fxml", "Battleship", true, false);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void cookieClickerGameLauncher() {
        try {
            changeScene("cookieClickerViews/cookie-clicker-view.fxml", "Cookie Clicker", true, false);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void blockFillGameLauncher() {
        try {
            changeScene("blockFillViews/block-fill-view.fxml", "Block Fill", false, true);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void closeButtonClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public <T> T changeScene(String viewName, String title, boolean maximized, boolean centered) throws IOException {
        return getT(viewName, title, maximized, centered);
    }

    public static <T> T getT(String viewName, String title, boolean maximized, boolean centered) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("/csc180/shaw/jaxon/gameshub/" + viewName));
        Parent root = loader.load();

        Stage stage = HelloApplication.primaryStage;
        Scene scene = new Scene(root);
        stage.setMaximized(maximized);
        stage.setScene(scene);
        stage.setTitle(title);
        if (centered) {
            stage.centerOnScreen();
        }
        stage.show();

        return loader.getController();
    }
}
