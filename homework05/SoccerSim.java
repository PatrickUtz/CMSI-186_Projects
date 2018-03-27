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
  private final double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;

  private double totalSec = 0;
  private double timeSlice;
  private Ball[] balls = null;
  private final double RADIUS = .370833;
  private final double DIAMETER = .741666;
  private int ballHit1;
  private int ballHit2;
  private int ballHitPole = -1;
  private int ballOutside;
  private SoccerClock clock;
  private double xPos;
  private double yPos;
  private double xVel;
  private double yVel;
  private int numberBalls;
  // private int ballsAtRest = 0;
  private double fieldBorder = 1000;


  /***************************
   *  The Constructor
  ****************************/
  public SoccerSim( String[] args ) {
      readValues(args);
      clock = new SoccerClock(timeSlice);
  }

  public void readValues( String[] args ) {
    try {
      if( args.length < 4 ) {
        System.out.println( "Not enough values inputted.\n" );
        System.exit(-2);
      } else if( args.length % 4 == 0 ) {
          numberBalls = (args.length)/4;
          balls = new Ball[numberBalls];
          timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
          for( int i = 0; i < (numberBalls); i++ ) {
            xPos = Double.parseDouble(args[4*i + 0]);
            yPos = Double.parseDouble(args[4*i + 1]);
            xVel = Double.parseDouble(args[4*i + 2]);
            yVel = Double.parseDouble(args[4*i + 3]);
            balls[i] = new Ball(xPos, yPos, xVel, yVel, timeSlice);
          }
        } else if( args.length % 4 == 1 ) {
          numberBalls = (args.length - 1)/4;
          balls = new Ball[numberBalls];
          timeSlice = Double.parseDouble(args[args.length - 1]);
          if( timeSlice < 0 || timeSlice > 1800 ) {
            System.out.println("Invalid time slice! Please enter a value between 0 and 1800 next time.");
            System.exit(-2);
          }
          for( int i = 0; i < (numberBalls); i++ ) {
            xPos = Double.parseDouble(args[4*i + 0]);
            yPos = Double.parseDouble(args[4*i + 1]);
            xVel = Double.parseDouble(args[4*i + 2]);
            yVel = Double.parseDouble(args[4*i + 3]);
            balls[i] = new Ball(xPos, yPos, xVel, yVel, timeSlice);
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


  public void initialStat() {
    System.out.println("\n\n\n\nHello! Welcome to the Soccer Simulation Program.\n");
    System.out.println("Initial Conditions: \n\n" + "Time Slice: " + timeSlice + "\nThe field size is 1000 X 1000 feet");
    System.out.println("The pole is at x = 321.0 ft & y = 543.0 ft");
    for( int i = 0; i < numberBalls; i++ ) {
      System.out.println("Ball " + (i + 1) + " status:  " + balls[i].getStatus());
    }
    System.out.print("\n");
  }

  public void currentStat() {
    System.out.println("Time: " + clock.toString());
    for( int i = 0; i < numberBalls; i++ ) {
      System.out.println("Ball " + (i + 1) + " status:  " + balls[i].getStatus());
    }
  }

  public boolean isCollision() {
    for( int i = 0; i < balls.length; i++ ) {
      double[] position = balls[i].getPosition();
      double x = position[0];
      double y = position[1];
      for( int j = i+1; j < balls.length; j++ ) {
        double[] positionTest = balls[j].getPosition();
        double xTest = positionTest[0];
        double yTest = positionTest[1];
        if( Math.sqrt( Math.pow((x - xTest),2) + Math.pow((y - yTest),2) ) < DIAMETER ) {
          ballHit1 = i;
          ballHit2 = j;
          return true;
        }
      }
    }

    for( int i = 0; i < balls.length; i++ ) {
      double[] position = balls[i].getPosition();
      double x = position[0];
      double y = position[1];
      if( Math.abs(Math.abs(x) - 321.0) < DIAMETER && Math.abs(Math.abs(y) - 543.0) < DIAMETER ) {
        ballHitPole = i;
        return true;
      }
    }
    return false;
  }

  public boolean isOutofBounds() {
    for( int i = 0; i < balls.length; i++ ) {
      double[] position = balls[i].getPosition();
      double x = position[0];
      double y = position[1];
      if( Math.abs(x) > fieldBorder || Math.abs(y) > fieldBorder ) {
        ballOutside = i;
        return true;
      }
    }
    return false;
  }

  public boolean allAtRest() {
    int ballsAtRest = 0;
    for( int i = 0; i < balls.length; i++ ) {
      double[] velocity = balls[i].getVelocity();
      double x = velocity[0];
      double y = velocity[1];
      // System.out.println( x + " " + y);
      if( (Math.abs(x) < (1.0/12.0)) && (Math.abs(y) < (1.0/12.0)) ) {
        // System.out.println(Math.abs(x));
        // System.out.println(Math.abs(y));
        ballsAtRest += 1;
        // System.out.println(ballsAtRest);
      }
    }
    if( ballsAtRest == balls.length ) {
      return true;
    } else {
      return false;
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
    if( ss.isCollision() ) {
      ss.currentStat();
      if (ss.ballHitPole != -1) {
        System.out.println("Collision detected between ball " + (ss.ballHitPole + 1) + " and the pole!");
        System.exit( 4 );
      } else {
        System.out.println("Collision detected between ball " + (ss.ballHit1 + 1) + " and ball " + (ss.ballHit2 + 1) + "!");
        System.exit( 4 );
      }
    }
    if( ss.isOutofBounds() ) {
      System.out.println("Ball " + (ss.ballOutside + 1) + " is outside the field!");
      System.exit( 4 );
    }

    if( ss.allAtRest() ) {
      System.out.println("All balls are at rest!");
      System.exit( 4 );
    }

    while ( true ) {
      ss.clock.tick();
      for( int i = 0; i < ss.balls.length; i++ ) {
        ss.balls[i].updatePosition();
        ss.balls[i].updateVelocity();
      }
      ss.currentStat();
      // double[] ref = ss.balls[0].getVelocity();
      // double vel = ref[0];
      // double vel2 = ref[1];
      // System.out.println(ref[0] + " " + ref[1]);
      System.out.println("");
      if( ss.isCollision() ) {
        // ss.currentStat();
        if (ss.ballHitPole != -1) {
          System.out.println("Collision detected between ball " + (ss.ballHitPole + 1) + " and the pole!");
          System.exit( 4 );
        } else {
          System.out.println("Collision detected between ball " + (ss.ballHit1 + 1) + " and ball " + (ss.ballHit2 + 1) + "!");
          System.exit( 4 );
        }
      }
      if( ss.isOutofBounds() ) {
        System.out.println("No Collison! Ball " + (ss.ballOutside + 1) + " is outside the field!");
        System.exit( 4 );
      }
      if( ss.allAtRest() ) {
        System.out.println("All balls are at rest!");
        System.exit( 4 );
      }

    }
  }
}
