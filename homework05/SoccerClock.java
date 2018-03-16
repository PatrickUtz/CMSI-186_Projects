/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerClock.java
 *  Purpose       :  Provides a class defining methods for the SoccerSim class
 *  @author       :  Patrick Utz
 *  Date written  :  2018-03-13
 *  Description   :  This class provides a bunch of methods which may be useful for the SoccerSim class
 *                   for Homework 5, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.1  2018-03-13  Patrick Utz   Added methods
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

import java.text.DecimalFormat;

public class SoccerClock {
  /**
   *  Class field definintions go here
   */
  private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;

  private static double totalSec = 0;
  private static double timeSlice;
  private static double hour;
  private static double minute;
  private static double second;

  /**
   *  Constructor goes here
  */
  public SoccerClock( double timeSlice ) {
    this.timeSlice = timeSlice;
  }

  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
  */
  public double tick() {
    totalSec += timeSlice;
    return totalSec;
  }

  /**
  *  Method to calculate and return the current hour
  *  @return double-precision value of the hour
  */
  public double getHour() {
    hour = Math.floor( totalSec/3600 );
    return hour;
  }

  /**
  *  Method to calculate and return the current minute
  *  @return double-precision value of the minute
  */
  public double getMinute() {
    minute = Math.floor( (totalSec - (hour*3600))/60 );
    return minute;
  }

  /**
  *  Method to calculate and return the seconds
  *  @return double-precision value of seconds
  */
  public double getInstantSeconds() {
    second = totalSec - ( hour*3600 ) - ( minute*60 );
    return second;
  }

  /**
  *  Method to fetch the total number of seconds
  *   we can use this to tell when 12 hours have elapsed
  *  @return double-precision value the total seconds private variable
  */
  public double getTotalSeconds() {
    return totalSec;
  }

  /**
  *  The main program starts here
  *  remember the constraints from the project description
  *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
  *  be sure to make LOTS of tests!!
  *  remember you are trying to BREAK your code, not just prove it works!
  */
  public static void main( String args[] ) {

    System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
    System.out.println( "  Creating a new clock: " );
    SoccerClock clock = new SoccerClock( 2 );
    System.out.println( "    New clock created: " + clock.toString() );
    System.out.println( "    Testing validateAngleArg()....");
    System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
  }
}
