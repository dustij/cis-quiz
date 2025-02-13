package utils;

import java.util.Random;

/**
 * This is for 8-bits only
 */
public class SignedMagnitudePairs {

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

  public SignedMagnitudePairs() {
    // 8 bit signed magnitude range -127 to +127
    // In order to avoid overflow, random number is bound to 63 (half of 127)
    sN1 = n1 = new Random().nextInt(64);
    sN2 = n2 = new Random().nextInt(64);
    sign = new Random().nextInt(2) << 7;
    if (sign > 0) {
      sN1 |= sign;
      sN2 |= sign;
      n1 *= -1;
      n2 *= -1;
    }
  }

  public Pair[] getPairs() {
    return new Pair[] { new Pair(n1, Int.toBinaryString(sN1, 8)), new Pair(n2, Int.toBinaryString(sN2, 8)) };
  }

  public String convertToBinary(int n) {
    if (n > 0xff) {
      throw new IllegalArgumentException("n should be 8-bits or less, but n is " + n);
    }
    int s = 0;

    if ( n < 0) {
      n *= -1;
      s = 1 << 7;
    }
    return Int.toBinaryString(n |= s, 8);
  }

  public static void main(String[] args) {
    var q = new SignedMagnitudePairs();
    var pairs = q.getPairs();
    System.out.println(pairs[0].decimal);
    System.out.println(pairs[0].binary);
    System.out.println(pairs[1].decimal);
    System.out.println(pairs[1].binary);
    System.out.println();
    System.out.printf("%6d\n", pairs[0].decimal);
    System.out.print("+ ");
    System.out.printf("%4d\n",pairs[1].decimal);
    System.out.println("-------------");
    System.out.println(pairs[0].decimal + pairs[1].decimal);
    System.out.println(q.convertToBinary(pairs[0].decimal + pairs[1].decimal));
  }
}
