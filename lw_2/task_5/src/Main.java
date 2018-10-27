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

        Map<String, Integer> incidence = readFromFile(args[0]);
        Map<String, Integer> orderedMap = sortMap(incidence);
        printWords( orderedMap, Integer.valueOf(args[1]));
    }

    private static void printWords( Map<String, Integer> orderedMap, int quantity)
    {
        int i = 0;
        for(Map.Entry<String, Integer> e : orderedMap.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
            ++i;
            if (i == quantity)
            {
                break;
            }
        }
    }

    private static Map<String, Integer> readFromFile(String fileName) {
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
                if (incidence.containsKey(v)) {
                    incidence.put(v, incidence.get(v) + 1);
                } else {
                    incidence.put(v, j);
                }
            }
        }
        return incidence;
    }

    private static  Map<String, Integer> sortMap( Map<String, Integer> map) {
        Map<String, Integer> orderedMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(LinkedHashMap::new,
                        (m, c) -> m.put(c.getKey(), c.getValue()),
                        LinkedHashMap::putAll);
        return orderedMap;
    }

    private static boolean isCorrectArgsNumber(String[] args) {
        return (args.length == 2);
    }

    private static boolean isFileExists(String filePath) {
        File f = new File(filePath);
        return (f.exists() && !f.isDirectory());
    }
}
