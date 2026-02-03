package Problems.BattleShip.strategy;

import Problems.BattleShip.model.Battlefield;
import Problems.BattleShip.model.Coordinate;
import Problems.BattleShip.model.Player;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class RandomFireStrategy implements FireStrategy {

    private final SecureRandom random = new SecureRandom();

    @Override
    public Coordinate nextShot(Player attacker, Player opponent, Battlefield battlefield, Set<Coordinate> fired) {
        List<Coordinate> available = new ArrayList<>();
        for (Coordinate coordinate : battlefield.getCoordinatesForSide(opponent.getSide())) {
            if (!fired.contains(coordinate)) {
                available.add(coordinate);
            }
        }
        if (available.isEmpty()) {
            throw new IllegalStateException("No available coordinates left to fire.");
        }
        return available.get(random.nextInt(available.size()));
    }
}
