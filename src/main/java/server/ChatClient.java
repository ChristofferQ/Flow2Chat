package server;

import java.io.*;
import java.net.Socket;

public class ChatClient extends Thread {

    private final Socket clientSocket;

    public ChatClient(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            handleClientSocket();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void handleClientSocket() throws IOException, InterruptedException {
        InputStream inputStream = clientSocket.getInputStream();
        OutputStream outputStream = clientSocket.getOutputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ( (line = reader.readLine()) != null) {
            if ("exit".equalsIgnoreCase(line)) {
                break;
            }
            String msg = "Message from client: " + line + "\n";
            outputStream.write(msg.getBytes());
        }
        clientSocket.close();
        System.out.println("Closing connection from " + clientSocket);
    }
}
