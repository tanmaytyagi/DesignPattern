package Problems.TicTacToe.strategies;

import Problems.TicTacToe.core.Board;
import Problems.TicTacToe.enums.Symbol;

public class RowWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWin(Board board, int row, int col, Symbol symbol) {
        int size = board.getSize();
        for (int c = 0; c < size; c++) {
            if (board.getCell(row, c).getSymbol() != symbol) {
                return false;
            }
        }
        return true;
    }
}
