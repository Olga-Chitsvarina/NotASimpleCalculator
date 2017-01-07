package NotASimpleCalculator;

/**
 * GENERAL INFORMATION:
 *      @author                      Olga Chitsvarina.
 *      Date of creation:            December 24, 2016.
 *      Date of last modification:   January 2, 2017.
 * ABOUT THE PROGRAM:
 *      This program calculates sum, difference and product of two positive decimal numbers.
 * ABOUT THE CLASS:
 *      This class calculates the sum of two numbers.
 */
class SumMultiplication {

    private String newStringA;                                  // Represents the first integer.
    private String newStringB;                                  // Represents the second integer.
    private short carry;                                        // Represents the carry.

    private String sum = "";                                    // Sum of the first number and the second number.

    /**
     * This is a class constructor. It assigns the private variables and calls the methods to calculate the sum of two
     * numbers.
     * @param newStringA first decimal number.
     * @param newStringB second decimal number.
     * @param carry carry that should be taken into consideration.
     */
    SumMultiplication(String newStringA, String newStringB, short carry){

        // Assign the private variables with the corresponding values:
        this.newStringA = newStringA;
        this.newStringB = newStringB;
        this.carry = carry;

        calculateSum();                                         // Calculate the sum of first and second numbers.
    }

    /**
     * This method calculates the sum of two numbers.
     */
    private void calculateSum(){

        short result;

        short i = (short)(newStringA.length() - 1);             // i is the last index of the number.
        while ( i >=0 ){

            // Get the characters at position i:
            String charA = "" + (newStringA.charAt(i));
            String charB = "" + (newStringB.charAt(i));

            // Convert these characters to short value (integers):
            short number1 = Short.parseShort(charA);
            short number2 = Short.parseShort(charB);

            result = (short) (number1 + number2 + carry);       // Calculate the sum of first and second integers plus
                                                                // the carry.

            String resultStr = String.valueOf(result);          // Convert the result to a string.

           carry = 0;

            // Set the new value of the carry, if carry is not 0:
            if (result >= 10){

                String firstDigit = "" +  resultStr.charAt(0);
                String secondDigit = "" + resultStr.charAt(1);
                carry = Short.parseShort(firstDigit);

                resultStr = secondDigit;
            }

            // Add the calculated result to sum. (next calculated integer of the sum).
            sum =  resultStr + sum;

            i--;
        }
    }

    String getSum(){
        return sum;                                             // Return the value of the sum
    }

    short getCarry() {
        return carry;                           // Return the carry value.
    }
}
