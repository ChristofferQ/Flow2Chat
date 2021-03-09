package server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatServer {


    //Call server with arguments like this: 0.0.0.0 8088 logfile.log
    public static void main(String[] args) throws UnknownHostException {
        String ip = "localhost";
        int port = 8088;
//        String logFile = "log.txt";  //Do we need this
//
//        try {
//            if (args.length == 3) {
//                ip = args[0];
//                port = Integer.parseInt(args[1]);
//                logFile = args[2];
//            } else {
//                throw new IllegalArgumentException("Server not provided with the right arguments");
//            }
//        } catch (NumberFormatException ne) {
//            System.out.println("Illegal inputs provided when starting the server!");
//            return;
//        }
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                System.out.println("Checking client connection");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket);
                ChatClient client = new ChatClient(clientSocket);
                client.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}