package Task2New;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoffeeMachineFrameGUI extends JFrame {


    private JFrame frame;
    private JPanel panel;


    CoffeeMachine coffeeMachine;
    private JButton startButton;
    private JLabel messageField;
    private JButton resetButton;
    private JButton fillButton;
    private JTextField numOfCupsField;
    private JButton totalCupsButton;
    private JLabel stateLabel;



    public CoffeeMachineFrameGUI() {
        coffeeMachine = new CoffeeMachine();
        setTitle("Coffee Machine");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        setLayout(new BorderLayout());


        messageField = new JLabel("Welcome!", JLabel.CENTER);
        messageField.setFont(new Font("Ariel", Font.PLAIN, 16));
        add(messageField, BorderLayout.NORTH);


        numOfCupsField = new JTextField();
        add(numOfCupsField, BorderLayout.CENTER);


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());


        startButton = new JButton("Start");
        resetButton = new JButton("Reset");
        fillButton = new JButton("Fill");
        totalCupsButton = new JButton("Total Cups");


        buttonsPanel.add(startButton);
        buttonsPanel.add(resetButton);
        buttonsPanel.add(fillButton);
        buttonsPanel.add(totalCupsButton);


        add(buttonsPanel, BorderLayout.SOUTH);

        stateLabel = new JLabel("Empty", SwingConstants.CENTER);
        stateLabel.setPreferredSize(new Dimension(150, 50));
        stateLabel.setOpaque(true);
        stateLabel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
        stateLabel.setBackground(new Color(252, 57, 3));
        add(stateLabel, BorderLayout.EAST);


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coffeeMachine.start();
                updateMessage();

            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coffeeMachine.reset();
                updateMessage();
            }
        });

        fillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numOfCups = Integer.parseInt(numOfCupsField.getText());
                    coffeeMachine.fill(numOfCups);
                    updateMessage();
                } catch (NumberFormatException ex) {
                    messageField.setText("Please enter a valid number!");
                }
            }
        });

        totalCupsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 int totalCups = DatabaseConnection.getTotalCupsForCurrentMonth();
                messageField.setText("Total cups brewed this month: " + totalCups);
            }
        });
        startButton.addActionListener(e -> startBrewing());
        setVisible(true);
    }



        public void startBrewing () {
            if (coffeeMachine.getState() instanceof EmptyState) {
                return;
            }
        coffeeMachine.setMessage("Coffee machine started brewing.");
        updateMessage();

        Timer timer = new Timer(5000, e -> {
            coffeeMachine.setState(new DoneState(coffeeMachine));
            coffeeMachine.setMessage("Brewing done. Coffee is ready!");
            updateMessage();

            int numOfCups = coffeeMachine.getNumberOfCups();
            DatabaseConnection.logCups(numOfCups);
        });
        timer.setRepeats(false);
        timer.start();
    }



    private void updateMessage() {
        messageField.setText(coffeeMachine.getMessage());
        updateStateLabel();
    }
    private void updateStateLabel() {
        State currentState = coffeeMachine.getState();

        stateLabel.setPreferredSize(new Dimension(150, 50));
        if (currentState instanceof EmptyState) {
            stateLabel.setText("Empty");
            stateLabel.setBackground(new Color(252, 57, 3));
        } else if (currentState instanceof IdleState) {
            stateLabel.setText("Idle");
            stateLabel.setBackground(new Color(252, 206, 3));
        } else if (currentState instanceof BrewingState) {
            stateLabel.setText("Brewing");
            stateLabel.setBackground(new Color(3, 181, 252));
        } else if (currentState instanceof DoneState) {
            stateLabel.setText("Done");
            stateLabel.setBackground(new Color(69, 252, 3));
        }
    }


    public static void main(String[] args) {
       DatabaseConnection.initializeDatabase();
        new CoffeeMachineFrameGUI();
    }
}
