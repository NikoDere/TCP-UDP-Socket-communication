package org.example;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPchatClient {
    public static void main(String[] args) {
        // Port on which the server is listening
        int port = 3000;

        Socket connection;          // Socket to connect to the server
        BufferedReader in;          // Buffer to read messages received from the server
        PrintWriter out;
        Scanner scanner;

        try {
            // Create the socket to connect to the server
            connection = new Socket(" ", port); //insert server adress
            System.out.println("Connected to the server.");
            System.out.println("Enter a message");

            while (true) {
                // Used to send messages to the server
                out = new PrintWriter(connection.getOutputStream(), true);

                // Read user input to send to the server
                System.out.print("Client: ");
                scanner = new Scanner(System.in); // Reads input from the client
                String message = scanner.nextLine(); // Reads the message typed by the client

                out.println(message);

                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                // Read the response sent by the server
                String response = in.readLine();

                System.out.println("Server: " + response);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}