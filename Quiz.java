import java.util.Map;
import java.util.Scanner;
import utils.*;

public class Quiz {
  private static int length = 2;
  private static Map<Integer, String> SUBSCRIPT_MAP = Map.ofEntries(
      Map.entry(2, "\u2082"),
      Map.entry(3, "\u2083"),
      Map.entry(4, "\u2084"),
      Map.entry(5, "\u2085"),
      Map.entry(6, "\u2086"),
      Map.entry(7, "\u2087"),
      Map.entry(8, "\u2088"),
      Map.entry(9, "\u2089"),
      Map.entry(10, "\u2081\u2080"),
      Map.entry(11, "\u2081\u2081"),
      Map.entry(12, "\u2081\u2082"),
      Map.entry(13, "\u2081\u2083"),
      Map.entry(14, "\u2081\u2084"),
      Map.entry(15, "\u2081\u2085"),
      Map.entry(16, "\u2081\u2086"));
  private static final QuizItem[] MEMORIZE_QUESTIONS = new QuizItem[] {
      new QuizItem("2^1 = ", "2"),
      new QuizItem("2^2 = ", "4"),
      new QuizItem("2^3 = ", "8"),
      new QuizItem("2^4 = ", "16"),
      new QuizItem("2^5 = ", "32"),
      new QuizItem("2^6 = ", "64"),
      new QuizItem("2^7 = ", "128"),
      new QuizItem("2^8 = ", "256"),
      new QuizItem("2^9 = ", "512"),
      new QuizItem("2^10 = ", "1024"),
      new QuizItem("Kilo (storage) = ", "2^10"),
      new QuizItem("Mega (storage) = ", "2^20"),
      new QuizItem("Giga (storage) = ", "2^30"),
      new QuizItem("Tera (storage) = ", "2^40"),
      new QuizItem("Milli (storage) = ", "2^-10"),
      new QuizItem("Micro (storage) = ", "2^-20"),
      new QuizItem("Nano (storage) = ", "2^-30"),
      new QuizItem("Pico (storage) = ", "2^-40"),
      new QuizItem("Kilo (speed) = ", "10^3"),
      new QuizItem("Mega (speed) = ", "10^6"),
      new QuizItem("Giga (speed) = ", "10^9"),
      new QuizItem("Tera (speed) = ", "10^12"),
      new QuizItem("Milli (speed) = ", "10^-3"),
      new QuizItem("Micro (speed) = ", "10^-6"),
      new QuizItem("Nano (speed) = ", "10^-9"),
      new QuizItem("Pico (speed) = ", "10^-12"),
      new QuizItem("Base 4 digit groups = ", "2"),
      new QuizItem("Base 8 digit groups = ", "3"),
      new QuizItem("Base 16 digit groups = ", "4")
  };

