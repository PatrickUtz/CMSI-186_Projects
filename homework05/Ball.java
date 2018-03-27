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

import java.text.DecimalFormat;

public class Ball {
  /**
   *  Class field definitions go here
   */
  // private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;

  private double[] balls = new double[5];

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
    balls[2] = balls[2]-(.01*balls[2])*balls[4];
    balls[3] = balls[3]-(.01*balls[3])*balls[4];
    // balls[2] = balls[2] * (Math.pow(0.99,balls[4]));
    // balls[3] = balls[3] * (Math.pow(0.99,balls[4]));
  }

  public void updatePosition() {
    balls[0] += balls[4]*balls[2];
    balls[1] += balls[4]*balls[3];
    // balls[0] += (Math.pow(0.99,balls[4]) + balls[2]);
    // balls[1] += (Math.pow(0.99,balls[4]) + balls[3]);
  }

  public double[] getPosition() {
    double positionX = balls[0];
    double positionY = balls[1];
    double[] currentPosition = {positionX, positionY};
    return currentPosition;
  }

  public double[] getVelocity() {
    double velocityX = balls[2];
    double velocityY = balls[3];
    double[] currentVelocity = {velocityX, velocityY};
    return currentVelocity;
  }

  public String getStatus() {
    StringBuilder status = new StringBuilder();
    String patternDecimal = "#.##";
    DecimalFormat decimalFormatOutput = new DecimalFormat(patternDecimal);
    status.append("Position = <" + decimalFormatOutput.format(balls[0]) + ", " + decimalFormatOutput.format(balls[1]) + "> ft");
    status.append("\t  Velocity = <" + decimalFormatOutput.format(balls[2]) + ", " + decimalFormatOutput.format(balls[3]) + "> ft/s");
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
