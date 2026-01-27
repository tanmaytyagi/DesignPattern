package Patterns.AdapterPattern.StrategyPattern.QuackStrategies;

public class FakeQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Fake Quack!");
    }
}
