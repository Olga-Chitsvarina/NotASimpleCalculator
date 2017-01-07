package NotASimpleCalculator;

import java.util.ArrayList;

/**
 * GENERAL INFORMATION:
 *      @author                      Olga Chitsvarina.
 *      Date of creation:            December 24, 2016.
 *      Date of last modification:   January 2, 2017.
 * ABOUT THE PROGRAM:
 *      This program calculates sum, difference and product of two positive decimal numbers.
 * ABOUT THE CLASS:
 *      This class calculates the partial products.
 */
class Multiplication {

    // Two numbers:
    private String newStringA;
    private String newStringB;


    private short lengthOfStrings;                              // Length of these numbers.

    // Array of partial products:
    private ArrayList<String> partialProducts = new ArrayList<>();

    private String partialProduct = "";                         // One partial product.

    /**
     * Class constructor that fill out the array of partial products.
     * @param newStringA represent first number.
     * @param newStringB represent second number.
     * @param lengthOfStrings represent length of first/second number.
     */
    Multiplication(String newStringA, String newStringB, short lengthOfStrings){

        // Assign private variables:
        this.newStringA = flipTheString(newStringA);
        this.newStringB = flipTheString(newStringB);
        this.lengthOfStrings = lengthOfStrings;

        calculate();                                            // Fill the array of partial products.

        // Make partial products of the same length:
        addZeroesAfter();
        addZerosBefore();
    }

    /**
     * This method flips the string.
     * @param string string to flip.
     * @return the value of flipped string.
     */
    private String flipTheString(String string) {
        String flippedString = "";
        short i = 0;

        // Flip the string character by character:
        while (i < string.length()) {
            flippedString = string.charAt(i) + flippedString;
            i++;
        }
        return flippedString;                                   // Return the value of the flipped string.
    }

    /**
     * This method returns the value of the carry.
     * @param result result of multiplication.
     * @return the value of the carry based on the result.
     */
    private short setCarry(String result) {

        if (result.length() > 1){                               // If result consist of two numbers, then
            return Short.parseShort(result.charAt(0) + "");     // Carry is the first number.
        } else                                                  // Result consist of 1 number, carry is 0.
            return 0;
    }

    /**
     * This method fills out the array of partial products.
     */
    private void calculate() {

        short i = 0;
        short j = 0;

        String characterA;
        String characterB;

        short integerA;
        short integerB;

        short integerAbyB;
        String stringAbyB;

        short carry = 0;

        // Multiply every number of the second number by every number of the first number:
        while (i < lengthOfStrings) {
            while (j < lengthOfStrings) {

                // Get the string representation of the numbers to multiply:
                characterA = "" + newStringA.charAt(i);
                characterB = "" + newStringB.charAt(j);

                // Convert them to short:
                integerA = Short.parseShort(characterA);
                integerB = Short.parseShort(characterB);

                // Calculate the product and convert it to string:
                integerAbyB = (short)((integerA * integerB) + carry );
                stringAbyB = String.valueOf(integerAbyB);

                carry = setCarry(stringAbyB);                   // Determine the value of carry.

                // Find the next symbol of the partialProduct based on the carry value:
                if (carry != 0) {
                    partialProduct = stringAbyB.charAt(1) + partialProduct;
                } else {
                    partialProduct = stringAbyB.charAt(0) + partialProduct;
                }

                j++;
            }

            // Add carry at the beginning of the partial product and add this partial product to array:
            partialProduct = String.valueOf(carry) + partialProduct;
            partialProducts.add(partialProduct);

            partialProduct = "";

            i++;
            j = 0;
            carry = 0;
        }
    }

    /**
     * This method adds zeroes at the end of all partial products so that to make right sides of each partial
     * product of the same length.
     */
    private void addZeroesAfter() {
        short i = 0;
        short j = 0;

        String innerElement;

        // Add zeroes to each line, so that each next line contains more zeroes in the end, compare to the previous
        // partial product (at least by one).
        while (i < lengthOfStrings) {
            while (j < i) {
                innerElement = partialProducts.get(i);
                partialProducts.set(i, innerElement + "0");

                j++;
            }

            j = 0;
            i++;
        }
    }

    /**
     * This method does the same as addZeroesAfter, but it adds zeroes not to the end, but to the beginning of the
     * partial product (to make the partial products of the same length).
     */
    private void addZerosBefore(){

        short i = (short)(lengthOfStrings-1);
        short j= 0;

        // Add zeroes to each line, so that each next line contains more zeroes in the beginning, compare to the previous
        // partial product (at least by one).
        while (i>=0){
            while (j < (lengthOfStrings-1-i)){
                partialProducts.set(i, "0" + partialProducts.get(i));
                j++;
            }
            i--;
            j =0;
        }
    }

    ArrayList<String> getPartialProducts() {
        return partialProducts;                                 // Return the value of the partial products array.
    }

}
