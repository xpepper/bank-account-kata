package com.xpeppers.kata.bankaccount;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.reverse;

public class StatementPrinter {
    private static final String HEADER = "DATE | AMOUNT | BALANCE";

    private final Console console;
    private final DecimalFormat amountFormatter = new DecimalFormat("####.00");

    public StatementPrinter(Console console) {
        this.console = console;
    }

    public void print(List<Transaction> transactions) {
        console.printLine(HEADER);

        int runningBalance = 0;
        List<String> statements = new ArrayList<>();
        for (Transaction transaction : transactions) {
            runningBalance += transaction.amount();
            String statement = String.format("%s | %s | %s", transaction.date(), amountFormatter.format(transaction.amount()), amountFormatter.format(runningBalance));
            statements.add(statement);
        }
        reverse(statements).forEach(s -> console.printLine(s));
    }
}
