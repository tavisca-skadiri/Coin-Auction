package com.tavicsa.coins;

import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Buyer buyer = new Buyer("Shivam");
        Buyer buyer2 = new Buyer("Vikas");
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Are you a Buyer or Seller?\n| 1. Buyer\n| 2. Seller\n| 3. Exit");
            choice = sc.nextInt();
            if (choice == 1) {
                System.out.println("Select Seller\n| 1. Saqlain\n| 2. Sushovan\n| 3. Exit");
                int choice1 = sc.nextInt();
                Seller seller = selectSeller(choice1);
                System.out.println("These are the Offerings of " + seller.getName());
                seller.getCoinCollection().getCoins().forEach(System.out::print);
                int choice2 = sc.nextInt();
//                Coin offering = seller.getCoinCollection().getCoins().get(choice2);
//                System.out.println("Current highest bid of this offering: " + offering.);
//                System.out.println("Do you want to bid");
//                int choice3 = sc.nextInt();
            } else if (choice == 2) {
                sellerActions(sc);
            }

        } while (choice != 3);
        //Auction auction = new Auction(offering,offering.getCurrentValue());
//        int newBidValue = 150;
//        auction = buyer.raiseBid(auction,newBidValue);
//
//        System.out.println(auction);


//        System.out.println(coinCollection.listByCountry("India"));
//        System.out.println(coinCollection.listByCurrentvalue(100));
//        System.out.println(coinCollection.listByYear(2012));
//        System.out.println(coinCollection.searchByCountryAndAcquiredDate("India", new Date(Calendar.DATE)));
//        System.out.println(coinCollection.searchByCountryAndDenomination("India", 100));
//        System.out.println(coinCollection.searchByCountryAndDenominationAndYearOfMinting("USA", 100, 2010));
//        System.out.println(coinCollection.searchByCountryAndYearOfMinting("India", 2014));
    }

    private static void sellerActions(Scanner sc) {
        System.out.println("Select Seller\n| 1. Saqlain\n| 2. Sushovan\n| 3. Exit");
        Seller seller = selectSeller(sc.nextInt());
        System.out.println("These are the Offerings of " + seller.getName());
        seller.getCoinCollection().getCoins().forEach(System.out::print);
        int choice2 = sc.nextInt();
        Coin offering = seller.getCoinCollection().getCoins().get(choice2 - 1);
        System.out.println("Select Action\n| 1. Mark Coin for Auction\n| 2. Track Auction Progress\n| 3. Close Auction");
        int choice1 = sc.nextInt();
        if (choice1 == 1) {
            Auction auction = new Auction(offering, offering.getCurrentValue());
            Thread auctionThread = new Thread(auction, seller.getName() + "|" + offering);
            auctionThread.start();
            Thread.getAllStackTraces().keySet().forEach(System.out::println);
            System.out.println("Auction Started");
        } else if (choice1 == 2) {

        } else if (choice1 == 3) {
            Set<Thread> setOfThread = Thread.getAllStackTraces().keySet();
            for (Thread thread : setOfThread) {
                if (thread.getName().equals(seller.getName() + "|" + offering)) {
                    System.out.println(thread);
                    thread.stop();
                    System.out.println("Auction Closed");
                }
            }
            setOfThread.forEach(System.out::println);
        }
    }

    private static Seller selectSeller(int choice) {
        if (choice == 1) {
            CoinOperations coinCollection = new CoinOperations();
            coinCollection.add(new Coin("India", 100, 2014, 100, "12/12/2010"));
            coinCollection.add(new Coin("USA", 125, 2010, 125, "12/12/2011"));
            coinCollection.add(new Coin("India", 75, 2012, 75, "12/12/2012"));
            return new Seller("Saqlain", coinCollection);
        } else {
            CoinOperations coinCollection = new CoinOperations();
            coinCollection.add(new Coin("USA", 125, 2010, 125, "12/12/2011"));
            coinCollection.add(new Coin("India", 75, 2012, 75, "12/12/2012"));
            coinCollection.add(new Coin("USA", 100, 2010, 100, "12/12/2010"));
            return new Seller("Sushovan", coinCollection);
        }
    }
}
