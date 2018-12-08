package com.javacourse2018.lw03.spreadsheet;

import java.text.DecimalFormat;
import java.util.*;

public class SpreadsheetViewer {

    private TreeMap<Character, TreeMap<Integer, String>> table;
    private boolean isDivisionByZero = false;

    SpreadsheetViewer(TreeMap<Character, TreeMap<Integer, String>> table) {
        this.table = table;
    }

    void display() {
        System.out.print("  ");
        for (Map.Entry entry : table.entrySet()) {
            System.out.printf("%15s", entry.getKey());
        }
        System.out.println();
        Set<Integer> column = new HashSet<Integer>();
        for (Map.Entry<Character, TreeMap<Integer, String>> entry : table.entrySet()) {
            this.viewRow(entry.getValue(), column);
        }
    }

    private void viewRow(TreeMap<Integer, String> row, Set<Integer> column) {
        for (Map.Entry element : row.entrySet()) {
            Integer key = (Integer) element.getKey();
            if (!column.contains(key)) {
                System.out.print(key + " ");
                this.viewCell(key);
                column.add(key);
                System.out.println();
            }
        }
    }

    private String getFormula(String value) {
        return (value.substring(value.split(" ")[0].length(), value.length()));
    }

    private void viewCell(Integer rowInteger) {
        int counter = 1;
        for (Map.Entry<Character, TreeMap<Integer, String>> entry : table.entrySet()) {
            TreeMap<Integer, String> row = entry.getValue();
            for (Map.Entry element : row.entrySet()) {
                Integer key = (Integer) element.getKey();
                if (Objects.equals(key, rowInteger)) {
                    String value = (String) element.getValue();
                    String format = "%" + (15 * counter) + "s";
                    counter = 0;
                    if (value.split(" ")[0].equals("formula")) {
                        this.isDivisionByZero = false;
                        Double result = makeCalculation(this.getFormula(value));
                        if (this.isDivisionByZero) {
                            System.out.printf(format, "");
                        }
                        else if (Double.isNaN(result)){
                            String str = concatinate(this.getFormula(value));
                            if (str.isEmpty()) {
                                System.out.printf(format, "");
                            }
                            else {
                                System.out.printf(format, str);
                            }
                        }
                        else {

                            String calcResult = new DecimalFormat("#0.00")
                                    .format(result);
                            System.out.printf(format, calcResult);
                        }

                        continue;
                    }
                    System.out.printf(format, value);
                }
            }
            ++counter;
        }
    }

    void checkDivisionByZero(String value) throws Exception {
        value = "formula " + value;
        this.isDivisionByZero = false;
        Double result = makeCalculation(this.getFormula(value));
        if (this.isDivisionByZero) {
            throw new Exception("[ERR] Division by zero");
        }
        else if (Double.isNaN(result)){
            String str = concatinate(this.getFormula(value));
            if (str.isEmpty()) {
                throw new Exception("[ERR] Something wrong with operation");
            }
        }
    }

    private String concatinate(String formula) {
        String[] prefixStrArray = formula.split(" ");
        Stack<String> stack = new Stack<>();

        Map<String, Concatination> operations = new HashMap<>();
        operations.put("+", (a, b) -> a + b);
        for (int i = prefixStrArray.length - 1; i > -1; i--) {
            String prefixStr = prefixStrArray[i];
            if (prefixStr.equals("")) {
                continue;
            }
            if (operations.containsKey(prefixStr)) {
                String a = stack.pop();
                String b = stack.pop();
                stack.push(operations.get(prefixStr).getStringResult(a, b));
            } else {
                stack.push(getStringValueVariable(prefixStr));
            }
        }
        return stack.pop();
    }


    private String getStringValueVariable(String variable) {
        String value = "";
        if (table.containsKey(variable.charAt(0))) {
            TreeMap<Integer, String> col = table.get(variable.charAt(0));
            if (col.containsKey(Integer.parseInt("" + variable.charAt(1)))) {
                String str = col.get(Integer.parseInt("" + variable.charAt(1)));
                if (str.split(" ")[0].equals("formula")) {
                    return concatinate(getFormula(str));
                }
                value = value + str;
            }
        }
        return value;
    }

    private Double getValueVariable(String variable) {
        Double value;
        if (table.containsKey(variable.charAt(0))) {
            TreeMap<Integer, String> col = table.get(variable.charAt(0));
            if (col.containsKey(Integer.parseInt("" + variable.charAt(1)))) {
                String str = col.get(Integer.parseInt("" + variable.charAt(1)));
                if (str.split(" ")[0].equals("formula")) {
                    return makeCalculation(getFormula(str));
                }
                value = (!isDouble(str)) ? Double.NaN : Double.parseDouble(str);
            } else {
                value = Double.NaN;
            }
        } else {
            value = Double.NaN;
        }
        return value;
    }

    double makeCalculation(String formula) {
        String[] prefixStrArray = formula.split(" ");
        Stack<Double> stack = new Stack<Double>();

        Map<String, Calculation> operations = new HashMap<>();
        operations.put("+", (a, b) -> a + b);
        operations.put("-", (a, b) -> a - b);
        operations.put("*", (a, b) -> a * b);
        operations.put("/", (a, b) -> a / b);
        for (int i = prefixStrArray.length - 1; i > -1; i--) {
            String prefixStr = prefixStrArray[i];
            if (prefixStr.equals("")) {
                continue;
            }
            if (operations.containsKey(prefixStr)) {
                Double a = stack.pop();
                Double b = stack.pop();
                if (prefixStr.equals("/") && (b == 0)) {
                    this.isDivisionByZero = true;
                    stack.push(Double.NaN);
                }
                else {
                    stack.push(operations.get(prefixStr).getResult(a, b));
                }
            } else {
                if (isDouble(prefixStr)) {
                    stack.push(Double.parseDouble(prefixStr));
                    continue;
                }
                stack.push(getValueVariable(prefixStr));
            }
        }
        return stack.pop();
    }

    private static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
