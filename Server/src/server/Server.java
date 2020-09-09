package server;

import connection.ConnectionServer;
import java.io.*;
import java.net.ServerSocket;
import static server.startClient.isChatReady;


public class Server {


    private static boolean chatIsReady = isChatReady();



    public static void main(String[] args) throws IOException {


        try (ServerSocket server = new ServerSocket(8000)) {

            System.out.println("Server Started!");

            while(true) {
                try ( ConnectionServer net = new ConnectionServer(server) ){
                    Thread newThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            requestLogic.RequestReceiver logic = new requestLogic.RequestReceiver(net);
                            while (!chatIsReady==true) {
                                String request = net.read();
                                logic.requestReceiver(request);
                            }
                        }
                    });
                    newThread.start();
                }
            }
        } catch (RuntimeException e) {
        }
    }
}




