package server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMusterloesung {
    private int port = TCPClient.port; //mein Port
    public static final int portnumber = TCPClient.portnumber; //Anlegen der Portnummer

    public static void main (String [] args) throws IOException, InterruptedException { //main zum Klasse starten
        ServerMusterloesung tcpServer = new ServerMusterloesung(portnumber);

        tcpServer.doSomething();

    }

    ServerMusterloesung(int port) {
        this.port = port;
    }

    void doSomething() throws IOException, InterruptedException {
        ServerSocket srvSocket = new ServerSocket(this.port); //Erzeugen eines neuen Sockets mit Port
        System.out.println("server socket created");
        Socket socket = srvSocket.accept();
        System.out.println("client connection accepted");

        socket.getInputStream().read();
        System.out.println("read something");


        OutputStream os = socket.getOutputStream();
        os.write(":)".getBytes()); //Zurücksenden eines Strings
        System.out.println("write something");


        Thread.sleep(5000);
        System.out.println("woke up");


        os.close(); //Output Stream schließen
    }
}
