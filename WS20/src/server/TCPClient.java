package server;

// Verbindung zu einem beliebigen Server herstellen
// Client soll eine Zeichenfolge an den Server senden
// Client soll den Rückgabewert emüfangen und ausgeben

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

// Auf Kommandozeile die Parameter für Verbindung eingeben (Name und Port
// des Rechners mit dem ich mich verbinden will)
public class TCPClient {
    public static String ip;
    public static int port;
    public static int portnumber;

    public static void main (String [] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            ip = scanner.next();
            port = scanner.nextInt();

            Socket socket = new Socket(ip, port);//Einrichtung einer Socket Verbindung über Port x
            OutputStream os = socket.getOutputStream(); //Output Stream in den geschrieben werden soll
            PrintStream ps = new PrintStream(os, true); //Erweiterung von OutputStream, to write output data in commonly readable form (text) instead of bytes
//flush sorgt dafür, dass alle momentan im Puffer befindlichen Daten in den Stream geschrieben werden

            InputStream is = socket.getInputStream(); //Input Stream aus dem gelesen werden soll
            System.out.println("verfügbare bytes: " + is.available()); // available liefert die Anzahl an Bytes, die ohne Blockieren mindestens gelesen werden können
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); //Daten werden zuerst in Zwischenspeicher geladen

            while (br.ready()) { // ready liefert true, falls der nächste Aufruf von read erfolgen kann, ohne daß die Eingabe blockt
                System.out.println(br.readLine());
            }
        }
        catch (IOException ex) {
            System.err.println("mission failed");

        }

    }

 }
