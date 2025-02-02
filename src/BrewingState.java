package Task2New;

import javax.swing.*;

public class BrewingState implements State{
    CoffeeMachine coffeeMachine;

    public BrewingState(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }


    @Override
    public void start() {
        coffeeMachine.setMessage("Machine is already Brewing...");
    }

    @Override
    public void fill(int numOfCups) {
        coffeeMachine.setMessage("Cannot FILL! Machine is BREWING!");
    }

    @Override
    public void reset() {
        coffeeMachine.setMessage("Cannot Reset! Machine is BREWING!");
    }



}
