package org.example;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPchatServer {
    public static void main(String[] args) {
        // Port used by the server for the connection
        int port = 3000;

        ServerSocket welcomeSocket;   // Socket where the server listens
        Socket connection;            // Socket for the connection with the client
        BufferedReader in;            // Buffer to read incoming messages from the client
        PrintWriter out;
        Scanner scanner;

        try {
            // Create the server socket to accept connections on the specified port
            welcomeSocket = new ServerSocket(port);
            System.out.println("Server listening on port " + port + "\n");

            // The server accepts the client connection
            connection = welcomeSocket.accept();

            // Notify that the client is connected, showing the client’s IP address
            System.out.println("Client connected: " + connection.getInetAddress());

            while (true) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                // Reads a line of text sent by the client
                String message = in.readLine();

                System.out.println("Client: " + message);

                out = new PrintWriter(connection.getOutputStream(), true);

                System.out.print("Server: ");
                scanner = new Scanner(System.in); // Reads server user input
                String response = scanner.nextLine(); // Reads the server's response

                out.println(response);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}