package com.xpeppers.kata.bankaccount;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class TransactionRepository {
    private final List<Transaction> transactions  = new ArrayList<>();
    private final Clock clock;

    public TransactionRepository(Clock clock) {
        this.clock = clock;
    }

    public void addDeposit(int amount) {
        Transaction deposit = new Transaction(clock.todayAsString(), amount);
        transactions.add(deposit);
    }

    public void addWithdrawal(int amount) {
        throw new UnsupportedOperationException();
    }

    public List<Transaction> allTransactions() {
        return unmodifiableList(transactions);
    }
}
