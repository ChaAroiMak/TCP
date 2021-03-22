package server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private final int port;
    public static final int PORTNUMBER = 7777;

    public static void main (String [] args) throws IOException, InterruptedException {
        TCPServer tcpServer = new TCPServer(PORTNUMBER);
        tcpServer.doSomething();

    }

    TCPServer (int port) { //Konstruktor
        this.port = port;  //mit Port an dem er lauschen kann
    }

    private void doSomething() throws IOException, InterruptedException {
        ServerSocket srvSocket = new ServerSocket(this.port); //Serverport anlegen
        System.out.println("server socket created");

        //Port wird aufgemacht der von außen sichtbar ist, Aufruf blockiert und kommt erst zurück wenn sich ein Client mit Server verbunden hat
        Socket socket = srvSocket.accept(); //wenn wir accept mehrfach aufrufen kann sich der Server mit mehreren Clients verbinden
        System.out.println("client connection accepted");

        socket.getInputStream().read(); //Input Stream aus Socket holen und darauf lesen
        System.out.println("read something");


        OutputStream os = socket.getOutputStream(); //Output stream zwischenspeichern
        os.write(":)".getBytes()); //in OutputStream kann man Bytes reinschreiben, kann so daraus Byte Array machen
        System.out.println("write something");

        Thread.sleep(5000); //legt Prozess kurz schlafen
        System.out.println("woke up");


        os.close(); //Output Stream schließen

    }
}
