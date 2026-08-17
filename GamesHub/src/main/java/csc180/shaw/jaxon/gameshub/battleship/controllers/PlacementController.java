package csc180.shaw.jaxon.gameshub.battleship.controllers;

import csc180.shaw.jaxon.gameshub.HelloApplication;
import csc180.shaw.jaxon.gameshub.HelloController;
import csc180.shaw.jaxon.gameshub.battleship.models.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

import static csc180.shaw.jaxon.gameshub.HelloController.getT;

public class PlacementController {
    public final Game game = new Game();
    private ShipType selectedShipType;
    private Facing selectedFacing;
    private Ship currentShip;

    @FXML
    private AnchorPane facingMenu;
    @FXML
    private AnchorPane carrier;
    @FXML
    private AnchorPane submarine;
    @FXML
    private AnchorPane battleship;
    @FXML
    private AnchorPane cruiser;
    @FXML
    private AnchorPane destroyer;
    @FXML
    private Label instruction;
    @FXML
    private Label invalid;
    @FXML
    private GridPane gameBoardDisplay;
    @FXML
    private Rectangle shipSelectBlocker;

    @FXML
    protected void onExitButtonClick() {
        try {
            changeScene("menu-view.fxml", "Main Menu", false, true);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        createBoard();
        game.start();
    }


    private void createBoard() {
        gameBoardDisplay.getChildren().clear();

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {

                Rectangle cell = new Rectangle();
                cell.setWidth(70);
                cell.setHeight(70);
                cell.setId(row+" "+col);
                cell.setFill(Color.DODGERBLUE);
                cell.setStroke(Color.BLACK);
                cell.setOnMousePressed(this::boardCellClicked);

                gameBoardDisplay.add(cell, col, row);
            }
        }
    }

    /**
     * allows user to pick ships to place
     * sets chosen ships visibility to false so player cannot place the same ship twice
     * @param event mouse click
     */
    @FXML
    protected void selectShip(MouseEvent event) {
        String target = ((Node) event.getSource()).getId();

        shipSelectBlocker.setVisible(true);
        facingMenu.setVisible(true);
        switch (target) {
            case "carrier":
                selectedShipType = ShipType.CARRIER;
                carrier.setVisible(false);
                break;
            case "battleship":
                selectedShipType = ShipType.BATTLESHIP;
                battleship.setVisible(false);
                break;
            case "cruiser":
                selectedShipType = ShipType.CRUISER;
                cruiser.setVisible(false);
                break;
            case "submarine":
                selectedShipType = ShipType.SUBMARINE;
                submarine.setVisible(false);
                break;
            case "destroyer":
                selectedShipType = ShipType.DESTROYER;
                destroyer.setVisible(false);
                break;
        }
    }

    /**
     * opens a pop-up window to allow user to pick which direction the ship should be placed in
     * @param event mouse click
     */
    @FXML
    protected void getOrientation(MouseEvent event) {
        String target = ((Node) event.getSource()).getId();
        switch (target) {
            case "North" -> selectedFacing = Facing.NORTH;
            case "South" -> selectedFacing = Facing.SOUTH;
            case "East" -> selectedFacing = Facing.EAST;
            case "West" -> selectedFacing = Facing.WEST;
        }
        facingMenu.setVisible(false);
        currentShip = game.createShip(selectedShipType, selectedFacing);
        instruction.setVisible(true);
    }

    @FXML
    protected void boardCellClicked(MouseEvent event) {
        if (currentShip != null) {
            String target = ((Node) event.getSource()).getId();
            int row = Integer.parseInt(String.valueOf(target.charAt(0)));
            int col = Integer.parseInt(String.valueOf(target.charAt(2)));

            if (!game.placeShip(currentShip, new Coordinate(row, col))) {
                invalid.setVisible(true);
            } else {
                invalid.setVisible(false);
                instruction.setVisible(false);
                shipSelectBlocker.setVisible(false);
                currentShip = null;
                redrawBoard();
            }
        }
    }

    protected void redrawBoard() {
        for (javafx.scene.Node node : gameBoardDisplay.getChildren()) {
            Integer columnIndex = GridPane.getColumnIndex(node);
            Integer rowIndex = GridPane.getRowIndex(node);

            int colIndex = (columnIndex == null) ? 0 : columnIndex;
            int rowIdx = (rowIndex == null) ? 0 : rowIndex;

            if (game.currentPlayer.board.getCell(new Coordinate(rowIdx, colIndex)).hasShip()) {
                ((Rectangle) node).setFill(Color.GREEN);
            }
        }
    }

    public static <T> T changeScene(String viewName, String title, boolean maximized, boolean centered) throws IOException {
        return HelloController.getT(viewName, title, maximized, centered);
    }

    //TODO remove later
    @FXML
    private void change() throws IOException {
        getT("battleshipViews/attack-view.fxml", "Battleship");
    }

    public <T> T getT(String viewName, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("/csc180,/shaw/jaxon/gameshub/" + viewName));
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