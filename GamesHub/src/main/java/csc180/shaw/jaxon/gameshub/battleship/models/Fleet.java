package csc180.shaw.jaxon.gameshub.battleship.models;

import java.util.ArrayList;

public class Fleet {
    private ArrayList<Ship> ships = new  ArrayList<>();

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public Ship getShipByCoordinate(Coordinate coordinate) {
        for (Ship ship : ships) {
            for (Coordinate coord : ship.getCoordinates()) {
                if (coord.x == coordinate.x && coord.y == coordinate.y) {
                    return ship;
                }
            }
        }
        return null;
    }

    public boolean allSunk() {
        for (Ship ship : ships) {
            if (ship.getHealth() != 0) {
                return false;
            }
        }
        return true;
    }

    public int getSize() {return this.ships.size();}
}
