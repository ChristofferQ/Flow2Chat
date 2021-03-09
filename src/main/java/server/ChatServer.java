package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.sun.xml.internal.bind.v2.TODO;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatServer {
    private boolean commandInput(String msg, PrintWriter pw) {
        String[] parts = msg.split("#");
        if (parts.length == 1) {
            if (parts[0].equals("CLOSE")) {
                pw.println("CLOSE#");
            }
            else {
                pw.println("Unknown request - must obey protocol");
            }
            return false;
        } else if (parts.length == 2) {
            String token = parts[0];
            String argument = parts[1];  //Værdi efter #-tegnet
            switch (token) {
                case "CONNECT":
                    break;
                case "SEND":
                    break;
                default:
                    pw.println("Invalid input - closing connection...");
                    return false;
            }
        }
        return true;
    }

    //Lav client handler med socket og kald socket.close()

    private List<Client> clients = new ArrayList<>(); // or Set<> or Map<> ?

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

    private void online() {
        // Create string
        String message = "ONLINE#";
        for (Client client : clients)
            message += client.name + ",";
        message = message.replaceAll(", $", "");

        // Send string to all clients
        for (Client client : clients)
            message(Client.address, message);
    }

    private void message(String sender, String message) {

    }
}
