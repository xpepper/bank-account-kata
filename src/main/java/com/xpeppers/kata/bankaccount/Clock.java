package com.xpeppers.kata.bankaccount;

import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ofPattern;

public class Clock {

    private static final String DD_MM_YYYY = "dd/MM/YYYY";

    public String todayAsString() {
        return today().format(ofPattern(DD_MM_YYYY));
    }

    protected LocalDate today() {
        return LocalDate.now();
    }
}
