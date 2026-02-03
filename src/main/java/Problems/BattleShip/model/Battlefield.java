package Problems.BattleShip.model;

import java.util.*;

public final class Battlefield {
    private final int size;
    private final int half;
    private final Map<Side, Map<Coordinate, Ship>> occupancy = new HashMap<>();
    private final Map<Side, List<Coordinate>> sideCoordinates = new HashMap<>();

    public Battlefield(int size) {
        if (size <= 0 || size % 2 != 0) {
            throw new IllegalArgumentException("Battlefield size must be a positive even number.");
        }
        this.size = size;
        this.half = size / 2;
        occupancy.put(Side.LEFT, new HashMap<>());
        occupancy.put(Side.RIGHT, new HashMap<>());
        sideCoordinates.put(Side.LEFT, buildCoordinatesForSide(Side.LEFT));
        sideCoordinates.put(Side.RIGHT, buildCoordinatesForSide(Side.RIGHT));
    }

    public int getSize() {
        return size;
    }

    public List<Coordinate> getCoordinatesForSide(Side side) {
        return sideCoordinates.get(side);
    }

    public void placeShip(Player player, Ship ship) {
        validateShipPlacement(player.getSide(), ship);
        Map<Coordinate, Ship> sideMap = occupancy.get(player.getSide());
        for (Coordinate cell : ship.getOccupiedCells()) {
            sideMap.put(cell, ship);
        }
        player.addShip(ship);
    }

    public HitResult fireAt(Player target, Coordinate coordinate) {
        Map<Coordinate, Ship> sideMap = occupancy.get(target.getSide());
        Ship ship = sideMap.get(coordinate);
        if (ship == null) {
            return HitResult.miss(coordinate);
        }
        for (Coordinate cell : ship.getOccupiedCells()) {
            sideMap.remove(cell);
        }
        target.removeShip(ship.getId());
        return HitResult.hit(coordinate, ship.getId(), target.getSide());
    }

    public String render() {
        StringBuilder builder = new StringBuilder();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                Coordinate coordinate = new Coordinate(x, y);
                String label = resolveLabel(coordinate);
                builder.append("| ");
                if (label == null) {
                    builder.append("    ");
                } else {
                    builder.append(label);
                }
                builder.append(" ");
            }
            builder.append("|").append(System.lineSeparator());
        }
        return builder.toString();
    }

    private String resolveLabel(Coordinate coordinate) {
        Ship leftShip = occupancy.get(Side.LEFT).get(coordinate);
        if (leftShip != null) {
            return Side.LEFT.getLabel() + "-" + leftShip.getId();
        }
        Ship rightShip = occupancy.get(Side.RIGHT).get(coordinate);
        if (rightShip != null) {
            return Side.RIGHT.getLabel() + "-" + rightShip.getId();
        }
        return null;
    }

    private void validateShipPlacement(Side side, Ship ship) {
        for (Coordinate cell : ship.getOccupiedCells()) {
            if (!isWithinBounds(cell)) {
                throw new IllegalArgumentException("Ship " + ship.getId() + " is out of battlefield bounds.");
            }
            if (!isWithinSide(side, cell)) {
                throw new IllegalArgumentException("Ship " + ship.getId() + " must be inside player's half.");
            }
            if (occupancy.get(side).containsKey(cell)) {
                throw new IllegalArgumentException("Ship " + ship.getId() + " overlaps another ship.");
            }
        }
    }

    private boolean isWithinBounds(Coordinate coordinate) {
        return coordinate.getX() >= 0 && coordinate.getX() < size
                && coordinate.getY() >= 0 && coordinate.getY() < size;
    }

    private boolean isWithinSide(Side side, Coordinate coordinate) {
        if (side == Side.LEFT) {
            return coordinate.getX() < half;
        }
        return coordinate.getX() >= half;
    }

    private List<Coordinate> buildCoordinatesForSide(Side side) {
        List<Coordinate> coordinates = new ArrayList<>();
        int startX = side == Side.LEFT ? 0 : half;
        int endX = side == Side.LEFT ? half : size;
        for (int y = 0; y < size; y++) {
            for (int x = startX; x < endX; x++) {
                coordinates.add(new Coordinate(x, y));
            }
        }
        return Collections.unmodifiableList(coordinates);
    }

    public static final class HitResult {
        private final Coordinate coordinate;
        private final boolean hit;
        private final String shipId;
        private final Side shipSide;

        private HitResult(Coordinate coordinate, boolean hit, String shipId, Side shipSide) {
            this.coordinate = coordinate;
            this.hit = hit;
            this.shipId = shipId;
            this.shipSide = shipSide;
        }

        public static HitResult hit(Coordinate coordinate, String shipId, Side shipSide) {
            return new HitResult(coordinate, true, shipId, shipSide);
        }

        public static HitResult miss(Coordinate coordinate) {
            return new HitResult(coordinate, false, null, null);
        }

        public boolean isHit() {
            return hit;
        }

        public Coordinate getCoordinate() {
            return coordinate;
        }

        public String getShipId() {
            return shipId;
        }

        public Side getShipSide() {
            return shipSide;
        }
    }
}
