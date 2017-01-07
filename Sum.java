package NotASimpleCalculator;

/**
 * GENERAL INFORMATION:
 *      @author                      Olga Chitsvarina.
 *      Date of creation:            December 24, 2016.
 *      Date of last modification:   December 27, 2016.
 * ABOUT THE PROGRAM:
 *      This program calculates sum, difference and product of two positive decimal numbers.
 * ABOUT THE CLASS:
 *      This class calculates the sum of two numbers entred by the user.
 */
class Sum {

    // Modified values of two numbers entered by the user:
    private String newStringA;
    private String newStringB;

    private String sum = "";

    /**
     * This is a class constructor that calls methods in order to calculate the sum of two numbers.
     * @param newStringA - modified first number entered by the user.
     * @param newStringB - modified second number entered by the user.
     */
    Sum(String newStringA, String newStringB){
        // Set newStringA and newStringB:
        this.newStringA = newStringA;
        this.newStringB = newStringB;

        // Calculate the sum:
        calculateSum();
    }

    /**
     * This method calculates sum entered by the user.
     */
    private void calculateSum(){

        short carryOver = 0;

        short result;
        short i = (short)(newStringA.length() - 1);             // i corresponds to the last index of newStringA.

        while (i >=0){
            // Find characters at position i in newStringA and newStringB:
            String charA = "" + (newStringA.charAt(i));
            String charB = "" + (newStringB.charAt(i));

            // Convert these characters to short numbers:
            short number1 = Short.parseShort(charA);
            short number2 = Short.parseShort(charB);

            // Find sum of these numbers and the carry:
            result = (short) (number1 + number2 + carryOver);
            String resultStr = String.valueOf(result);

            carryOver = 0;

            // Set the new carry value and write the number to the result:
            if (result >= 10){

                // Find first and second number of the result:
                String firstDigit = resultStr.charAt(0) + "";
                String secondDigit = resultStr.charAt(1) + "";

                carryOver = Short.parseShort(firstDigit);       // Set the new carry value.

                resultStr = secondDigit;                        // Set the new value of resultStr.
            }

            sum =  resultStr + sum;                             // Add another digit character to sum result.

            // Make sure that value of last carry is not lost:
            if (i==0 && carryOver!=0)
                sum = carryOver + sum;
            i--;
        }
    }

    String getSum(){
        return sum;                                             // Return the value of sum.
    }
}
