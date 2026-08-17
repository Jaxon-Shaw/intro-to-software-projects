package csc180.shaw.jaxon.gameshub.battleship.models;

public class Player {
    private String name;
    public Board board;
//    public Board fog;
    private Fleet fleet = new Fleet();

    Player(Board board) {
        this.board = board;
    }

    public void addToFleet(Ship ship) {
        fleet.addShip(ship);
    }

    public Fleet getFleet() {return fleet;}

    public String getName() {
        return name;
    }
    protected void setName(String name) {
        this.name = name;
    }
}
