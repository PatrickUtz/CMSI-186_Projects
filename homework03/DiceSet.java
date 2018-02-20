/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  Patrick Utz
 *  Date          :  2017-02-09
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DiceSet( int k, int n );                  // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public void rollIndividual( int i );             // Randomly rolls only the ith die in this set
 *                   public int getIndividual( int i );               // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString( DiceSet ds );     // Classwide version of the preceding instance method
 *                   public boolean isIdentical( DiceSet ds );        // Returns true iff this set is identical to the set ds
 *                   public static void main( String[] args );        // The built-in test program for this class
 *
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-09  B.J. Johnson  Initial writing and release
 *  @version 1.2.0  2018-02-08  Patrick Utz   Completed the code
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class DiceSet {

/**
  * private instance data
  */
  private int count;
  private int sides;
  private Die[] ds = null;

  // public constructor:
/**
  * constructor
  * @param  count int value containing total dice count
  * @param  sides int value containing the number of pips on each die
  * @throws IllegalArgumentException if one or both arguments don't make sense
  * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
  */
  public DiceSet( int count, int sides ) {
    if( count < 1 || sides < 4 ) {
      System.out.println("Invalid values. Please enter a count 1 or greater and sides 4 or greater");
    } else {
        this.count = count;
        this.sides = sides;
        this.ds = new Die[count];
        for( int i = 0; i < count; i++ ) {
          ds[i] = new Die( sides );
          ds[i].roll();
        }
      }
  }

/**
  * @return the sum of all the dice values in the set
  */
  public int sum() {
    int sum = 0;
    for( int i = 0; i < ds.length; i++ ) {
      sum += ds[i].getValue();
    }
    return sum;
  }

/**
  * Randomly rolls all of the dice in this set
  *  NOTE: you will need to use one of the "toString()" methods to obtain
  *  the values of the dice in the set
  */
  public void roll() {
    for( int i = 0; i < ds.length; i++ ) {
      ds[i].roll();
    }
  }

/**
  * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
  * @param  dieIndex int of which die to roll
  * @return the integer value of the newly rolled die
  * @trhows IllegalArgumentException if the index is out of range
  */
  public void rollIndividual( int dieIndex ) {
    ds[dieIndex].roll();
  }

/**
  * Gets the value of the die in this set indexed by 'dieIndex'
  * @param  dieIndex int of which die to roll
  * @trhows IllegalArgumentException if the index is out of range
  */
  public int getIndividual( int dieIndex ) {
    return ds[dieIndex].getValue();
  }

/**
  * @return Public Instance method that returns a String representation of the DiceSet instance
  */
  public String toString() {
    StringBuilder dsArray = new StringBuilder();
    for( int i = 0; i < ds.length; i++ ) {
      dsArray.append( "[" + ds[i].getValue() + "] " );
    }
    String dsArrayString = dsArray.toString();
    return dsArrayString;
  }

/**
  * @return Class-wide version of the preceding instance method
  */
  public static String toString( DiceSet ds ) {
    return ds.toString();
  }

/**
  * @return  tru iff this set is identical to the set passed as an argument
  */
  public boolean isIdentical( DiceSet ds ) {
    return true;
  }
  /**
   * A little test main to check things out
   */
  public static void main( String[] args ) {
    DiceSet test = new DiceSet(5,8);
    System.out.println("Original array: " + test.toString());
    System.out.println("The sume of the array: " + test.sum());
    test.roll();
    System.out.println("The reult of rolling the dice: " + test.toString());
    test.rollIndividual(3);
    System.out.println("Rolling the third die you get: " + test.getIndividual(3));
    System.out.println("The reult of rolling the die you now get: " + test.toString());
  }

}
