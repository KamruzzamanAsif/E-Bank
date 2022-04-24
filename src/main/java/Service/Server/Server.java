package Service.Server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket serverSocket;
    private static Socket clientSocket;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(5555);
            System.out.println("Server started.");
        } catch (Exception e) {
            System.err.println("Port already in use.");
            System.exit(1);
        }

        while (true) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Accepted connection : " + clientSocket);

                Thread t = new Thread(new ClientService(clientSocket));
                t.start();

            } catch (Exception e) {
                System.err.println("Error. Couldn't connect.");
            }
        }
    }

}
