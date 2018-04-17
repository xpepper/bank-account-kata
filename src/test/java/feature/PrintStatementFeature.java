package feature;

import com.xpeppers.kata.bankaccount.Account;
import com.xpeppers.kata.bankaccount.Console;
import com.xpeppers.kata.bankaccount.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PrintStatementFeature {

    @Mock
    private Console console;

    private Account account;
    private TransactionRepository transactionRepository = new TransactionRepository();

    @Before
    public void setUp() throws Exception {
        account = new Account(transactionRepository);
    }

    @Test
    public void
    print_statement_containing_all_transactions() {
        account.deposit(1000);
        account.withdrawal(100);
        account.deposit(500);

        account.printStatement();

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/04/2014 | 500.00 | 1400.00");
        inOrder.verify(console).printLine("02/04/2014 | -100.00 | 900.00");
        inOrder.verify(console).printLine("01/04/2014 | 1000.00 | 1000.00");
    }
}
