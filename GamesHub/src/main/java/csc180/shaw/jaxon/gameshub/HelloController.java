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
//        changeScene();
    }

    @FXML
    protected void battleshipGameLauncher() {

    }

    @FXML
    protected void cookieClickerGameLauncher() {

    }

    @FXML
    protected void blockFillGameLauncher() {

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
