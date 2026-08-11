package csc180.shaw.jaxon.gameshub.battleship.models;

public class Game {
    Player player1 = new Player();
    Player player2 = new Player();

    public void start() {
        System.out.println("Game Started");
    }

    public void createShip(ShipType type, Facing facing) {
        Ship ship = new Ship(type, facing);
        
    }
}
