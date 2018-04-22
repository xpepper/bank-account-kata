package com.xpeppers.kata.bankaccount;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ClockShould {

    @Test
    public void return_the_current_date_in_dd_MM_YYYY_format() {
        Clock clock = new TestableClock();

        String date = clock.todayAsString();

        assertThat(date, is("11/04/2018"));
    }

    class TestableClock extends Clock {
        @Override
        protected LocalDate today() {
            return LocalDate.of(2018, 4, 11);
        }
    }
}