package com.javacourse2018.lw03.spreadsheet;

import java.util.*;

public class Spreadsheet {
    private static TreeMap<Character, TreeMap<Integer, String>> table = new TreeMap<>();
    private static SpreadsheetViewer viewer = new SpreadsheetViewer(table);

    public Spreadsheet() {
    }

    void setValue(PairStruct pair, String newValue) {
        if (table.containsKey(pair.symbol)) {
            TreeMap<Integer, String> row = table.get(pair.symbol);
            row.put(pair.number, newValue);
        } else {
            TreeMap<Integer, String> row = new TreeMap<Integer, String>();
            row.put(pair.number, newValue);
            table.put(pair.symbol, row);
        }
    }

    void setFormula(PairStruct pair, String newValue) {
        if (table.containsKey(pair.symbol)) {
            TreeMap<Integer, String> row = table.get(pair.symbol);
            row.put(pair.number, newValue);
        } else {
            TreeMap<Integer, String> row = new TreeMap<Integer, String>();
            row.put(pair.number, newValue);
            table.put(pair.symbol, row);
        }
    }


    boolean isFormula(PairStruct cell) {
        boolean result = false;
        if (table.containsKey(cell.symbol)) {
            if (table.get(cell.symbol).containsKey(cell.number)) {
                String[] input = table.get(cell.symbol).get(cell.number).split(" ");
                result = input[0].equals("formula");
            }
        }
        return result;
    }

    String getFormula(PairStruct cell) {
        String str = "";
        if (table.containsKey(cell.symbol)) {
            if (table.get(cell.symbol).containsKey(cell.number)) {
                String[] input = table.get(cell.symbol).get(cell.number).split(" ");
                str = input[1];
            }
        }
        return str;
    }

    void display() throws Exception {
        viewer.display();
    }

    void checkDivisionByZero(String value) throws Exception {
        viewer.checkDivisionByZero(value);
    }
}
