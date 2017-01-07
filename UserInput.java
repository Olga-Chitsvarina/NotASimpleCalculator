package NotASimpleCalculator;

/**
 * GENERAL INFORMATION:
 *      @author                      Olga Chitsvarina.
 *      Date of creation:            December 24, 2016.
 *      Date of last modification:   December 27, 2016.
 * ABOUT THE PROGRAM:
 *      This program calculates sum, difference and product of two positive decimal numbers.
 * ABOUT THE CLASS:
 *      This class modifies the numbers entered by the user: removes the point from both number (rememmbering the
 *      position), adds zeroes at the beginning and at the end, so that the length of numbers is the same.
 */

class UserInput {

    // Numbers entered by the user:
    private String stringA;
    private String stringB;

    // Modified numbers entered by the user:
    private String newStringA;
    private String newStringB;

    // Number that corresponds to the number of characters to the point:
    private short stepsToPoint;

    /**
     * Class constructor that modifies the user input.
     * @param stringA - represents the first number entered by the user.
     * @param stringB - represents the second number entered by the user.
     */
    UserInput(String stringA, String stringB) {
        // Assign stringA and stringB:
        this.stringA = stringA;
        this.stringB = stringB;

        setNewStrings();                                        // Modify user input.
    }

    private short getIndexOfPoint(String string){
        return (short)string.indexOf('.');                      // Return the index of the point in the string.
    }

    /**
     * This method adds ".0" to the end of the string, so that if this string represents the integer then it converts
     * this integer to the decimal number.
     * @param string some string that should be modified
     * @return the string which consists of the parameter string plus ".0".
     */
    private String makeDecimal(String string){
        return string + ".0";                                   // Add ".0" to the end of the string.

    }

    private String getLeftSide(String string, short pointIndex){
        return(string.substring(0,pointIndex));                 // Return the part of the number before the point.
    }

    private String getRightSide(String string, short pointIndex){
        return(string.substring(pointIndex+1));                 // Return the part of the number after the point.
    }

    /**
     * This method returns the largest number out of two numbers passes in as parameters.
     * @param lengthStringA - some short value.
     * @param lengthStringB - some short value.
     * @return - returns the largest number out of lengthStringA and lengthStringB.
     */
    private short returnMaxLength(short lengthStringA, short lengthStringB){
        if (lengthStringA >= lengthStringB){
            return lengthStringA;
        } else{
            return lengthStringB;
        }
    }

    private short calculateZerosToAdd(short length, short desiredLength){
        return (short)(desiredLength - length);                 // Find the number of zeroes that should be added.
    }

    /**
     * This method modifies the string by adding numberOfZeroes times zeroes at the beginning of the string.
     * @param string - string that should be modified.
     * @param numberOfZeros - number of zeroes that should be added at the beginning of the string.
     * @return - the modified number of the string (with numberOfZeroes zeroes at the beginning).
     */
    private String addZerosBefore(String string, short numberOfZeros){
        String newString = string;
        short i  = 0;

        while( i < numberOfZeros){                              // If length is not the same as the desired length, then
            newString = "0" + newString;                        // add zero at the beginning.
            i++;
        }
        return newString;
    }

    /**
     * Does the same as addZeroesBefore method, but adds zeroes to the end of the string.
     * @param string - string that should be modified.
     * @param numberOfZeros - number of zeroes that should be added to the end of the string.
     * @return - the modified string value.
     */
    private String addZerosAfter(String string, short numberOfZeros){
        String newString = string;
        short i = 0;
        while( i < numberOfZeros){
            newString = newString + "0";
            i++;
        }
        return newString;
    }

    private void setStepsToPoint(short pointIndexSteps){
        this.stepsToPoint = pointIndexSteps;                    // Find characters to the point.
    }

    /**
     * This method modifies stringA and stringB so that they have the same length (but represent the same decimal
     * numbers) and also do not contain the point. If the numbers entered are not decimal numbers, but integers,
     * it converts these numbers to decimal numbers.
     */
    private void setNewStrings(){

        // Find indexes of the point:
        short indexOfPointA = getIndexOfPoint(stringA);
        short indexOfPointB = getIndexOfPoint(stringB);

        // If first number is not a decimal number, convert it to decimal:
        if ((indexOfPointA==-1)&&(stringA.length()< 32765)){
            stringA = makeDecimal(stringA);
            indexOfPointA = getIndexOfPoint(stringA);
        }

        // If second number is not a decimal number, convert it to decimal:
        if ((indexOfPointB==-1)&&(stringB.length()< 32765)){
            stringB = makeDecimal(stringB);
            indexOfPointB = getIndexOfPoint(stringB);
        }

        // Set left side (before the point) and right side(after the point) of stringA:
        String leftSideA = getLeftSide(stringA, indexOfPointA);
        String rightSideA = getRightSide(stringA, indexOfPointA);

        // Set left/right sides of stringB:
        String leftSideB = getLeftSide(stringB, indexOfPointB);
        String rightSideB = getRightSide(stringB, indexOfPointB);

        // Find the length of left and right sides of stringA:
        short lengthLeftA = (short)leftSideA.length();
        short lengthRightA = (short) rightSideA.length();

        // Find the length of left and right sides of stringB:
        short lengthLeftB = (short) leftSideB.length();
        short lengthRightB = (short) rightSideB.length();

        // Find the maximum length of left sides and right sides:
        short leftMaxLength = returnMaxLength(lengthLeftA, lengthLeftB);
        short rightMaxLength = returnMaxLength(lengthRightA, lengthRightB);

        short zerosToAdd;

        // Add zeroes at the beginning of left shortest side (make left sides of the same length):
        if (lengthLeftA != leftMaxLength){
            zerosToAdd = calculateZerosToAdd(lengthLeftA, leftMaxLength);
            leftSideA = addZerosBefore(leftSideA, zerosToAdd);
        }else {
            zerosToAdd = calculateZerosToAdd(lengthLeftB, leftMaxLength);
            leftSideB = addZerosBefore(leftSideB, zerosToAdd);
        }

        // Add zeroes to the end of the right shortest side (make right sides of the same length):
        if (lengthRightA != rightMaxLength){
            zerosToAdd = calculateZerosToAdd(lengthRightA, rightMaxLength);
            rightSideA = addZerosAfter(rightSideA, zerosToAdd);
        }else {
            zerosToAdd = calculateZerosToAdd(lengthRightB, rightMaxLength );
            rightSideB = addZerosAfter(rightSideB, zerosToAdd);
        }

        setStepsToPoint(rightMaxLength);                        // Find number of characters to the point from the right.

        // Add left and right sides together, removing the point:
        newStringA = leftSideA + rightSideA;
        newStringB = leftSideB + rightSideB;
    }

    String getNewStringA(){
        return newStringA;                                      // Return the value of modified first number.
    }

    String getNewStringB(){
        return newStringB;                                      // Return the value of modified second number.
    }

    short getStepsToPoint(){
        return stepsToPoint;                                    // Return the value of stepsToPoint.
    }
}



