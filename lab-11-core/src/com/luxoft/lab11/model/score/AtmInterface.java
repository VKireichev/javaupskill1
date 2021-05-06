package com.luxoft.lab11.model.score;

import com.luxoft.lab11.model.money.Money;

public interface AtmInterface {
    void getMoneyFromScore(Score score, Money money);

    void addMoneyToScore(Score score, Money money);
}
