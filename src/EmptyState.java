package Task2New;

public class EmptyState implements State{
    CoffeeMachine coffeeMachine;

    public EmptyState(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    @Override
    public void start() {
        coffeeMachine.setMessage("Cannot Start! Machine is Empty!");
    }

    @Override
    public void fill(int numOfCups) {
        if (numOfCups > 0){
            coffeeMachine.setNumberOfCups(numOfCups);
            coffeeMachine.setState(new IdleState(coffeeMachine));
            coffeeMachine.setMessage("Coffee Machine has been filled with " + numOfCups + " cups.");
        } else {
            coffeeMachine.setMessage("Please enter a valid number.");
        }
    }

    @Override
    public void reset() {
        coffeeMachine.setMessage("Cannot Reset! Machine is already Empty!");
    }



}
