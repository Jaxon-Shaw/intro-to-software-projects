package csc180.shaw.jaxon.gameshub.battleship.models;


public class Ship {
    public final ShipType type;
    public final Facing facing;
    private int size;
    private int health;
    private Coordinate[] coordinates;

    Ship(ShipType type,  Facing facing) {
        this.type = type;
        this.facing = facing;
        setSize();
        setHealth(getSize());
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
    public void setHealth(int health) {
        this.health = health;
    }

    public Coordinate[] getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinate[] coordinates) {
        this.coordinates = coordinates;
    }

    public boolean hasCoordinate(Coordinate check) {
        for (Coordinate coordinate : coordinates) {
            if (coordinate.x == check.x && coordinate.y == check.y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return type.name().toLowerCase();
    }
}
