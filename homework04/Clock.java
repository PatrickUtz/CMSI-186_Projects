/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  Patrick Utz
 *  Date written  :  2018-02-22
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 *  @version 1.0.1  2018-02-22  Patrick Utz   Added methods
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

import java.text.DecimalFormat;

public class Clock {
  /**
   *  Class field definintions go here
   */
  private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
  private static final double INVALID_ARGUMENT_VALUE = -1.0;
  private static final double MAXIMUM_DEGREE_VALUE = 360.0;
  private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
  private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;

  private static double totalSec = 0;
  private static double angle;
  private static double timeSlice;
  private static double hour;
  private static double minute;
  private static double second;
  private static double hourAngle;
  private static double minuteAngle;

  /**
   *  Constructor goes here
  */
  public Clock( double angle, double timeSlice ) {
    this.angle = angle;
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

  // /**
  //  ****** NO NEED TO VALIDATE -> TAKEN CARE OF IN ClockSolver MAIN ******
  //  *  Method to validate the angle argument
  //  *  @param   argValue  String from the main programs args[0] input
  //  *  @return  double-precision value of the argument
  //  *  @throws  NumberFormatException
  // */
  // public double validateAngleArg( String argValue ) throws NumberFormatException {
  //   if( argValue < 0 || argValue > 360 ) {
  //     throw new IllegalArgumentException("invalid angle");
  //   } else {
  //     return argValue;
  //   }
  // }
  //
  // /**
  // *  Method to validate the optional time slice argument
  // *  @param  argValue  String from the main programs args[1] input
  // *  @return double-precision value of the argument or -1.0 if invalid
  // *  note: if the main program determines there IS no optional argument supplied,
  // *         I have elected to have it substitute the string "60.0" and call this
  // *         method anyhow.  That makes the main program code more uniform, but
  // *         this is a DESIGN DECISION, not a requirement!
  // *  note: remember that the time slice, if it is small will cause the simulation
  // *         to take a VERY LONG TIME to complete!
  // */
  // public double validateTimeSliceArg( String argValue ) {
  //   return 0.0;
  // }

  /**
  *  Method to calculate and return the current position of the hour hand
  *  @return double-precision value of the hour hand location
  */
  public double getHourHandAngle() {
    hourAngle = HOUR_HAND_DEGREES_PER_SECOND * totalSec;
    while( Math.abs(hourAngle) > 180 ) {
      hourAngle -= 180;
    }
    return hourAngle;
  }

  /**
  *  Method to calculate and return the current position of the minute hand
  *  @return double-precision value of the minute hand location
  */
  public double getMinuteHandAngle() {
    minuteAngle = MINUTE_HAND_DEGREES_PER_SECOND * totalSec;
    while( Math.abs(minuteAngle) > 180 ) {
      minuteAngle -= 180;
    }
    return minuteAngle;
  }

  /**
  *  Method to calculate and return the angle between the hands
  *  @return double-precision value of the angle between the two hands
  */
  public double getHandAngle() {
    hourAngle = getHourHandAngle();
    minuteAngle = getMinuteHandAngle();
    double handAngle = Math.abs( hourAngle - minuteAngle );
    return handAngle;
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
  *  Method to return a String representation of this clock
  *  @return String value of the current clock
  */
  public String toString() {
    return "Clock string, dangit!";
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
    Clock clock = new Clock( 2, 2 );
    System.out.println( "    New clock created: " + clock.toString() );
    System.out.println( "    Testing validateAngleArg()....");
    System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
    // try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
    // catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
  }
}
