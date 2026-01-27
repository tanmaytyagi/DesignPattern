package StrategyPattern;

import StrategyPattern.Ducks.MallardDuck;
import StrategyPattern.Ducks.RubberDuck;
import StrategyPattern.FlyStrategies.FlyNoWay;
import StrategyPattern.FlyStrategies.FlyWithWings;

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
