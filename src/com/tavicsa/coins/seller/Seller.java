package com.tavicsa.coins.seller;

import com.tavicsa.coins.Coin;

import java.util.List;

public class Seller {
    private String name;
    private List<Coin> coinCollection;

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

    public List<Coin> getCoinCollection() {
        return coinCollection;
    }

}
