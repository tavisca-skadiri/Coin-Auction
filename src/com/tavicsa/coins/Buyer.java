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
//
//    public Auction raiseBid() {
////        auction.setHighestBid(newBidValue);
////        auction.getBidHistory().put(this.name,newBidValue);
////        return auction;
//    }
}
