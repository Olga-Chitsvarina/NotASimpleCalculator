package NotASimpleCalculator;

import java.util.ArrayList;

/**
 * GENERAL INFORMATION:
 *      @author                      Olga Chitsvarina.
 *      Date of creation:            December 24, 2016.
 *      Date of last modification:   December 27, 2016.
 * ABOUT THE PROGRAM:
 *      This program calculates sum, difference and product of two positive decimal numbers.
 * ABOUT THE CLASS:
 *      This class coordinates the interaction between the classes that calculate sum, difference and product.
 */
class Model {

    // Variables that are set using UserInput:
    private UserInput userInput;
    private short stepsToPoint;
    private String newStringA;
    private String newStringB;
    private short lengthOfStrings;

    // Variables that are set using Comparison:
    private Comparison comparisonHelper;
    private boolean firstIsLarger;

    // Variables that are set using Sum:
    private Sum sumHelper;
    private String sum;

    // Variables that are set using Difference:
    private Difference differenceHelper;
    private String difference;

    // Variables that are set using Multiplication:
    private Multiplication multiplicationHelper;
    private ArrayList<String> partialProducts;

    // Variables that are set using AdditionMultiplication:
    private AdditionMultiplication additionMultiplicationHelper;
    private String product ="";

    /**
     * This method calls methods that calculate sum, difference and product.
     * @param stringA - first decimal number entered by the user.
     * @param stringB - second decimal number entered by the user.
     */
    Model(String stringA, String stringB){

        // Modify the input numbers so that they can be used in other classes:
        userInput = new UserInput(stringA, stringB);
        setStepsToPoint();
        setNewStringA();
        setNewStringB();
        setLengthOfStrings();

        // Find which of the two numbers is larger:
        comparisonHelper = new Comparison(newStringA, newStringB);
        setFirstIsLarger();

        // Find the sum of the numbers:
        sumHelper = new Sum(newStringA, newStringB);
        setSum();

        // Find the difference of the numbers:
        differenceHelper = new Difference(newStringA, newStringB, firstIsLarger);
        setDifference();

        // Find the partial products of numbers entered:
        multiplicationHelper = new Multiplication(newStringA, newStringB, lengthOfStrings);
        setIntermediateCalculations();

        // Find the product of the numbers entered:
        additionMultiplicationHelper = new AdditionMultiplication(partialProducts,lengthOfStrings);
        setProduct();
    }

    private void setStepsToPoint(){
        stepsToPoint = userInput.getStepsToPoint();             // Get the value of the number of characters to the point.
    }

    private void setNewStringA(){
        newStringA = userInput.getNewStringA();                 // Get the modified value of the first number.
    }

    private void setNewStringB(){
        newStringB = userInput.getNewStringB();                 // Get the modified value of the second number.
    }

    private void setLengthOfStrings(){
        lengthOfStrings = (short)newStringA.length();           // Find the length of the modified first(second) number.
    }

    private void setFirstIsLarger(){
        firstIsLarger = comparisonHelper.getFirstIsLarger();    // Get the value of firstIsLarger.
    }

    private void setSum(){
        sum = sumHelper.getSum();           // Get the value of sum.
    }

    private void setDifference(){
        difference = differenceHelper.getDifference();          // Get the difference value.
    }

    private void setIntermediateCalculations(){
        partialProducts =                                       // Get the partial products.
                multiplicationHelper.getPartialProducts();
    }

    private void setProduct(){
        product = additionMultiplicationHelper.getProduct();    // Get the value of the product.
    }

    String getSum() {
        return sum;                                 // Return the sum value.
    }

    String getDifference() {
        return difference;                  // Return the difference value.
    }

    String getProduct(){
        return product;                         // Return the value of the product.
    }

    short getStepsToPoint() {
        return stepsToPoint;                // Return stepsToPoint value.
    }

}
