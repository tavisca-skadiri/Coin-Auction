package com.tavicsa.coins;

public class Buyer {
    String name;

    public Buyer() {
    }

    public Buyer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Auction raiseBid(Auction auction, int newBidValue) {
        auction.setHighestBid(newBidValue);
        auction.getBidHistory().put(this.name,newBidValue);
        return auction;
    }
}
