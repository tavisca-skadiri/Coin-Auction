package com.tavicsa.coins.seller;

import com.tavicsa.coins.Coin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Seller2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        List<Coin> coins = new ArrayList<>();
        coins.add(new Coin("USA", 125, 2010, 125, "12/12/2011"));
        coins.add(new Coin("India", 75, 2012, 75, "12/12/2012"));
        coins.add(new Coin("USA", 100, 2010, 100, "12/12/2010"));
        Seller seller = new Seller("Shivani", coins);

        while (true) {
            System.out.println("\n\n***************************************************************");
            System.out.println("Welcome Shivani");
            System.out.println("Select Action\n| 1. Mark Coin for Auction\n| 2. Track Auction Progress\n| 3. Close Auction");
            int choice1 = sc.nextInt();
            switch (choice1) {
                case 1: {
                    System.out.println("Select Coin to Mark for Auction :");
                    seller.getCoinCollection().forEach(System.out::print);
                    Coin offering = seller.getCoinCollection().get(sc.nextInt() - 1);
                    auctionBoardActions(seller, offering, "AddAuction");
                    break;
                }
                case 2: {
                    System.out.println("Select Marked Coin to Track :");
                    seller.getCoinCollection().forEach(System.out::print);
                    Coin offering = seller.getCoinCollection().get(sc.nextInt() - 1);
                    auctionBoardActions(seller, offering, "TrackAuction");
                    break;
                }
                case 3: {
                    System.out.println("Select Marked Coin to Close It's Auction :");
                    seller.getCoinCollection().forEach(System.out::print);
                    Coin offering = seller.getCoinCollection().get(sc.nextInt() - 1);
                    auctionBoardActions(seller, offering, "ShowHighestBid");

                    System.out.println("Do you want to select the highest bid\n| 1. Yes\n| 2. No");
                    int choice3 = sc.nextInt();
                    if (choice3 == 1)
                        auctionBoardActions(seller, offering, "CloseAuction");
                    break;
                }
            }
        }
    }

    private static void auctionBoardActions(Seller seller, Coin offering, String action) throws IOException {
        Socket socket = new Socket("localhost", 1600);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF(action);
        dos.writeUTF(seller.getName());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(offering);

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        String response = dis.readUTF();
        System.out.println(response);
        dis.close();

        oos.close();
        dos.close();
        socket.close();
    }
}