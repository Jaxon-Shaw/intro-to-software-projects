package csc180.shaw.jaxon.gameshub.battleship.controllers;

import csc180.shaw.jaxon.gameshub.HelloApplication;
import csc180.shaw.jaxon.gameshub.battleship.models.Board;
import csc180.shaw.jaxon.gameshub.battleship.models.Coordinate;
import csc180.shaw.jaxon.gameshub.battleship.models.Game;
import csc180.shaw.jaxon.gameshub.battleship.models.Ship;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

import static csc180.shaw.jaxon.gameshub.battleship.controllers.StartController.changeScene;

public class AttackController {
    @FXML
    private GridPane attackBoardDisplay;
    @FXML
    private GridPane gameBoardDisplay;
    @FXML
    private AnchorPane intermissionScreen;
    @FXML
    private Label pBoard;
    @FXML
    private Label eBoard;
    @FXML
    private Label passText;

    private Game game;

    protected void setGame(Game game) {
        this.game = game;
    }

    @FXML
    protected void initialize() {
        intermissionScreen.setVisible(true);
        createBoard(77, attackBoardDisplay);
        createBoard(35, gameBoardDisplay);
    }

    @FXML
    protected void changeName() {
        pBoard.setText(game.currentPlayer.getName() + "'s Board");
        double pWidth = pBoard.prefWidth(-1);
        pBoard.setLayoutX(360 - pWidth / 2);

        eBoard.setText(game.enemy.getName() + "'s Board");
        double eWidth = eBoard.prefWidth(-1);
        eBoard.setLayoutX(1130 - eWidth / 2);

        passText.setText(game.currentPlayer.getName() + "'s Turn");
        double width = passText.prefWidth(-1);
        passText.setLayoutX((double) 1695 / 2 - width / 2);
    }

    @FXML
    protected void onExitButtonClick() throws IOException {
        try {
            changeScene("menu-view.fxml", "Main Menu", false);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void resumeButtonClick() {
        intermissionScreen.setVisible(false);
        redrawBoard(gameBoardDisplay, game.currentPlayer.board);
        redrawBoard(attackBoardDisplay, game.enemy.board);
    }

    private void createBoard(int cellSize, GridPane board) {
        board.getChildren().clear();

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {

                Rectangle cell = new Rectangle();
                cell.setWidth(cellSize);
                cell.setHeight(cellSize);
                cell.setId(row+" "+col);
                cell.setFill(Color.DODGERBLUE);
                cell.setStroke(Color.BLACK);
                cell.setOnMousePressed(this::attackCell);

                board.add(cell, col, row);
            }
        }
    }

    protected void redrawBoard(GridPane display, Board board) {
        for (javafx.scene.Node node : display.getChildren()) {
            Integer columnIndex = GridPane.getColumnIndex(node);
            Integer rowIndex = GridPane.getRowIndex(node);

            int colIndex = (columnIndex == null) ? 0 : columnIndex;
            int rowIdx = (rowIndex == null) ? 0 : rowIndex;

            Coordinate coordinate = new Coordinate(rowIdx, colIndex);

            if (display == gameBoardDisplay) {
                if (board.getCell(coordinate).hasShip()) {
                    ((Rectangle) node).setFill(Color.GREEN);
                }
            }
            if (board.getCell(coordinate).hasShip() && board.getCell(coordinate).isHit()) {
                ((Rectangle) node).setFill(Color.DARKRED);
            }
            else if (board.getCell(coordinate).isHit()) {
                ((Rectangle) node).setFill(Color.GRAY);
            }
        }
    }

    @FXML
    protected void attackCell(MouseEvent event) {
        String target = ((Node) event.getSource()).getId();
        int row = Integer.parseInt(String.valueOf(target.charAt(0)));
        int col = Integer.parseInt(String.valueOf(target.charAt(2)));

        if (!game.enemy.board.getCell(new Coordinate(row, col)).isHit()) {
            game.currentPlayer.setAttackCount(game.currentPlayer.getAttackCount() + 1);
            boolean hitShip = game.attackCell(row, col);
            redrawBoard(attackBoardDisplay, game.enemy.board);
            if (hitShip) {
                game.currentPlayer.setShipsHit(game.currentPlayer.getShipsHit() + 1);
                if (game.shipWasSunk(row, col)) {
                    game.currentPlayer.setSankCount(game.currentPlayer.getSankCount() + 1);
                    Ship ship = game.enemy.getFleet().getShipByCoordinate(new Coordinate(row, col));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "You sunk the enemy's " + ship + "!");
                    alert.showAndWait();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Hit!");
                    alert.showAndWait();
                }
            }
            else {
                game.currentPlayer.setMissCount(game.currentPlayer.getMissCount() + 1);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Miss");
                alert.showAndWait();
                game.switchActivePlayer();
                try {
                    change();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            if (game.enemy.getFleet().allSunk()) {
                game.setWinnerIsPlayer2(game.isPlayer2());
                try {
                    getT("battleshipViews/game-end-view.fxml");
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void change() throws IOException {
        getT("battleshipViews/attack-view.fxml");
    }

    protected  <T> T getT(String viewName) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("/csc180/shaw/jaxon/gameshub/" + viewName));
        Parent root = loader.load();

        if (loader.getController() instanceof AttackController) {
            AttackController controller = loader.getController();
            controller.setGame(game);
        }
        if (loader.getController() instanceof GameEndController) {
            GameEndController controller = loader.getController();
            controller.setGame(game);
        }

        Stage stage = HelloApplication.primaryStage;
        Scene scene = new Scene(root);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Battleship");
        stage.show();

        return loader.getController();
    }
}
