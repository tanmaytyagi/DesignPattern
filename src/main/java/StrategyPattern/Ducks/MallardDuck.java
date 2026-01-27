package StrategyPattern.Ducks;

import StrategyPattern.Duck;
import StrategyPattern.FlyStrategies.FlyWithWings;
import StrategyPattern.QuackStrategies.DefaultQuack;

public class MallardDuck extends Duck {

    public MallardDuck() {
        super(new FlyWithWings(), new DefaultQuack());
    }

    @Override
    protected void display() {
        System.out.println("I am a Mallard Duck");
    }
}
