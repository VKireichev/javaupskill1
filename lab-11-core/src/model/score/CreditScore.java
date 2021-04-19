package model.score;

import model.account.Account;
import model.money.Money;
import model.annotation.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


@Loggable
public class CreditScore extends Score {
    public CreditScore(Money balance, Account owner, Integer number) {
        super(balance, owner, number);
    }
}

