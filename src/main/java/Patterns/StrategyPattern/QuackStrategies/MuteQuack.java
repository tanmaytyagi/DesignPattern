package Patterns.AdapterPattern.StrategyPattern.QuackStrategies;

public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("<< Silence >>");
    }
}
