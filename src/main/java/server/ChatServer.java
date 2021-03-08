package server;


import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class ChatServer {

    private List<Client> clients = new ArrayList<>(); // or Set<> or Map<> ?

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

    private void online() {
        // Create string
        String clientNames;
        for (Client client : clients)
            clientNames += client.name + ",";
        clientNames = clientNames.replaceAll(", $", "");

        // Send string to all clients
        for (Client client : clients)
            message(Client.address, clientNames);
    }

    private void message(String sender, String message) {

    }


}
