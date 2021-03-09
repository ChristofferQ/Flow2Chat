package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import com.sun.xml.internal.bind.v2.TODO;
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

    //Call server with arguments like this: 8088
    public static void main(String[] args) throws UnknownHostException {
        int port = 8088;

        try {
            if (args.length == 1) {
                port = Integer.parseInt(args[0]);
            } else {
                throw new IllegalArgumentException("Server not provided with the right arguments");
            }
        } catch (NumberFormatException ne) {
            System.out.println("Illegal inputs provided when starting the server!");
            return;
        }
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

    ConcurrentHashMap<String,Boolean> List = new ConcurrentHashMap<>();

    private void connect(String name){
        List.put(name,true);
        online();
    }

    public static void printMap(Map mp) {
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }


    private void online() {
        for (ChatClient client : clients)
            message += client.name + ",";
        message = message.replaceAll(", $", "");

        // Send string to all clients
        for (ChatClient client : clients)
            message(Client.address, message);
    }

    private void message(String sender, String message) {

    }
}
