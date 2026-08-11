package csc180.shaw.jaxon.gameshub.battleship.models;

public class Board {
    private Cell[][] cells = new Cell[10][10];

    public boolean inBounds(Coordinate coordinate) {
        return coordinate.x >= 0  && coordinate.y >= 0 && coordinate.x < 10 && coordinate.y < 10;
    }

    public boolean  placeShip(Coordinate front, Ship ship, Facing facing) {
        return true;
    }

    public boolean attack() {
        return true;
    }

    public Cell getCell(Coordinate coordinate) {
        if (inBounds(coordinate)) {
            return cells[coordinate.x][coordinate.y];
        }
        else return null;
    }
}
