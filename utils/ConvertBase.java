package utils;


public class ConvertBase {
  public static String convert(String value, int inputBase, int outputBase) {
    if (value.contains(".")) {
      var sides = value.split("\\.");
      var left = convert(sides[0], inputBase, outputBase);
      var right = convertFraction(sides[1], inputBase, outputBase);
      return left + "." + String.format("%-7s", right).substring(0, 6); // pad then truncate at 6 places
    }
    if (outputBase == 10) {
      return String.valueOf(Integer.parseInt(value, inputBase));
    }
    if (inputBase == 10) {
      return Integer.toString(Integer.parseInt(value), outputBase);
    }
    return convert(convert(value, inputBase, 10), 10, outputBase);
  }

  private static String convertFraction(String value, int inputBase, int outputBase) {
    if (outputBase == 10) {
      var result = "";
      var decimalValue = 0.0;
      for (int i = 0; i < value.length(); i++) {
        int digit = Character.digit(value.charAt(i), inputBase);
        decimalValue += digit * Math.pow(inputBase, (i + 1) * -1);
      }
      result = String.valueOf(decimalValue);
      return result.substring(result.indexOf(".") + 1);
    }
    if (inputBase == 10) {
      var result = "";
      var fractionalValue = Double.parseDouble("." + value);
      for (int i = 0; i < 6; i++) {
        var next = (fractionalValue * outputBase);
        var d = (int) next;
        fractionalValue = next % 1;
        result += Integer.toString(d, outputBase);
      }
      return result;
    }
    return convertFraction(convertFraction(value, inputBase, 10), 10, outputBase);
  }

}