package Problems.BattleShip.model;

import Problems.BattleShip.strategy.FireStrategy;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Player {

    private final String name;
    private final Side side;
    private final FireStrategy strategy;
    private final Map<String, Ship> ships = new LinkedHashMap<>();

    public Player(String name, Side side, FireStrategy strategy) {
        this.name = name;
        this.side = side;
        this.strategy = strategy;
    }

    public String getName() {
        return name;
    }

    public Side getSide() {
        return side;
    }

    public FireStrategy getStrategy() {
        return strategy;
    }

    public void addShip(Ship ship) {
        ships.put(ship.getId(), ship);
    }

    public void removeShip(String id) {
        ships.remove(id);
    }

    public int getShipCount() {
        return ships.size();
    }

    public Map<String, Ship> getShips() {
        return Collections.unmodifiableMap(ships);
    }

}
