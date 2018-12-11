import java.util.Scanner;

public class FracCalc {

    /**
     * Prompts user for input, passes that input to produceAnswer, then outputs the result.
     * @param args - unused
     */
	// TODO: Read the input from the user and call produceAnswer with an equation
    // Checkpoint 1: Create a Scanner, read one line of input, pass that input to produceAnswer, print the result.
    // Checkpoint 2: Accept user input multiple times.
    public static void main(String[] args)
    {
    	Scanner scan = new Scanner(System.in);
    	String input = scan.nextLine();
    	while(!input.equalsIgnoreCase("quit")) {
        	System.out.println(produceAnswer(input));
        	input = scan.nextLine();
    	}
    		
    }
    
    /**
     * produceAnswer - This function takes a String 'input' and produces the result.
     * @param input - A fraction string that needs to be evaluated.  For your program, this will be the user input.
     *      Example: input ==> "1/2 + 3/4"
     * @return the result of the fraction after it has been calculated.
     *      Example: return ==> "1_1/4"
     */
    // TODO: Implement this function to produce the solution to the input
    // Checkpoint 1: Return the second operand.  Example "4/5 * 1_2/4" returns "1_2/4".
    // Checkpoint 2: Return the second operand as a string representing each part.
    //               Example "4/5 * 1_2/4" returns "whole:1 numerator:2 denominator:4".
    // Checkpoint 3: Evaluate the formula and return the result as a fraction.
    //               Example "4/5 * 1_2/4" returns "6/5".
    //               Note: Answer does not need to be reduced, but it must be correct.
    // Final project: All answers must be reduced.
    //               Example "4/5 * 1_2/4" returns "1_1/5".
    public static String produceAnswer(String input)
    {  
    	
    	// I take out the first character in case it is a negative.
    	String takeOutFirstChar = input.substring(1, input.length());
    	String operator;
    	// Checks to see what the operator is
    	if(takeOutFirstChar.indexOf(" +") != -1) {
    		operator = "+";
    	} else if(takeOutFirstChar.indexOf(" *") != -1) {
    		operator = "*";
    	} else if(takeOutFirstChar.indexOf("/ -") != -1) {
    		operator = "/";
    	} else if(takeOutFirstChar.indexOf(" -") != -1) {
    		operator = "-";
    	} else {
    		operator = "/";
    	}
    	// Looks for a space then the operator, then uses that to find the two operands.
    	String operand1 = input.substring(0, input.indexOf(" " + operator));
    	String operand2 = input.substring(input.indexOf(" " + operator) + 3, input.length());
    	// Checks for whole numbers and sets whole for operand1.
    	String whole1;
    	if(operand1.indexOf("_") != -1) {
    		whole1 = operand1.substring(0, operand1.indexOf("_"));
    	} else {
    		whole1 = "0";
    	}
    	// Checks for fraction and sets numerator and denominator. Also sets whole if no fraction is found for operand1.
    	String num1 = "0";
    	String denom1 = "1";
    	if(operand1.indexOf("/") != -1) {
    		if(operand1.indexOf("_") != -1) {
    			num1 = operand1.substring(operand1.indexOf("_") + 1, operand1.indexOf("/"));
    		} else {
    			num1 = operand1.substring(0, operand1.indexOf("/"));
    		}
    		denom1 = operand1.substring(operand1.indexOf("/") + 1, operand1.length());
    	} else {
    		whole1 = operand1;
    	}
    	// Checks for whole numbers and sets whole for operand2.
    	String whole2;
    	if(operand2.indexOf("_") != -1) {
    		whole2 = operand2.substring(0, operand2.indexOf("_"));
    	} else {
    		whole2 = "0";
    	}
    	// Checks for fraction and sets numerator and denominator. Also sets whole if no fraction is found for operand2.
    	String num2 = "0";
    	String denom2 = "1";
    	if(operand2.indexOf("/") != -1) {
    		if(operand2.indexOf("_") != -1) {
    			num2 = operand2.substring(operand2.indexOf("_") + 1, operand2.indexOf("/"));
    		} else {
    			num2 = operand2.substring(0, operand2.indexOf("/"));
    		}
    		denom2 = operand2.substring(operand2.indexOf("/") + 1, operand2.length());
    	} else {
    		whole2 = operand2;
    	}
    	String parseInput = "whole:" + whole1 + " numerator:" + num1 + " denominator:" + denom1;
    	int whole1i = Integer.parseInt(whole1);
    	int whole2i = Integer.parseInt(whole2);
    	int num1i = Integer.parseInt(num1);
    	int num2i = Integer.parseInt(num2);
    	int denom1i = Integer.parseInt(denom1);
    	int denom2i = Integer.parseInt(denom2);
    	String finalAnswer = "";
    	if(operator.equals("*")) {
    		finalAnswer = multiplication(whole1i, num1i, denom1i, whole2i, num2i, denom2i);
    	} else if(operator.equals("/")) {
    		finalAnswer = division(whole1i, num1i, denom1i, whole2i, num2i, denom2i);
    	} else if(operator.equals("+")) {
    		finalAnswer = addition(whole1i, num1i, denom1i, whole2i, num2i, denom2i);
    	} else {
    		finalAnswer = subtraction(whole1i, num1i, denom1i, whole2i, num2i, denom2i);
    	}
    	return(finalAnswer);
    }

