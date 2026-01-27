package Patterns.AdapterPattern.StrategyPattern;

import Patterns.AdapterPattern.StrategyPattern.Ducks.MallardDuck;
import Patterns.AdapterPattern.StrategyPattern.Ducks.RubberDuck;
import Patterns.AdapterPattern.StrategyPattern.FlyStrategies.FlyNoWay;
import Patterns.AdapterPattern.StrategyPattern.FlyStrategies.FlyWithWings;

public class Main {

    public static void main(String[] args) {
        MallardDuck mallardDuck = new MallardDuck();
        mallardDuck.performFly();
        RubberDuck rubberDuck = new RubberDuck();
        rubberDuck.performQuack();
        rubberDuck.setFlyBehavior(new FlyNoWay());
        rubberDuck.performFly();
        rubberDuck.setFlyBehavior(new FlyWithWings());
        rubberDuck.performFly();
    }
}
