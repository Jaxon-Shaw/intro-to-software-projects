package csc180.shaw.jaxon.gameshub.battleship.controllers;

import csc180.shaw.jaxon.gameshub.HelloApplication;
import csc180.shaw.jaxon.gameshub.battleship.models.Board;
import csc180.shaw.jaxon.gameshub.battleship.models.Coordinate;
import csc180.shaw.jaxon.gameshub.battleship.models.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class AttackController {
    @FXML
    private GridPane attackBoardDisplay;
    @FXML
    private GridPane gameBoardDisplay;
    @FXML
    private AnchorPane intermissionScreen;

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
    protected void onExitButtonClick() {
        try {
            PlacementController.changeScene("menu-view.fxml", "Main Menu", false);
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

        boolean hitShip = game.attackCell(row, col);
        redrawBoard(attackBoardDisplay, game.enemy.board);

        if (!hitShip) {
            game.switchActivePlayer();
            try {
                change();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    @FXML
    private void change() throws IOException {
        getT("battleshipViews/attack-view.fxml", "Battleship");
    }

    protected  <T> T getT(String viewName, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("/csc180/shaw/jaxon/gameshub/" + viewName));
        Parent root = loader.load();

        AttackController controller = loader.getController();
        controller.setGame(game);

        Stage stage = HelloApplication.primaryStage;
        Scene scene = new Scene(root);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();

        return loader.getController();
    }
}
