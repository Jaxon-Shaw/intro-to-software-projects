package csc180.shaw.jaxon.gameshub.battleship.models;

public class Ship {
    public final ShipType type;
    private Facing facing;
    private int size;
    private int health;
    private Coordinate[] coordinates;

    Ship(ShipType type) {
        this.type = type;
        setSize();
        setHealth();
    }

    public int getSize() {
        return size;
    }
    public void setSize() {
        switch (type) {
            case CARRIER -> this.size = 5;
            case BATTLESHIP -> this.size = 4;
            case CRUISER, SUBMARINE -> this.size = 3;
            case DESTROYER -> this.size = 2;
        }
    }

    public int getHealth() {
        return health;
    }
    public void setHealth() {
        this.health = getSize();
    }

    public Coordinate[] getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinate[] coordinates) {
        this.coordinates = coordinates;
    }

    public Facing getFacing() {
        return facing;
    }
    public void setFacing(Facing facing) {
        this.facing = facing;
    }
}
