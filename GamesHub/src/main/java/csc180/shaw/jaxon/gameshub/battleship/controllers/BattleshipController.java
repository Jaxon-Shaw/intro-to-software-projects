package csc180.shaw.jaxon.gameshub.battleship.controllers;

import csc180.shaw.jaxon.gameshub.battleship.models.Facing;
import csc180.shaw.jaxon.gameshub.battleship.models.Game;
import csc180.shaw.jaxon.gameshub.battleship.models.ShipType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

import static csc180.shaw.jaxon.gameshub.HelloController.getT;

public class BattleshipController {
    public Game game = new Game();
    private ShipType selectedShipType;
    private Facing selectedFacing;

    @FXML
    private AnchorPane facingMenu;
    @FXML
    private Rectangle carrier;
    @FXML
    private Rectangle submarine;
    @FXML
    private Rectangle battleship;
    @FXML
    private Rectangle cruiser;
    @FXML
    private Rectangle destroyer;

    @FXML
    protected void onExitButtonClick() {
        try {
            changeScene("menu-view.fxml", "Main Menu", false);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    private GridPane gameBoard;

    @FXML
    private GridPane fogBoard;

    @FXML
    public void initialize() {
        createBoard(gameBoard, true);
        createBoard(fogBoard, false);
        game.start();
    }

    private void createBoard(GridPane board, boolean interactable) {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {

                Rectangle cell = new Rectangle();
                cell.setWidth(70);
                cell.setHeight(70);
                cell.setId(row+" "+col);
                cell.setFill(Color.DODGERBLUE);
                cell.setStroke(Color.BLACK);
                if (interactable) cell.setOnMousePressed(this::boardCellClicked);

                board.add(cell, col, row);
            }
        }
    }

    @FXML
    protected void selectShip(MouseEvent event) {
        String target = ((Node) event.getSource()).getId();

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
        game.createShip(selectedShipType, selectedFacing);
    }

    @FXML
    protected void boardCellClicked(MouseEvent event) {
        String target = ((Node) event.getSource()).getId();
        System.out.println(target);
    }

    public <T> T changeScene(String viewName, String title, boolean maximized) throws IOException {
        return getT(viewName, title, maximized);
    }
}
