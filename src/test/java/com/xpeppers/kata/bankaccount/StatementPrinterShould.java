package com.xpeppers.kata.bankaccount;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StatementPrinterShould {

    @Mock
    private Console console;
    private StatementPrinter statementPrinter;

    @Before
    public void setUp() throws Exception {
        statementPrinter = new StatementPrinter(console);
    }

    @Test
    public void always_print_the_header_first() {
        statementPrinter.print(EMPTY_LIST);

        verify(console).printLine("DATE | AMOUNT | BALANCE");
    }

    @Test
    public void print_transactions_in_reverse_order() {
        statementPrinter.print(transactionsContaining(
                aDeposit("01/04/2014", 1000),
                aWithdrawal("02/04/2014", 100),
                aDeposit("10/04/2014", 500)
        ));

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/04/2014 | 500.00 | 1400.00");
        inOrder.verify(console).printLine("02/04/2014 | -100.00 | 900.00");
        inOrder.verify(console).printLine("01/04/2014 | 1000.00 | 1000.00");
    }

    private List<Transaction> transactionsContaining(Transaction... transactions) {
        return asList(transactions);
    }

    private Transaction aWithdrawal(String date, int amount) {
        return new Transaction(date, -amount);
    }

    private Transaction aDeposit(String date, int amount) {
        return new Transaction(date, amount);
    }
}
