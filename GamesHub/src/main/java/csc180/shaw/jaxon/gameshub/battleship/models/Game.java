package csc180.shaw.jaxon.gameshub.battleship.models;

public class Game {
    Player player1 = new Player(new Board());
    Player player2 = new Player(new Board());

    public Player currentPlayer = player1;

    public void start() {
        System.out.println("Game Started");
    }

    public Ship createShip(ShipType type, Facing facing) {
        return new Ship(type, facing);
    }

    //true is valid placement, false is invalid placement
    public boolean placeShip(Ship ship, Coordinate coordinate) {
        if (currentPlayer.board.validatePlacement(ship, coordinate)) {
            currentPlayer.board.placeShip(coordinate, ship);
            currentPlayer.addToFleet(ship);
            return true;
        }
        return false;
    }
}
