package com.tavicsa.coins.board;

import com.tavicsa.coins.Coin;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AuctionBoard {
    public static List<Auction> auctionList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Auction Board is live . . . . .");
        new Thread(() -> {
            try {
                createSocketForBuyer();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                createSocketForSeller();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void createSocketForSeller() throws IOException, ClassNotFoundException {
        ServerSocket serverSocketForSeller = new ServerSocket(1600);
        while (true) {
            Socket s = serverSocketForSeller.accept();
            DataInputStream dis = new DataInputStream(s.getInputStream());
            String commandName = dis.readUTF();
            String sellerName = dis.readUTF();
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            Coin offering = (Coin) ois.readObject();
            switch (commandName) {
                case "AddAuction":
                    sendResponse(s, addAuction(sellerName, offering, 2 * 60 * 1000));
                    break;
                case "TrackAuction":
                    sendResponse(s, trackAuction(sellerName, offering));
                    break;
                case "ShowHighestBid":
                    sendResponse(s, showHighestBid(sellerName, offering));
                    break;
                case "CloseAuction":
                    sendResponse(s, closeAuction(sellerName, offering));
                    break;
            }
            ois.close();
            dis.close();
            s.close();
        }
    }

    private static void sendResponse(Socket s, String response) throws IOException {
        DataOutputStream dos1 = new DataOutputStream(s.getOutputStream());
        dos1.writeUTF(response);
        dos1.close();
    }

    private static String addAuction(String sellerName, Coin offering, int deadline) {
        Auction auction = new Auction(sellerName, offering, deadline);
        auctionList.add(auction);
        String response = "Auction added successfully";
        System.out.println(response);
        return response;
    }

    private static String trackAuction(String sellerName, Coin offering) {
        Auction auctionToTrack = getAuctionByOfferingAndSeller(sellerName, offering);
        String response = "Bid History: " + auctionToTrack.getBidHistory().toString();
        System.out.println(response);
        return response;
    }

    private static String showHighestBid(String sellerName, Coin offering) {
        Auction auctionToTrack = getAuctionByOfferingAndSeller(sellerName, offering);
        String response = "Highest Bid is : " + auctionToTrack.getHighestBid();
        System.out.println(response);
        return response;
    }

    public static String closeAuction(String sellerName, Coin offering) {
        Auction auctionToTrack = getAuctionByOfferingAndSeller(sellerName, offering);
        auctionList.remove(auctionToTrack);
        System.out.println(auctionList);
        String response = "Auction Closed | Bid selected : " + auctionToTrack.getHighestBid();
        System.out.println(response);
        return response;
    }

    private static Auction getAuctionByOfferingAndSeller(String sellerName, Coin offering) {
        return auctionList.stream()
                .filter(auction ->
                        auction.getSellerName().equalsIgnoreCase(sellerName)
                                && auction.getOffering().toString().equals(offering.toString()))
                .findAny()
                .get();
    }


    private static void createSocketForBuyer() throws IOException, ClassNotFoundException {
        ServerSocket serverSocketForBuyer = new ServerSocket(1500);
        while (true) {
            Socket s = serverSocketForBuyer.accept();
            DataInputStream dis = new DataInputStream(s.getInputStream());
            String commandName = dis.readUTF();
            switch (commandName) {
                case "LiveAuctions":
                    sendResponse(s, getAllLiveSellers());
                    break;
                case "SellerOfferings":
                    String sellerName = dis.readUTF();
                    List<Coin> offerings = getAuctionsBySeller(sellerName);
                    ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                    oos.writeObject(offerings);
                    oos.close();
                    break;
                case "ShowHighestBid":
                    String sellerName1 = dis.readUTF();
                    ObjectInputStream ois1 = new ObjectInputStream(s.getInputStream());
                    Coin offering = (Coin) ois1.readObject();
                    sendResponse(s, showHighestBid(sellerName1, offering));
                    ois1.close();
                    break;
                case "RaiseBid":
                    String sellerName2 = dis.readUTF();
                    String buyerName = dis.readUTF();
                    int newBidValue = Integer.parseInt(dis.readUTF());
                    String coin = dis.readUTF();
                    sendResponse(s, raiseBid(sellerName2, coin, buyerName, newBidValue));
                    break;
            }
            dis.close();
            s.close();
        }
    }

    private static String getAllLiveSellers() {
        String s = auctionList.stream()
                .map(Auction::getSellerName)
                .distinct()
                .collect(Collectors.toList())
                .toString();
        return s.substring(1, s.length() - 1);
    }

    private static List<Coin> getAuctionsBySeller(String sellerName) {
        return auctionList.stream()
                .filter(auction -> auction.getSellerName().equalsIgnoreCase(sellerName))
                .map(Auction::getOffering)
                .collect(Collectors.toList());
    }

    private static String raiseBid(String sellerName, String offering, String buyerName, int newHigherBid) {
        Auction auction = auctionList.stream()
                .filter(a ->
                        a.getSellerName().equalsIgnoreCase(sellerName)
                                && a.getOffering().toString().equals(offering))
                .findAny()
                .get();
        String response = "";
        synchronized (auction){
            if (auction.getHighestBid() < newHigherBid) {
                auction.setHighestBid(newHigherBid);
                auction.getBidHistory().put(buyerName, newHigherBid);
                response = "| Bid Raised to " + auction.getHighestBid();
            } else
                response = "| Invalid Bid Amount";
            System.out.println(response);
        }
        return response;
    }
}