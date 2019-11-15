package com.tavicsa.coins;

public class Seller {
    private String name;
    private CoinCollection coinCollection;

    public Seller() {
    }

    public Seller(String name, CoinCollection coinCollection) {
        this.name = name;
        this.coinCollection = coinCollection;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "name='" + name + '\'' +
                ", coinCollection=" + coinCollection +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoinCollection getCoinCollection() {
        return coinCollection;
    }

    public void setCoinCollection(CoinCollection coinCollection) {
        this.coinCollection = coinCollection;
    }
}
