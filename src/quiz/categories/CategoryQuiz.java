package src.quiz.categories;

import java.util.Random;
import src.quiz.QuizItem;

public abstract class CategoryQuiz
{
  protected static final Random RANDOM=new Random();

  public abstract QuizItem[] generate(int size);
}
