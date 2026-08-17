package csc180.shaw.jaxon.gameshub.battleship.models;

public class Game {
    Player player1 = new Player(new Board());
    Player player2 = new Player(new Board());

    public Player currentPlayer = player1;
    public Player enemy = player2;

    public void start() {
//        player1.fog = player2.board;
//        player2.fog = player1.board;
    }

    public Ship createShip(ShipType type, Facing facing) {
        return new Ship(type, facing);
    }

    public boolean isPlayer2() {return currentPlayer.equals(player2);}

    //true is valid placement, false is invalid placement
    public boolean placeShip(Ship ship, Coordinate coordinate) {
        if (currentPlayer.board.validatePlacement(ship, coordinate)) {
            currentPlayer.board.placeShip(coordinate, ship);
            currentPlayer.addToFleet(ship);
            return true;
        }
        return false;
    }

    public boolean attackCell(int row, int col) {
        return enemy.board.attack(row, col);
    }

    public void switchActivePlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
            enemy = player1;
        }
        else if (currentPlayer == player2) {
            currentPlayer = player1;
            enemy = player2;
        }
    }
}
