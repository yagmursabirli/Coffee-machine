package Task2New;


import javax.swing.*;

public class CoffeeMachine {
    State emptyState;
    State idleState;
    State brewingState;
    State doneState;
    String message;

    int numberOfCups;

    State state;

    public CoffeeMachine() {
        emptyState = new EmptyState(this);
        idleState = new IdleState(this);
        brewingState = new BrewingState(this);
        doneState = new DoneState(this);

        state = emptyState;

    }



    public void start() {
        state.start();
    }
    public void fill(int numOfCups){state.fill(numOfCups);}
    public void reset(){state.reset();}

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setNumberOfCups(int numberOfCups){
        this.numberOfCups = numberOfCups;
    }

    public int getNumberOfCups() {
        return numberOfCups;
    }



    public State getEmptyState() {
        return emptyState;
    }

    public State getIdleState() {
        return idleState;
    }

    public State getBrewingState() {
        return brewingState;
    }

    public State getDoneState() {
        return doneState;
    }


}
