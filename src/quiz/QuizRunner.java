package src.quiz;

import java.util.Scanner;

public class QuizRunner
{
  private final int size;

  public QuizRunner()
  {
    this(1);
  }

  public QuizRunner(int size)
  {
    this.size=size;
  }

  public void start()
  {
    QuizItem[][] quiz=QuizCategory.getAllQuizItems(size);
    int correct=0, wrong=0;
    Scanner scanner=new Scanner(System.in);

    for (QuizItem[] group : quiz)
    {
      for (var item : group)
      {
        System.out.println(item.getQuestion());
      }
    }
  }

  public int getSize()
  {
    return size;
  }


}
