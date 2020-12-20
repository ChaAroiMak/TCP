package server;

import java.io.*;
import java.net.Socket;

public class start {
    public static void main (String [] args) {
       TCPServer neu = new TCPServer(3333);
        Socket socket = new Socket();
        InputStream is = null;
        OutputStream os = null;
        String text = "Hallo Welt";

        try {
            is = socket.getInputStream();
        }
        catch (IOException ex) {
            System.err.println("error");
            System.exit(0);
        }

        byte[] readBuffer = new byte[1];
        try{
            is.read(readBuffer);
        }
        catch (IOException ex) {
            System.err.println("Fehler");
            System.exit(0);
        }

        byte[] textToByte = text.getBytes();
        try {
            os = socket.getOutputStream();
            os.write(textToByte);
        }
        catch (IOException ex) {
            System.err.println("Fehler");
            System.exit(0);
        }




    }
}
