package Patterns.AdapterPattern.StrategyPattern.Ducks;

import Patterns.AdapterPattern.StrategyPattern.Duck;
import Patterns.AdapterPattern.StrategyPattern.FlyStrategies.FlyWithWings;
import Patterns.AdapterPattern.StrategyPattern.QuackStrategies.DefaultQuack;

public class MallardDuck extends Duck {

    public MallardDuck() {
        super(new FlyWithWings(), new DefaultQuack());
    }

    @Override
    protected void display() {
        System.out.println("I am a Mallard Duck");
    }
}
