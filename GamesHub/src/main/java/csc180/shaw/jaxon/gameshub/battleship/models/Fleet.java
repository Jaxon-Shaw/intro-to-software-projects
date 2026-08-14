package csc180.shaw.jaxon.gameshub.battleship.models;

import java.util.ArrayList;

public class Fleet {
    private ArrayList<Ship> ships = new  ArrayList<>();

    public void addShip(Ship ship) {
        ships.add(ship);
    }
}
