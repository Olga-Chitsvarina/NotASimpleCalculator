package NotASimpleCalculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GENERAL INFORMATION:
 *      @author                      Olga Chitsvarina.
 *      Date of creation:            December 24, 2016.
 *      Date of last modification:   December 27, 2016.
 * ABOUT THE PROGRAM:
 *      This program calculates sum, difference and product of two positive decimal numbers.
 * ABOUT THE CLASS:
 *      This class coordinates the interaction between the classes View and Model.
 */
class Controller implements ActionListener {

    private View view;

    private Model model;
    private String sum = "";
    private String difference = "";
    private String product = "";
    private short stepsToPoint;                                 // Number of characters after the point character.

    Controller(){
        view = new View(this);                      // Demonstrate GUI to the user.
    }

    /**
     * This method is called if the user wants to generate random numbers:
     */
    private void generateRandom(){

        // Create object that will be used to access methods in class RandomNumbers:
        RandomNumbers random1  = new RandomNumbers();
        RandomNumbers random2 = new RandomNumbers();

        // Get the values of random numbers:
        String number1 = random1.getRandomNumber();
        String number2 = random2.getRandomNumber();

        // Demonstrate the generated numbers to the user:
        view.updateFirstTextField(number1);
        view.updateSecondTextField(number2);
    }

    /**
     * This method is called if the user clicks on the "CALCULATE" or "GENERATE RANDOM NUMBERS" button.
     * @param e - event (user clicked on the button).
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // User clicked on "GENERATE RANDOM NUMBERS" button:
        if (e.getActionCommand().equals("GENERATE RANDOM NUMBERS")){
            generateRandom();
        }

        // User clicked on "CALCULATE" button:
        else {
            try {
                // Get the input from the user:
                String stringA = view.getFirstNumber();
                String stringB = view.getSecondNumber();

                model = new Model(stringA, stringB);                // Create the instance of the Model class.

                // Get the results of the calculation:
                setSum();
                setDifference();
                setProduct();
                setStepsToPoint();

                prepareStringsForOutput();                          // Delete unnecessary zeroes, place the point.

                printResults();                                     // Display results to the user.

            } catch (Exception exception) {
                exception.printStackTrace();
                view.showDialogWindow();                            // Inform the user about the error.
            }
        }
    }

    private void setSum(){
        sum = model.getSum();                                   // Get the calculated value of sum.
    }

    private void setDifference(){
        difference = model.getDifference();                     // Get the calculated value of difference.
    }

    private void setProduct(){
        product = model.getProduct();                           // Get the calculated value of product.
    }

    private void setStepsToPoint(){
        stepsToPoint = model.getStepsToPoint();                 // Get the value of stepsToPoint.
    }

    /**
     * This method places the point character in difference/sum Strings.
     * @param string - some String that represents a pattern of numbers
     * @return  - string that represents decimal number which corresponds to sum/difference. (It might have extra
     * zero characters at the beginning/in the end of the string.
     */
    private String placePoint(String string){

        // Find left (before the point character) and
        // right (after the point character) sides of the decimal number:
        String lhs = string.substring(0, string.length()- stepsToPoint);
        String rhs = string.substring(string.length() - stepsToPoint);

        return lhs+"."+ rhs;                                    // Place the point between left and right sides.
    }

    /**
     * This method flips the string passed in as a parameter (character by character).
     * @param stringToFlip - string that should be flipped.
     * @param result - some flipped part of stringToFlip.
     * @return - the flipped value of stringToFlip.
     */
    private String flipString(String stringToFlip, String result){
        if(stringToFlip.length() !=0){                          // Recursion base case. If length is 0, then there is
                                                                // nothing to flip.
            result = stringToFlip.charAt(0) + result;           // Adds next character to the left side of the result.

            // Recursive call, because first character stringToFlip
            // has already been added to result, move to the next character:
            return flipString (stringToFlip.substring(1), result);
        }
        else
            return result;                                      // Nothing to flip, just return the result.
    }


    /**
     * This method returns the decimal number that corresponds to the product (It might have extra zeroes at the
     *  beginning and in the end).
     * @return - decimal number (product).
     */
    private String placePointInProduct(){
        product = flipString(product, "");                      // Assign product with the flipped value of the product.

        String result = "";
        short i = 0;
        while(i<stepsToPoint){
            result = result + product.charAt(i);                // Result contains half of the right side of the product.
            i++;
        }

        product = product.substring(i);                         // First part is in result, move to the next part.

        i = 0;
        while(i<stepsToPoint){
            result = result + product.charAt(i);                // result contains full right side of the product
            i++;
        }

        product = product.substring(i);                         // product contains the left side of the product

        result = result + "." + product;                        // result contains right side, point and left side.

        result = flipString(result, "");                        // Flip the result so that it contains left side, point
                                                                // and right side of the original product.
        return result;
    }

    /**
     * This method removes zeroes at the beginning of the decimal number (extra zeroes).
     * @param string - some decimal number which might have unnecessary zeroes at the beginning.
     * @return - valid representation of the decimal number.
     */
    private String removeZeroes(String string){
        String firstCharacter = string.charAt(0) + "";          // Get the first character of the string.
        String secondCharacter = string.charAt(1) + "";         // Get the second character of the string.

        if (secondCharacter.equals(".")){                       // If second character is a point, then just
            return string;                                      // return the string, it does not have unnecessary 0s.
        }

        if (!firstCharacter.equals("0")){                       // If first character is a number different from zero,
            return string;                                      // then return the string, there is no unnecessary 0s.
        }

        return removeZeroes(string.substring(1));               // Remove the zero, move to the next character.
    }

    /**
     * This method removes the unnecessary zeroes at the beginning and at the end of the string.
     * @param string - represents a decimal number that might contain unnecessary zeroes at the beginning/end .
     * @return - unnecessary zeroes have been deleted, string is a valid decimal number.
     */
    private String deleteZeroesAtTheBeginningAndEnd(String string){

        string = removeZeroes(string);                          // Remove zeroes at the beginning of the string.
        string = flipString(string, "");                        // Flip the original string.
        string = removeZeroes(string);                          // Remove zeroes at the beginning.
        string = flipString(string, "");                        // Flip it back to original form.

        return string;
    }

    /**
     * This method modifies the sum, difference and product so that they represent valid decimal numbers and contain
     * point in the proper position.
     */
    private void prepareStringsForOutput(){

        // Place the point in the proper position:
        sum = placePoint(sum);
        difference = placePoint(difference);
        product = placePointInProduct();

        // Delete unnecessary zeroes:
        sum = deleteZeroesAtTheBeginningAndEnd(sum);
        difference = deleteZeroesAtTheBeginningAndEnd(difference);
        product = deleteZeroesAtTheBeginningAndEnd(product);
    }

    /**
     * This method demonstrates the output to the user.
     */
    private void printResults(){
        view.updateDisplay("SUM: " + "\n" + sum                 // Call the View method to demonstrate the output.
                + "\n" + "\n" + "DIFFERENCE: " +"\n"+ difference
                + "\n" + "\n" + "PRODUCT: " + "\n" + product + "\n");
    }
}


