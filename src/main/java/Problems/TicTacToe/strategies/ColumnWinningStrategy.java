package Problems.TicTacToe.strategies;

import Problems.TicTacToe.core.Board;
import Problems.TicTacToe.enums.Symbol;

public class ColumnWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWin(Board board, int row, int col, Symbol symbol) {
        int size = board.getSize();
        for (int r = 0; r < size; r++) {
            if (board.getCell(r, col).getSymbol() != symbol) {
                return false;
            }
        }
        return true;
    }
}
