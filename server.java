package seecs.edu;

import java.io.*;
import java.net.*;

class server {

    private static String fileToSend = "E:\\Malghanitest.txt";

    public  static void main(String args[]) {

        while (true) {
            ServerSocket welcomeSocket = null;
            Socket connectionSocket = null;
            BufferedOutputStream sendToClient = null;

            try {
                welcomeSocket = new ServerSocket(55555);
                connectionSocket = welcomeSocket.accept();
               sendToClient = new BufferedOutputStream(connectionSocket.getOutputStream());
            } catch (IOException ex) {
                // Do exception handling
            }

            if (sendToClient != null) {
                File mf = new File( fileToSend );
                byte[] mybytearray = new byte[(int) mf.length()];

                FileInputStream FileInStream = null;

                try {
                    FileInStream = new FileInputStream(mf);
                } catch (FileNotFoundException ex) {
                    // Do exception handling
                }
                BufferedInputStream bis = new BufferedInputStream(FileInStream);

                try {
                    bis.read(mybytearray, 0, mybytearray.length);
                    System.out.println("File transferring....");
                    sendToClient.write(mybytearray, 0, mybytearray.length);
                    sendToClient.flush();
                    sendToClient.close();
                    connectionSocket.close();

                    // File sent, exit the main method
                    return;
                } catch (IOException ex) {
                    // Do exception handling
                }
            }
        }
    }
}
