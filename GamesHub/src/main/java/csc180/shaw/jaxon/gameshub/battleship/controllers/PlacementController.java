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
    private AnchorPane intermissionScreen;

    @FXML
    protected void onExitButtonClick() {
        try {
            changeScene("menu-view.fxml", "Main Menu", false);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        createBoard();
        game.start();
    }


    /**
     * prefills 10x10 game board with blue squares and adds click functionality
     */
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

    /**
     * places players currently selected ship on the board
     * only allows valid placement
     * prints a message if the placement is not valid
     * if the player has placed five ships switches active player and notifies the player to trade the screen with them
     * if the 2nd player has placed all of their ships, switches scenes to the attack-controller
     * @param event mouse click
     */
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
                if (game.currentPlayer.getFleet().getSize() == 5) {
                    if (game.isPlayer2()) {
                        try {
                            game.switchActivePlayer();
                            getT("battleshipViews/attack-view.fxml", "Battleship");
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    }
                    else {
                        game.switchActivePlayer();
                        intermissionScreen.setVisible(true);
                    }
                }
            }
        }
    }

    /**
     * refreshes the board
     * changes the color of squares with a ship on them to be green
     */
    protected void redrawBoard() {
        for (javafx.scene.Node node : gameBoardDisplay.getChildren()) {
            Integer columnIndex = GridPane.getColumnIndex(node);
            Integer rowIndex = GridPane.getRowIndex(node);

            int colIndex = (columnIndex == null) ? 0 : columnIndex;
            int rowIdx = (rowIndex == null) ? 0 : rowIndex;

            if (game.currentPlayer.board.getCell(new Coordinate(rowIdx, colIndex)).hasShip()) {
                ((Rectangle) node).setFill(Color.GREEN);

            }
            else ((Rectangle) node).setFill(Color.DODGERBLUE);
        }
    }

    /**
     * redraws the board
     * resets the ships visibility
     * turns off the intermission screen
     */
    @FXML
    protected void resumeButtonClick() {
        redrawBoard();
        carrier.setVisible(true);
        battleship.setVisible(true);
        cruiser.setVisible(true);
        submarine.setVisible(true);
        destroyer.setVisible(true);
        intermissionScreen.setVisible(false);
    }

    public static <T> T changeScene(String viewName, String title, boolean maximized) throws IOException {
        return HelloController.getT(viewName, title, maximized);
    }

    //TODO remove later
    @FXML
    private void change() throws IOException {
        if (game.isPlayer2()) {
            try {
                game.switchActivePlayer();
                getT("battleshipViews/attack-view.fxml", "Battleship");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        else {
            game.switchActivePlayer();
            intermissionScreen.setVisible(true);
        }
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