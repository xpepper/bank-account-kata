package com.xpeppers.kata.bankaccount;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TransactionRepositoryShould {

    private static final String TODAY = "21/04/2018";

    private TransactionRepository transactionRepository;
    private @Mock Clock clock;

    @Before
    public void setUp() throws Exception {
        transactionRepository = new TransactionRepository(clock);
    }

    @Test
    public void create_and_store_a_deposit_transaction() {
        given(clock.todayAsString()).willReturn(TODAY);
        transactionRepository.addDeposit(100);

        List<Transaction> transactions = transactionRepository.allTransactions();

        assertThat(transactions.size(), is(1));
        assertThat(transactions.get(0), is(transaction(TODAY, 100)));
    }

    private Transaction transaction(String date, int amount) {
        return new Transaction(date, amount);
    }
}