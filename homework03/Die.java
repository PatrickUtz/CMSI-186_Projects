/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Die.java
 *  Purpose       :  Provides a class describing a single die that can be rolled
 *  @author       :  Patrick Utz
 *  Date          :  2018-02-22
 *  Description   :  This class provides the data fields and methods to describe a single game die.  A
 *                   die can have "N" sides.  Sides are randomly assigned sequential pip values, from 1
 *                   to N, with no repeating numbers.  A "normal" die would thus has six sides, with the
 *                   pip values [spots] ranging in value from one to six.  Includes the following:
 *                   public Die( int nSides );                  // Constructor for a single die with "N" sides
 *                   public int roll();                         // Roll the die and return the result
 *                   public int getValue()                      // get the value of this die
 *                   public void setSides()                     // change the configuration and return the new number of sides
 *                   public String toString()                   // Instance method that returns a String representation
 *                   public static String toString()            // Class-wide method that returns a String representation
 *                   public static void main( String args[] );  // main for testing porpoises
 *
 *  Notes         :  Restrictions: no such thing as a "two-sided die" which would be a coin, actually.
 *                   Also, no such thing as a "three-sided die" which is a physical impossibility without
 *                   having it be a hollow triangular prism shape, presenting an argument as to whether
 *                   the inner faces are faces which then should be numbered.  Just start at four for
 *                   minimum number of faces.  However, be aware that a four-sided die dosn't have a top
 *                   face to provide a value, since it's a tetrahedron [pyramid] so you'll have to figure
 *                   out a way to get the value, since it won't end up on its point.
 *
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-06  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2017-02-17  B.J. Johnson  Filled in method code
 *  @version 1.2.0  2018-02-08  Patrick Utz   Completed the code
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Random;

public class Die {

  /**
   * private instance data
   */
   private int sides;
   private int pips;
   private int checkRoll;
   private int randDieNum;
   private final int MINIMUM_SIDES = 1;

   // public constructor:
  /**
   * constructor
   * @param nSides int value containing the number of sides to build on THIS Die
   * @throws       IllegalArgumentException
   * Note: parameter must be checked for validity; invalid value must throw "IllegalArgumentException"
   */
  public Die( int nSides ) {
    if (nSides < 5) {
      System.out.println( "Invalid side value. Please enter a value greater than 4." );
      System.exit(0);
    } else {
      sides = nSides;
      checkRoll = 0;
    }
  }

  /**
   * Roll THIS die and return the result
   * @return  integer value of the result of the roll, randomly selected
   */
  public int roll() {
    Random randNum = new Random();
    randDieNum = randNum.nextInt(sides) + 1;
    checkRoll += 1;
    return randDieNum;
  }

  /**
   * Get the value of THIS die to return to the caller; note that the way
   *  the count is determined is left as a design decision to the programmer
   *  For example, what about a four-sided die - which face is considered its
   *  "value"?
   * @return the pip count of THIS die instance
   */
  public int getValue() {
    if( checkRoll != 0 ) {
      return randDieNum;
    } else {
      return 0;
    }
  }

  /**
   * @param int  the number of sides to set/reset for this Die instance
   * @return      The new number of sides, in case anyone is looking
   * @throws      IllegalArgumentException
   */
  public void setSides( int sides ) {
    if (sides < 5) {
      System.out.println( "Invalid side value. Please enter a value greater than 4." );
      System.exit(0);
    } else {
      this.sides = sides;
    }
  }

  /**
   * Public Instance method that returns a String representation of THIS die instance
   * @return String representation of this Die
   */
  public String toString() {
    if( checkRoll != 0 ) {
      return "[" + randDieNum + "]";
    } else {
      return "0";
    }
  }

  /**
   * Class-wide method that returns a String representation of THIS die instance
   * @return String representation of this Die
   */
  public static String toString( Die d ) {
    return d.toString();
  }

