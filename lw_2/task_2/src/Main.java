
import java.util.Arrays;
class CaesarCipher {
  public static void main(String[] args) {
    if (!isCorrectArgsNumber(args)) {
      System.out.println("Wrong arguments");
      System.exit(0);
    }
    if (!isCorrectOperationMode(args[0])) {
      System.out.println("Wrong arguments");
      System.exit(0);
    }
    System.out.println(processOperation(args[0], args[1], args[2]));
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
    int a;
    StringBuffer res = new StringBuffer();

    try {
      for (int i = 0; i < str.length(); i++) {
        int temp = str.charAt(i) + key;
        temp = checkCircleEncryption(temp, key);
        res.append(Character.toString((char) (str.charAt(i) + temp)));
      }
    } catch (Exception e) {
      return new String();
    }

    return res.toString();
  }

  private static int checkCircleEncryption(int symbol, int key)
  {
    char temp = (char)(symbol + key);
    if (temp > 'z')
    {
      return (symbol + key - 26);
    }
    return key;
  }

  private static int checkCircleDecryption(int symbol, int key)
  {
    char temp = (char)(symbol - key);
    if (temp < 'a')
    {
      return (symbol - key + 26);
    }
    return key;
  }

  private static int getDifference(int key)
  {
    return (key > 26) ? (key % 26) : key;
  }


  private static String decryption(String key1, String str) {
    int key = getDifference(Integer.parseInt(key1));
    int a;
    StringBuffer res = new StringBuffer();

    try {
      for (int i = 0; i < str.length(); i++) {
        int temp = str.charAt(i) - key;
        temp = checkCircleDecryption(temp, key);
        res.append(Character.toString((char) (str.charAt(i) - temp)));
      }
    } catch (Exception e) {
      return new String();
    }

    return res.toString();
  }

  private static boolean isCorrectOperationMode(String operation) {
    return (operation.equals("-e") || operation.equals("-d"));
  }

  private static boolean isCorrectArgsNumber(String[] args) {
    return (args.length == 3);
  }
}