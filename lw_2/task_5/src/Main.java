import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        if (!isCorrectArgsNumber(args)) {
            System.out.println("Incorrect number of arguments");
            System.exit(0);
        }
        if (!isFileExists(args[0])) {
            System.out.println("File not exists");
            System.exit(0);
        }

        ArrayList<String> wordsList = new ArrayList<>();
        Map<String, Integer> incidence = readFromFile(args[0], wordsList);
        printWords(incidence, Integer.valueOf(args[1]), wordsList);
    }

    private static void printWords(Map<String, Integer> orderedMap, int quantity, ArrayList<String> wordsList) {
        int i = 0;
        for (int j = (wordsList.size() - 1); j >= 0; j--) {
            String temp = wordsList.get(j);
            System.out.println(temp + " " + orderedMap.get(temp));
            ++i;
            if (i == quantity) {
                break;
            }
        }
    }

    private static Map<String, Integer> readFromFile(String fileName, ArrayList<String> wordsList) {
        Map<String, Integer> incidence = new HashMap<String, Integer>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int j = 1;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] string = line.split(" ");
            for (String v : string) {
                boolean repeatWord = false;
                if (incidence.containsKey(v)) {
                    incidence.put(v, incidence.get(v) + 1);
                    repeatWord = true;
                } else {
                    incidence.put(v, j);
                }
                addWordToList(v, wordsList, repeatWord);
            }
        }

        return incidence;
    }

    private static void addWordToList(String word, ArrayList<String> wordsList, boolean repeatWord) {
        if (repeatWord) {
            int index = wordsList.indexOf(word);
            if (index != (wordsList.size() - 1)) {
                wordsList.remove(word);
                wordsList.add(index + 1, word);
            }
        } else {
            wordsList.add(0, word);
        }
    }

    private static boolean isCorrectArgsNumber(String[] args) {
        return (args.length == 2);
    }

    private static boolean isFileExists(String filePath) {
        File f = new File(filePath);
        return (f.exists() && !f.isDirectory());
    }
}
