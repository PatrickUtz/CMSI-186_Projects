/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :  The SoccerSim class that simulates soccer balls in an arena.
 *  @author       :  Patrick Utz
 *  Date written  :  2018-03-13
 *  Description   :  This class is the SoccerSim class that simulates soccer balls in an arena
 *                   for Homework 5, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.1  2018-03-13  Patrick Utz   Added methods
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

import java.text.DecimalFormat;

public class SoccerSim {
  /**
   *  Class field definitions go here
   */
  private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;

  private static double totalSec = 0;
  private static double timeSlice;


  /***************************
   *  The Constructor
  ****************************/
  public SoccerSim( double angle, double timeSlice ) {
    this.timeSlice = timeSlice;
  }

  public static void readValues( String[] args ) {

    try {
      if( args.length < 4 ) {
        System.out.println( "Not enough values inputted.\n" );
        System.exit(-2);
      } else if( args.length % 4 == 0 ) {
          timeSlice = 1;
          int n = 0;
          for( int i = 0; i < ((args.length)/4); i++ ) {
            double xPosition = Double.parseDouble(args[n + 0]);
            double yPosition = Double.parseDouble(args[n + 1]);
            double xVelocity = Double.parseDouble(args[n + 2]);
            double yVelocity = Double.parseDouble(args[n + 3]);
            n += 4;
          }
        } else if( args.length % 4 == 1 ) {
          timeSlice = Double.parseDouble(args[args.length - 1]);
          int n = 0;
          for( int i = 0; i < ((args.length - 1)/4); i++ ) {
            double xPosition = Double.parseDouble(args[n + 0]);
            double yPosition = Double.parseDouble(args[n + 1]);
            double xVelocity = Double.parseDouble(args[n + 2]);
            double yVelocity = Double.parseDouble(args[n + 3]);
            n += 4;
          }
        } else {
          System.out.println( "Invalid amount of values inputted.\n" );
          System.exit(-3);
        }
      }
      catch( NumberFormatException nfe ) {
        System.out.println( "Not a valid double!" );
        System.exit(-1);
      }
    }

/**
*  The main program starts here
*  remember the constraints from the project description
*  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
*  @param  args  String array of the arguments from the command line
*/
  public static void main( String args[] ) {
    readValues( args );
    // System.out.println(timeSlice);
    // ClockSolver cse = new ClockSolver();
    // Clock clock = new Clock( angle, timeSlice );
    // System.out.println( "\n   Hello world, from the ClockSolver program!!\n\n" );
    System.exit( 0 );
  }
}
