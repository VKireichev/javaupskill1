package com.luxoft.lab11.model.score;

import com.luxoft.lab11.model.account.Account;
import com.luxoft.lab11.model.money.Money;


public class DebitScore extends Score {

    public DebitScore(Money balance, Account owner, Integer number) {
        super(balance, owner, number);
    }

    @Override
    public void addMoney(Money money) {
        if (isLargeCredit(this.getOwner())) {
            System.out.println("You have too large credit.");
            return;
        }
        super.addMoney(money);
    }

    @Override
    public Money getMoney(Money money) {
        if (isLargeCredit(this.getOwner())) {
            System.out.println("You have too large credit.");
            return super.getMoneyWithoutLess();
        }
        return super.getMoney(money);
    }

    private boolean isLargeCredit(Account owner) {
        for (Score score : owner.getScoreMap().values()) {
            if (score.getBalance().getValue() < -20000) {
                return true;
            }
        }
        return false;
    }
}

