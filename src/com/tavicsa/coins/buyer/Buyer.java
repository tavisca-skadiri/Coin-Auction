package com.tavicsa.coins.buyer;

public class Buyer {
    String name;

    public Buyer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "name='" + name + '\'' +
                '}';
    }
}
