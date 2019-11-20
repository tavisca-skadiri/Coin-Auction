package com.tavicsa.coins.board;

import com.tavicsa.coins.Coin;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class Auction extends TimerTask {
    private String auctionId;
    private String sellerName;
    private Coin offering;
    private int highestBid;
    private HashMap<String, Integer> bidHistory;
    private Timer timer;

    public Auction(String sellerName, Coin offering, int deadline) {
        auctionId = UUID.randomUUID().toString();
        this.sellerName = sellerName;
        this.offering = offering;
        this.highestBid = offering.getCurrentValue();
        this.bidHistory = new HashMap<>();
        this.timer = new Timer();
        timer.schedule(this, deadline);
    }

    @Override
    public void run() {
        AuctionBoard.closeAuction(sellerName, offering);
        timer.cancel();
    }

    @Override
    public String toString() {
        return "| SellerName='" + sellerName + '\'' +
                " | Offering=" + offering
                ;
    }

    public String getSeller() {
        return sellerName;
    }

    public void setSeller(String seller) {
        this.sellerName = seller;
    }

    public Coin getOffering() {
        return offering;
    }

    public void setOffering(Coin offering) {
        this.offering = offering;
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

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }
}