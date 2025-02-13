package utils;

public abstract class AbstractQuizQuestion {
  public static class QuizQuestion {
    private final String questionText;
    private final String answerValue;
    private final String answerUnit;

    public QuizQuestion(String questionText, String answerValue, String answerUnit) {
      this.questionText = questionText;
      this.answerValue = answerValue;
      this.answerUnit = answerUnit;
    }

    public String getQuestionText() {
      return questionText;
    }

    public String getAnswerValue() {
      return answerValue;
    }

    public String getAnswerUnit() {
      return answerUnit;
    }

    @Override
    public String toString() {
      return String.format("Question: %s\nAnswer: %.6g %s",
          questionText, answerValue, answerUnit);
    }
  }

  public abstract QuizQuestion generateQuestion();
}