  /**
   * A little test main to check things out
   */
  public static void main( String[] args ) {
    System.out.println( "\nWelcome to the Die class!" );
    System.out.println( "\n *** 6 sided die ***" );
    Die test1 = new Die(8);
    System.out.println("Rolling the die you get: " + test1.roll());
    System.out.println("Rolling the die you get: " + test1.roll());
    System.out.println("Rolling the die you get: " + test1.roll());
    System.out.println("Rolling the die you get: " + test1.roll());
    System.out.println("Testing the getValue method you get: " + test1.getValue());
    System.out.println("Testing the toString method you get: " + test1.toString());
    System.out.println("Testing the static toString method you get: " + Die.toString(test1));
    test1.setSides(5);
    System.out.println("Testing the setSides method using 2 sides you get: " + test1.roll());

    System.out.println( "\n *** 1000001 sided die ***" );
    Die test2 = new Die(1000001);
    System.out.println("Rolling the die you get: " + test2.roll());
    System.out.println("Rolling the die you get: " + test2.roll());
    System.out.println("Rolling the die you get: " + test2.roll());
    System.out.println("Rolling the die you get: " + test2.roll());
    System.out.println("Testing the getValue method you get: " + test2.getValue());
    System.out.println("Testing the toString method you get: " + test2.toString());
    System.out.println("Testing the static toString method you get: " + Die.toString(test2));
    test2.setSides(5);
    System.out.println("Testing the setSides method using 2 sides you get: " + test2.roll());

    System.out.println( "\n *** 201 sided die ***" );
    Die test3 = new Die(201);
    System.out.println("Rolling the die you get: " + test3.roll());
    System.out.println("Rolling the die you get: " + test3.roll());
    System.out.println("Rolling the die you get: " + test3.roll());
    System.out.println("Rolling the die you get: " + test3.roll());
    System.out.println("Testing the getValue method you get: " + test3.getValue());
    System.out.println("Testing the toString method you get: " + test3.toString());
    System.out.println("Testing the static toString method you get: " + Die.toString(test3));
    test3.setSides(5);
    System.out.println("Testing the setSides method using 2 sides you get: " + test3.roll());

    System.out.println( "\n *** 9 sided die ***" );
    Die test4 = new Die(9);
    System.out.println("Rolling the die you get: " + test4.roll());
    System.out.println("Rolling the die you get: " + test4.roll());
    System.out.println("Rolling the die you get: " + test4.roll());
    System.out.println("Rolling the die you get: " + test4.roll());
    System.out.println("Testing the getValue method you get: " + test4.getValue());
    System.out.println("Testing the toString method you get: " + test4.toString());
    System.out.println("Testing the static toString method you get: " + Die.toString(test4));
    test4.setSides(5);
    System.out.println("Testing the setSides method using 2 sides you get: " + test4.roll());

    System.out.println( "\n *** 23 sided die ***" );
    Die test5 = new Die(23);
    System.out.println("Rolling the die you get: " + test5.roll());
    System.out.println("Rolling the die you get: " + test5.roll());
    System.out.println("Rolling the die you get: " + test5.roll());
    System.out.println("Rolling the die you get: " + test5.roll());
    System.out.println("Testing the getValue method you get: " + test5.getValue());
    System.out.println("Testing the toString method you get: " + test5.toString());
    System.out.println("Testing the static toString method you get: " + Die.toString(test5));
    test5.setSides(5);
    System.out.println("Testing the setSides method using 2 sides you get: " + test5.roll());

    System.out.println( "\n *** 1 sided die ***" );
    Die test6 = new Die(1);
    System.out.println("Rolling the die you get: " + test6.roll());
    System.out.println("Rolling the die you get: " + test6.roll());
    System.out.println("Rolling the die you get: " + test6.roll());
    System.out.println("Rolling the die you get: " + test6.roll());
    System.out.println("Testing the getValue method you get: " + test6.getValue());
    System.out.println("Testing the toString method you get: " + test6.toString());
    System.out.println("Testing the static toString method you get: " + Die.toString(test6));
    test6.setSides(5);
    System.out.println("Testing the setSides method using 2 sides you get: " + test6.roll());
  }

}
