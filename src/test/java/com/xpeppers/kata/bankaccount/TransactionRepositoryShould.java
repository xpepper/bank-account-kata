package com.xpeppers.kata.bankaccount;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TransactionRepositoryShould {

    private static final String TODAY = "21/04/2018";

    private TransactionRepository transactionRepository;
    private @Mock Clock clock;
    private List<Transaction> transactions;

    @Before
    public void setUp() throws Exception {
        transactionRepository = new TransactionRepository(clock);
        given(clock.todayAsString()).willReturn(TODAY);
    }

    @Test
    public void create_and_store_a_deposit_transaction() {
        transactionRepository.addDeposit(100);

        transactions = transactionRepository.allTransactions();

        assertThat(transactions, hasSize(1));
        assertThat(transactions.get(0), is(transaction(TODAY, 100)));
    }

    @Test
    public void create_and_store_a_withdrawal_transaction() {
        transactionRepository.addWithdrawal(100);

        List<Transaction> transactions = transactionRepository.allTransactions();

        assertThat(transactions, hasSize(1));
        assertThat(transactions.get(0), is(transaction(TODAY, -100)));
    }

    private Transaction transaction(String date, int amount) {
        return new Transaction(date, amount);
    }
}