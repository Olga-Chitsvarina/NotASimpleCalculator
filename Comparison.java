package NotASimpleCalculator;

/**
 * GENERAL INFORMATION:
 *      @author                      Olga Chitsvarina.
 *      Date of creation:            December 24, 2016.
 *      Date of last modification:   December 27, 2016.
 * ABOUT THE PROGRAM:
 *      This program calculates sum, difference and product of two positive decimal numbers.
 * ABOUT THE CLASS:
 *      This class compares the numbers in newStringA and newStringB in order to find which of the two numbers entered
 *      by the user is larger (stringA or stringB).
 */
class Comparison {

    // Modified user input numbers:
    private String newStringA;
    private String newStringB;

    // By default first number entered is true:
    private boolean firstIsLarger = true;

    /**
     * This is a class constructor, it calls methods that find the largest number entered by the user.
     * @param newStringA - first decimal number entered by the user.
     * @param newStringB - second number entered by the user.
     */
    Comparison(String newStringA, String newStringB){
        // Set newStringA and newStringB:
        this.newStringA = newStringA;
        this.newStringB = newStringB;

        setFirstIsLarger();                                     // Find the larges number.
    }

    /**
     * This method finds the largest number among the two numbers entered by the user.
     */
    private void setFirstIsLarger(){

        short i = 0;
        while( i < newStringA.length()){                        // Compare character by character:

            String charA = newStringA.charAt(i) + "";           // Get the character at position i in newStringA.
            short intA = Short.parseShort(charA);               // Convert that character to short value.

            String charB = newStringB.charAt(i) + "";           // Get the character at position i in newStringB.
            short intB = Short.parseShort(charB);               // Convert that character to short value.

            // Find the first pair of different numbers at position i in
            // newStringA and newStringB, compare them and change the
            // value of firstIsLarger if needed:
            if (intA > intB){
                break;
            } else if (intA < intB) {
                firstIsLarger = false;
                break;
            }
            i++;
        }
    }

    boolean getFirstIsLarger(){
        return firstIsLarger;                                   // Return the value of firstIsLarger.
    }
}

