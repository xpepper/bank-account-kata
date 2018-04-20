package com.xpeppers.kata.bankaccount;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountShould {

    @Mock private TransactionRepository transactionRepository;
    @Mock private StatementPrinter statementPrinter;

    private Account account;

    @Before
    public void setUp() throws Exception {
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
    public void store_a_deposit_transaction() {
        account.deposit(100);

        verify(transactionRepository).addDeposit(100);
    }

    @Test
    public void store_a_withdraw_transaction() {
        account.withdrawal(100);

        verify(transactionRepository).addWithdrawal(100);
    }

    @Test
    public void print_a_statement() {
        List<Transaction> transactions = asList(new Transaction("12/03/2017", 100));
        given(transactionRepository.allTransactions()).willReturn(transactions);

        account.printStatement();

        verify(statementPrinter).print(transactions);
    }
}