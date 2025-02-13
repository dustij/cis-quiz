package src.quiz.categories;

import src.quiz.QuizItem;

public class MemorizationQuiz extends CategoryQuiz
{
  private static final QuizItem[] QUESTIONS=
  {
      new QuizItem("2^1 = ", "2"), new QuizItem("2^2 = ", "4"), new QuizItem("2^3 = ", "8"),
      new QuizItem("2^4 = ", "16"), new QuizItem("2^5 = ", "32"), new QuizItem("2^6 = ", "64"),
      new QuizItem("2^7 = ", "128"), new QuizItem("2^8 = ", "256"), new QuizItem("2^9 = ", "512"),
      new QuizItem("2^10 = ", "1024"), new QuizItem("Kilo (storage) = ", "2^10"),
      new QuizItem("Mega (storage) = ", "2^20"), new QuizItem("Giga (storage) = ", "2^30"),
      new QuizItem("Tera (storage) = ", "2^40"), new QuizItem("Milli (storage) = ", "2^-10"),
      new QuizItem("Micro (storage) = ", "2^-20"), new QuizItem("Nano (storage) = ", "2^-30"),
      new QuizItem("Pico (storage) = ", "2^-40"), new QuizItem("Kilo (speed) = ", "10^3"),
      new QuizItem("Mega (speed) = ", "10^6"), new QuizItem("Giga (speed) = ", "10^9"),
      new QuizItem("Tera (speed) = ", "10^12"), new QuizItem("Milli (speed) = ", "10^-3"),
      new QuizItem("Micro (speed) = ", "10^-6"), new QuizItem("Nano (speed) = ", "10^-9"),
      new QuizItem("Pico (speed) = ", "10^-12"), new QuizItem("Base 4 digit groups = ", "2"),
      new QuizItem("Base 8 digit groups = ", "3"), new QuizItem("Base 16 digit groups = ", "4")
  };

  @Override
  public QuizItem[] generate(int size)
  {
    QuizItem[] quizItems=new QuizItem[size];
    for (int i=0; i<size; i++)
    {
      quizItems[i]=QUESTIONS[RANDOM.nextInt(QUESTIONS.length)];
    }
    return quizItems;
  }

}
