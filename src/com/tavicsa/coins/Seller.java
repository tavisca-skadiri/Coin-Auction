package com.tavicsa.coins;

import java.util.List;

public class Seller {
    private String name;
    private List<Coin> coinCollection;

    public Seller() {
    }

    public Seller(String name, List<Coin> coinCollection) {
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

    public List<Coin> getCoinCollection() {
        return coinCollection;
    }

    public void setCoinCollection(List<Coin> coinCollection) {
        this.coinCollection = coinCollection;
    }
}
