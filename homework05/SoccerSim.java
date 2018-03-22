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
  private static Ball[] balls = null;


  /***************************
   *  The Constructor
  ****************************/
  public SoccerSim( String[] args ) {
      readValues(args);
      SoccerClock clock = new SoccerClock(timeSlice);
  }

  public static void readValues( String[] args ) {
    try {
      if( args.length < 4 ) {
        System.out.println( "Not enough values inputted.\n" );
        System.exit(-2);
      } else if( args.length % 4 == 0 ) {
          balls = new Ball[args.length];
          timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
          int n = 0;
          for( int i = 0; i < ((args.length)/4); i++ ) {
            double xPosition = Double.parseDouble(args[n + 0]);
            double yPosition = Double.parseDouble(args[n + 1]);
            double xVelocity = Double.parseDouble(args[n + 2]);
            double yVelocity = Double.parseDouble(args[n + 3]);
            Ball ball = new Ball(xPosition, yPosition, xVelocity, yVelocity, timeSlice);
            balls[i] = ball;
            n += 4;
          }
        } else if( args.length % 4 == 1 ) {
          balls = new Ball[args.length - 1];
          timeSlice = Double.parseDouble(args[args.length - 1]);
          if( timeSlice < 0 || timeSlice > 1800 ) {
            System.out.println("Invalid time slice! Please enter a value between 0 and 1800 next time.");
            System.exit(-2);
          }
          int n = 0;
          for( int i = 0; i < ((args.length - 1)/4); i++ ) {
            double xPosition = Double.parseDouble(args[n + 0]);
            double yPosition = Double.parseDouble(args[n + 1]);
            double xVelocity = Double.parseDouble(args[n + 2]);
            double yVelocity = Double.parseDouble(args[n + 3]);
            Ball ball = new Ball(xPosition, yPosition, xVelocity, yVelocity, timeSlice);
            balls[i] = ball;
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


  public static void initialStat() {
    System.out.println("\n\n\n\nHello! Welcome to the Soccer Simulation Program.\n");
    System.out.println("Initial Conditions: \n\n" + "Time Slice: " + timeSlice + "\n");
    for( int i = 1; i < balls.length; i++ ) {
      System.out.println("Ball " + i + " status:  " + balls[0].getStatus()+"\n");
    }
  }

  public static void currentStat() {
    for( int i = 1; i < balls.length; i++ ) {
      System.out.println("Ball " + i + " status:  " + balls[0].getStatus()+"\n");
    }
  }

  public static void calcCollision() {

    while (true) {
      break;
    }
  }


/**
*  The main program starts here
*  remember the constraints from the project description
*  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
*  @param  args  String array of the arguments from the command line
*/
  public static void main( String args[] ) {
    SoccerSim ss = new SoccerSim( args );
    ss.initialStat();
    ss.calcCollision();
    // ss.currentStat();
    System.exit( 0 );
  }
}
