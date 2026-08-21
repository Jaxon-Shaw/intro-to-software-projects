package csc180.shaw.jaxon.gameshub.battleship.controllers;

import csc180.shaw.jaxon.gameshub.HelloApplication;
import csc180.shaw.jaxon.gameshub.HelloController;
import csc180.shaw.jaxon.gameshub.battleship.models.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class StartController {
    public final Game game = new Game();

    @FXML
    private TextField p1Name;
    @FXML
    private TextField p2Name;
    @FXML
    private Label p1Err;
    @FXML
    private Label p2Err;

    @FXML
    protected void onExitButtonClick() {
        try {
            changeScene("menu-view.fxml", "Main Menu", false);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void onSubmitClicked() {
        boolean p1ValidName = p1Name.getText().matches(".{1,90}");
        boolean p2ValidName = p2Name.getText().matches(".{1,90}");

        p1Err.setVisible(!p1ValidName);
        p2Err.setVisible(!p2ValidName);

        if (p1ValidName && p2ValidName) {
            try {
                game.start(p1Name.getText(), p2Name.getText());
                changeScene();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public static <T> T changeScene(String viewName, String title, boolean maximized) throws IOException {
        return HelloController.getT(viewName, title, maximized, false);
    }

    public <T> void changeScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("/csc180/shaw/jaxon/gameshub/" + "battleshipViews/placement-view.fxml"));
        Parent root = loader.load();

        PlacementController controller = loader.getController();
        controller.setGame(game);

        Stage stage = HelloApplication.primaryStage;
        Scene scene = new Scene(root);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.setTitle("Battleship");
        stage.show();

        loader.getController();
    }
}
