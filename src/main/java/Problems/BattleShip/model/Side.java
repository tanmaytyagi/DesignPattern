package Problems.BattleShip.model;

public enum Side {
    LEFT("A"),
    RIGHT("B");

    private final String label;

    Side(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
