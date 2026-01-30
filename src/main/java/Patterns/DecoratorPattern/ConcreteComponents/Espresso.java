package Patterns.DecoratorPattern.ConcreteComponents;

import Patterns.DecoratorPattern.Beverage;

public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 0.80;
    }
}
