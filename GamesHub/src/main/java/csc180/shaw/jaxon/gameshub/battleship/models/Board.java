package csc180.shaw.jaxon.gameshub.battleship.models;

public class Board {
    private Cell[][] cells = new Cell[10][10];
    private final int ZERO_BASED_OFFSET = 1;

    Board() {
        createBoard();
    }

    private void createBoard() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                cells[row][col] = new Cell();
            }
        }
    }

    public boolean inBounds(Coordinate coordinate) {
        return coordinate.x >= 0  && coordinate.y >= 0 && coordinate.x < 10 && coordinate.y < 10;
    }

    public void placeShip(Coordinate front, Ship ship) {
        int row = front.x;
        int col = front.y;
        Facing facing = ship.facing;
        int length = ship.getSize();
        Coordinate[] coordinates = new Coordinate[length];
        for (int i = 0; i < length; i++) {
            switch (facing) {
                case NORTH:
                    cells[row+i][col].setHasShip(true);
                    coordinates[i] = new Coordinate(row+i, col);
                    break;
                case EAST:
                    cells[row][col-i].setHasShip(true);
                    coordinates[i] = new Coordinate(row, col-i);
                    break;
                case SOUTH:
                    cells[row-i][col].setHasShip(true);
                    coordinates[i] = new Coordinate(row-i, col);
                    break;
                case WEST:
                    cells[row][col+i].setHasShip(true);
                    coordinates[i] = new Coordinate(row, col+i);
                    break;
            }
        }
        ship.setCoordinates(coordinates);
    }

    public boolean attack(int row, int col) {
        if (cells[row][col].isHit()) {
            return true;
        }
        cells[row][col].setHit(true);
        return cells[row][col].hasShip();
    }

    public Cell getCell(Coordinate coordinate) {
        if (inBounds(coordinate)) {
            return cells[coordinate.x][coordinate.y];
        }
        else return null;
    }

    public boolean validatePlacement(Ship ship, Coordinate coordinate) {
        Facing facing = ship.facing;
        int length = ship.getSize();
        int rowChange = 0;
        int columnChange = 0;
        if (facing == Facing.NORTH) {
            if (coordinate.x + length > 10) return false;
            rowChange = 1;
        }
        else if (facing == Facing.EAST) {
            if (coordinate.y - length + ZERO_BASED_OFFSET < 0) return false;
            columnChange = -1;
        }
        else if (facing == Facing.SOUTH) {
            if (coordinate.x - length + ZERO_BASED_OFFSET < 0) return false;
            rowChange = -1;
        }
        else if (facing == Facing.WEST) {
            if (coordinate.y + length > 10) return false;
            columnChange = 1;
        }
        for (int i = 0; i < length; i++) {
            int currentRow = coordinate.x + rowChange * i;
            int currentColumn = coordinate.y + columnChange * i;
            for (int adjR = currentRow - 1; adjR <= currentRow + 1; adjR++) {
                for (int adjC = currentColumn - 1; adjC <= currentColumn + 1; adjC++) {
                    if (adjR < 0 || adjR >= 10 || adjC < 0 || adjC >= 10) continue;
                    if (cells[adjR][adjC].hasShip()) return false;
                }
            }
        }
        return true;
    }
}
