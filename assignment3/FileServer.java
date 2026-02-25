import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        try {
            // Step 1: Create ServerSocket
            ServerSocket ss = new ServerSocket(5000);
            System.out.println("Server waiting for client...");

            // Step 2: Accept client connection
            Socket s = ss.accept();
            System.out.println("Client connected");

            // Step 3: Read file
            File file = new File("send.txt");   // file to send
            FileInputStream fis = new FileInputStream(file);

            // Step 4: Send file data
            DataOutputStream dos =
                    new DataOutputStream(s.getOutputStream());

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) > 0) {
                dos.write(buffer, 0, bytesRead);
            }

            System.out.println("File sent successfully");

            // Step 5: Close resources
            fis.close();
            dos.close();
            s.close();
            ss.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
