/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  The Ball class keeps track of soccer balls for the SoccerSim class.
 *  @author       :  Patrick Utz
 *  Date written  :  2018-03-13
 *  Description   :  The Ball class keeps track of soccer balls for the SoccerSim class.
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

public class Ball {
  /**
   *  Class field definitions go here
   */
  // private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;

  private static double[] balls = {4};

  /***************************
   *  The Constructor
  ****************************/
  public Ball( double xPosition, double yPosition, double xVelocity, double yVelocity ) {
    balls[0] = xPosition;
    balls[1] = yPosition;
    balls[2] = xVelocity;
    balls[3] = yVelocity;
  }

  public static void main( String args[] ) {
    // checkIfValid( args );
    // ClockSolver cse = new ClockSolver();
    // Clock clock = new Clock( angle, timeSlice );
    // System.out.println( "\n   Hello world, from the ClockSolver program!!\n\n" );
    System.exit( 0 );
  }
}
