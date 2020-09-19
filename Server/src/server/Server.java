package server;

import connection.ConnectionServer;
import requestLogic.RequestReceiver;
import java.io.*;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;


public class Server {

    private ConnectionServer net;
    private static int threadCount = 0;
    private static List<ConnectionServer> users = new LinkedList<>();

    public void start () throws IOException {

        try (ServerSocket server = new ServerSocket(7775)) {

            System.out.println("Server Started!");

            while(true) {
                net = new ConnectionServer(server);
                System.out.println("Новое подключение");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int currentTread = ++threadCount;
                        System.out.println("Поток номер: " + currentTread + " заработал");
                        while (!net.isClosed()) {
                            System.out.println("zashol v potok");
                            try {
                                String request = net.read();
                                new RequestReceiver(net, request);
                            } catch (NullPointerException e) {
                                System.out.println("поток "+ currentTread + " закрыт1");
                                net.close();
                                System.out.println("disconnect client ");
                                throw new NullPointerException("Клиент "+ net.toString() +" завершил соединение");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        net.close();
                        System.out.println("disconnect verified client ");
                    }
                }).start();
            }
        } catch (RuntimeException e) {
        }
    }

    public static List<ConnectionServer> getUsers() {
        return users;
    }
}




