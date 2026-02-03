package Problems.BattleShip.strategy;

import Problems.BattleShip.model.Battlefield;
import Problems.BattleShip.model.Coordinate;
import Problems.BattleShip.model.Player;

import java.util.Set;

public interface FireStrategy {
    Coordinate nextShot(Player attacker, Player opponent, Battlefield battlefield, Set<Coordinate> fired);
}
