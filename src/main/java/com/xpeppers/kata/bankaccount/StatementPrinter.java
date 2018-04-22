package com.xpeppers.kata.bankaccount;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.reverse;

public class StatementPrinter {
    private static final String HEADER = "DATE | AMOUNT | BALANCE";

    private final Console console;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public StatementPrinter(Console console) {
        this.console = console;
    }

    public void print(List<Transaction> transactions) {
        console.printLine(HEADER);

        printStatementLines(transactions);
    }

    private void printStatementLines(List<Transaction> transactions) {
        int runningBalance = 0;
        List<String> statementLines = new ArrayList<>();
        for (Transaction transaction : transactions) {
            runningBalance += transaction.amount();
            String statementLine = String.format("%s | %s | %s", transaction.date(), format(transaction.amount()), format(runningBalance));
            statementLines.add(statementLine);
        }
        reverse(statementLines).forEach(console::printLine);
    }

    private String format(int amount) {
        return decimalFormat.format(amount);
    }
}
