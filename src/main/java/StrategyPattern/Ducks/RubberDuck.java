package StrategyPattern.Ducks;

import StrategyPattern.Duck;
import StrategyPattern.FlyStrategies.FlyNoWay;
import StrategyPattern.QuackStrategies.FakeQuack;

public class RubberDuck extends Duck {

    public RubberDuck() {
        super(new FlyNoWay(), new FakeQuack());
    }

    @Override
    protected void display() {
        System.out.println("I'm a rubber duck");
    }
}
