package client;

import java.io.*;
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
            throw new IOException("missing parameters: hostname, portnumber");
        }

        String hostname = args[0];
        String portString = args[1];
        String fileName = null;

        if(args.length > 2) {
            fileName = args[2];
        }

        int portnumber = Integer.parseInt(portString); //String von Port zu Int umwandeln
        TCPClient tcpClient = new TCPClient(hostname, portnumber);

        if(fileName != null) {
            tcpClient.copyFile(fileName);
        }else {
            //tcpClient.doSomething();
            long timeStamp = System.currentTimeMillis();
            float value = (float)42.0;
            String sensorName = "Sensor A";
            tcpClient.sendSensorDate(timeStamp, value,sensorName);
        }

        tcpClient.doSomething();
    }

    TCPClient(String hostname, int port) {//repräsentiert Verbinung zum Server
        this.hostname = hostname;
        this.port = port;
    }

    private void sendSensorDate(long timeStamp, float value, String sensorName) throws IOException {
        Socket socket = new Socket(this.hostname, this.port);

        OutputStream os = socket.getOutputStream();//nutzen um datum an den server zu senden

        DataOutputStream daos = new DataOutputStream(os);
        daos.writeLong(timeStamp);
        daos.writeFloat(value);
        daos.writeUTF(sensorName);

        daos.close(); //daos schließt automatisch os
    }

    private void copyFile(String fileName) throws IOException {
        Socket socket = new Socket(this.hostname, this.port);//Socket anlegen für Server
        
        //wollen Stream haben um File zu übertragen- fileinput stream eröffnen
        FileInputStream fis = new FileInputStream(fileName);

        OutputStream os = socket.getOutputStream(); //zum Bytes versenden
        
        int read = 0;
        do {
            //vom is lesen
            read = fis.read();
            if(read != -1) {
                os.write(read);
            }
        } while(read != -1);
        os.close();
    }

    private void doSomething() throws IOException {
        Socket socket = new Socket(this.hostname, this.port);
        socket.getOutputStream().write(IRGENDWAS.getBytes()); //schicken bytes rüber

        InputStream is = socket.getInputStream();

        ByteArrayOutputStream baos = new ByteArrayOutputStream(); //Klasse die ein Outputstream ist. Daten werden in ein ByteArray rein geschrieben
        int i = 0;

        int read = 0;

        do {
            read = is.read(); //lesen. bei -1: Server schickt nichts mehr
            if(read != -1) {
                byte readByte = (byte) read;
                baos.write(read);
            }
        } while(read != -1);

        String receivedString = new String(baos.toByteArray());

        System.out.println("received: "+receivedString);
    }
}
