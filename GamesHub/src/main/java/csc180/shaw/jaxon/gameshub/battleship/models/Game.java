package csc180.shaw.jaxon.gameshub.battleship.models;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private Player player1;
    private Player player2;
    private boolean winnerIsPlayer2;
    private boolean p2IsAI;

    public Player currentPlayer;
    public Player enemy;
    private static final ArrayList<int[]> aiTargetQueue = new ArrayList<>();

    private static int firstHitRow = -1;
    private static int firstHitColumn = -1;
    private static int lastHitRow = -1;
    private static int lastHitColumn = -1;
    private static boolean hunting = false;
    private static String lockedDirection = null;

    public void start(String p1Name, String p2Name, boolean p2AI) {
        player1 = new Player(p1Name, new Board());
        player2 = new Player(p2Name, new Board());
        p2IsAI = p2AI;

        currentPlayer = player1;
        enemy = player2;
    }

    public Player getPlayer2() {
        return player2;
    }
    public Player getPlayer1() {
        return player1;
    }

    public boolean winnerIsPlayer2() {
        return winnerIsPlayer2;
    }
    public void setWinnerIsPlayer2(boolean winnerIsPlayer2) {
        this.winnerIsPlayer2 = winnerIsPlayer2;
    }

    public Ship createShip(ShipType type, Facing facing) {
        return new Ship(type, facing);
    }

    public boolean isPlayer2() {return currentPlayer.equals(player2);}
    public boolean p2IsAI() {return p2IsAI;}

    //true is valid placement, false is invalid placement
    public boolean placeShip(Ship ship, Coordinate coordinate) {
        if (currentPlayer.board.validatePlacement(ship, coordinate)) {
            currentPlayer.board.placeShip(coordinate, ship);
            currentPlayer.addToFleet(ship);
            return true;
        }
        return false;
    }

    public void aiPlaceShips() {
        Random rand = new Random();
        for (ShipType shipType : ShipType.values()) {
            int choice = rand.nextInt(4);
            Facing facing = switch (choice) {
                case 0 -> Facing.NORTH;
                case 1 -> Facing.SOUTH;
                case 2 -> Facing.EAST;
                default -> Facing.WEST;
            };
            Ship ship = createShip(shipType, facing);
            Coordinate coordinate;
            do  {
                int x = rand.nextInt(10);
                int y = rand.nextInt(10);
                coordinate = new Coordinate(x, y);
            } while (!player2.board.validatePlacement(ship, coordinate));
            player2.board.placeShip(coordinate, ship);
            player2.addToFleet(ship);
        }
    }

