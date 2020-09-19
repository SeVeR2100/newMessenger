package server;

import connection.ConnectionServer;
import yarovoy.History;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static server.Server.getUsers;
import static yarovoy.History.getLastMess;

public class startClient {

    private static List<ConnectionServer> users = getUsers();
    private static DateFormat df = new SimpleDateFormat();
    private static History history = new History();
    private static ConnectionServer net ;


    public static void startClient(ConnectionServer net, String name) {
        SendAllClients sac = new SendAllClients();
        users.add(net);
        System.out.println("Обслуживание клиента "+ net.toString() +" почалось!! " );
        try {
            String[] lastMess = getLastMess();
            for (String s : lastMess) {
                net.write(s);
            }
            System.out.println("История сообщений отправтена : " + net.toString() );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sac.sendAllClients("ACTION");
        sac.sendAllClients(name + " online");
        while (!net.isClosed()) {
            try {
                System.out.println("test");
                String request = net.read();
                System.out.println(request);
                String message = df.format(new Date()) + " | " + request;
                history.addMessageInHistory(message);
                sac.sendAllClients(message);
                System.out.println("test2");
            } catch (NullPointerException e) {
                users.remove(net);
                System.out.println("удаление клиента из списка");
                sac.sendAllClients("ACTION");
                sac.sendAllClients(name + "offline");
                throw new NullPointerException("Клиент "+net.toString()+ " завершил соединение");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

