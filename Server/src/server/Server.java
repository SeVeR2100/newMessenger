package server;

import connection.ConnectionServer;
import userSafety.User;
import yarovoy.History;
import java.io.*;
import java.net.ServerSocket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import static yarovoy.History.getLastMess;

public class Server {

    private static ArrayList<ConnectionServer> users = new ArrayList<>();
    private static History history = new History();
    private static DateFormat df = new SimpleDateFormat();



    public static void main(String[] args) throws IOException {


        try (ServerSocket server = new ServerSocket(8000)) {

            System.out.println("Server Started!");


            while(true) {
                try ( ConnectionServer net = new ConnectionServer(server) ){
                    Thread newThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            requestLogic.RequestReceiver logic = new requestLogic.RequestReceiver(net);
                            while (!server.isClosed()) {
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

    public static void startClient(ConnectionServer net, String name){
        users.add(net);
        System.out.println("Обслуживание клиента почалось!! " + net.toString());
        try {
            String [] lastMess = getLastMess();
            for(String s : lastMess){
                net.write(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sendAllClients("ACTION");
        sendAllClients(name + " online");
        while(!net.isClosed()) {
            String message = df.format(new Date()) + " | " + net.read();
            history.addMessageInHistory(message);
            sendAllClients(message);
            }
        users.remove(net);
        sendAllClients("ACTION");
        sendAllClients(name + "offline");
        System.out.println("Обслуживание клиента завершилось!! " + net.toString());
            try {
                net.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    private static void sendAllClients(String msg) {
        for (ConnectionServer u : users) {
            u.write(msg);
        }

    }

}




