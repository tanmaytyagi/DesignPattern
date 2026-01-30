package Problems.TicTacToe;

import Problems.TicTacToe.core.Game;
import Problems.TicTacToe.enums.GameStatus;
import Problems.TicTacToe.models.Player;
import Problems.TicTacToe.observers.Scoreboard;

public class TicTacToeSystem {
    private static volatile TicTacToeSystem instance;
    private static final Object lock = new Object();

    private final Scoreboard scoreboard;
    private Game currentGame;

    private TicTacToeSystem() {
        this.scoreboard = null;
    }

    public static TicTacToeSystem getInstance() {
        // - Check if instance is null
        // - Synchronize on lock
        // - Check again if instance is null
        // - Create new instance
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new TicTacToeSystem();
                }
            }
        }
        return instance;
    }

    public Game createGame(Player player1, Player player2) {
        // - Create new Game
        // - Add scoreboard as observer
        // - Print "New game started: X vs Y"
        // - Return the game
        currentGame = new Game(player1, player2, 3);
        currentGame.addObserver(scoreboard);
        System.out.println("New game started: " + player1.getName() +
                " vs " + player2.getName());
        return currentGame;
    }

    public void makeMove(Player player, int row, int col) {
        // - Check if there's an active game
        // - Print "PlayerName plays at (row, col)"
        // - Make the move
        // - Print the board
        if (currentGame == null) {
            throw new IllegalStateException("No active game. Call createGame first.");
        }
        System.out.println(player.getName() + " plays at (" + row + ", " + col + ")");
        currentGame.makeMove(row, col);
        currentGame.printBoard();
    }

    public GameStatus getGameStatus() {
        if (currentGame == null) {
            throw new IllegalStateException("No active game.");
        }
        return currentGame.getStatus();
    }

    public void printScoreboard() {
        scoreboard.printScoreboard();
    }

    // For testing: reset the singleton
    public static void resetInstance() {
        synchronized (lock) {
            instance = null;
        }
    }
}
