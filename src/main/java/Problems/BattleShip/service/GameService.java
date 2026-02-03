package Problems.BattleShip.service;

import Problems.BattleShip.model.*;
import Problems.BattleShip.strategy.RandomFireStrategy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class GameService {
    private Battlefield battlefield;
    private Player playerA;
    private Player playerB;
    private final Set<Coordinate> fired = new HashSet<>();
    private boolean initialized;

    public void initGame(int size) {
        battlefield = new Battlefield(size);
        playerA = new Player("PlayerA", Side.LEFT, new RandomFireStrategy());
        playerB = new Player("PlayerB", Side.RIGHT, new RandomFireStrategy());
        fired.clear();
        initialized = true;
    }

    public void addShip(String id, int size, int xA, int yA, int xB, int yB) {
        ensureInitialized();
        Ship shipA = new Ship(id, size, new Coordinate(xA, yA));
        Ship shipB = new Ship(id, size, new Coordinate(xB, yB));
        battlefield.placeShip(playerA, shipA);
        battlefield.placeShip(playerB, shipB);
    }

    public void startGame() {
        ensureInitialized();
        if (playerA.getShipCount() == 0 || playerB.getShipCount() == 0) {
            throw new IllegalStateException("Both players must have at least one ship to start.");
        }

        Player current = playerA;
        Player opponent = playerB;
        while (playerA.getShipCount() > 0 && playerB.getShipCount() > 0) {
            Coordinate target = current.getStrategy()
                    .nextShot(current, opponent, battlefield, fired);
            fired.add(target);
            Battlefield.HitResult result = battlefield.fireAt(opponent, target);
            StringBuilder message = new StringBuilder();
            message.append(current.getName())
                    .append("'s turn: Missile fired at ")
                    .append(target)
                    .append(" : ")
                    .append(result.isHit() ? "\"Hit\"" : "\"Miss\"");
            if (result.isHit()) {
                message.append(" ")
                        .append(result.getShipSide().getLabel())
                        .append("-")
                        .append(result.getShipId())
                        .append(" destroyed");
            }
            message.append(" : Ships Remaining - ")
                    .append(playerA.getName())
                    .append(":")
                    .append(playerA.getShipCount())
                    .append(", ")
                    .append(playerB.getName())
                    .append(":")
                    .append(playerB.getShipCount());
            System.out.println(message);

            if (playerA.getShipCount() == 0 || playerB.getShipCount() == 0) {
                break;
            }
            if (!result.isHit()) {
                Player temp = current;
                current = opponent;
                opponent = temp;
            }
        }
        Player winner = playerA.getShipCount() > 0 ? playerA : playerB;
        System.out.println("Game Over. " + winner.getName() + " wins.");
    }

    public void viewBattleField() {
        ensureInitialized();
        System.out.print(battlefield.render());
    }

    private void ensureInitialized() {
        if (!initialized) {
            throw new IllegalStateException("Game is not initialized. Call initGame() first.");
        }
        Objects.requireNonNull(battlefield, "Battlefield is not initialized.");
    }
}
