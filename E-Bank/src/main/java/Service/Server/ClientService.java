package Service.Server;

import java.io.*;
import java.net.Socket;

public class ClientService implements Runnable{

    private final Socket clientSocket;
    DataInputStream inputStream = null;
    DataOutputStream outputStream = null;

    public ClientService(Socket clientSocket){
        this.clientSocket = clientSocket;
    }


    @Override
    public void run() {
        try {
            inputStream = new DataInputStream(clientSocket.getInputStream());
            outputStream = new DataOutputStream(clientSocket.getOutputStream());
            String clientText;
            while (true) {
                clientText = inputStream.readUTF();
                if(clientText.equalsIgnoreCase("help")){
                    sendFile();
                }
                else if(clientText.equalsIgnoreCase("stop")){
                    closeConnection();
                }
                else{
                    // chat here
                    System.out.println("Client writes:" + clientText);
                    sendMessage();
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void sendMessage() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String message = in.readLine();
        outputStream.writeUTF(message);
        outputStream.flush();
        System.out.println("Server writes:" +message);
    }

    private void sendFile(){

    }
    private void closeConnection(){
        try {
            outputStream.close();
            inputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
