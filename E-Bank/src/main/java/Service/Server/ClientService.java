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
                    System.out.println(clientText);
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
    }

    private void sendFile() {
        try {
            File myFile = new File("src/main/java/Service/Server/userManual.txt");
            byte[] myBytearray = new byte[(int) myFile.length()];
            if(!myFile.exists()) {
                System.out.println("File does not exist..");
                return;
            }

            FileInputStream fis = new FileInputStream(myFile);
            BufferedInputStream bis = new BufferedInputStream(fis);

            DataInputStream dis = new DataInputStream(bis);
            dis.readFully(myBytearray, 0, myBytearray.length);


            OutputStream os = clientSocket.getOutputStream();  //handle file send over socket

            DataOutputStream dos = new DataOutputStream(os); //Sending file name and file size to the server
            dos.writeUTF(myFile.getName());
            dos.writeLong(myBytearray.length);
            dos.write(myBytearray, 0, myBytearray.length);
            dos.flush();
            System.out.println("File "+" sent to client.");
        } catch (Exception e) {
            System.err.println("File does not exist!");
        }
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
