package csc180.shaw.jaxon.gameshub.battleship.models;

public class Game {
    private Player player1;
    private Player player2;
    private boolean winnerIsPlayer2;

    public Player currentPlayer;
    public Player enemy;

    public void start(String p1Name, String p2Name) {
        player1 = new Player(p1Name, new Board());
        player2 = new Player(p2Name, new Board());

        currentPlayer = player1;
        enemy = player2;
    }

    public Player getPlayer2() {
        return player2;
    }
    public Player getPlayer1() {
        return player1;
    }

    public boolean winnerIsPlayer2() {
        return winnerIsPlayer2;
    }
    public void setWinnerIsPlayer2(boolean winnerIsPlayer2) {
        this.winnerIsPlayer2 = winnerIsPlayer2;
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
        Coordinate coordinate = new Coordinate(row, col);
        if (enemy.board.getCell(coordinate).hasShip()) {
            Ship ship = enemy.getFleet().getShipByCoordinate(coordinate);
            if (ship != null) {
                ship.setHealth(ship.getHealth() - 1);
            }
        }
        return enemy.board.attack(coordinate);
    }

    public boolean shipWasSunk(int row, int col) {
        Coordinate coordinate = new Coordinate(row, col);
        Ship ship = enemy.getFleet().getShipByCoordinate(coordinate);
        if (ship != null) {
            return ship.getHealth() == 0;
        }
        return false;
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
