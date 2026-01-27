package Patterns.AdapterPattern.StrategyPattern.Ducks;

import Patterns.AdapterPattern.StrategyPattern.Duck;
import Patterns.AdapterPattern.StrategyPattern.FlyStrategies.FlyNoWay;
import Patterns.AdapterPattern.StrategyPattern.QuackStrategies.FakeQuack;

public class RubberDuck extends Duck {

    public RubberDuck() {
        super(new FlyNoWay(), new FakeQuack());
    }

    @Override
    protected void display() {
        System.out.println("I'm a rubber duck");
    }
}
