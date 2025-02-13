package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class FrequencyToPeriod extends AbstractQuizQuestion {

  private static final Random RANDOM = new Random();

  private enum UnitPair {
    KILO("kHz", 10e3, "ms", 10e-3),
    MEGA("MHz", 10e6, "μs", 10e-6),
    GIGA("GHz", 10e9, "ns", 10e-9),
    TERA("THz", 10e12, "ps", 10e-12);

    public final String freqUnit;
    public final double freqPower;
    public final String periodUnit;
    public final double periodPower;

    UnitPair(String freqUnit, double freqToHz,
        String periodUnit, double periodToSec) {
      this.freqUnit = freqUnit;
      this.freqPower = freqToHz;
      this.periodUnit = periodUnit;
      this.periodPower = periodToSec;
    }
  }

  public FrequencyToPeriod() {
  }

  /**
   * Generates one quiz question of the form:
   * "X freqUnit -> ? periodUnit" (frequency to period)
   * or
   * "X periodUnit -> ? freqUnit" (period to frequency)
   */
  @Override
  public QuizQuestion generateQuestion() {
    // 1) Pick a random pair of (frequency unit, period unit)
    UnitPair[] pairs = UnitPair.values();
    UnitPair chosenInputPair = pairs[RANDOM.nextInt(pairs.length)];
    UnitPair chosenOutputPair = chosenInputPair;

    // 2) Decide direction: freq → period or period → freq
    boolean freqToPeriod = RANDOM.nextBoolean();

    // 3) Generate a random value in a "realistic" range
    // e.g. from 0.1 to 10.0 (you can adjust as you see fit)
    BigDecimal randomValue = new BigDecimal(0.1 + 9.9 * RANDOM.nextDouble()).setScale(2, RoundingMode.DOWN);
    var answer = new BigDecimal(1 / randomValue.doubleValue()).setScale(6, RoundingMode.DOWN).doubleValue();

    if (freqToPeriod) {
      // "Given X freqUnit, what is it in periodUnit?"

      String questionText = String.format("Convert %.3g %s to %s = ",
          randomValue, chosenInputPair.freqUnit,
          chosenOutputPair.periodUnit);

      return new QuizQuestion(questionText, String.valueOf(answer), chosenOutputPair.periodUnit);

    } else {
      // "Given X periodUnit, what is it in freqUnit?"

      String questionText = String.format("Convert %.3g %s to %s = ",
          randomValue, chosenInputPair.periodUnit,
          chosenOutputPair.freqUnit);

      return new QuizQuestion(questionText, String.valueOf(answer), chosenOutputPair.freqUnit);
    }
  }

  // Simple demo
  public static void main(String[] args) {
    // Generate 5 sample questions
    for (int i = 0; i < 5; i++) {
      QuizQuestion q = new FrequencyToPeriod().generateQuestion();
      System.out.println(q);
      System.out.println();
    }
  }
}
