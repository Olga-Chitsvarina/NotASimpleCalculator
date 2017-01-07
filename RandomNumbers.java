package NotASimpleCalculator;

import java.util.Random;

/**
 * GENERAL INFORMATION:
 *      @author                         Olga Chitsvarina.
 *      Date of creation:               December 27, 2016.
 *      Date of last modification:      January 5, 2016.
 * ABOUT THE PROGRAM:
 *      This program calculates sum, difference and product of two positive decimal numbers.
 * ABOUT THE CLASS:
 *      This class generates random decimal numbers using the following format:
 *      RANGE number of numbers Point(.) RANGE number of numbers.
 */
class RandomNumbers {
    private String randomNumber;                                // Random decimal number.
    private final short RANGE = 500;                           // RANGE numbers before/after the point symbol.

    /**
     * This is a class constructor, it calls the method to generate a random decimal number.
     */
    RandomNumbers(){
        generateRandomNumber();
    }

    /**
     * This method generates RANGE number of random numbers.
     * @return String that corresponds to the RANGE number of random numbers.
     */
    private String generateNumbers(){

        String result = "";
        Random randomGenerator = new Random();

        short i = 0;
        // Generate random number, convert it to string and add to the result string:
        while (i<RANGE){
            result  = result + String.valueOf(randomGenerator.nextInt(10));
            i++;
        }
         return result;
    }

    /**
     * This method calls another method to generate the first/second part of the decimal number and then connects the
     * first and second part with the point sign.
     */
    private void generateRandomNumber(){
        // Generate first/second part of the number:
        String firstPart = generateNumbers();
        String secondPart = generateNumbers();

        randomNumber = firstPart + "." + secondPart;            // Connect first and second part of the decimal number.
    }

    /**
     * This method returns the value of the random decimal number.
     * @return - random decimal number, which is constructed out of RANGE number of numbbers before/after the point
     * sign.
     */
    String getRandomNumber(){
        return randomNumber;
    }
}
