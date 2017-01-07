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
 *      This class calculates the sum of partial products using class SumMultiplication methods.
 */
class AdditionMultiplication {

    private ArrayList<String> arrayListToAdd;                   // Partial products to add.
    private ArrayList<String> leftHandSide = new ArrayList<>(); // Left side of partial products.
    private ArrayList<String> rightHandSide = new ArrayList<>();// Right side of partial products.

    private short lengthOfStrings;                              // Length of decimal numbers.

    private boolean done = false;

    private String product = "";

    /**
     * This is a class constructor, which calls methods that split each partial product in two halves and writes the
     * value of the left side to the left sides list and the value of the right side into right sides list and calculate
     * the sum of partial products.
     * @param arrayListToAdd partialProducts to add
     * @param lengthOfStrings length of decimal numbers that were multiplied to get the list of partial products.
     */
    AdditionMultiplication(ArrayList<String> arrayListToAdd,
                           short lengthOfStrings){
        // Set the value of private variables:
        this.arrayListToAdd = arrayListToAdd;
        this.lengthOfStrings = lengthOfStrings;

        // Split each partial product in half, write value of left side to left sides list (do the same with the
        // right side):
        fillRightAndLeftSides();

        calculateSum();
    }

    /**
     * Split the partial products into two halves and write these values to the right/left sides arrays.
     */
    private void fillRightAndLeftSides(){

        // Split each partial product into two halves:
        short i = (short)(arrayListToAdd.size()-1);
        while(i >= 0){

            // Add left side to left sides list and right side to right sides list:
            leftHandSide.add(0,arrayListToAdd.get(i).substring(0, lengthOfStrings));
            rightHandSide.add(0, arrayListToAdd.get(i).substring(lengthOfStrings ));

            i--;
        }
    }

    /**
     * This method calculates the sum of each partial product line by line by calculating the sum of first two elements
     * of right sides list, then moving to calculation of the sum of first two elements of left sides list. Write right
     * side of the result to right sides list and left side of the result to left side list.
     */
    private void calculateSum() {
        if (leftHandSide.size()==1){                            // Base case of the recursion algorithm ( there is only
                                                                // there is only one element in the list).
            done = true;

            // Left side of the product is in left sides list, right side is in right sides list.
            product = leftHandSide.get(0) + rightHandSide.get(0);
        }
        if (!done) {
            // Find the strings that corresponds to first and second left halves of first and second partial products:
            String stringALeft = leftHandSide.get(0);
            String stringBLeft = leftHandSide.get(1);

            // Find the strings that corresponds to first and second right halves of first and second partial products:
            String stringBRight = rightHandSide.get(1);
            String stringARight = rightHandSide.get(0);

            // Calculate the sum of right sides:
            SumMultiplication sumHelper1 = new SumMultiplication(stringARight, stringBRight, (short)0);

            // Set the carry:
            short carry = sumHelper1.getCarry();

            // Calculate the sum of left side:
            SumMultiplication sumHelper2 = new SumMultiplication(stringALeft, stringBLeft, carry);

            // Remove first and second element of left sides list and place the left side of the sum of the first and
            // second partial products at the first position of the left sides list:
            leftHandSide.remove(0);
            leftHandSide.remove(0);
            leftHandSide.add(0, sumHelper2.getSum());

            // Remove first and second element of left sides list and place the right side of the sum of the first and
            // second partial products at the first position of the right sides list:
            rightHandSide.remove(0);
            rightHandSide.remove(0);
            rightHandSide.add(sumHelper1.getSum());

            calculateSum();
        }
    }

    String getProduct() {
        return product;                         // Return the value of the product.
    }
}



