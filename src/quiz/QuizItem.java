package src.quiz;

public class QuizItem
{
  private String question;
  private String answer;

  public QuizItem()
  {}

  public QuizItem(String q, String a)
  {
    question = q;
    answer = a;
  }

  public String getQuestion()
  {
    return question;
  }

  public void setQuestion(String question)
  {
    this.question = question;
  }

  public String getAnswer()
  {
    return answer;
  }

  public void setAnswer(String answer)
  {
    this.answer = answer;
  }
}
