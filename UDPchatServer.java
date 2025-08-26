package org.example;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPchatServer {
    public static void main(String[] args) {
        //Port on which the server listens
        int port = 3000;

        DatagramSocket dSocket;       //Socket for receiving and sending packets
        DatagramPacket inPacket;      //Datagram to receive data
        DatagramPacket outPacket;     //Datagram to send data
        Scanner scanner;

        try {
            //Create the socket to listen on the specified port
            dSocket = new DatagramSocket(port);
            System.out.println("Server listening on port " + port + "\n");

            while (true) {
                //Byte buffer to store incoming packet data
                byte[] receiveData = new byte[3000];

                //Create a Datagram to receive data from clients
                inPacket = new DatagramPacket(receiveData, receiveData.length);

                //Receive the packet from the client
                dSocket.receive(inPacket);

                //Extract the client’s address and port from the received packet
                InetAddress clientAddress = inPacket.getAddress();  //Client’s IP address
                int clientPort = inPacket.getPort();                //Client’s port

                //Convert the received data (bytes) into a readable string
                String message = new String(inPacket.getData(), 0, inPacket.getLength());

                //Show the client’s message in the server console
                System.out.println("Client " + clientAddress + ": " + message);

                //Get the server’s response from console input
                System.out.print("Server: ");
                scanner = new Scanner(System.in);
                String response = scanner.nextLine();

                byte[] sendData = response.getBytes();

                //Create a Datagram to send the response to the client
                outPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);

                dSocket.send(outPacket);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}