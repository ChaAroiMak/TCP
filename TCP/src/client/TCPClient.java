package client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TCPClient {
    private static final String IRGENDWAS = "irgendwas";
    private final String hostname;
    private final int port;

    public static final String HOST = "localhost";
    public static final int PORTNUMBER = 7777;


    public static void main(String []args) throws IOException {
        if(args.length < 2) { //wenn Länge der Argumente kleiner als 2 ist fehlt Host/Port
            System.out.println("missing parameters: hostname, portnumber");
        }

        String hostname = args[0];
        String portString = args[1];

        int portnumber = Integer.parseInt(portString); //String von Port zu Int umwandeln

        //TCPClient tcpClient = new TCPClient(HOST, PORTNUMBER);
        TCPClient tcpClient = new TCPClient(hostname, portnumber);

        tcpClient.doSomething();
    }

    TCPClient(String hostname, int port) {//repräsentiert Verbinung zum Server
        this.hostname = hostname;
        this.port = port;
    }

    private void doSomething() throws IOException {
        Socket socket = new Socket(this.hostname, this.port);
        socket.getOutputStream().write(IRGENDWAS.getBytes()); //schicken bytes rüber

        InputStream is = socket.getInputStream();

        //byte[] buffer = new byte[10000]; //Byte array zum daten sammeln. Problem: weiß nicht wie groß es sein muss
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); //Klasse die ein Outputstream ist. Daten werden in ein ByteArray rein geschrieben
        int i = 0;

        int read = 0;

        do {
            read = is.read(); //lesen. bei -1: Server schickt nichts mehr
            if(read != -1) {
                byte readByte = (byte) read;
                // buffer[i] = readByte;
                baos.write(read);
            }
        } while(read != -1);

       /* byte[] receivedBytes = new byte[i];
        for(int j = 0; j<i; j++) {
            receivedBytes[j] = buffer[j]; //richtige Anzahl von Bytes bekommen, ohne Überlänge
        }

        String receivedString = new String(receivedBytes);
        */
        String receivedString = new String(baos.toByteArray());

        System.out.println("received: "+receivedString);
    }
}
