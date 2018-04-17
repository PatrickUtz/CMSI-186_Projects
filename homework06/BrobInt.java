/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  Patrick Utz
 * Date       :  2018-04-02
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractInt and subtractInt methods
 *  1.2.0  2018-04-02  Patrick Utz    Finshed the program
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class BrobInt {

   public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
   public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
   // public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
   // public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
   // public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
   // public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
   // public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
   // public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
   // public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
   // public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
   public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

  /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
   public  static final BrobInt MAX_INT  = new BrobInt( new Integer( Integer.MAX_VALUE ).toString() );
   public  static final BrobInt MIN_INT  = new BrobInt( new Integer( Integer.MIN_VALUE ).toString() );
   public  static final BrobInt MAX_LONG = new BrobInt( new Long( Long.MAX_VALUE ).toString() );
   public  static final BrobInt MIN_LONG = new BrobInt( new Long( Long.MIN_VALUE ).toString() );
   private static final int     CHARS_THAT_FIT = 8;

  /// These are the internal fields
   private String internalValue = "";        // internal String representation of this BrobInt
                                             // internal StringBuilder representation of this BrobInt
   private byte   sign          = 0;         // "0" is positive, "1" is negative
   private int    signTemp      = 0;         // temporary storage for sign of the result
   private String reversed      = "";        // the backwards version of the internal String representation
   private int[]  intVersion    = null;      // int array for storing the string values; uses the reversed
   private int[]  biggerInt     = null;      // int array for storing the bigger int for subtraction
   private int[]  smallerInt    = null;      // int array for storing the smaller int for subtraction
   private int    index         = 0;         // int that keeps track iteration of looping during addition
   private int    length        = 0;         // length of the BronInt
   private int    carry         = 0;         // holds the value of the carry for algebraic processes

  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
   public BrobInt( String value ) {
      internalValue = value;
      if (internalValue.charAt(0) == '-') {
        sign = 1;
        internalValue = internalValue.substring(1);
      } else {
        sign = 0;
      }

      length = internalValue.length();

      if (!validateDigits()) {
        System.out.println("Not a valid integer BrobInt");
        System.exit(-1);
      }

      StringBuilder s = new StringBuilder(internalValue);
      reversed = s.reverse().toString();

      int i = 0;
      int start  = length - CHARS_THAT_FIT;
      int size   = (int)(Math.ceil( length / CHARS_THAT_FIT ) + 1);
      intVersion = new int[ size ];
      StringBuffer sb = new StringBuffer(internalValue).reverse();
      // System.out.println( "Current State: length = " + length + "\n" +
      //                     "       buffered value = " + sb.toString() );
      while (length >= 8) {
         intVersion[i] = Integer.parseInt( internalValue.substring( start, length ) );
         start -= CHARS_THAT_FIT;
         length -= CHARS_THAT_FIT;
         // System.out.print( " length: " + length + ", start: " + start );
         // System.out.println( "  -- converted values[" + i + "] is: " + intVersion[i] );
         i++;
      }
      if (length > 0) {
         intVersion[i] = Integer.parseInt( internalValue.substring( 0, length ) );
         // System.out.print( " length: " + length + ", start: " + start );
         // System.out.println( "  -- converted values[" + i + "] is: " + intVersion[i] );
      }
      // System.out.println( "Array contents: " + Arrays.toString( intVersion ) );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits() {
     String valid = "+-0123456789";
     for (int i = 0; i < (internalValue.length()); i++) {
       if (! valid.contains(Character.toString(internalValue.charAt(i)))) {
         return false;
       }
     }
       return true;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this BrobInt
   *  @return BrobInt that is the reverse of the value of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt reverser() {
      StringBuilder s = new StringBuilder(internalValue);
      reversed = s.reverse().toString();
      return new BrobInt(reversed);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobIntk passed as argument
   *  Note: static method
   *  @param  gint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt reverser( BrobInt gint ) {
     String internalStringValue = gint.toString();
     StringBuilder s = new StringBuilder(internalStringValue);
     String reversedValue = s.reverse().toString();
     return new BrobInt(reversedValue);
   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method that returns the sign of the BrobInt
    *  @param  gint         BrobInt to check the sign
    *  @return int "0" if it is positive, and int "1" if it is negative
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public int getSign() {
      return sign;
    }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method that returns the int array representation of the BrobInt
    *  @param  gint         BrobInt to check array
    *  @return int[] representation of the BrobInt
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public int[] getArray() {
      return intVersion;
    }

  // /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  //  *  Method to add the value of a BrobIntk passed as argument to this BrobInt using int array
  //  *  @param  gint         BrobInt to add to this
  //  *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
  //  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  //  public BrobInt addInt( BrobInt gint ) {
  //     throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
  //  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt add( BrobInt gint ) {
      carry = 0;
      int[] result = null;
      int[] intVersion2 = gint.getArray();
      if (sign == 0 && gint.getSign() == 0) {
        // System.out.println("They are both positive.");
        if (gint.toString().length() > internalValue.length()) {
          // System.out.println("pos2 is bigger");
          result = new int[intVersion2.length];
          // System.out.print("Pos 1: ");
          // toArray(intVersion);
          // System.out.print("Pos 2: ");
          // gint.toArray(intVersion2);
          for (int i = 0; i < intVersion.length; i++) {
            result[i] = intVersion[i] + intVersion2[i] + carry;
            String stringI = Integer.toString((result[i]));
            if (stringI.length() > 8) {
              carry = Character.getNumericValue((stringI.charAt(0)));
              result[i] = Integer.parseInt( new StringBuilder(stringI).deleteCharAt(0).toString() );
            } else {
              carry = 0;
            }
            index = i;
          }
          if (intVersion2.length > index) {
            for (int j = index+1; j < (intVersion2.length); j++) {
              result[j] = intVersion2[j];
            }
          }
          // toArray(result);
          String output = toStringIntArray(result);
          return new BrobInt(output);

        } else if (toString().length() > gint.toString().length()) {
          // System.out.println("pos1 is bigger");
          result = new int[intVersion.length];
          // toArray(intVersion);
          // gint.toArray(intVersion2);

          for (int i = 0; i < intVersion2.length; i++) {
            result[i] = intVersion[i] + intVersion2[i] + carry;
            String stringI = Integer.toString((result[i]));
            if (stringI.length() > 8) {
              carry = Character.getNumericValue((stringI.charAt(0)));
              result[i] = Integer.parseInt( new StringBuilder(stringI).deleteCharAt(0).toString() );
            } else {
              carry = 0;
            }
          }
          // toArray(result);
          // intVersion = result;
          String output = toStringIntArray(result);
          // System.out.println(output);
          return new BrobInt(output);
        } else {
          // System.out.println("same size");
          result = new int[intVersion.length];
          // toArray(intVersion);
          // gint.toArray(intVersion2);

          for (int i = 0; i < intVersion.length; i++) {
            result[i] = intVersion[i] + intVersion2[i] + carry;
            String stringI = Integer.toString((result[i]));
            if (stringI.length() > 8) {
              carry = Character.getNumericValue((stringI.charAt(0)));
              result[i] = Integer.parseInt( new StringBuilder(stringI).deleteCharAt(0).toString() );
            } else {
              carry = 0;
            }
            // System.out.println(stringI + " " + carry);
          }
          // toArray(result);
          // intVersion = result;
          String output = toStringIntArray(result);
          // System.out.println(output);
          return new BrobInt(output);
        }

      } else if (sign == 1 && gint.getSign() == 1) {
        // System.out.println("They are both negative.");
        if (gint.toString().length() > toString().length()) {
          // System.out.println("neg2 magnitude is bigger");
          result = new int[intVersion2.length];
          // toArray(intVersion);
          // gint.toArray(intVersion2);
          for (int i = 0; i < intVersion.length; i++) {
            result[i] = intVersion[i] + intVersion2[i] + carry;
            String stringI = Integer.toString((result[i]));
            if (stringI.length() > 8) {
              carry = Character.getNumericValue((stringI.charAt(0)));
              result[i] = Integer.parseInt( new StringBuilder(stringI).deleteCharAt(0).toString() );
            } else {
              carry = 0;
            }
            // System.out.println(stringI + " " + carry);
          }
          // toArray(result);
          // intVersion = result;
          String output = toStringIntArray(result);
          // output = new StringBuilder(output).insert(0,"-").toString();
          // System.out.println(output);
          // System.out.println(gint.toString());
          return new BrobInt(output);

        } else if (toString().length() > gint.toString().length()) {
          // System.out.println("neg1 magnitude is bigger");
          result = new int[intVersion.length];
          // toArray(intVersion);
          // gint.toArray(intVersion2);

          for (int i = 0; i < intVersion2.length; i++) {
            result[i] = intVersion[i] + intVersion2[i] + carry;
            String stringI = Integer.toString((result[i]));
            if (stringI.length() > 8) {
              carry = Character.getNumericValue((stringI.charAt(0)));
              result[i] = Integer.parseInt( new StringBuilder(stringI).deleteCharAt(0).toString() );
            } else {
              carry = 0;
            }
            // System.out.println(stringI + " " + carry);
          }
          // toArray(result);
          // intVersion = result;
          String output = toStringIntArray(result);
          // output = new StringBuilder(output).insert(0,"-").toString();
          // System.out.println(output);
          return new BrobInt(output);
        } else {
          // System.out.println("same size");
          result = new int[intVersion.length];
          // toArray(intVersion);
          // gint.toArray(intVersion2);

          for (int i = 0; i < intVersion.length; i++) {
            result[i] = intVersion[i] + intVersion2[i] + carry;
            String stringI = Integer.toString((result[i]));
            if (stringI.length() > 8) {
              carry = Character.getNumericValue((stringI.charAt(0)));
              result[i] = Integer.parseInt( new StringBuilder(stringI).deleteCharAt(0).toString() );
            } else {
              carry = 0;
            }
            // System.out.println(stringI + " " + carry);
          }
          // toArray(result);
          // intVersion = result;
          String output = toStringIntArray(result);
          // output = new StringBuilder(output).insert(0,"-").toString();
          // System.out.println(output);
          return new BrobInt(output);
        }
      } else if (sign == 0 && gint.getSign() == 1) {
        // System.out.println("adding pos1 to neg2");
        return subtract(gint);
      } else if (sign == 1 && gint.getSign() == 0) {
        // System.out.println("adding neg1 to pos2");
        return gint.subtract(new BrobInt(toString()));
      }
      return new BrobInt("123");
   }

  // /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  //  *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using ints
  //  *  @param  gint         BrobInt to subtract from this
  //  *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
  //  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  //  public BrobInt subtractInt( BrobInt gint ) {
  //     throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
  //  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtract( BrobInt gint ) {
      int [] result = null;
      if (sign == 0 && gint.getSign() == 0) {
        // System.out.println("They are both positive.");
        if (toString().length() > gint.toString().length()) {
          // System.out.println("pos 1 is greater in magnitude than pos 2");
          biggerInt = intVersion;
          smallerInt = gint.getArray();
          result = new int[biggerInt.length];
          for (int i = 0; i < smallerInt.length; i++) {
            if (biggerInt[i] < smallerInt[i]) {
              int temp = biggerInt[i];
              temp = Integer.parseInt("1"+Integer.toString(biggerInt[i]));
              result[i] = temp - smallerInt[i];
              biggerInt[i+1] -= 1;
            } else {
              result[i] = biggerInt[i] - smallerInt[i];
            }
          }
          String output = toStringIntArray(result);
          // System.out.println(output);
          return new BrobInt(output);
        } else if (gint.toString().length() > toString().length()) {
          // System.out.println("pos 2 is greater in magnitude than pos 1");
          // sign = 1;
          biggerInt = gint.getArray();
          smallerInt = intVersion;
          result = new int[biggerInt.length];
          for (int i = 0; i < smallerInt.length; i++) {
            if (biggerInt[i] < smallerInt[i]) {
              int temp = biggerInt[i];
              temp = Integer.parseInt("1"+Integer.toString(biggerInt[i]));
              result[i] = temp - smallerInt[i];
              biggerInt[i+1] -= 1;
            } else {
              result[i] = biggerInt[i] - smallerInt[i];
            }
          }
          String output = toStringIntArray(result);
          // System.out.println(output);
          return new BrobInt("-" + output);
        } else {
          // System.out.println("pos 1 has same magnitude as pos 2");
          for (int i = 0; i < intVersion.length; i++) {
            if (intVersion[i] > gint.getArray()[i]) {
              signTemp = 0;
              biggerInt = intVersion;
              smallerInt = gint.getArray();
              break;
            } else if (gint.getArray()[i] > intVersion[i]) {
              signTemp = 1;
              biggerInt = gint.getArray();
              smallerInt = intVersion;
              break;
            } else if ((i == intVersion.length - 1) && (intVersion[i] == gint.getArray()[i])) {
              return new BrobInt("0");
            }
          }
          result = new int[biggerInt.length];
          for (int i = 0; i < smallerInt.length; i++) {
            if (biggerInt[i] < smallerInt[i]) {
              int temp = biggerInt[i];
              temp = Integer.parseInt("1"+Integer.toString(biggerInt[i]));
              result[i] = temp - smallerInt[i];
              biggerInt[i+1] -= 1;
            } else {
              result[i] = biggerInt[i] - smallerInt[i];
            }
          }
          String output = toStringIntArray(result);
          // System.out.println(output);
          if (signTemp == 1) {
            return new BrobInt("-" + output);
          }
          return new BrobInt(output);
        }

      } else if (sign == 1 && gint.getSign() == 1) {
        // System.out.println("They are both negative.");
        if (toString().length() > gint.toString().length()) {
          // System.out.println("neg 1 is greater in magnitude than neg 2");
          // sign = 1;
          biggerInt = intVersion;
          smallerInt = gint.getArray();
          result = new int[biggerInt.length];
          for (int i = 0; i < smallerInt.length; i++) {
            if (biggerInt[i] < smallerInt[i]) {
              int temp = biggerInt[i];
              temp = Integer.parseInt("1"+Integer.toString(biggerInt[i]));
              result[i] = temp - smallerInt[i];
              biggerInt[i+1] -= 1;
            } else {
              result[i] = biggerInt[i] - smallerInt[i];
            }
          }
          String output = toStringIntArray(result);
          // System.out.println(output);
          return new BrobInt(output);
        } else if (gint.toString().length() > toString().length()) {
          // System.out.println("neg 2 is greater in magnitude than neg 1");
          biggerInt = gint.getArray();
          smallerInt = intVersion;
          result = new int[biggerInt.length];
          for (int i = 0; i < smallerInt.length; i++) {
            if (biggerInt[i] < smallerInt[i]) {
              int temp = biggerInt[i];
              temp = Integer.parseInt("1"+Integer.toString(biggerInt[i]));
              result[i] = temp - smallerInt[i];
              biggerInt[i+1] -= 1;
            } else {
              result[i] = biggerInt[i] - smallerInt[i];
            }
          }
          String output = toStringIntArray(result);
          // System.out.println(output);
          return new BrobInt(new StringBuilder(output).deleteCharAt(0).toString());
        } else {
          // System.out.println("neg 1 has same magnitude as neg 2");
          for (int i = 0; i < intVersion.length; i++) {
            if (intVersion[i] > gint.getArray()[i]) {
              biggerInt = intVersion;
              smallerInt = gint.getArray();
              signTemp = 1;
              break;
            } else if (gint.getArray()[i] > intVersion[i]) {
              biggerInt = gint.getArray();
              smallerInt = intVersion;
              signTemp = 0;
              break;
            } else if ((i == intVersion.length - 1) && (intVersion[i] == gint.getArray()[i])) {
              return new BrobInt("0");
            }
          }
          result = new int[biggerInt.length];
          for (int i = 0; i < smallerInt.length; i++) {
            if (biggerInt[i] < smallerInt[i]) {
              int temp = biggerInt[i];
              temp = Integer.parseInt("1"+Integer.toString(biggerInt[i]));
              result[i] = temp - smallerInt[i];
              biggerInt[i+1] -= 1;
            } else {
              result[i] = biggerInt[i] - smallerInt[i];
            }
          }
          String output = toStringIntArray(result);
          // System.out.println(output);
          if (signTemp == 0) {
            return new BrobInt(new StringBuilder(output).deleteCharAt(0).toString());
          }
          return new BrobInt(output);
        }
      } else if (sign == 1 && gint.getSign() == 0) {
        // System.out.println("neg 1 is negative and pos 2 is positive");
        // System.out.println(toString() + " ||||||||| " + gint.toString());
        // System.out.println(toString() + " ||||||||| " + new BrobInt("-" + gint.toString()).toString());
        if (toString().length() > gint.toString().length()) {
          // System.out.println("neg 1 is greater in magnitude than pos 2");
          BrobInt temp = new BrobInt(new StringBuilder(toString()).deleteCharAt(0).toString());
          BrobInt finalResult = new BrobInt("-" + temp.add(gint).toString());
          return finalResult;
        } else if (gint.toString().length() > toString().length()) {
          // System.out.println("pos 2 is greater in magnitude than neg 1");
          BrobInt temp = new BrobInt(new StringBuilder(toString()).deleteCharAt(0).toString());
          BrobInt finalResult = new BrobInt("-" + temp.add(gint).toString());
          return finalResult;
        } else {
          // System.out.println("neg 1 has same magnitude as pos 2");
          // for (int i = 0; i < intVersion.length; i++) {
          //   if (intVersion[i] > gint.getArray()[i]) {
          //     biggerInt = intVersion;
          //     smallerInt = gint.getArray();
          //     break;
          //   } else if (gint.getArray()[i] > intVersion[i]) {
          //     biggerInt = gint.getArray();
          //     smallerInt = intVersion;
          //     break;
          //   } else if ((i == intVersion.length - 1) && (intVersion[i] == gint.getArray()[i])) {
          //     return new BrobInt("0");
          //   }
          // }
          BrobInt temp = new BrobInt(new StringBuilder(toString()).deleteCharAt(0).toString());
          BrobInt finalResult = new BrobInt("-" + temp.add(gint).toString());
          return finalResult;
        }
      } else if (sign == 0 && gint.getSign() == 1) {
        // System.out.println("pos 1 is positive and neg 2 is negative");
        if (toString().length() > gint.toString().length()) {
          // System.out.println("pos 1 is greater in magnitude than neg 2");
          BrobInt temp = new BrobInt(new StringBuilder(gint.toString()).deleteCharAt(0).toString());
          return add(temp);
        } else if (gint.toString().length() > toString().length()) {
          // System.out.println("neg 2 is greater in magnitude than pos 1");
          BrobInt temp = new BrobInt(new StringBuilder(gint.toString()).deleteCharAt(0).toString());
          return add(temp);
        } else {
          // System.out.println("pos 1 has same magnitude as neg 2");
          BrobInt temp = new BrobInt(new StringBuilder(gint.toString()).deleteCharAt(0).toString());
          return add(temp);
        }
      }
      return new BrobInt("123");
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  gint         BrobInt to multiply by this
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt gint ) {
     if (subtract(new BrobInt("0")).toString().equals("0") || gint.subtract(new BrobInt("0")).toString().equals("0")) {
      return new BrobInt("0");
    } else if (subtract(new BrobInt("1")).toString().equals("0")) {
      return gint;
    } else if (gint.subtract(new BrobInt("1")).toString().equals("0")) {
      return new BrobInt(toString());
    } else {
      BrobInt isLast = new BrobInt(gint.toString());
      BrobInt timesTwo = new BrobInt(toString());
      BrobInt finalSum = new BrobInt("0");
      Halver halver = new Halver();
      int[] lastOneIC = isLast.getArray();
      int lengthOfArrayIC = lastOneIC.length;
      if (lastOneIC[lengthOfArrayIC-1]%2 != 0) {
        finalSum = finalSum.add(timesTwo);
      }
      while (!isLast.equals(new BrobInt("1"))) {
        timesTwo = timesTwo.add(timesTwo);
        isLast = new BrobInt(halver.halve(isLast.toString()));
        // System.out.println(timesTwo.toString() + "  " + isLast.toString());
        int[] lastOne = isLast.getArray();
        int lengthOfArray = lastOne.length;
        if (lastOne[lengthOfArray-1]%2 != 0) {
          finalSum = finalSum.add(timesTwo);
          // System.out.println("Adding " + timesTwo.toString() + " to get " + finalSum.toString());
        }
      }
      if ((sign == 1 && gint.getSign() == 0) || (sign == 0 && gint.getSign() == 1)) {
        return new BrobInt("-" + finalSum.toString());
      } else {
        return finalSum;
      }
    }
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  gint         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  gint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  gint  BrobInt to add to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method performs a lexicographical comparison using the java String "compareTo()" method
   *        THAT was easy.....
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo( BrobInt gint ) {
      return (internalValue.compareTo( gint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  gint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
   *        also using the java String "equals()" method -- THAT was easy, too..........
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt gint ) {
      return (internalValue.equals( gint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value         long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt gi = null;
      try {
         gi = new BrobInt( Long.toString(value) );
      }
      catch (NumberFormatException nfe) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return gi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
      String Output = "";
      String intVersionOutput = "";
      for (int i = intVersion.length-1; i >= 0; i--) {
        if (Integer.toString( intVersion[i] ).length() < 8 && i != intVersion.length-1) {
          String out = Integer.toString( intVersion[i] );
          for (int j = 0; j < 8 - out.length(); j++) {
            out = "0" + out;
          }
          intVersionOutput = intVersionOutput.concat( out );
        } else {
          intVersionOutput = intVersionOutput.concat( Integer.toString( intVersion[i] ) );
        }
      }
      intVersionOutput = new String( new StringBuffer( intVersionOutput ) );
      if (sign == 1) {
        intVersionOutput = "-" + intVersionOutput;
      }
      if (intVersionOutput.charAt(0) == '0' && intVersionOutput.length() > 1) {
        int k = 0;
        char nextChar = '0';
        while (nextChar == '0') {
          intVersionOutput = new StringBuilder(intVersionOutput).deleteCharAt(k).toString();
          k += 1;
          nextChar = intVersionOutput.charAt(k);
        }
      }
      // toArray(intVersion);
      return intVersionOutput;
   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to return a String representation of an int array for BrobInt
    *  @return String  which is the String representation of int array for BrobInt
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public String toStringIntArray( int[] intArray ) {
       String Output = "";
       String intVersionOutput = "";
       for (int i = intArray.length-1; i >= 0; i--) {
         if (Integer.toString( intArray[i] ).length() < 8 && i != intArray.length-1) {
           String out = Integer.toString( intArray[i] );
           for (int j = 0; j < 8 - out.length(); j++) {
             out = "0" + out;
           }
           intVersionOutput = intVersionOutput.concat( out );
         } else {
           intVersionOutput = intVersionOutput.concat( Integer.toString( intArray[i] ) );
         }
       }
       intVersionOutput = new String( new StringBuffer( intVersionOutput ) );
       if (sign == 1) {
         intVersionOutput = "-" + intVersionOutput;
       }
       // toArray(intArray);
       return intVersionOutput;
    }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this BrobInt as its ints
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void toArray( int[] d ) {
      System.out.println( Arrays.toString( d ) );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  note:  we don't really care about these
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );
      BrobInt pos1  = new BrobInt("-999999999999999");
      BrobInt pos2 = new BrobInt("-999999999999999");
      pos1.add(pos2);
      System.exit( 0 );
   }
}
