package utils;

public class PrintBinaryToConsole {
  public static void println(int n) {
    println(n, 32);
  }

  public static void println(int n, int e) {
    if (e > 32 || e < 0) {
      throw new IllegalArgumentException("Argument e must be less than 33 and greater than 0, but e is " + e);
    }
    int m = 0x80000000 >>> (32 - e);
    for (int i = 0; i < e; i++) {
      if ((n & m) == 0)
        System.out.print("0");
      else
        System.out.print("1");
      if (i % 4 == 3)
        System.out.print(" ");
      m >>>= 1;
    }
    System.out.println();
  }

  public static void main(String[] args) {
    println(1, 8);
  }
}
