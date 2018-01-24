public class CountTheDays {
  public static void main ( String [] args ) {

    long month1;
    long day1;
    long year1;
    long month2;
    long day2;
    long year2;

    month1 = Long.parseLong( args[0] );
    day1   = Long.parseLong( args[1] );
    year1  = Long.parseLong( args[2] );
    month2 = Long.parseLong( args[3] );
    day2   = Long.parseLong( args[4] );
    year2  = Long.parseLong( args[5] );

    long totalDays = CalendarStuff.daysBetween( month1, day1, year1, month2, day2, year2 );

    System.out.println( totalDays );
  }
}
