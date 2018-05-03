/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  This program helps a user decide the optimal number of coins of a certain denomination
                 that result in a desired total amount [optimal = the least amount of coins
                 possible]
 * @author    :  Patrick Utz
 * Date       :  2018-04-26
 * Description:  Program takes as input arguments a sequence of coin denominations [distinct positive
                 integers in no particular order], followed by an arbitrary amount of money [a non-negative integer],
                 and which outputs the optimal way of making change for that amount using those denominations
                 [optimal meaning the minimum number of coins], or if change cannot be made, outputs the
                 message "IMPOSSIBLE"
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2018-04-26  Patrick Utz   Initial writing and begin coding
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

import java.util.Arrays;

public class DynamicChangeMaker {

   /**
    *  This Constructor does not do much as this class is typically used in a static context
    *  @param  null  Nothing is inputted
    */

    public DynamicChangeMaker() {
      System.out.println("Nothing to see here!");
    }


   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to validate all inputted data. It also uses a dynamic algorithm to find the least amount of coins
    *  needed to make a targeted quantity of cents out of set denomination values
    *  @return  Tuple object  This Tuple object represents the optimum number of coins
    *  @throws  NumberFormatException if something is hinky
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public static Tuple makeChangeWithDynamicProgramming( int[] denominations, int targetCents ) {
      // Checking for bad data //
      try {
        for (int i = 0; i < denominations.length; i++) {
          String checkInt = Integer.toString(denominations[i]);
          // Checking validity of denominations //
          if (denominations[i] < 1) {
            System.out.println("Invalid coin denomination, please enter positive integers only!");
            System.exit(-1);
          }
        }
        // Checking validity of target cents input //
        if (targetCents < 0) {
          System.out.println("Invalid target cent value, please enter positive values only!");
          System.exit(-1);
        }
        // Checking for duplicates in denominations //
        int[] testDup = denominations;
        for (int i = 0; i < denominations.length; i++) {
          for (int j = i+1; j < testDup.length-1; j++) {
            if (denominations[i] == testDup[j]) {
              System.out.println("Invalid coin denomination, no duplicates allowed!");
              System.exit(-2);
            }
          }
        }
      }
      catch( NumberFormatException nfe ) {
        System.out.println( "Invalid integer values inputted!" );
        System.exit(-1);
      }


      // Implementing dynamic algorithm //
      Tuple denoms = new Tuple(denominations);
      int rowCount = denominations.length;
      int columnCount = targetCents + 1;
      Tuple[][] table = new Tuple[rowCount][columnCount];
      for (int i = 0; i < rowCount; i++) {
        for( int j = 0; j < columnCount; j++ ) {
          if (j == 0) {
            // This is column 0 //
            table[i][j] = new Tuple(rowCount);
          } else {
              if (denoms.getElement(i) > j) {
                table[i][j] = Tuple.IMPOSSIBLE;
              } else {
                Tuple goesIn = new Tuple(rowCount);
                goesIn.setElement(i,1);
                table[i][j] = goesIn;
              }
              if ((j-denoms.getElement(i)) >= 0) {
                // This means it can go backwards //
                if ((table[i][j-denoms.getElement(i)].isImpossible()) || (table[i][j].isImpossible())) {
                  // The cell looking backwards or the one in question is an IMPOSSIBLE //
                  table[i][j] = Tuple.IMPOSSIBLE;
                } else {
                  // The cell looking backwards is NOT an IMPOSSIBLE //
                  table[i][j] = table[i][j].add(table[i][j-denoms.getElement(i)]);
                }
              }
              if (i != 0) {
                // Can look above //
                if (!(table[i-1][j].isImpossible())) {
                  // The cell above is NOT an IMPOSSIBLE //
                  if (table[i][j].isImpossible()) {
                    // The cell in question is an IMPOSSIBLE so replace with top cell //
                    table[i][j] = table[i-1][j];
                  } else if (table[i-1][j].total() < table[i][j].total()) {
                    // The cell above has a total less than that of the one in question //
                    table[i][j] = table[i-1][j];
                  }
                }
              }
          }
        }
      }
      return table[rowCount-1][columnCount-1];
    }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  The main method is used if one wishes to use the program from the command line
    *  @param  args  String array which contains command line arguments 
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public static void main( String[] args ) {
      System.out.println( "\n  Hello! Welcome to Dynamic Change Maker! \n" );
      // Convert input arguments from command line to ints //
      try {
        String inputRawDenoms = args[0];
        int inputTargetCents = Integer.parseInt(args[1]);

        String[] tokens = inputRawDenoms.split(",");
        int[] inputDenoms = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
          inputDenoms[i] = Integer.parseInt(tokens[i]);
        }
        Tuple result = makeChangeWithDynamicProgramming(inputDenoms,inputTargetCents);

        // Output results //
        System.out.println("You can make " + inputTargetCents + " cents as follows: \n");
        for (int i = 0; i < result.length(); i++) {
          System.out.println("Use  " + result.getElement(i) + "  [" + inputDenoms[i] + " cent] coins");
        }
      }
      catch( NumberFormatException nfe ) {
        System.out.println( "Invalid integer values inputted!" );
        System.exit(-1);
      }
    }
}
