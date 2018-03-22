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

  private static double[] balls = new double[5];

  /***************************
   *  The Constructor
  ****************************/
  public Ball( double xPosition, double yPosition, double xVelocity, double yVelocity, double timeSlice ) {
    balls[0] = xPosition;
    balls[1] = yPosition;
    balls[2] = xVelocity;
    balls[3] = yVelocity;
    balls[4] = timeSlice;
  }

  public void updateVelocity() {
    balls[2] = balls[2] * (Math.pow(0.99,balls[4]));
    balls[3] = balls[3] * (Math.pow(0.99,balls[4]));
  }

  public void updatePosition() {
    balls[0] += balls[2]*balls[4];
    balls[1] += balls[3]*balls[4];
  }

  public String getStatus() {
    StringBuilder status = new StringBuilder();
    status.append("Position = <" + balls[0] + ", " + balls[1] + ">");
    status.append("\t  Velocity = <" + balls[2] + ", " + balls[3] + ">");
    // status.append("\tTime Slice: <" + balls[4] + ">");
    return status.toString();
  }

  public static void main( String args[] ) {
    // checkIfValid( args );
    // ClockSolver cse = new ClockSolver();
    // Clock clock = new Clock( angle, timeSlice );
    // System.out.println( "\n   Hello world, from the ClockSolver program!!\n\n" );
    System.exit( 0 );
  }
}