    // TODO: Fill in the space below with helper methods
    
    public static String addition(int whole1, int num1, int denom1, int whole2, int num2, int denom2) 
    {
    	int totalNum;
    	int wholeToNum1 = 0;
    	int wholeToNum2 = 0;
    	if(whole1 != 0) {
    		wholeToNum1 = whole1 * denom1;
    	} 
    	if(whole2 != 0) {
    		wholeToNum2 = whole2 * denom2;
    	}
    	if(whole1 < 0) {
    		num1 = -num1;
    	}
    	if(whole2 < 0) {
    		num2 = -num2;
    	}
    	num1+=wholeToNum1;
    	num2+=wholeToNum2;
    	int lcm = leastCommonMultiple(denom1, denom2);
    	num1 = num1 * (lcm/denom1);
    	num2 = num2 * (lcm/denom2);
    	totalNum = num1 + num2;
    	String answer = totalNum + "/" + lcm;
		return(answer);
    }
    public static String subtraction(int whole1, int num1, int denom1, int whole2, int num2, int denom2) 
    {
    	int totalNum;
    	int wholeToNum1 = 0;
    	int wholeToNum2 = 0;
    	if(whole1 != 0) {
    		wholeToNum1 = whole1 * denom1;
    	} 
    	if(whole2 != 0) {
    		wholeToNum2 = whole2 * denom2;
    	}
    	if(whole1 < 0) {
    		num1 = -num1;
    	}
    	if(whole2 < 0) {
    		num2 = -num2;
    	}
    	num1+=wholeToNum1;
    	num2+=wholeToNum2;
    	int lcm = leastCommonMultiple(denom1, denom2);
    	num1 = num1 * (lcm/denom1);
    	num2 = num2 * (lcm/denom2);
    	totalNum = num1 - num2;
    	String answer = totalNum + "/" + lcm;
		return(answer);
    }
    public static String division(int whole1, int num1, int denom1, int whole2, int num2, int denom2)
    {

    	int totalNum;
    	int totalDenom;
    	int wholeToNum1 = 0;
    	int wholeToNum2 = 0;
    	boolean o1IsZero = false;
    	if(whole1 == 0 && num1 == 0) {
    		o1IsZero = true;
    	}
    	if(whole1 != 0) {
    		wholeToNum1 = whole1 * denom1;
    	} 
    	if(whole2 != 0) {
    		wholeToNum2 = whole2 * denom2;
    	}
    	if(whole1 < 0) {
    		num1 = -num1;
    	}
    	if(whole2 < 0) {
    		num2 = -num2;
    	}
    	num1+=wholeToNum1;
    	num2+=wholeToNum2;
    	int placeholder = denom2;
    	denom2 = num2;
    	num2 = placeholder;
    	if(num1 != 0 && num2 != 0) {
    		totalNum = num1 * num2;
    	} else {
    		if(num1 == 0) {
    			totalNum = num2;
    		} else{
    			totalNum = num1;
    		}
    	}
    	totalDenom = denom1 * denom2;
    	String answer = totalNum + "/" + totalDenom;
    	if(o1IsZero == true) {
    		answer = "0";
    	}
		return(answer);
    }	
    public static String multiplication(int whole1, int num1, int denom1, int whole2, int num2, int denom2)
    {
    	int totalNum;
    	int wholeToNum1 = 0;
    	int wholeToNum2 = 0;
    	int totalDenom;
    	boolean oIsZero = false;
    	if((whole1 == 0 && num1 == 0) || (whole2 == 0 && num2 == 0)) {
    		oIsZero = true;
    	}
       	if(whole1 != 0) {
    		wholeToNum1 = whole1 * denom1;
    	} 
    	if(whole2 != 0) {
    		wholeToNum2 = whole2 * denom2;
    	}
    	if(whole1 < 0) {
    		num1 = -num1;
    	}
    	if(whole2 < 0) {
    		num2 = -num2;
    	}
    	num1+=wholeToNum1;
    	num2+=wholeToNum2;
    	if(num1 != 0 && num2 != 0) {
    		totalNum = num1 * num2;
    	} else {
    		if(num1 == 0) {
    			totalNum = num2;
    		} else{
    			totalNum = num1;
    		}
    	}
    	totalDenom = denom1 * denom2;
    	String answer = totalNum + "/" + totalDenom;
    	if(oIsZero == true) {
    		answer = "0";
    	}
		return(answer);
    }
    /**
     * greatestCommonDivisor - Find the largest integer that evenly divides two integers.
     *      Use this helper method in the Final Checkpoint to reduce fractions.
     *      Note: There is a different (recursive) implementation in BJP Chapter 12.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The GCD.
     */
    public static int greatestCommonDivisor(int a, int b)
    {
        a = Math.abs(a);
        b = Math.abs(b);
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        while (min != 0) {
            int tmp = min;
            min = max % min;
            max = tmp;
        }
        return max;
    }
    
    /**
     * leastCommonMultiple - Find the smallest integer that can be evenly divided by two integers.
     *      Use this helper method in Checkpoint 3 to evaluate expressions.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The LCM.
     */
    public static int leastCommonMultiple(int a, int b)
    {
        int gcd = greatestCommonDivisor(a, b);
        return (a*b)/gcd;
    }
}
