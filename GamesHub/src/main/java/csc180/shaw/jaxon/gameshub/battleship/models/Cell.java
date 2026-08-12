package csc180.shaw.jaxon.gameshub.battleship.models;

public class Cell {
    private boolean hasShip = false;
    private boolean wasHit = false;

    public boolean hasShip() {
        return hasShip;
    }
    public void setHasShip(boolean hasShip) {
        this.hasShip = hasShip;
    }

    public boolean isHit() {
        return wasHit;
    }
    public void setHit(boolean wasHit) {
        this.wasHit = wasHit;
    }

    public boolean attack() {
        return true;
    }

    public boolean placeShip() {
        return true;
    }
}
