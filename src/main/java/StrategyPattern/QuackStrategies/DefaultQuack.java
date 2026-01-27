package StrategyPattern.QuackStrategies;

public class DefaultQuack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("Default quack");
    }
}
