package utils;


import java.math.BigInteger;

public class RandomNumbers {
  public static String[] generate(int size, String maxValue, int base) {
    String[] result = new String[size];
    BigInteger max = new BigInteger(maxValue, base); // Convert maxValue to BigInteger

    for (int i = 0; i < size; i++) {
      result[i] = build(max, base);
    }
    return result;
  }

  private static String build(BigInteger max, int base) {
    BigInteger randomValue;
    do {
      // Generate a random number within the valid range
      randomValue = new BigInteger(max.bitLength(), new java.util.Random());
    } while (randomValue.compareTo(BigInteger.ZERO) < 0 || randomValue.compareTo(max) > 0); // between 0 and max

    // Convert the random number to the desired base and return as a string
    return randomValue.toString(base);
  }
}
