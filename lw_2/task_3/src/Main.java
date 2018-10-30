import java.security.InvalidParameterException;
import java.util.Random;

class Main {
  public static void main(String[] args) {
    try {
      if (!isCorrectArgsNumber(args)) {
        throw new InvalidParameterException("Wrong arguments");
      }

      int passwordLenghts = Integer.valueOf(args[0]);

      if (!isPasswordLengthCorrect(passwordLenghts)) {
        throw new InvalidParameterException("Wrong arguments");
      }

      String password = getPandomPassword(passwordLenghts, args[1]);
      System.out.println(password);
    } catch (NumberFormatException e) {
      System.out.println("Wrong arguments");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
  private static boolean isCorrectArgsNumber(String[] args) {
    return (args.length == 2);
  }

  private static int getRandomNumber(int max) {
    Random random = new Random();
    int num = random.nextInt(max);
    return num;
  }

  private static String getPandomPassword(int number, String str) {
    int max = str.length();
    String result = "";
    for (int i = 0; i < number; ++i) {
      result = result + str.charAt(getRandomNumber(max));
    }

    return result;
  }


  private static boolean isPasswordLengthCorrect(int passwordLength) {
    return (passwordLength > 0);
  }
}

