package hierarchy;

import hierarchy.valut.Currecncy;

import java.util.List;

public class Market {
    private String name;
    private List<Currecncy> currencyList;
    private Emmitent emmitent;

    public Market(String name, Emmitent emmitent, List<Currecncy> currencyList) {
        this.name = name;
        this.emmitent = emmitent;
        this.currencyList = currencyList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Emmitent getEmmitent() {
        return emmitent;
    }

    public void setEmmitent(Emmitent emmitent) {
        this.emmitent = emmitent;
    }

    public List<Currecncy> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Currecncy> currencyList) {
        this.currencyList = currencyList;
    }
}
