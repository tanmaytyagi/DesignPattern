package Problems.BattleShip;

import Problems.BattleShip.service.GameService;

public final class Main {
    public static void main(String[] args) {
        GameService game = new GameService();
        game.initGame(6);
        game.addShip("SH1", 2, 1, 5, 4, 4);
        game.viewBattleField();
        game.startGame();
    }
}
