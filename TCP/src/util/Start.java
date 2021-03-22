package util;

import client.TCPClient;
import server.TCPServer;

import java.io.IOException;

public class Start {
    public static void main(String []args) throws IOException, InterruptedException {
        if(args.length >= 2) {
            TCPClient.main(args);
            return;
        }

        TCPServer.main(args);
    }
}
