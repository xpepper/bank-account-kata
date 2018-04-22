package com.xpeppers.kata.bankaccount;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static java.util.Collections.EMPTY_LIST;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StatementPrinterShould {

    @Mock
    private Console console;

    @Test
    public void always_print_the_header_first() {
        StatementPrinter statementPrinter = new StatementPrinter(console);

        statementPrinter.print(EMPTY_LIST);

        verify(console).printLine("DATE | AMOUNT | BALANCE");
    }
}