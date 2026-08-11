package csc180.shaw.jaxon.gameshub.battleship.models;

public class Player {
    String name;
    Board board;
    Board fog;
    Fleet fleet = new Fleet();

    public void addToFleet(Ship ship) {

    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getFog() {
        return fog;
    }

    public void setFog(Board fog) {
        this.fog = fog;
    }
}
