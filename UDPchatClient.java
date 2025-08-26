package org.example;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPchatClient {
    public static void main(String[] args) {
        //Port on which the server is listening
        int port = 3000;

        InetAddress serverAddress;
        DatagramSocket dSocket;      //Used to send and receive packets
        DatagramPacket inPacket;     //Datagram to receive data
        DatagramPacket outPacket;    //Datagram to send data
        Scanner scanner;

        try {
            //Initialize the server’s address
            serverAddress = InetAddress.getByName(" "); // insert server adress

            //Create a DatagramSocket to send and receive packets
            dSocket = new DatagramSocket();

            //Continuous loop to send and receive messages
            while (true) {
                //Get the message from client input
                System.out.print("Client: ");
                scanner = new Scanner(System.in);
                String message = scanner.nextLine();

                byte[] sendData = message.getBytes();

                //Create the DatagramPacket to send the message to the server
                outPacket = new DatagramPacket(sendData, sendData.length, serverAddress, port);

                //Send the packet to the server
                dSocket.send(outPacket);

                byte[] receiveData = new byte[3000];

                //Create the Datagram to receive the server’s response
                inPacket = new DatagramPacket(receiveData, receiveData.length);

                //Receive the response packet from the server
                dSocket.receive(inPacket);

                String response = new String(inPacket.getData(), 0, inPacket.getLength());

                System.out.println("Server: " + response);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}