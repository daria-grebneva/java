//import com.sun.org.apache.xpath.internal.operations.String;

import java.util.List;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        if (!isCorrectArgsNumber(args)) {
            System.out.println("incorrect number of arguments");
            System.exit(0);
        }
        if (!isCorrectOperationMode(args[0])) {
            System.out.println("incorrect operation");
            System.exit(0);
        }
        processOperation(args[0], args[1], args[2]);
    }

    private static char[] getAlphabet()
    {
        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        return alphabet;
    }

    private static String processOperation(String operation, String key, String str) {
        if (operation.equals("-e")) {
            //шифрование
            encryption(key, str);
        } else {
            //дешифрование
        }
        return "";
    }

    private static String encryption(String key1, String str) {
        int key = Integer.parseInt(key1);
//        String newString = new String();
//        for(int i = 0; i < str.length(); i++) {
//            boolean isLowerCase = true;
//            char letter = str.charAt(i);
//
//            if (!(letter >= 'a' && letter <= 'z')) {
//                isLowerCase = false;
//                letter = Character.toLowerCase(letter);
//            }
//
//            if(Character.isLetter((char) ((int) letter + key)))
//                newString += ((char) ((int) letter + key));
//            else{
//                int keyCheck = key;
//                keyCheck -= (int)'z' -  (int) letter;
//                keyCheck = keyCheck % 26;
//                if(keyCheck != 0) {
//                    if (isLowerCase)
//                        newString += ((char) ((int) 'a' + keyCheck - 1));
//                    else
//                        newString += (Character.toUpperCase ((char) ((int) 'a' + keyCheck - 1)));
//                }
//                else {
//                    if (isLowerCase)
//                        newString += 'z';
//                    else
//                        newString += 'Z';
//                }
//            }
//        }
        int a;
        StringBuffer res = new StringBuffer();

        try {
            for(int i = 0; i < str.length(); i++) {
                res.append(Character.toString((char) (str.charAt(i) + key)));
            }
        } catch (Exception e) {
            return new String();
        }

        return res.toString();
//        return newString;
    }

    private static boolean findElemInArray(char[] alphabet, char elem) {
        return Arrays.asList(alphabet).contains(elem);
    }


    private static boolean isCorrectOperationMode(String operation) {
        return (operation.equals("-e") || operation.equals("-d"));
    }

    private static boolean isCorrectArgsNumber(String[] args) {
        return (args.length == 3);
    }
}