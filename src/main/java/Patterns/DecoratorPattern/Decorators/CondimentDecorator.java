package Patterns.DecoratorPattern.Decorators;

import Patterns.DecoratorPattern.Beverage;

public abstract class CondimentDecorator extends Beverage {
    public abstract String getDescription();
}
