package Problems.TicTacToe.strategies;

import Problems.TicTacToe.core.Board;
import Problems.TicTacToe.enums.Symbol;

public interface WinningStrategy {
    boolean checkWin(Board board, int row, int col, Symbol symbol);
}
