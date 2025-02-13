package utils;

import java.util.Random;

public class TwosComplimentPairs {

  private static final Random RANDOM = new Random();
  private int n1;
  private int n2;
  private int sign1;
  private int sign2;

  public class Pair {
    public int decimal;
    public String binary;

    Pair(int d, String b) {
      decimal = d;
      binary = b;
    }
  }

  public TwosComplimentPairs() {
    // 8 bit signed magnitude range -127 to +127
    // In order to avoid overflow, random number is bound to 63 (half of 127)
    n1 = new Random().nextInt(64);
    n2 = new Random().nextInt(64);
    sign1 = new Random().nextInt(2);
    sign2 = new Random().nextInt(2);
    if (sign1 > 0) {
      n1 *= -1;
    }
    if (sign2 > 0) {
      n2 *= -1;
    }
  }

  public Pair[] getPairs() {
    return new Pair[] { new Pair(n1, Int.toBinaryString(n1, 8)), new Pair(n2, Int.toBinaryString(n2, 8)) };
  }

  public String convertToBinary(int n) {
    if (n > 0xff) {
      throw new IllegalArgumentException("n should be 8-bits or less, but n is " + n);
    }
    return Int.toBinaryString(n, 8);
  }

  public static void main(String[] args) {
    var q = new TwosComplimentPairs();
    var pairs = q.getPairs();
    System.out.println(pairs[0].binary);
    System.out.println(pairs[0].decimal);
    System.out.println(pairs[1].binary);
    System.out.println(pairs[1].decimal);
  }
}
