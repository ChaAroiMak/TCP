package server;

import java.io.*;
import java.net.Socket;

public class TCPServer {
    public static int port = TCPClient.port;

    public TCPServer(int port) {
        this.port= port;
    }

    public static void main (String [] args) throws IOException, InterruptedException {
        java.net.ServerSocket serverSocket = new java.net.ServerSocket(port);
        java.net.Socket client = warten (serverSocket);
        String message = readMessage(client);
        System.out.println(message);
        writeMessage(client, message);

    }

    static java.net.Socket warten(java.net.ServerSocket serverSocket) throws IOException {
        java.net.Socket socket = serverSocket.accept(); //blockiert bis sich ein Client anmeldet
        return socket;
    }

    static String readMessage(Socket socket) throws IOException {
        try {
            socket.getInputStream().read();
            System.out.println("read message");
        }
        catch (IOException ex) {
            System.err.println("mission failed");
        }
        return null;
    }

    static void writeMessage(Socket socket, String message) throws IOException, InterruptedException {
         try {
             OutputStream os = socket.getOutputStream();
             os.write(":)".getBytes()); //Zurücksenden eines Strings
             System.out.println("write message");
             Thread.sleep(5000);
         }
         catch (IOException ex) {
             System.err.println("mission failed");
         }
         catch (InterruptedException iex) {
             System.err.println("mission failed");
         }

        }


}
