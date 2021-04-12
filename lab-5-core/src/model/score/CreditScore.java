package model.score;

import model.account.Account;
import model.money.Money;

public class CreditScore extends Score {
    public CreditScore(Money balance, Account owner, Integer number) {
        super(balance, owner, number);
    }
}

