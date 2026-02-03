package Problems.BattleShip.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class Ship {
    private final String id;
    private final int size;
    private final Coordinate center;
    private final Set<Coordinate> occupiedCells;

    public Ship(String id, int size, Coordinate center) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Ship id must be provided.");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("Ship size must be positive.");
        }
        this.id = id;
        this.size = size;
        this.center = center;
        this.occupiedCells = buildOccupiedCells(size, center);
    }

    public String getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public Coordinate getCenter() {
        return center;
    }

    public Set<Coordinate> getOccupiedCells() {
        return Collections.unmodifiableSet(occupiedCells);
    }

    private static Set<Coordinate> buildOccupiedCells(int size, Coordinate center) {
        Set<Coordinate> cells = new HashSet<>();
        int half = size / 2;
        int startX = center.getX() - half;
        int startY = center.getY() - half;
        for (int y = startY; y < startY + size; y++) {
            for (int x = startX; x < startX + size; x++) {
                cells.add(new Coordinate(x, y));
            }
        }
        return cells;
    }
}
