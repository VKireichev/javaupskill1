package com.luxoft.lab11.model.score;

import com.luxoft.lab11.model.account.Account;
import com.luxoft.lab11.model.annotation.Loggable;
import com.luxoft.lab11.model.money.Money;


@Loggable
public class CreditScore extends Score {
    public CreditScore(Money balance, Account owner, Integer number) {
        super(balance, owner, number);
    }
}

