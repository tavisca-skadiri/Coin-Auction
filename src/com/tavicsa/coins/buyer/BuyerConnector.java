package com.tavicsa.coins.buyer;

import com.tavicsa.coins.Coin;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class BuyerConnector {
    void raiseBid(String sellerName, Coin offering, String buyerName, int newBidValue, String action) throws IOException {
        Socket socket = new Socket("localhost", 1500);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF(action);
        dos.writeUTF(sellerName);
        dos.writeUTF(buyerName);
        dos.writeUTF(String.valueOf(newBidValue));
        dos.writeUTF(offering.toString());

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        String response = dis.readUTF();
        System.out.println(response);
        dis.close();
        dos.close();

        socket.close();
    }

    void getHighestBid(String sellerName, Coin offering, String action) throws IOException {
        Socket socket = new Socket("localhost", 1500);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF(action);
        dos.writeUTF(sellerName);
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


    List<Coin> getOfferingsBySeller(String sellerName) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 1500);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF("SellerOfferings");
        dos.writeUTF(sellerName);

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        List<Coin> response = (List<Coin>) ois.readObject();
        System.out.println(response);
        ois.close();

        dos.close();
        socket.close();
        return response;
    }

    String getAllSellers() throws IOException {
        Socket socket = new Socket("localhost", 1500);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF("LiveAuctions");

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        String response = dis.readUTF();
        System.out.println(response);
        dis.close();

        dos.close();
        socket.close();
        return response;
    }

}
