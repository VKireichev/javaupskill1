package model.money;

public interface MoneyInterface {
    void addMoney(Money money);
    Money getMoney(Money money);
    Money getMoneyWithoutLess();
}
