package model.score;

import model.account.Account;
import model.money.Money;

import java.util.Map;

public class CreditScore extends Score {
    public CreditScore(Money balans, Account owner, Integer number) {
        super(balans, owner, number);
    }
}

