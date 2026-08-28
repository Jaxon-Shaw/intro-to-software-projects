package csc180.shaw.jaxon.gameshub.battleship.controllers;

import csc180.shaw.jaxon.gameshub.HelloApplication;
import csc180.shaw.jaxon.gameshub.battleship.models.Board;
import csc180.shaw.jaxon.gameshub.battleship.models.Coordinate;
import csc180.shaw.jaxon.gameshub.battleship.models.Game;
import csc180.shaw.jaxon.gameshub.battleship.models.Player;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;

import static csc180.shaw.jaxon.gameshub.battleship.controllers.StartController.changeScene;

public class GameEndController {
    private Game game;
    private final int P1CENTER = 320;
    private final int P2CENTER = 615;

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
    @FXML private GridPane p1Board;
    @FXML private GridPane p2Board;

    protected void setGame(Game game) {
        this.game = game;
    }

    @FXML
    protected void initialize() {
        createBoard(p1Board);
        createBoard(p2Board);
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

        displayBoard(p1Board, game.getPlayer1().board);
        displayBoard(p2Board, game.getPlayer2().board);
    }

    private void createBoard(GridPane gameBoardDisplay) {
        gameBoardDisplay.getChildren().clear();

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {

                Rectangle cell = new Rectangle();
                cell.setWidth(36);
                cell.setHeight(36);
                cell.setId(row+" "+col);
                cell.setFill(Color.DODGERBLUE);
                cell.setStroke(Color.BLACK);

                gameBoardDisplay.add(cell, col, row);
            }
        }
    }

    private void displayBoard(GridPane displayBoard, Board board) {
        for (javafx.scene.Node node : displayBoard.getChildren()) {
            Integer columnIndex = GridPane.getColumnIndex(node);
            Integer rowIndex = GridPane.getRowIndex(node);

            int colIndex = (columnIndex == null) ? 0 : columnIndex;
            int rowIdx = (rowIndex == null) ? 0 : rowIndex;

            Coordinate coordinate = new Coordinate(rowIdx, colIndex);


            if (board.getCell(coordinate).hasShip()) {
                ((Rectangle) node).setFill(Color.GREEN);
            }
            if (board.getCell(coordinate).hasShip() && board.getCell(coordinate).isHit()) {
                ((Rectangle) node).setFill(Color.DARKRED);
            }
            else if (board.getCell(coordinate).isHit()) {
                ((Rectangle) node).setFill(Color.GRAY);
            }
        }
    }
}
