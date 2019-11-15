package com.tavicsa.coins;

import java.util.HashMap;

public class Auction implements Runnable{
    private Coin offering;
    private int highestBid;
    private HashMap<String,Integer> bidHistory;

    public Auction(Coin offering, int highestBid) {
        this.offering = offering;
        this.highestBid = highestBid;
        this.bidHistory = new HashMap<>();
    }

    @Override
    public void run() {

    }

    public int getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(int highestBid) {
        this.highestBid = highestBid;
    }

    public HashMap<String, Integer> getBidHistory() {
        return bidHistory;
    }

    public void setBidHistory(HashMap<String, Integer> bidHistory) {
        this.bidHistory = bidHistory;
    }

    public Coin getOffering() {
        return offering;
    }

    public void setOffering(Coin offering) {
        this.offering = offering;
    }

    @Override
    public String toString() {
        return "Auction{" +
                "offering=" + offering +
                ", highestBid=" + highestBid +
                ", bidHistory=" + bidHistory +
                '}';
    }
}
