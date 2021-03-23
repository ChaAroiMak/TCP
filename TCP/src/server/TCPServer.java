package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private final int port;
    public static final int PORTNUMBER = 7777;

    public static void main (String [] args) throws IOException, InterruptedException {
        TCPServer tcpServer = new TCPServer(PORTNUMBER);

        if(args.length == 1) {
            tcpServer.readFile(args[0]);
            return;
        } else {
            //tcpServer.doSomething();
            tcpServer.receiveSensorDate();
        }


    }

    TCPServer (int port) { //Konstruktor
        this.port = port;  //mit Port an dem er lauschen kann
    }

    private void receiveSensorDate() throws IOException {
        Socket socket = this.acceptSocket();
        InputStream is = socket.getInputStream(); //is auf dem ich daten einlesen kann

        DataInputStream dais = new DataInputStream(is);
        long timeStamp = dais.readLong();
        float value = dais.readFloat();
        String sensorName = dais.readUTF();

        System.out.println("timeStamp: " + timeStamp +"\n"+
                              "value: "+ value +"\n"+
                              "sensorName: " + sensorName);
    }

    private void readFile(String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);

        Socket socket = this.acceptSocket();
        InputStream is = socket.getInputStream();//input stream raus holen as socket

        int read = 0;
        do {
            read = is.read();
            if(read != -1) {
                fos.write(read);
            }
        } while(read != -1);
    }

    private Socket acceptSocket() throws IOException {
        ServerSocket srvSocket = new ServerSocket(this.port); //Serverport anlegen
        System.out.println("server socket created");

        //Port wird aufgemacht der von außen sichtbar ist, Aufruf blockiert und kommt erst zurück wenn sich ein Client mit Server verbunden hat
        return srvSocket.accept(); //wenn wir accept mehrfach aufrufen kann sich der Server mit mehreren Clients verbinden

    }

    private void doSomething() throws IOException, InterruptedException {
        System.out.println("client connection accepted");

        Socket socket = this.acceptSocket();
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
