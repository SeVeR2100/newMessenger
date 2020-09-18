package server;

import connection.ConnectionServer;
import requestLogic.RequestReceiver;
import java.io.*;
import java.net.ServerSocket;


public class Server {

    private static ConnectionServer net;
    private static int threadCount = 0;

    public static void main(String[] args) throws IOException {


        try (ServerSocket server = new ServerSocket(7777)) {

            System.out.println("Server Started!");

            while(!server.isClosed()) {
                net = new ConnectionServer(server);
                System.out.println("Новое подключение");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        threadCount++;
                        System.out.println("Поток номер: " + threadCount + " заработал");
                        while (!Thread.interrupted()) {
                            try {
                                Thread.sleep(25);
                                String request = net.read();
                                new RequestReceiver(net, request);
                                Thread.sleep(25);
                            } catch (NullPointerException e) {
                               // net.close();
                                System.out.println("поток "+threadCount+" закрыт");
                                Thread.interrupted();
                                throw new NullPointerException("Клиент завершил соединение");
                            } catch (Exception e) {
                               // net.close();
                                System.out.println("поток "+threadCount+" закрыт");
                                Thread.interrupted();
                                e.printStackTrace();
                            }
                        }

                    }
                }).start();
            }
        } catch (RuntimeException e) {
        }
    }
}




