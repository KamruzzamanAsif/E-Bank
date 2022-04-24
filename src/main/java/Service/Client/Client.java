package Service.Client;

import GUI.User;

import java.io.*;
import java.net.Socket;

public class Client {
    Socket socket;
    DataInputStream inputStream;
    DataOutputStream outputStream;
    BufferedReader br;

    String clientName;

    public void clientRun(String userName) throws IOException {
        clientName = userName;
        System.out.println("Joined with Server.");
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
            if(message.equalsIgnoreCase("help")){
                outputStream.writeUTF(message);
                downloadFile();
            }
            else{
                outputStream.writeUTF(clientName + " writes: " + message);
                String serverMessage = inputStream.readUTF();
                System.out.println("Server writes:" + serverMessage);
            }
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

    public void downloadFile() {
        try {
            int bytesRead;
            InputStream in = socket.getInputStream();

            DataInputStream clientData = new DataInputStream(in);

            String fileName = clientData.readUTF();
            OutputStream output = new FileOutputStream("src/main/java/Service/Client/userManual.txt");
            long size = clientData.readLong();
            byte[] buffer = new byte[1024];
            while (size > 0 && (bytesRead = clientData.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                output.write(buffer, 0, bytesRead);
                size -= bytesRead;
            }
            System.out.println("File "+fileName+" received from Server.");
        } catch (IOException ex) {
            System.out.println("Exception: "+ex);
        }
    }
}
