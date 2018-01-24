/**
 *  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  01/25/2018
 *  Author        :  Patrick Utz
 *  Date          :  01/21/2018
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.  It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time".  It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2018.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
 *  @version 1.0.1  2017-12-25  B.J. Johnson  Updated for Spring 2018
 *  @version 1.0.2  2018-01-21  Patrick Utz   Updated to include valid methods
 */
public class CalendarStuff {

  /**
   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
   */
   private static final int SUNDAY    = 0;
   private static final int MONDAY    = SUNDAY    + 1;
   private static final int TUESDAY   = SUNDAY    + 2;
   private static final int WEDNESDAY = SUNDAY    + 3;
   private static final int THURSDAY  = SUNDAY    + 4;
   private static final int FRIDAY    = SUNDAY    + 5;
   private static final int SATURDAY  = SUNDAY    + 6;

  // you can finish the rest on your own

  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
   */
   private static final int JANUARY   = 0;
   private static final int FEBRUARY  = JANUARY   + 1;
   private static final int MARCH     = JANUARY   + 2;
   private static final int APRIL     = JANUARY   + 3;
   private static final int MAY       = JANUARY   + 4;
   private static final int JUNE      = JANUARY   + 5;
   private static final int JULY      = JANUARY   + 6;
   private static final int AUGUST    = JANUARY   + 7;
   private static final int SEPTEMBER = JANUARY   + 8;
   private static final int OCTOBER   = JANUARY   + 9;
   private static final int NOVEMBER  = JANUARY   + 10;
   private static final int DECEMBER  = JANUARY   + 11;

  // you can finish these on your own, too

  /**
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   *  NOTE: this is optional, but suggested
   */
   private static int[]  daysNormalYear  = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
   private static int[]  daysLeapYear    = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

  /**
   * The constructor for the class
   */

   public CalendarStuff() {
      System.out.println( "Constructor called..." );  // replace this with the actual code
   }

  /**
   * A method to determine if the year argument is a leap year or not<br />
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear( long year ) {
      if ( ((year % 4) == 0 ) && ( ( year % 100) != 0 ) ) {
        return true;
      } else {
          if ( ((year % 4) == 0 ) && ( (year % 400) == 0 ) ) {
            return true;
          } else {
            return false;
          }
      }
    }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */
   public static long daysInMonth( long month, long year ) {

      if ( (CalendarStuff.isLeapYear(year)) == true ) {
        return (daysLeapYear[(int)(--month)]);
      } else {
        return (daysNormalYear[(int)(--month)]);
      }
   }

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      return true;  // replace this with the actual code
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      if ( year1 < year2 ) {
        return -1;
      } else {
        if ( year1 > year2 ) {
          return 1;
        } else {
          if ( month1 < month2 ) {
            return -1;
          } else {
            if ( month1 > month2 ) {
              return 1;
            } else {
              if ( day1 < day2 ) {
                return -1;
              } else {
                if ( day1 > day2 ) {
                  return 1;
                } else {
                  return 0;
                }
              }
            }
          }
        }
      }
    }

  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   public static boolean isValidDate( long month, long day, long year ) {
      try {
        Long monthL = new Long( month );
        Long dayL = new Long( day );
        Long yearL = new Long( year );
      }
      catch (NumberFormatException nfe) {
        System.out.println( "Please input a valid date value." );
      }
      --month;
      if ( CalendarStuff.isLeapYear(year) ) {
        if ( ((month >= 0) && (month <= 11)) && ( (1 <= day) && (day <= (daysLeapYear[(int)month])) ) ) {
          return true;
        } else {
          return false;
        }
      } else {
        if ( ((month >= 0) && (month <= 11)) && ( (1 <= day) && (day <= (daysNormalYear[(int)month])) ) ) {
          return true;
        } else {
          return false;
        }
      }
    }

  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */
   public static String toMonthString( int month ) {
      switch( month - 1 ) {
         default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
      }
   }

  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   */
   public static String toDayOfWeekString( int day ) {
      switch( day - 1 ) {
         default       : throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }
   }

  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */
   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {

     long firstMonth;
     long firstDay;
     long firstYear;
     long secondMonth;
     long secondDay;
     long secondYear;

     if ( (CalendarStuff.compareDate( month1, day1, year1, month2, day2, year2 )) == -1 ) {
       firstYear = year1;
       firstMonth = month1;
       firstDay = day1;
       secondYear = year2;
       secondMonth = month2;
       secondDay = day2;
     } else {
       if ( (CalendarStuff.compareDate( month1, day1, year1, month2, day2, year2 )) == 1 ) {
        firstYear = year2;
        firstMonth = month2;
        firstDay = day2;
        secondYear = year1;
        secondMonth = month1;
        secondDay = day1;
       } else {
         return 0;
       }
     }

     int firstYearExtra = 0;
     int lastYearExtra = 0;
     int extraLeapDays = 0;
     int wholeYearDays = 0;
     int totalDays = 0;

     //for testing: System.out.println( firstMonth + " " + firstDay + "  " + firstYear );

     //this part accounts for the rest of the days in the beginning year
     for ( int i = 0; i < (firstMonth - 1); i++ ) {
        firstYearExtra = firstYearExtra + daysNormalYear[i];
     }
     firstYearExtra += firstDay;
     firstYearExtra = 365 - firstYearExtra;

    //for testing: System.out.println( firstYearExtra );

     //this part accounts for the rest of the days in the ending year

     for ( int i = 0; i < (secondMonth - 1); i++ ) {
        lastYearExtra += daysNormalYear[i];
     }
     lastYearExtra += secondDay;

     //for testing: System.out.println( lastYearExtra );

     //this part adds all the days in the full years between the two dates
     //this does not account for leap years yet
     wholeYearDays = wholeYearDays + (int)( ((secondYear) - (++firstYear)) * 365 );

     //for testing: System.out.println( wholeYearDays );

     //this part of the code accounts for the leap years and adds the extra days to the totalDays
     for ( int i = 0; i < (secondYear - firstYear + 1); i++ ) {
        if ( CalendarStuff.isLeapYear( (long)(firstYear + (long)i) ) ) {
          extraLeapDays += 1;
        }
     }

     //for testing: System.out.println( extraLeapDays );

     totalDays = firstYearExtra + lastYearExtra + extraLeapDays + wholeYearDays;

     return totalDays;
  }

}