//region ai attack code
    public void aiTakeAttack() {
        Random numberGenerator = new Random();
        boolean continueAttacking = true;
        while (continueAttacking && !player1.getFleet().allSunk() && !player2.getFleet().allSunk()) {
            int row, column;
            if (!aiTargetQueue.isEmpty()) {
                int[] coords = aiTargetQueue.removeFirst();
                row = coords[0];
                column = coords[1];
                if (!isValidTarget(row, column)) continue;
            } else {
                do {
                    row = numberGenerator.nextInt(10);
                    column = numberGenerator.nextInt(10);
                } while ((row + column) % 2 == 1 || !isValidTarget(row, column));
                firstHitRow = firstHitColumn = lastHitColumn = lastHitRow = -1;
                hunting = false;
                lockedDirection = null;
            }
            Coordinate coordinate = new Coordinate(row, column);
            player2.setAttackCount(player2.getAttackCount() + 1);
            if (player1.board.attack(coordinate)) {
                Ship ship = player1.getFleet().getShipByCoordinate(coordinate);
                ship.setHealth(ship.getHealth() - 1);
                player2.setShipsHit(player2.getShipsHit() + 1);
                if (ship.getHealth() == 0) {
                    player2.setSankCount(player2.getSankCount() + 1);
                    resetAi();
                } else {
                    if (!hunting) {
                        firstHitRow = lastHitRow = row;
                        firstHitColumn = lastHitColumn = column;
                        hunting = true;
                        queueAdjacent(row, column);
                    } else if (lockedDirection == null) {
                        setLockedDirection(row, column);
                        queueFullLine();
                    }
                    lastHitRow = row;
                    lastHitColumn = column;
                }
            } else {
                continueAttacking = false;
                player2.setMissCount(player2.getMissCount() + 1);
                if (lockedDirection != null) {
                    removeBadInQueue();
                }
            }
        }
    }

    private static void removeBadInQueue() {
        aiTargetQueue.sort((a, b) -> {
            int cmp = Integer.compare(b[0], a[0]);
            return (cmp != 0) ? cmp : Integer.compare(b[1], a[1]);
        });


        if (lockedDirection.equals("horizontal")) {
            for (int i = aiTargetQueue.size() - 1; i >= 0; i--) {
                if (aiTargetQueue.get(i)[1] > lastHitColumn) {
                    aiTargetQueue.remove(i);
                }
            }
        } else if (lockedDirection.equals("vertical")) {
            for (int i = aiTargetQueue.size() - 1; i >= 0; i--) {
                if (aiTargetQueue.get(i)[0] > lastHitRow) {
                    aiTargetQueue.remove(i);
                }
            }
        }
    }

    private void queueFullLine() {
        aiTargetQueue.clear();
        if (lockedDirection.equals("horizontal")) {
            for (int currentColumn = firstHitColumn + 1; currentColumn < 10; currentColumn++) {
                if (!player1.board.getCell(new Coordinate(firstHitRow, currentColumn)).isHit()) {
                    aiTargetQueue.add(new int[]{firstHitRow, currentColumn});
                }
            }
            for (int currentColumn = firstHitColumn; currentColumn > 0; currentColumn--) {
                if (!player1.board.getCell(new Coordinate(firstHitRow, currentColumn)).isHit()) {
                    aiTargetQueue.add(new int[]{firstHitRow, currentColumn});
                }
            }
        } else if (lockedDirection.equals("vertical")) {
            for (int currentRow = firstHitRow + 1; currentRow < 10; currentRow++) {
                if (!player1.board.getCell(new Coordinate(currentRow, firstHitColumn)).isHit()) {
                    aiTargetQueue.add(new int[]{currentRow, firstHitColumn});
                }
            }
            for (int currentRow = firstHitRow - 1; currentRow > 0; currentRow--) {
                if (!player1.board.getCell(new Coordinate(currentRow, firstHitColumn)).isHit()) {
                    aiTargetQueue.add(new int[]{currentRow, firstHitColumn});
                }
            }
        }
    }

    private void queueAdjacent(int row, int column) {
        if (isValidTarget(row + 1, column)) aiTargetQueue.add(new int[]{row + 1, column});
        if (isValidTarget(row, column + 1)) aiTargetQueue.add(new int[]{row, column + 1});
        if (isValidTarget(row - 1, column)) aiTargetQueue.add(new int[]{row - 1, column});
        if (isValidTarget(row, column - 1)) aiTargetQueue.add(new int[]{row, column - 1});
    }

    private static void setLockedDirection(int row, int column) {
        if (row == firstHitRow) lockedDirection = "horizontal";
        else if (column == firstHitColumn) lockedDirection = "vertical";
    }

    private boolean isValidTarget(int row, int column) {
        return row >= 0 && row < 10 && column >= 0 && column < 10 && !player1.board.getCell(new Coordinate(row, column)).isHit();
    }

    private static void resetAi() {
        firstHitRow = firstHitColumn = lastHitColumn = lastHitRow = -1;
        hunting = false;
        lockedDirection = null;
        aiTargetQueue.clear();
    }
//endregion

    public boolean attackCell(int row, int col) {
        Coordinate coordinate = new Coordinate(row, col);
        if (enemy.board.getCell(coordinate).hasShip()) {
            Ship ship = enemy.getFleet().getShipByCoordinate(coordinate);
            if (ship != null) {
                ship.setHealth(ship.getHealth() - 1);
            }
        }
        return enemy.board.attack(coordinate);
    }

    public boolean shipWasSunk(int row, int col) {
        Coordinate coordinate = new Coordinate(row, col);
        Ship ship = enemy.getFleet().getShipByCoordinate(coordinate);
        if (ship != null) {
            return ship.getHealth() == 0;
        }
        return false;
    }

    public void switchActivePlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
            enemy = player1;
        }
        else if (currentPlayer == player2) {
            currentPlayer = player1;
            enemy = player2;
        }
    }
}