  public static void main(String[] args) {
    if (args.length > 0) {
      try {
        if (length < 1) {
          throw new NumberFormatException("Arg must be greater than 0.");
        }
        length = Integer.parseInt(args[0]);
      } catch (NumberFormatException e) {
        System.out.println("Argument must be a positive integer. Using default (5) as length.");
        System.out.println();
      }
    }
    var bases = new int[length];
    var numbersInBaseN = new String[length];
    var numbersInBase10 = new int[length];
    var fractionInBaseN = new String[length];
    var fractionInBase10 = new double[length];
    var binaryAlignedSourceBases = new int[length];
    var binaryAlignedTargetBases = new int[length];
    var numbersInBinaryAlignedSource = new String[length];

    var quizBaseNToBase10 = new QuizItem[length];
    var quizBase10ToBaseN = new QuizItem[length];
    var quizFractionalBaseNToBase10 = new QuizItem[length];
    var quizFractionalBase10ToBaseN = new QuizItem[length];
    var quizBinaryAligned = new QuizItem[length];
    var quizMemorize = new QuizItem[length];
    var quizFreqPeriodConvert = new QuizItem[length];
    var quizAddSignedMagnitudeBinary = new QuizItem[length];
    var quizAddTwosCompBinary = new QuizItem[length];
    var quizSignedBinaryToDec = new QuizItem[length];
    var quizOnesCompBinaryToDec = new QuizItem[length];
    var quizTwosCompBinaryToDec = new QuizItem[length];
    var quizBinaryMultiply = new QuizItem[length];
    var quizIeee754Into = new QuizItem[length];
    var quizIeee754From = new QuizItem[length];

    // TODO
    var quizToBcd = new QuizItem[length];
    var quizFromBcd = new QuizItem[length];
    var quizAddMod2 = new QuizItem[length];
    var quizSubMod2 = new QuizItem[length];
    var quizMulMod2 = new QuizItem[length];
    var quizDivMod2 = new QuizItem[length];
    var quizFindHammingCode = new QuizItem[length];
    var quizFindOneBitError = new QuizItem[length];

    for (int i = 0; i < length; i++) {
      bases[i] = Radix.getRandomRadix();

      numbersInBaseN[i] = RandomNumbers.generate(1, Radix.getMax(bases[i], 4), bases[i])[0];
      numbersInBase10[i] = Integer.parseInt(RandomNumbers.generate(1, Radix.getMax(10, 4), 10)[0]);

      var nums = RandomNumbers.generate(2, Radix.getMax(bases[i], 4), bases[i]);
      fractionInBaseN[i] = nums[0] + "." + nums[1];
      nums = RandomNumbers.generate(2, Radix.getMax(10, 4), 10);
      fractionInBase10[i] = Double.parseDouble(nums[0] + "." + nums[1]);

      int[] options = { 2, 4, 8, 16 };
      binaryAlignedSourceBases[i] = options[(int) (Math.random() * 4)];
      binaryAlignedTargetBases[i] = options[(int) (Math.random() * 4)];
      numbersInBinaryAlignedSource[i] = RandomNumbers.generate(
          1, Radix.getMax(binaryAlignedSourceBases[i], 6),
          binaryAlignedSourceBases[i])[0];

      quizBase10ToBaseN[i] = new QuizItem();
      quizBase10ToBaseN[i].setQuestion(
          String.format("Convert %4d%-2s to ?%-2s = ", numbersInBase10[i], SUBSCRIPT_MAP.get(10),
              SUBSCRIPT_MAP.get(bases[i])));
      quizBase10ToBaseN[i].setAnswer(
          ConvertBase.convert(String.valueOf(numbersInBase10[i]), 10, bases[i]));

      quizBaseNToBase10[i] = new QuizItem();
      quizBaseNToBase10[i].setQuestion(
          String.format("Convert %4s%-2s to ?%-2s = ", numbersInBaseN[i], SUBSCRIPT_MAP.get(bases[i]),
              SUBSCRIPT_MAP.get(10)));
      quizBaseNToBase10[i].setAnswer(
          ConvertBase.convert(String.valueOf(numbersInBaseN[i]), bases[i], 10));

      quizFractionalBaseNToBase10[i] = new QuizItem();
      quizFractionalBaseNToBase10[i].setQuestion(
          String.format("Convert %9s%-2s to ?%-2s = ", fractionInBaseN[i], SUBSCRIPT_MAP.get(bases[i]),
              SUBSCRIPT_MAP.get(10)));
      quizFractionalBaseNToBase10[i].setAnswer(
          ConvertBase.convert(String.valueOf(fractionInBaseN[i]), bases[i], 10));

      quizFractionalBase10ToBaseN[i] = new QuizItem();
      quizFractionalBase10ToBaseN[i].setQuestion(
          String.format("Convert %4.4f%-2s to ?%-2s = ", fractionInBase10[i], SUBSCRIPT_MAP.get(10),
              SUBSCRIPT_MAP.get(bases[i])));
      quizFractionalBase10ToBaseN[i].setAnswer(
          ConvertBase.convert(String.valueOf(fractionInBase10[i]), 10, bases[i]));

      quizBinaryAligned[i] = new QuizItem();
      quizBinaryAligned[i].setQuestion(
          String.format("Convert %4s%-2s to ?%-2s = ", numbersInBinaryAlignedSource[i],
              SUBSCRIPT_MAP.get(binaryAlignedSourceBases[i]), SUBSCRIPT_MAP.get(binaryAlignedTargetBases[i])));
      quizBinaryAligned[i].setAnswer(
          ConvertBase.convert(String.valueOf(numbersInBinaryAlignedSource[i]), binaryAlignedSourceBases[i],
              binaryAlignedTargetBases[i]));

      quizMemorize[i] = MEMORIZE_QUESTIONS[Integer
          .parseInt(RandomNumbers.generate(1, MEMORIZE_QUESTIONS.length - 1 + "", 10)[0])];

      quizFreqPeriodConvert[i] = new QuizItem();
      var q = new FrequencyToPeriod().generateQuestion();
      quizFreqPeriodConvert[i].setQuestion(q.getQuestionText());
      quizFreqPeriodConvert[i].setAnswer(q.getAnswerValue());

      quizAddSignedMagnitudeBinary[i] = new QuizItem();
      var sm = new SignedMagnitudePairs();
      var smPairs = sm.getPairs();
      quizAddSignedMagnitudeBinary[i]
          .setQuestion("Add signed magnitude: " + smPairs[0].binary + " + " + smPairs[1].binary + " = ");
      quizAddSignedMagnitudeBinary[i].setAnswer(sm.convertToBinary(smPairs[0].decimal + smPairs[1].decimal));

      quizAddTwosCompBinary[i] = new QuizItem();
      var tc = new TwosComplimentPairs();
      var tcPairs = tc.getPairs();
      quizAddTwosCompBinary[i]
          .setQuestion("Add twos compliment: " + tcPairs[0].binary + " + " + tcPairs[1].binary + " = ");
      quizAddTwosCompBinary[i].setAnswer(tc.convertToBinary(tcPairs[0].decimal + tcPairs[1].decimal));

      quizSignedBinaryToDec[i] = new QuizItem();
      sm = new SignedMagnitudePairs();
      smPairs = sm.getPairs();
      quizSignedBinaryToDec[i]
          .setQuestion("Convert signed magnitude to decimal: " + smPairs[0].binary + " = ");
      quizSignedBinaryToDec[i].setAnswer(String.valueOf(smPairs[0].decimal));

      quizOnesCompBinaryToDec[i] = new QuizItem();
      var oc = new OnesComplimentPairs();
      var ocPairs = oc.getPairs();
      quizOnesCompBinaryToDec[i]
          .setQuestion("Convert one's compliment to decimal: " + ocPairs[0].binary + " = ");
      quizOnesCompBinaryToDec[i].setAnswer(String.valueOf(ocPairs[0].decimal));

      quizTwosCompBinaryToDec[i] = new QuizItem();
      tc = new TwosComplimentPairs();
      tcPairs = tc.getPairs();
      quizTwosCompBinaryToDec[i]
          .setQuestion("Convert twos's compliment to decimal: " + tcPairs[0].binary + " = ");
      quizTwosCompBinaryToDec[i].setAnswer(String.valueOf(tcPairs[0].decimal));

      quizBinaryMultiply[i] = new QuizItem();
      var b1 = Int.RANDOM.nextInt(15);
      var b2 = Int.RANDOM.nextInt(15);
      quizBinaryMultiply[i].setQuestion("Multiply the two (answer in 8-bit): " + Int.toBinaryString(b1, 4) + " x "
          + Int.toBinaryString(b2, 4) + " = ");
      quizBinaryMultiply[i].setAnswer(Int.toBinaryString(b1 * b2, 8));

      quizIeee754Into[i] = new QuizItem();
      var ieee = new IEEE754(true);
      quizIeee754Into[i].setQuestion(ieee.getQuestion());
      quizIeee754Into[i].setAnswer(ieee.getAnswer());

      quizIeee754From[i] = new QuizItem();
      ieee = new IEEE754(false);
      quizIeee754From[i].setQuestion(ieee.getQuestion());
      quizIeee754From[i].setAnswer(ieee.getAnswer());
    }

    var correct = 0;
    var wrong = 0;

    // ==== Add quiz questions here ==== //
    var quiz = new QuizItem[][] {
        quizMemorize,
        quizFreqPeriodConvert,
        quizBaseNToBase10,
        quizBase10ToBaseN,
        quizFractionalBaseNToBase10,
        quizFractionalBase10ToBaseN,
        quizBinaryAligned,
        quizAddSignedMagnitudeBinary,
        quizAddTwosCompBinary,
        quizSignedBinaryToDec,
        quizOnesCompBinaryToDec,
        quizTwosCompBinaryToDec,
        quizBinaryMultiply,
        quizIeee754Into,
        quizIeee754From
    };
    // ================================ //

    var scanner = new Scanner(System.in);

    for (QuizItem[] group : quiz) {
      for (var item : group) {
        System.out.print(item.getQuestion());
        var guess = scanner.nextLine();
        if (guess.equals(":q")) {
          System.exit(1);
        }
        if (item.getAnswer().equals(guess)) {
          printYes();
          correct++;
        } else {
          printNo(item.getAnswer());
          wrong++;
        }
      }
    }

    scanner.close();

    System.out.println();
    System.out.println("You got " + correct + " questions correct.");
    System.out.println("You got " + wrong + " questions wrong.");
    System.out.printf("Score: %.2f%%%n", (double) correct / (correct + wrong) * 100);
    System.out.println("================================");
  }

  private static void printYes() {
    System.out.println("  \u2713 Yes!\n");
  }

  private static void printNo(String answer) {
    System.out.println("  \u2717 Sorry, the correct answer was: " + answer + "\n");
  }
}
