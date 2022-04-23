package Service.Client;

import javafx.event.ActionEvent;

import java.io.*;
import java.net.Socket;

public class Client {
    Socket socket;
    DataInputStream inputStream;
    DataOutputStream outputStream;
    BufferedReader br;

    public void clientRun() throws IOException {
        try {
            socket = new Socket("localhost", 5555);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String message;
        while(true){
            message = br.readLine();
            if(message.equalsIgnoreCase("stop")){
                closeConnection();
            }
            outputStream.writeUTF(message);
            System.out.println("Client writes:" + message);
            String serverMessage = inputStream.readUTF();
            System.out.println("Server writes:" + serverMessage);
        }
    }

    private void closeConnection(){
        try {
            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(ActionEvent event) {
    }
}
