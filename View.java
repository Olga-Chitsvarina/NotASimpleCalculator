package NotASimpleCalculator;

import javax.swing.*;                                           // Import statement for J-classes.
import java.awt.*;                                              // Import statement for classes: Container and Colors.
import java.awt.event.ActionListener;                           // Import statement for ActionListener class.

/**
 * GENERAL INFORMATION:
 *      @author                      Olga Chitsvarina.
 *      Date of creation:            December 24, 2016.
 *      Date of last modification:   December 27, 2016.
 * ABOUT THE PROGRAM:
 *      This program calculates sum, difference and product of two positive decimal numbers.
 * ABOUT THE CLASS:
 *      This class represents GUI (Graphical User Interface).
 */
class View extends JFrame {

    private Container container = getContentPane();             // Get the contentPane object.

    // Set the colour for GUI window:
    private static final Color MY_COLOR_1 = new Color(203, 214, 226);

    private JPanel mainPanel = new JPanel(new GridLayout(9, 0));// Main panel will contain 9 sections

    // Labels that will be used in GUI window:
    private JLabel welcomeLabel = new JLabel ("PLEASE ENTER TWO NUMBERS:");
    private JLabel firstNumberLabel = new JLabel ("NUMBER 1: ");
    private JLabel secondNumberLabel = new JLabel ("NUMBER 2: ");
    private JLabel resultLabel = new JLabel ("RESULTS: ");

    // Button that will be used in GUI window:
    private JButton generateButton = new JButton("GENERATE RANDOM NUMBERS");
    private JButton calculateButton  = new JButton("CALCULATE");

    // Dimension of the GUI window:
    private static final short WIDTH = 1000;
    private static final short HEIGHT = 850;

    // Text areas that will be used in GUI window:
    private JTextArea firstTextField = new JTextArea ();
    private JTextArea secondTextField = new JTextArea ();
    private JTextArea displayTextField = new JTextArea();

    /**
     * Class Constructor that fills and runs the GUI window.
     * @param listener - object that implements ActionListener and listens to events (mouse clicks).
     */
    View(ActionListener listener) {

        // Set name, dimension, and location of the GUI window.
        super("Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        // Set the layout of the container:
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

        setPanel();                                             // Set the elements of the GUI window.

        setActionListenerForButtons(listener);                  // Set the ActionListener for the button.

        setVisible(true);                                       // Set the GUI window visible to the User.
    }

    /**
     * This method sets the inner elements of the GUI window.
     */
    private void setPanel() {

        mainPanel.setBackground(MY_COLOR_1);                    // Set the colour of the background.

        // Set the font and the font size of the welcomeLabel:
        Font welcomeTextFont = new Font("Arial", Font.BOLD, 20);
        welcomeLabel.setFont(welcomeTextFont);

        // Set the font and font size of firstNumberLabel, secondNumberLabel, resultLabel, calculateButton, generateButton:
        Font mainFont = new Font ("Arial", Font.BOLD, 15);
        firstNumberLabel.setFont(mainFont);
        secondNumberLabel.setFont(mainFont);
        resultLabel.setFont(mainFont);
        generateButton.setFont(mainFont);
        calculateButton.setFont(mainFont);

        // Set the font and font size of all text fields:
        Font textFieldFont = new Font ("Arial", Font.PLAIN, 15);
        firstTextField.setFont(textFieldFont);
        secondTextField.setFont(textFieldFont);
        displayTextField.setFont(textFieldFont);

        // Set the line-wrap of text fields:
        firstTextField.setLineWrap(true);
        secondTextField.setLineWrap(true);
        displayTextField.setLineWrap(true);

        // Set the scroll bar of the text-fields:
        JScrollPane firstScrollPane =
                new JScrollPane(firstTextField,
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollPane secondScrollPane =
                new JScrollPane(secondTextField,
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollPane displayScrollPane =
                new JScrollPane(displayTextField,
                                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add all elements to the mainPanel:
        mainPanel.add(welcomeLabel);

        mainPanel.add(generateButton);

        mainPanel.add(firstNumberLabel);
        mainPanel.add(firstScrollPane);

        mainPanel.add(secondNumberLabel);
        mainPanel.add(secondScrollPane);

        mainPanel.add(calculateButton);

        mainPanel.add(resultLabel);
        mainPanel.add(displayScrollPane);

        // Add mainPanel to the container:
        container.add(mainPanel);
    }

    /**
     * Set the ActionListener for the event(action of clicking on the "CALCULATE" button)
     * @param listener - some class that implements ActionListener and can "catch" the events.
     */
    private void setActionListenerForButtons(ActionListener listener)  {
        generateButton.addActionListener(listener);
        calculateButton.addActionListener(listener);            // Set the ActionListener to the button.
        }

    String getFirstNumber(){
        return firstTextField.getText();                        // Return the value of the first text field.
    }

    String getSecondNumber(){
        return secondTextField.getText();                       // Return the value of the second text field.
    }

    void updateDisplay(String string){
        displayTextField.setText(string);                       // Set text in the display text field.
    }

    void updateFirstTextField(String string){
        firstTextField.setText(string);                         // Set text in the first text field.
    }

    void updateSecondTextField(String string){
        secondTextField.setText(string);                        // Set text in the second text field.
    }


    /**
     * This method demonstrates the error message to the user if user input is invalid.
     */
    void showDialogWindow(){

        JOptionPane.showMessageDialog(container,                // Demonstrate the error message to the user.
                "Numbers entered must :" + "\n" +
                "   1) be valid decimal numbers or integers of length less than 32765" + "\n" +
                "   2) be positive numbers" + "\n" +
                "   3) not contain any space characters",
                "INVALID INPUT",
                JOptionPane.ERROR_MESSAGE);
    }
}






