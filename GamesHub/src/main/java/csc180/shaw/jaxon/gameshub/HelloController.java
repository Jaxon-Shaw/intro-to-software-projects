package csc180.shaw.jaxon.gameshub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    protected void sodokuGameLauncher() {
        try {
            changeScene("sudokuViews/adoku-view.fxml", "Adoku");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void battleshipGameLauncher() {
        try {
            changeScene("battleshipViews/battleship-view.fxml", "Battleship");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void cookieClickerGameLauncher() {
        try {
            changeScene("cookieClickerViews/cookie-clicker-view.fxml", "Cookie Clicker");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void blockFillGameLauncher() {
        try {
            changeScene("blockFillViews/block-fill-view.fxml", "Block Fill");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public <T> T changeScene(String viewName, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("/csc180/shaw/jaxon/gameshub/" + viewName));
        Parent root = loader.load();

        Stage stage = HelloApplication.primaryStage;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();

        return loader.getController();
    }
}
