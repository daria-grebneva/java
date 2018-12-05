package com.javacourse2018.lw03;

import com.javacourse2018.lw03.spreadsheet.*;

public class Main {
    public static void main(String[] args) {
        try {
            Spreadsheet spreadsheet = new Spreadsheet();
            new SpreadsheetHandler(spreadsheet);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}