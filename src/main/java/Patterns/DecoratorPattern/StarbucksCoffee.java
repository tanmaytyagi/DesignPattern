package Patterns.DecoratorPattern;

import Patterns.DecoratorPattern.ConcreteComponents.DarkRoast;
import Patterns.DecoratorPattern.Decorators.Mocha;
import Patterns.DecoratorPattern.Decorators.Whip;

public class StarbucksCoffee {
    public static void main(String[] args) {
        Beverage beverage = new DarkRoast();
        beverage = new Mocha(beverage);
        beverage = new Whip(beverage);
        System.out.println("Cost: " + beverage.cost());
        System.out.println("Description: " + beverage.getDescription());
    }
}
