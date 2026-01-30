package Problems.TicTacToe.models;

import Problems.TicTacToe.enums.Symbol;

public class Player {

    // Immutability prevents bugs thus kept as "final"
    private final String name;
    private final Symbol symbol;

    public Player(String name, Symbol symbol) {
        if(symbol == Symbol.EMPTY) {
            throw new IllegalArgumentException("Player cannot have Empty symbol");
        }
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return name + " (" + symbol.getDisplayChar() + ")";
    }
}
