package src.quiz;

import src.quiz.categories.MemorizationQuiz;

public enum QuizCategory
{
  // Add categories here
  MEMORIZATION(n -> new MemorizationQuiz().generate(n));

  private final QuizGenerator generator;

  QuizCategory(QuizGenerator generator)
  {
    this.generator=generator;
  }

  public QuizItem[] getQuizItems(int size)
  {
    return generator.generate(size);
  }

  public static QuizItem[][] getAllQuizItems(int size)
  {
    return new QuizItem[][]
    {
        // Add categories here
        MEMORIZATION.getQuizItems(size)
    };
  }
}


@FunctionalInterface
interface QuizGenerator
{
  QuizItem[] generate(int size);
}
