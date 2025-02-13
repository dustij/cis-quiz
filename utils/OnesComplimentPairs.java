package utils;

import java.util.Random;

public class OnesComplimentPairs {

  private static final Random RANDOM = new Random();
  private int n1;
  private int n2;
  private int sign;
  private int sN1;
  private int sN2;

  public class Pair {
    public int decimal;
    public String binary;

    Pair(int d, String b) {
      decimal = d;
      binary = b;
    }
  }

  public OnesComplimentPairs() {
    // 8 bit signed magnitude range -127 to +127
    // In order to avoid overflow, random number is bound to 63 (half of 127)
    sN1 = n1 = new Random().nextInt(64);
    sN2 = n2 = new Random().nextInt(64);
    sign = new Random().nextInt(2);
    if (sign > 0) {
      n1 *= -1;
      n2 *= -1;
      sN1 *= -1;
      sN1 -= 1;
      sN2 *= -1;
      sN2 -= 1;
    }
  }

  public Pair[] getPairs() {
    return new Pair[] { new Pair(n1, Int.toBinaryString(sN1, 8)), new Pair(n2, Int.toBinaryString(sN2, 8)) };
  }

  public String convertToBinary(int n) {
    if (n > 0xff) {
      throw new IllegalArgumentException("n should be 8-bits or less, but n is " + n);
    }
    if ( n < 0) {
      n -= 1;
    }
    return Int.toBinaryString(n, 8);
  }

  public static void main(String[] args) {
    var q = new OnesComplimentPairs();
    var pairs = q.getPairs();
    System.out.println(pairs[0].binary);
    System.out.println(pairs[0].decimal);
    System.out.println(pairs[1].binary);
    System.out.println(pairs[1].decimal);
    System.out.println(q.convertToBinary(-5));
  }
}
