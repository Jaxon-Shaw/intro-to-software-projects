package csc180.shaw.jaxon.gameshub.battleship.controllers;

import csc180.shaw.jaxon.gameshub.HelloApplication;
import csc180.shaw.jaxon.gameshub.battleship.models.Game;
import csc180.shaw.jaxon.gameshub.battleship.models.Player;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;

import static csc180.shaw.jaxon.gameshub.battleship.controllers.StartController.changeScene;

public class GameEndController {
    private Game game;
    private final int P1CENTER = 118;
    private final int P2CENTER = 416;

    @FXML
    private Label winner;
    @FXML
    private Label p1Name;
    @FXML
    private Label p1ShotCount;
    @FXML
    private Label p1SinkCount;
    @FXML
    private Label p1MissCount;
    @FXML
    private Label p1HitCount;
    @FXML
    private Label p2Name;
    @FXML
    private Label p2ShotCount;
    @FXML
    private Label p2SinkCount;
    @FXML
    private Label p2MissCount;
    @FXML
    private Label p2HitCount;

    protected void setGame(Game game) {
        this.game = game;
    }

    @FXML
    protected void onMenuButtonClick() throws IOException {
        try {
            changeScene("menu-view.fxml", "Main Menu", false);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void onAgainButtonClick() throws IOException {
        changeScene("battleshipViews/game-start-view.fxml", "Battleship", false);
    }

    @FXML
    protected void onExitButtonClick() throws IOException {
        HelloApplication.primaryStage.close();
    }

    @FXML
    protected void setGameStats() {
        winner.setText("Winner!");
        if (game.winnerIsPlayer2()) {
            winner.setLayoutX(P2CENTER);
        } else {winner.setLayoutX(P1CENTER);}

        Player player1 = game.getPlayer1();
        Player player2 = game.getPlayer2();

        p1Name.setText(player1.getName());
        p2Name.setText(player2.getName());
        double p1NameWidth = p1Name.prefWidth(-1);
        p1Name.setLayoutX(150 - p1NameWidth / 2);
        double p2NameWidth = p2Name.prefWidth(-1);
        p2Name.setLayoutX(150 - p2NameWidth / 2);

        p1ShotCount.setText(String.valueOf(player1.getAttackCount()));
        p2ShotCount.setText(String.valueOf(player2.getAttackCount()));

        p1HitCount.setText(String.valueOf(player1.getShipsHit()));
        p2HitCount.setText(String.valueOf(player2.getShipsHit()));

        p1SinkCount.setText(String.valueOf(player1.getSankCount()));
        p2SinkCount.setText(String.valueOf(player2.getSankCount()));

        p1MissCount.setText(String.valueOf(player1.getMissCount()));
        p2MissCount.setText(String.valueOf(player2.getMissCount()));
    }
}
