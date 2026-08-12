package csc180.shaw.jaxon.gameshub.battleship.models;

public class Player {
    private String name;
    public Board board;
    Board fog;
    Fleet fleet = new Fleet();

    Player(Board board) {
        this.board = board;
    }

    public void addToFleet(Ship ship) {
        fleet.addShip(ship);
    }

    public Board getFog() {
        return fog;
    }
    public void setFog(Board fog) {
        this.fog = fog;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
