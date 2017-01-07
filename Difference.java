package NotASimpleCalculator;

/**
 * GENERAL INFORMATION:
 *      @author                      Olga Chitsvarina.
 *      Date of creation:            December 25, 2016.
 *      Date of last modification:   December 27, 2016.
 * ABOUT THE PROGRAM:
 *      This program calculates sum, difference and product of two positive decimal numbers.
 * ABOUT THE CLASS:
 *      This class calculates the difference of two numbers entred by the user.
 */
class Difference {

    private boolean firstIsLarger;                              // true if first number entered is larger, false otherwise.

    // First and second number entered by the user:
    private String newStringA;
    private String newStringB;

    private String difference = "";

    /**
     * This is a class constructor that calls the methods that calculate the difference of the two numbers entered by
     * the user.
     * @param newStringA - first number entered by the user.
     * @param newStringB - second number entered by the user.
     * @param firstIsLarger - true: if first number is larger, false otherwise.
     */
    Difference(String newStringA, String newStringB, boolean firstIsLarger){

        // Set private variables:
        this.newStringA = newStringA;
        this.newStringB = newStringB;
        this.firstIsLarger = firstIsLarger;

        // Calculate the difference:
        calculateDifference();
    }

    /**
     * This method takes the string as a parameter and returns the flipped value of that string.
     * @param string - string that should be flipped.
     * @return - the flipped value of the string passed in as a parameter.
     */
    private String flipTheString(String string){
        String flippedString = "";

        // Flip the string character by character:
        for (short i =0; i< string.length(); i++){
            flippedString = string.charAt(i)+ flippedString;
        }

        return flippedString;
    }

    /**
     * This method modifies the numbers in string (part of difference calculation algorithm if someone would do it using
     * pen and paper). (Part of borrowing mechanism).
     * @param string - string to modify.
     * @return - modified string as if it would be done in borrowing mechanism using pen and pencil.
     */
    private String changeStr(String string){

        short intToInsert;
        short intToRemove;

        short i = 1;
        while(i < string.length()){


            intToRemove= Short.parseShort(""+ string.charAt(i));// Find the character that should be modified.

            // If character is zero, change it to 9:
            if (intToRemove == 0){
                intToInsert = 9;
                string = string.substring(0,i) +
                        String.valueOf(intToInsert) +
                        string.substring(i+1);

            }else {
            // If character is not zero, change it to number which is less than the character number by 1:
                intToInsert = (short) (intToRemove - 1);
                string = string.substring(0,i) +
                        String.valueOf(intToInsert) +
                        string.substring(i+1);
                break;
            }
            i++;
        }
        return string;
    }

    /**
     * This method calculates the difference among the two numbers entered by the user:
     * @param string1 - corresponds to the number which is larger than string2.
     * @param string2 - corresponds to the number which is smaller that string1.
     * @return - result of the difference (string1 - string2).
     */
    private String calculate(String string1, String string2) {

        if (string1.length() > 0){                              // Base case for the recursion.

            // Get the first characters:
            String characterA = "" + string1.charAt(0);
            String characterB = "" + string2.charAt(0);

            // Convert these characters to short:
            short shortA = Short.parseShort(characterA);
            short shortB = Short.parseShort(characterB);

            if (shortA >= shortB){                              // Check to see which one is larger.
                // Calculate the difference:
                difference = String.valueOf(shortA-shortB) + difference;
                return calculate(string1.substring(1), string2.substring(1));

            }else{                                              // shortB is greater than shortA

                string1 = changeStr(string1);                   // Do the borrowing.

                // Calculate the difference:
                difference = String.valueOf(shortA + 10 - shortB) + difference;
                return calculate(string1.substring(1), string2.substring(1));

            }
        }

        return difference;                                      // Return the value of the difference.
    }


    /**
     * This method determines which of the numbers is larger and call other methods which calculates the following:
     * number which is larger - number which is smaller.
     */
    private void calculateDifference(){

        // Flip the strings:
        String string1 = flipTheString(newStringA);
        String string2 = flipTheString(newStringB);

        if (firstIsLarger){                                     // string1 is larger:
            difference = calculate(string1,string2);            // Calculate string1 minus string2
        }
        else{                                                   // string2 is larger:
            difference = calculate(string2,string1);            // Calculate string2 minus string1
        }
    }

    String getDifference(){
        return difference;
    }

}


