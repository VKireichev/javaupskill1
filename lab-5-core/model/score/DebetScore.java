package model.score;

import model.account.Account;
import model.money.Money;

import java.util.Map;

public class DebetScore extends Score {

    public DebetScore(Money balans, Account owner, Integer number) {
        super(balans, owner, number);
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
    public Money getMoney(double balanceLess) {
        if (isLargeCredit(this.getOwner())) {
            System.out.println("You have too large credit.");
            return super.getMoneyWithoutLess();
        }
        return super.getMoney(balanceLess);
    }

    private boolean isLargeCredit(Account owner) {
        for (Score score : owner.getScoreMap()) {
            if (score.getBalance().getValue() < -20000) {
                return true;
            }
        }
        return false;
    }
}

