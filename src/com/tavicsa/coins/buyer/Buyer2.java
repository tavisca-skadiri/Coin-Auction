package com.tavicsa.coins.buyer;

import com.tavicsa.coins.Coin;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Buyer2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BuyerConnector buyerConnector = new BuyerConnector();
        Scanner sc = new Scanner(System.in);
        Buyer buyer = new Buyer("Sushovan");
        while (true) {
            System.out.println("\n\n***************************************************************");
            System.out.println("Welcome Sushovan");
            System.out.println("Select Seller");
            String sellerInfo = buyerConnector.getAllSellers();
            String sellerName = sellerInfo.split(",")[sc.nextInt() - 1];

            List<Coin> offeringsBySeller = buyerConnector.getOfferingsBySeller(sellerName);
            Coin offering = offeringsBySeller.get(sc.nextInt() - 1);

            System.out.print("Do you want to raise bid on offering : " + offering + "| 1. Yes\n| 2. No\n");
            buyerConnector.getHighestBid(sellerName, offering, "ShowHighestBid");

            if (sc.nextInt() == 1) {
                System.out.println("Enter New Bid Amount :");
                int newBidValue = sc.nextInt();
                buyerConnector.raiseBid(sellerName, offering, buyer.name, newBidValue, "RaiseBid");
            }
        }
    }
}
