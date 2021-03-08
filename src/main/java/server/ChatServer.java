package server;


import com.sun.xml.internal.bind.v2.TODO;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
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


    //Call server with arguments like this: 0.0.0.0 8088 logfile.log
    public static void main(String[] args) throws UnknownHostException {
        String ip ="localhost";
        int port = 8088;
        String logFile = "log.txt";  //Do we need this

        try {
            if (args.length == 3) {
                ip = args[0];
                port = Integer.parseInt(args[1]);
                logFile = args[2];
            }
            else {
                throw new IllegalArgumentException("Server not provided with the right arguments");
            }
        } catch (NumberFormatException ne) {
            System.out.println("Illegal inputs provided when starting the server!");
            return;
        }

    }


}
