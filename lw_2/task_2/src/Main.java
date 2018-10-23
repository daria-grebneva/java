import java.util.List;
import java.util.Arrays;

public class Main {
    char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

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

    private static String processOperation(String operation, String key, String str) {
        if (operation == "-e") {
            //шифрование
        } else {
            //дешифрование
        }
        return "";
    }

    private static String encryption(String key, String str) {
        for (int i = 0; i < str.length(); i++) {
            str.charAt(i); //-находим в массиве, потом находим число сдвинутое в массиве на одну позицию
        }
        return "";
    }

    private static boolean findElemInArray(char[] alphabet, char elem) {
        return Arrays.asList(alphabet).contains(elem);
    }


    private static boolean isCorrectOperationMode(String operation) {
        System.out.println(operation);
        return ((operation == "-e") || (operation == "-d"));
    }

    private static boolean isCorrectArgsNumber(String[] args) {
        return (args.length == 3);
    }
}