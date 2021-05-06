package com.luxoft.lab11.model.money;

public class Currency {
    private String name;
    private float usdExchangeRate;

    public Currency(String name, float usdExchangeRate) {
        this.name = name;
        this.usdExchangeRate = usdExchangeRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getUsdExchangeRate() {
        return usdExchangeRate;
    }

    public void setUsdExchangeRate(float usdExchangeRate) {
        this.usdExchangeRate = usdExchangeRate;
    }

}
