package csc180.shaw.jaxon.gameshub.battleship.models;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;

public class Game {
    private Player player1;
    private Player player2;
    private boolean winnerIsPlayer2;
    private boolean p2IsAI;

    public Player currentPlayer;
    public Player enemy;

    private boolean hunting = false;
    private String lockedDirection = null;
    private int firstHitRow = -1;
    private int firstHitColumn = -1;
    private Deque<int[]> aiTargetQueue = new ArrayDeque<>();

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
        Random random = new Random();
        boolean continueAttacking = true;

        while (continueAttacking
                && !player1.getFleet().allSunk()
                && !player2.getFleet().allSunk()) {

            int[] target = getAiTarget(random);

            if (target == null) {
                continue;
            }

            int row = target[0];
            int column = target[1];

            Coordinate coordinate = new Coordinate(row, column);

            player2.setAttackCount(player2.getAttackCount() + 1);

            if (player1.board.attack(coordinate)) {
                handleAiHit(row, column);
            } else {
                handleAiMiss(row, column);
                continueAttacking = false;
            }
        }
    }

    private int[] getAiTarget(Random random) {

        while (!aiTargetQueue.isEmpty()) {
            int[] target = aiTargetQueue.removeFirst();

            if (isValidTarget(target[0], target[1])) {
                return target;
            }
        }

        int row;
        int column;

        do {
            row = random.nextInt(10);
            column = random.nextInt(10);
        } while ((row + column) % 2 == 1 || !isValidTarget(row, column));

        resetAi();

        return new int[]{row, column};
    }

    private void handleAiHit(int row, int column) {

        Ship ship = player1.getFleet()
                .getShipByCoordinate(new Coordinate(row, column));

        ship.setHealth(ship.getHealth() - 1);
        player2.setShipsHit(player2.getShipsHit() + 1);

        if (ship.getHealth() == 0) {
            player2.setSankCount(player2.getSankCount() + 1);
            resetAi();
            return;
        }

        if (!hunting) {
            firstHitRow = row;
            firstHitColumn = column;
            hunting = true;

            queueAdjacent(row, column);

        } else if (lockedDirection == null) {
            setLockedDirection(row, column);
            queueFullLine();
        }
    }

    private void handleAiMiss(int row, int column) {
        player2.setMissCount(player2.getMissCount() + 1);

        if (lockedDirection != null) {
            reverseDirection(row, column);
        }
    }

    private void reverseDirection(int row, int column) {

        aiTargetQueue.clear();

        if (lockedDirection.equals("horizontal")) {

            int oppositeColumn =
                    column > firstHitColumn
                            ? firstHitColumn - 1
                            : firstHitColumn + 1;

            while (oppositeColumn >= 0 && oppositeColumn < 10) {

                if (isValidTarget(firstHitRow, oppositeColumn)) {
                    aiTargetQueue.add(
                            new int[]{firstHitRow, oppositeColumn}
                    );
                }

                oppositeColumn +=
                        column > firstHitColumn ? -1 : 1;
            }

        } else {

            int oppositeRow =
                    row > firstHitRow
                            ? firstHitRow - 1
                            : firstHitRow + 1;

            while (oppositeRow >= 0 && oppositeRow < 10) {

                if (isValidTarget(oppositeRow, firstHitColumn)) {
                    aiTargetQueue.add(
                            new int[]{oppositeRow, firstHitColumn}
                    );
                }

                oppositeRow +=
                        row > firstHitRow ? -1 : 1;
            }
        }
    }

    private void queueAdjacent(int row, int column) {

        if (isValidTarget(row + 1, column))
            aiTargetQueue.add(new int[]{row + 1, column});

        if (isValidTarget(row, column + 1))
            aiTargetQueue.add(new int[]{row, column + 1});

        if (isValidTarget(row - 1, column))
            aiTargetQueue.add(new int[]{row - 1, column});

        if (isValidTarget(row, column - 1))
            aiTargetQueue.add(new int[]{row, column - 1});
    }

    private void setLockedDirection(int row, int column) {

        if (row == firstHitRow) {
            lockedDirection = "horizontal";
        } else {
            lockedDirection = "vertical";
        }
    }

    private void resetAi() {
        firstHitRow = -1;
        firstHitColumn = -1;
        hunting = false;
        lockedDirection = null;
        aiTargetQueue.clear();
    }

    private void queueFullLine() {
        aiTargetQueue.clear();

        if (lockedDirection.equals("horizontal")) {

            // Search to the right
            for (int currentColumn = firstHitColumn + 1;
                 currentColumn < 10;
                 currentColumn++) {

                if (!player1.board
                        .getCell(new Coordinate(firstHitRow, currentColumn))
                        .isHit()) {

                    aiTargetQueue.add(
                            new int[]{firstHitRow, currentColumn}
                    );
                }
            }

            // Search to the left
            for (int currentColumn = firstHitColumn - 1;
                 currentColumn >= 0;
                 currentColumn--) {

                if (!player1.board
                        .getCell(new Coordinate(firstHitRow, currentColumn))
                        .isHit()) {

                    aiTargetQueue.add(
                            new int[]{firstHitRow, currentColumn}
                    );
                }
            }

        } else if (lockedDirection.equals("vertical")) {

            // Search downward
            for (int currentRow = firstHitRow + 1;
                 currentRow < 10;
                 currentRow++) {

                if (!player1.board
                        .getCell(new Coordinate(currentRow, firstHitColumn))
                        .isHit()) {

                    aiTargetQueue.add(
                            new int[]{currentRow, firstHitColumn}
                    );
                }
            }

            // Search upward
            for (int currentRow = firstHitRow - 1;
                 currentRow >= 0;
                 currentRow--) {

                if (!player1.board
                        .getCell(new Coordinate(currentRow, firstHitColumn))
                        .isHit()) {

                    aiTargetQueue.add(
                            new int[]{currentRow, firstHitColumn}
                    );
                }
            }
        }
    }



    private boolean isValidTarget(int row, int column) {
        Coordinate coordinate = new Coordinate(row, column);
        return  player1.board.inBounds(coordinate) && !player1.board.getCell(coordinate).isHit();
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
