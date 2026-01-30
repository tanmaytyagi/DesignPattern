package Problems.TicTacToe.observers;

import Problems.TicTacToe.core.Game;
import Problems.TicTacToe.models.Player;

import java.util.concurrent.ConcurrentHashMap;

public class Scoreboard implements GameObserver {
    private final ConcurrentHashMap<String, Integer> scores;

    public Scoreboard() {
        this.scores = new ConcurrentHashMap<>();
    }

    @Override
    public void update(Game game) {
        Player winner = game.getWinner();
        if (winner != null) {
            recordWin(winner);
            System.out.println("Scoreboard updated: " + winner.getName() + " wins!");
        }
    }

    public void recordWin(Player player) {
        // - Increment the player's score (use merge for thread safety)
        scores.merge(player.getName(), 1, Integer::sum);
    }

    public int getScore(String playerName) {
        return scores.getOrDefault(playerName, 0);
    }

    public void printScoreboard() {
        System.out.println("\n===== SCOREBOARD =====");
        if (scores.isEmpty()) {
            System.out.println("No games played yet.");
        } else {
            scores.forEach((name, score) ->
                    System.out.println(name + ": " + score + " wins")
            );
        }
        System.out.println("======================\n");
    }
}
