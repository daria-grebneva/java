import java.security.InvalidParameterException;

class Main {
  public static void main(String[] args) {
    try {
      if (!isCorrectArgsNumber(args)) {
        throw new InvalidParameterException("Wrong arguments");
      }
      if (!isCorrectOperationMode(args[0])) {
        throw new Exception("Wrong arguments");
      }
      System.out.println(processOperation(args[0], args[1], args[2]));
    } catch (NumberFormatException e) {
      System.out.println("Wrong arguments");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private static String processOperation(String operation, String key, String str) {
    if (operation.equals("-e")) {
      return encryption(key, str);
    } else {
      return decryption(key, str);
    }
  }

  private static String encryption(String key1, String str) {
    int key = getDifference(Integer.parseInt(key1));
    StringBuffer res = new StringBuffer();

    try {
      for (int i = 0; i < str.length(); i++) {
        int temp = str.charAt(i) + key;
        boolean low = checkForLowUp(str.charAt(i));
        temp = checkCircleEncryption(temp, low);
        res.append(Character.toString((char) (temp)));
      }
    } catch (Exception e) {
      return new String();
    }

    return res.toString();
  }

  private static int checkCircleEncryption(int symbol, boolean low) {
    char temp = (char) (symbol);
    if (low) {
      if (temp > 'z') {
        return (symbol - 26);
      }
    } else {
      if (temp > 'Z') {
        return (symbol - 26);
      }
    }

    return temp;
  }

  private static int checkCircleDecryption(int symbol, boolean low) {
    char temp = (char) (symbol);
    if (low) {
      if (temp < 'a') {
        return (symbol + 26);
      }
    } else {
      if (temp < 'A') {
        return (symbol + 26);
      }
    }
    return temp;
  }

  private static int getDifference(int key) {
    return (key > 26) ? (key % 26) : key;
  }


  private static String decryption(String key1, String str) {
    int key = getDifference(Integer.parseInt(key1));
    StringBuffer res = new StringBuffer();

    try {
      for (int i = 0; i < str.length(); i++) {
        int temp = str.charAt(i) - key;
        boolean low = checkForLowUp(str.charAt(i));
        temp = checkCircleDecryption(temp, low);
        res.append(Character.toString((char) (temp)));
      }
    } catch (Exception e) {
      return new String();
    }

    return res.toString();
  }

  private static boolean isCorrectOperationMode(String operation) {
    return (operation.equals("-e") || operation.equals("-d"));
  }

  private static boolean checkForLowUp(int code) throws Exception {
    boolean low = isLow(code);
    boolean up = isUp(code);
    if (!low && !up) {
      throw new Exception("Wrong arguments");
    }
    return low;
  }

  private static boolean isLow(int code) {
    return (code >= 'a' && code <= 'z');
  }

  private static boolean isUp(int code) {
    return (code >= 'A' && code <= 'Z');
  }

  private static boolean isCorrectArgsNumber(String[] args) {
    return (args.length == 3);
  }
}