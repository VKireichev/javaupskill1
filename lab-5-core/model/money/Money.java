package model.money;

public class Money {
    private Currency currency;
    private double value;

    public Money(double value, String currencyName) {
        this.value = value;
        this.currency = new Currency(currencyName, 65.5f);
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
