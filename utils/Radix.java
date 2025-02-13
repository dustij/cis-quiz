package utils;


public class Radix {
  public static int getRandomRadix() {
    // Generate a random number between 2 and 16
    return (int) (Math.random() * 15) + 2; // (16 - 2 + 1 = 15)
  }

  public static String getMax(int radix, int digits) {
    // Create a string of the maximum value in the given radix
    StringBuilder maxValueBuilder = new StringBuilder();
    for (int i = 0; i < digits; i++) {
      // Append the maximum digit for the radix (radix - 1)
      maxValueBuilder.append(Character.forDigit(radix - 1, radix));
    }
    return maxValueBuilder.toString();
  }
}
