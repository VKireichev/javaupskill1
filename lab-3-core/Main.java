import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.regex.*;

public class Main {
   private static Scanner in = new Scanner(System.in); 
   private static int number1, number2;
   private static String operation;
   private static String usage = 
      "Enter arithmetic expression: 'a + b' 'a - b' 'a * b' 'a / b' 'a !';  where a and b are integer numbers.";
                                                                                             
   private static long plus(int number1,  int number2) {
      return (number1 + number2);
   }
   private static long minus(int number1,  int number2) {
      return (number1 - number2);
   }
   private static long mult(int number1,  int number2) {
      return (number1 * number2);
   }
   private static long devide(int number1,  int number2) {
      return (number1 / number2);
   }
   private static long fact(int number) {
      long res = 1;
      for (int i = 2; i <= number; i++) {
         res *= i;
      }
      return res;
   }

   public static void main(String[] args) {

      System.out.println(usage);
      while(true) {
         // Read user input line and parse it. 
         int nOfTokens = readLine();
         if (nOfTokens == -1) {                        // EXIT sign
            return;
         }
         if (nOfTokens != 0) {                         // Wrong input
            continue;
         }

         // Execute operation and print result.
         switch(operation) {
            case "+":
               System.out.println(plus(number1, number2));
               break;
            case "-":
               System.out.println(minus(number1, number2));
               break;
            case "*":
               System.out.println(mult(number1, number2));
               break;
            case "/":
               if (number2 == 0) {
                  System.out.println("Error.Division by zero! ");
                  break;
               }
               System.out.println(devide(number1, number2));
               break;
            case "!":
               System.out.println(fact(number1));
               break;
            default:
               System.out.println("Invalid operation.");
         }
      }
   } 

   private static int readLine() {
      String inputString = "";
      Pattern expression;
      Matcher matcher;
      
      try {
         expression = Pattern.compile("(-?[0-9]+) *([*+-/!]) *(-?[0-9]*) *");  // pattern: number1 operation [number2] 
         while ("".equals(inputString)) {       // Read non empty input line.	        
            System.out.print(":> ");            // Print a prompt  
            inputString = in.nextLine();
         }
         if ("q".equals(inputString)) {         // EXIT sign
            return -1;
         }
         matcher = expression.matcher(inputString);
         if (matcher.find()) {                  // Pattern is found. Extract operation and numbers
            operation = matcher.group(2);
            number1   = new Integer(matcher.group(1));
            if ( ! "!".equals(operation) ) {
               number2   = new Integer(matcher.group(3));
            } 
            return 0;                            // Return OK.
         }            
         System.out.println(usage);
         return 1;                               // Wrong input sign
      }
      catch (NumberFormatException e) {
         System.out.println("Invalid Number");
         return 1;                               // Wrong input
      }
      catch (Exception e) {
         System.out.println("Internal error");
         return -1;                              // Exit
      }
   }

}
     
                         