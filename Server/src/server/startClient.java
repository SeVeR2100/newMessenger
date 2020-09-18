package server;

import connection.ConnectionServer;
import yarovoy.History;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static yarovoy.History.getLastMess;

public class startClient {

    private static ArrayList<ConnectionServer> users = sendAllClients.getUsers();
    private static DateFormat df = new SimpleDateFormat();
    private static History history = new History();


    public static void startClient(ConnectionServer net, String name) {
        sendAllClients sac = new sendAllClients();
        users.add(net);
        System.out.println("Обслуживание клиента почалось!! " + net.toString());
        try {
            String[] lastMess = getLastMess();
            for (String s : lastMess) {
                net.write(s);
            }
            System.out.println("Message history delivery!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sac.sendAllClients("ACTION");
        sac.sendAllClients(name + " online");
        while (!net.isClosed()) {
            try {
                Thread.sleep(25);
                System.out.println("test");
                String message = df.format(new Date()) + " | " + net.read();
                history.addMessageInHistory(message);
                sac.sendAllClients(message);
                System.out.println("test");
                Thread.sleep(25);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    net.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        sac.sendAllClients("ACTION");
        sac.sendAllClients(name + "offline");
        System.out.println("Обслуживание клиента завершилось!! " + net.toString());
        users.remove(net);
        System.out.println("test");
        try {
            net.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

