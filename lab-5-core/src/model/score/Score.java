package model.score;

import model.account.Account;
import model.money.Money;
import model.money.MoneyInterface;

public abstract class Score implements MoneyInterface {
    private Money balance;
    private Account owner;
    private Integer number;

    protected Score(Money balance, Account owner, Integer number) {
        this.balance = balance;
        this.owner = owner;
        this.number = number;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public void addMoney(Money money) {
        double usdValueIn = money.getValue() * money.getCurrency().getUsdCource();
        double usdValueThis = this.balance.getValue() * this.balance.getCurrency().getUsdCource();
        this.balance.setValue((usdValueThis + usdValueIn) * this.balance.getCurrency().getUsdCource());
    }

    public Money getMoney(double balanceLess) {
        if (balanceLess > 30000) {
            throw new IllegalArgumentException("Wrong balance less!");
        }
        if (this.balance.getValue() < balanceLess) {
            System.out.println("You have no so much!");
        } else {
            this.balance.setValue(this.balance.getValue() - balanceLess);
        }
        return this.balance;
    }

    public Money getMoneyWithoutLess() {
        return this.balance;
    }
}
