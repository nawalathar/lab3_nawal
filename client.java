package seecs.edu;


import java.io.*;
import java.io.ByteArrayOutputStream;
import java.net.*;
//import java.util.Scanner;
class client {

    public static  String serverIP = "127.0.0.1";
    private static  int serverPort = 55555;
    private static  String fileOutput = "E:\\test1.txt";

    public static  void main(String args[]) {
        byte[] aByte = new byte[1];
        int bytesRead;

        Socket clientSocket = null;
        InputStream inStream = null;

        try {
            clientSocket = new Socket(serverIP , serverPort );
            inStream = clientSocket.getInputStream();
        } catch (IOException ex) {
     
        }
        
       
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        if (inStream != null) {

            FileOutputStream fileOutStream = null;
            BufferedOutputStream buff = null;
            try {
                fileOutStream = new FileOutputStream( fileOutput );
                buff = new BufferedOutputStream(fileOutStream);
                bytesRead = inStream.read(aByte, 0, aByte.length);
               
                do {
                        baos.write(aByte);
                        bytesRead = inStream.read(aByte);
                } while (bytesRead != -1);

                buff.write(baos.toByteArray());
                buff.flush();
                buff.close();
                System.out.println("File Successfully transfered");
                clientSocket.close();
            } catch (IOException ex) {
                
            }
        }
    }
}
