package src.quiz;

public class QuizUtils
{
  public static void printYes()
  {
    System.out.println("  \u2713 Yes!\n");
  }

  public static void printNo(String answer)
  {
    System.out.println("  \u2717 Sorry, the correct answer was: " + answer + "\n");
  }

  public static int parseArgs(String[] args, int defaultSize)
  {
    if (args.length > 0)
    {
      try
      {
        int size = Integer.parseInt(args[0]);
        if (size > 0) return size;
      }
      catch (NumberFormatException ignored)
      {
      }
    }
    return defaultSize;
  }
}
