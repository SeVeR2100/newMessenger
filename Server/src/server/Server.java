package server;

import connection.ConnectionServer;
import prepareStartClient.SendOnlineList;
import requestLogic.RequestReceiver;

import java.io.*;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;

import static requestLogic.RequestReceiver.getCurrentUserName;


public class Server {
    private static int threadCount = 0;
    private static List<ConnectionServer> usersConnections = new LinkedList<>();
    private static List<String> usersOnline = new LinkedList<>();
    private ConnectionServer net;

    public void start() throws IOException {

        try (ServerSocket server = new ServerSocket(7775)) {

            System.out.println("Server Started!");

            while (true) {
                net = new ConnectionServer(server);
                System.out.println("Новое подключение");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        RequestReceiver requestReceiver = new RequestReceiver(net);
                        int currentTread = ++threadCount;
                        System.out.println("Поток номер: " + currentTread + " заработал");
                        while (!net.isClosed()) {
                            try {
                                String request = net.read();
                                requestReceiver.getReceiver(request);
                            } catch (NullPointerException e) {
                                net.close();
                                usersConnections.remove(net);
                                usersOnline.remove(getCurrentUserName());
                                SendOnlineList sendOnlineList = new SendOnlineList(net);
                                sendOnlineList.updateOnlineList();
                                System.out.println("Клиент " + net.toString() + " завершил соединение1");
                            }
                        }
                        System.out.println("Поток номер: " + currentTread + " завершён");
                    }
                }).start();
            }
        } catch (RuntimeException e) {
        }
    }

    public static List<ConnectionServer> getUsersConnections() {
        return usersConnections;
    }

    public static List<String> getUsersOnline() {
        return usersOnline;
    }
}




