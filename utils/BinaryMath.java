package utils;

import java.util.Random;

public class BinaryMath {

  private static final Random RANDOM = new Random();

  public static enum Option {
    ADD_UINT8,
    ADD_SM_INT8,
    ADD_2CMP_INT8,
    SUB_2CMP_INT8,
    MULT_INT8
  }

  /**
   * A small data structure to hold the generated question and its answer.
   */
  public static class QuizQuestion {
    private String questionText;
    private String answerText;

    public QuizQuestion() {
    }

    public QuizQuestion(String questionText, String answerText) {
      this.questionText = questionText;
      this.answerText = answerText;
    }

    public String getQuestionText() {
      return questionText;
    }

    public String getAnswerText() {
      return answerText;
    }

    @Override
    public String toString() {
      return String.format("Question: %s\nAnswer: %d",
          questionText, answerText);
    }

    public void setQuestionText(String questionText) {
      this.questionText = questionText;
    }

    public void setAnswerText(String answerText) {
      this.answerText = answerText;
    }
  }

  public static QuizQuestion generateQuestion(Option questionType) {
    var quizQuestion = new QuizQuestion();
    var randomValueA = new Random().nextInt(256);
    var randomValueB = new Random().nextInt(256);

    switch (questionType) {
      case ADD_UINT8:
        quizQuestion.setQuestionText(
            String.format(
                "%8s+%8s",
                Integer.toBinaryString(randomValueA),
                Integer.toBinaryString(randomValueB)).replace(' ', '0'));

        quizQuestion.setAnswerText(
            String.format(
                "%8s",
                Integer.toBinaryString(randomValueA + randomValueB)).replace(' ', '0'));

        return quizQuestion;

      case ADD_SM_INT8:

        break;

      case ADD_2CMP_INT8:

        break;

      case SUB_2CMP_INT8:

        break;

      case MULT_INT8:

        break;

      default:
        break;
    }
    return new QuizQuestion();
  }

  public static void main(String[] args) {
    var q = generateQuestion(Option.ADD_UINT8);
    System.out.println(q.getQuestionText());
    System.out.println(q.getAnswerText());
  }
}
