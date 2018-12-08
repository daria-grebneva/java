package com.javacourse2018.lw03.spreadsheet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class SpreadsheetHandler {
    private Spreadsheet spreadsheet;
    private HashMap<String, Command> commands;

    public SpreadsheetHandler(Spreadsheet spreadsheet) throws Exception {
        this.spreadsheet = spreadsheet;
        commands = new HashMap<>();
        commands.put("set", this::setValue);
        commands.put("setformula", this::checkCondition);
        commands.put("display", input -> this.spreadsheet.display());

        this.start();
    }

    private void start() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        do {
            System.out.print("-> ");
            line = reader.readLine();
            try {
                if (line.endsWith("stop")) {
                    break;
                }
                String[] input = getLineInput(line);
                if (input.length == 0) {
                    throw new Exception("[ERR] unknown command");
                }

                if (commands.containsKey(input[0])) {
                    commands.get(input[0]).runCommand(input);
                } else {
                    throw new Exception("[ERR] unknown command");
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } while (line != null);
    }

    private void setValue(String[] input) throws Exception {
        if (input.length == 1) {
            throw new Exception("[ERR] no identifier");
        }
        PairStruct pair = getVariableInfo(input[1]);

        if (input.length == 2) {
            throw new Exception("[ERR] value not entered");
        }
        spreadsheet.setValue(pair, input[2]);
        System.out.println("OK");
    }

    private void checkCondition(String[] input) throws Exception {
        if (input.length == 1) {
            throw new Exception("[ERR] no identifier");
        }
        PairStruct pair = getVariableInfo(input[1]);

        if (input.length == 2) {
            throw new Exception("[ERR] formula not entered");
        }
        List<String> items = Arrays.asList(input);
        items = items.subList(1, 3);
        if (!isCorrectFormula(items)) {
            throw new Exception("[ERR] wrong formula");
        }
        spreadsheet.setFormula(pair, "formula " + items.get(1));
        System.out.println("OK");
    }

    private static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isCorrectFormula(List<String> input) throws Exception {
        String formula = input.get(1).replaceAll("\\(|\\)|\\[|\\]", " ");
        String[] prefixes = formula.split(" ");
        Stack<String> stack = new Stack<String>();
        boolean division = false;
        boolean zero = false;
        for (int i = prefixes.length - 1; i > -1; i--) {
            String prefix = prefixes[i];
            if (prefix.equals("")) {
                continue;
            }
            if (prefix.equals("+") || prefix.equals("/") || prefix.equals("*")
                    || prefix.equals("-")) {
                if (prefix.equals("/")) {
                    division = true;
                }
                if (stack.size() < 2) {
                    return false;
                }
                stack.push(stack.pop() + stack.pop());
            } else {
                if (input.get(0).equals(prefix)) {
                    return false;
                }
                if (!isDouble(prefix)) {
                    PairStruct cell = getVariableInfo(prefix);
                    if (spreadsheet.isFormula(cell)) {
                        //TODO:: проверить деление на ноль
                       // spreadsheet.makeCalculaton(spreadsheet.getFormula(cell));
                        List<String> items = new ArrayList<String>();
                        items.add(input.get(0));
                        items.add(spreadsheet.getFormula(cell));

                        return isCorrectFormula(items);
                    }
                }
                stack.push(prefix);
            }
        }

        if ((stack.pop() != null) && (stack.size() != 0)) {
            return false;
        }

        if (!checkCalculation(formula))
        {
            throw new Exception("[ERR] delim na nol'");
        }
        input.set(1, formula);
        return true;
    }

    private String[] getLineInput(String line) {
        String[] input = line.split(" ");
        if (input.length >= 3) {
            if (input[0].equals("setformula")) {
                StringBuilder formula = new StringBuilder();
                for (int i = 2; i < input.length; ++i) {
                    formula.append(input[i]).append(" ");
                }
                input[2] = formula.toString();
            }
        }
        return input;
    }

    private PairStruct getVariableInfo(String variable) throws Exception {
        PairStruct pair = new PairStruct();
        if (variable.length() < 2) {
            throw new Exception("[ERR] wrong identifier");
        }

        Character symbol = variable.charAt(0);

        if (((int) symbol < (int) 'A') || ((int) symbol > (int) 'Z')) {
            throw new Exception("[ERR] wrong identifier");
        }

        String newStr = variable.substring(1, variable.length());

        Integer number;
        try {
            number = Integer.parseInt(newStr);
        } catch (NumberFormatException ex) {
            throw new Exception("[ERR] wrong identifier");
        }

        pair.symbol = symbol;
        pair.number = number;
        return pair;
    }

    private boolean checkCalculation(String formula) {
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
                   // throw new Exception("[ERR] wrong formula");
                    //stack.push(Double.NaN);
                    return false;
                }
                else {
                    return true;
                    //stack.push(operations.get(prefixStr).getResult(a, b));
                }
            } else {
                if (isDouble(prefixStr)) {
                    //stack.push(Double.parseDouble(prefixStr));
                    continue;
                }
               // stack.push(getValueVariable(prefixStr));
            }
        }

        return true;
        //return stack.pop();
    }

}
