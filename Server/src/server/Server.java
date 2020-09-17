package server;

import connection.ConnectionServer;
import requestLogic.RequestReceiver;

import java.io.*;
import java.net.ServerSocket;
import java.net.SocketException;

import static server.startClient.isChatReady;


public class Server {

    private static boolean chatIsReady = isChatReady();
    private ConnectionServer net;
    private static int threadCount = 0;

    public static void main(String[] args) throws IOException {


        try (ServerSocket server = new ServerSocket(7777)) {

            System.out.println("Server Started!");

            while(!server.isClosed()) {
                ConnectionServer net = new ConnectionServer(server);
                System.out.println("Новое подключение");
                new Thread(new Runnable() {
                   @Override
                   public void run() {
                       threadCount++;
                       System.out.println("Поток номер: "+threadCount+" заработал");

                       while (!net.isClosed()) {
                           try {
                               String request = net.read();
                               new RequestReceiver(net,request);
                           } catch (Exception e) {
                               e.printStackTrace();
                           } finally {
                               try {
                                   net.close();
                               } catch (IOException e) {
                                   e.printStackTrace();
                               }
                           }
                       }
                       System.out.println("поток "+threadCount+" закрыт");
                   }
               }).start();
            }
        } catch (RuntimeException e) {
        }
    }
}




