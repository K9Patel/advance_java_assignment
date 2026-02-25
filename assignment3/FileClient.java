import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) {
        try {
            // Step 1: Connect to server
            Socket s = new Socket("localhost", 5000);
            System.out.println("Connected to server");

            // Step 2: Receive file
            DataInputStream dis =
                    new DataInputStream(s.getInputStream());

            FileOutputStream fos =
                    new FileOutputStream("received.txt");

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = dis.read(buffer)) > 0) {
                fos.write(buffer, 0, bytesRead);
            }

            System.out.println("File received successfully");

            // Step 3: Close resources
            fos.close();
            dis.close();
            s.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//FileClient.java