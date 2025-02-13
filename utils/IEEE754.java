package utils;

import java.util.Random;

public class IEEE754 {
  private static final Random RANDOM = new Random();
  private int ieee754;

  private String strBinaryWithRadix;

  private boolean isBinaryToIeee754;

  public IEEE754() {
    this(RANDOM.nextBoolean());
  }

  public IEEE754(boolean isBinaryToIeee754) {
    int sign = RANDOM.nextInt(2);
    int whole = RANDOM.nextInt(0xF);
    int fraction = RANDOM.nextInt(16);
    strBinaryWithRadix = new StringBuilder()
        .append(sign == 1 ? "-" : "")
        .append(Integer.toBinaryString(whole))
        .append(".")
        .append(Integer.toBinaryString(fraction))
        .toString();
    ieee754 = binaryStringToIEEE754(strBinaryWithRadix);
    this.isBinaryToIeee754 = isBinaryToIeee754;
  }

  public void setIeee754(int ieee754) {
    this.ieee754 = ieee754;
  }

  public static String ieee754ToBinaryString(int ieee754) {
    // Extract sign, exponent, and mantissa
    int sign = (ieee754 >>> 31) & 1;
    int exponent = (ieee754 >>> 23) & 0xFF;
    int mantissa = ieee754 & 0x7FFFFF;

    // Compute actual exponent value (bias = 127)
    int actualExponent = exponent - 127;

    // Reconstruct floating-point number
    double value = 1.0; // Implicit leading 1 in normalized numbers
    for (int i = 0; i < 23; i++) {
      if ((mantissa & (1 << (22 - i))) != 0) {
        value += Math.pow(2, -(i + 1));
      }
    }

    // Apply exponent
    value *= Math.pow(2, actualExponent);

    // Apply sign
    if (sign == 1) {
      value = -value;
    }

    // Convert to binary string with a radix point
    return floatToBinaryString(value);
  }

  public static String floatToBinaryString(double value) {
    if (value == 0.0)
      return "0.0000"; // Ensure formatting for zero

    StringBuilder result = new StringBuilder();
    if (value < 0) {
      result.append("-");
      value = -value;
    }

    int wholePart = (int) value;
    double fractionPart = value - wholePart;

    result.append(Integer.toBinaryString(wholePart)).append(".");

    // Convert fraction part to binary with exactly 4 places
    for (int i = 0; i < 4; i++) {
      fractionPart *= 2;
      if (fractionPart >= 1) {
        result.append("1");
        fractionPart -= 1;
      } else {
        result.append("0");
      }
    }

    return result.toString();
  }

  public void setStrBinaryWithRadix(String strBinaryWithRadix) {
    this.strBinaryWithRadix = strBinaryWithRadix;
  }

  public static int binaryStringToIEEE754(String binary) {
    // Convert binary string to float
    float value = binaryStringToFloat(binary);

    // Convert float to IEEE 754 representation
    return Float.floatToIntBits(value);
  }

  private static float binaryStringToFloat(String binary) {
    // Split into integer and fractional parts
    String[] parts = binary.split("\\.");
    int integerPart = Integer.parseInt(parts[0], 2);
    float fractionPart = 0f;

    if (parts.length > 1) {
      String fraction = parts[1];
      for (int i = 0; i < fraction.length(); i++) {
        if (fraction.charAt(i) == '1') {
          fractionPart += Math.pow(2, -(i + 1));
        }
      }
    }

    return integerPart + fractionPart;
  }

  public static Random getRandom() {
    return RANDOM;
  }

  public int getIeee75() {
    return ieee754;
  }

  public String getStrBinaryWithRadix() {
    return strBinaryWithRadix;
  }

  public String getQuestion() {
    if (isBinaryToIeee754) {
      return String.format("Convert INTO 32-bit IEEE 754 (answer in hex): %9s", getStrBinaryWithRadix()) + " = ";
    }
    return String.format("Convert FROM 32-bit IEEE 754 (answer in binary): %X", getIeee75()) + " = ";
  }

  public String getAnswer() {
    if (isBinaryToIeee754) {
      return String.format("%X", getIeee75());
    }
    return ieee754ToBinaryString(getIeee75()); // Convert IEEE 754 back to binary correctly
  }

  public static void main(String[] args) {
    var ie = new IEEE754();
    ie.setStrBinaryWithRadix("-1110.111");
    ie.setIeee754(IEEE754.binaryStringToIEEE754(ie.getStrBinaryWithRadix()));
    // System.out.println(ie.getBit8());
    System.out.println(ie.getStrBinaryWithRadix());
    System.out.printf("%X\n", ie.getIeee75());
    System.out.println(ie.getQuestion());
    System.out.println(ie.getAnswer());
  }

}