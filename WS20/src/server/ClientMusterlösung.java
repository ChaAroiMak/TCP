package server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientMusterlösung {
    private final String hostname;
    private final int port;
    private static final String IRGENDWAS = "irgendwas";

    public static final String HOST = "localhost";
    public static final int PORT = 7777;


    public static void main (String[] args) throws IOException {
        if(args.length < 2) {
            System.out.println("mission parameters: hostname, portnumber");
        }

        String hostname = args[0];
        String portString = args[1];

       int portnumber= Integer.parseInt(portString);

        //ClientMusterlösung tcpClient = new ClientMusterlösung(HOST, PORT);
        ClientMusterlösung tcpClient = new ClientMusterlösung(hostname, portnumber);


        tcpClient.doSomething();


    }


    ClientMusterlösung (String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    private static void doSomething() throws IOException {
        Socket socket = new Socket(HOST, PORT);
        socket.getOutputStream().write(IRGENDWAS.getBytes());

        InputStream is = socket.getInputStream();

        byte[] buffer = new byte[10000];
        int i = 0;

        int read = 0;
        do {
            read = is.read();
            if (read != -1) {
                byte readByte = (byte) read;
                buffer[i++] = readByte;
            }
        } while (read != -1);

        byte[] receivedBytes = new byte[i];

        for(int j = 0; j < i; j++) {
            receivedBytes[j] = buffer[j];
        }

        String reveivedString = new String(receivedBytes);

        System.out.println("received: " + reveivedString);

    }
}
