package Task2New;

import javax.swing.*;

public class IdleState implements State{
    CoffeeMachine coffeeMachine;

    public IdleState(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }
    @Override
    public void start() {
        coffeeMachine.setMessage("Machine started brewing...");
        coffeeMachine.setState(new BrewingState(coffeeMachine));
    }


    @Override
    public void fill(int numOfCups) {
        coffeeMachine.setMessage("Cannot Fill! Machine is already Filled!");
    }

    @Override
    public void reset() {
        coffeeMachine.setNumberOfCups(0);
        coffeeMachine.setState(new EmptyState(coffeeMachine));
        coffeeMachine.setMessage("Machine is reset!");
    }


}
