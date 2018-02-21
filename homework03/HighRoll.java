/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  HighRoll.java
 *  Purpose       :  TUI that uses DiceSet.java to play a cool game
 *  Author        :  Patrick Utz
 *  Date          :  2018-02-22
 *  Description   :
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-14  B.J. Johnson  Initial writing and release
 *  @version 1.0.1  2018-02-20  Patrick Utz  adapted shell script to work as a TUI for DiceSet.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll {

  public static void main( String args[] ) {
    int count = Integer.parseInt( args[0] );
    int sides = Integer.parseInt( args[1] );
    int score = 0;
    int highScore = 0;
    DiceSet mainDs = new DiceSet( count, sides );
    System.out.println( "\n\n\n\n\n\n\n\n\n\n\n\n *** Welcome to the High Roll Game Simulation! ***\n" );

    // This line uses the two classes to assemble an "input stream" for the user to type
    // text into the program

    BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
    while( true ) {
      System.out.println( "\nPlease enter one of the following options: \n\n" +
                                "| [Typing '1'] --> Rolls all the dice.                                | \n" +
                                "| [Typing '2'] --> Rolls a single die. Enter which die after the 2.   | \n" +
                                "| [Typing '3'] --> Calculates the score for this set.                 | \n" +
                                "| [Typing '4'] --> Saves this score as a high score.                  | \n" +
                                "| [Typing '5'] --> Displays the high score.                           | \n" +
                                "| [Typing 'Q'] --> Quits the program.                                 | \n" );
      System.out.print( ">> " );
      try {
        String inputLine = input.readLine();
        switch( inputLine.charAt(0) ) {
            case '1':
                mainDs.roll();
                System.out.println("\n\n\n\n\n\n\n\n\n\n\nAll the dice have been rolled.");
                System.out.println("Current dice set: " + mainDs.toString());
                break;
            case '2':

                int index = Integer.parseInt(  inputLine.substring( 2, inputLine.length() ) );
                mainDs.rollIndividual( index );
                System.out.println("\n\n\n\n\n\n\n\n\n\n\nRolling die " + index + " you get: " + mainDs.getIndividual(index));
                System.out.println("Current dice set: " + mainDs.toString());
                break;
            case '3':
                score = mainDs.sum();
                System.out.println("\n\n\n\n\n\n\n\n\n\n\nThe score for this set is: " + score);
                System.out.println("Current dice set: " + mainDs.toString());
                break;
            case '4':
                if( score >= highScore ) {
                  highScore = score;
                  System.out.println( "\n\n\n\n\n\n\n\n\n\n\n\nScore saved. This is a new high score!" );
                } else {
                  System.out.println( "\n\n\n\n\n\n\n\n\n\n\n\nScore saved. Not the highest score." );
                }
                break;
            case '5':
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\nThe high score is: " + highScore);
                break;
            case 'q':
                System.exit(0);
                break;
            case 'Q':
                System.exit(0);
                break;
            default:
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n'" + inputLine +
                                  "' is an invalid response. Please enter a valid option.\n");
                break;
        }
      }
      catch( IOException ioe ) {
          System.out.println( "Caught IOException" );
      }
      catch( StringIndexOutOfBoundsException lol ) {
          System.out.println( "\n\n\n\n\n\n\n\n\n\n\n\nType some text please!" );
      }
    }
  }
}
