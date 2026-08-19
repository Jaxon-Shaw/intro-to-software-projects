package csc180.shaw.jaxon.gameshub.battleship.models;

public class Player {
    private String name;
    public Board board;
    private Fleet fleet = new Fleet();
    private int attackCount;
    private int missCount;
    private int sankCount;
    private int shipsHit;

    Player(String name, Board board) {
        setName(name);
        setAttackCount(0);
        setMissCount(0);
        setSankCount(0);
        this.board = board;
    }

    public int getShipsHit() {
        return shipsHit;
    }
    public void setShipsHit(int shipsHit) {
        if (shipsHit >= this.shipsHit) {
            this.shipsHit = shipsHit;
        }
    }

    public int getAttackCount() {
        return attackCount;
    }
    public void setAttackCount(int attackCount) {
        if (attackCount >= this.attackCount) {
            this.attackCount = attackCount;
        }
    }

    public int getSankCount() {
        return sankCount;
    }
    public void setSankCount(int sankCount) {
        if (sankCount >= this.sankCount) {
            this.sankCount = sankCount;
        }
    }

    public int getMissCount() {
        return missCount;
    }
    public void setMissCount(int missCount) {
        if (missCount >= this.missCount) {
            this.missCount = missCount;
        }
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
