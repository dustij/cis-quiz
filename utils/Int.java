package utils;

import java.util.Random;

public class Int {
  public static final Random RANDOM = new Random();

  public static String toBinaryString(int n) {
    return toBinaryString(n, 32);
  }

  public static String toBinaryString(int n, int k) {
    var sb = new StringBuilder();
    if (k > 32 || k < 0) {
      throw new IllegalArgumentException("Argument k must be less than 33 and greater than 0, but k is " + k);
    }
    int m = 0x80000000 >>> (32 - k);
    for (int i = 0; i < k; i++) {
      if ((n & m) == 0)
        sb.append("0");
      else
        sb.append("1");
      if (i % 4 == 3)
        sb.append(" ");
      m >>>= 1;
    }
    return sb.toString().trim();
  }
}
