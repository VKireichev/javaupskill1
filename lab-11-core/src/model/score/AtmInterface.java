package model.score;

import model.money.Money;

public interface AtmInterface {
    public void getMoneyFromScore(Score score, Money money);
    public void addMoneyToScore(Score score, Money money);
}
