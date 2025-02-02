package Task2New;

public class DoneState implements State{
    CoffeeMachine coffeeMachine;

    public DoneState(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    @Override
    public void start() {
        coffeeMachine.setMessage("Cannot START, Coffee is DONE, please RESET!");
    }

    @Override
    public void fill(int numOfCups) {
        coffeeMachine.setMessage("Cannot FILL! Please RESET!!");
    }

    @Override
    public void reset() {
        coffeeMachine.setNumberOfCups(0);
        coffeeMachine.setState(new EmptyState(coffeeMachine));
        coffeeMachine.setMessage("Machine is Ready!");
    }
}
