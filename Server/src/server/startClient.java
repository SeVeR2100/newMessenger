package server;

import connection.ConnectionServer;
import requestLogic.RequestReceiver;
import java.io.FileNotFoundException;
import java.util.List;
import static server.Server.getUsers;
import static yarovoy.History.getLastMess;

public class startClient {

    private static List<ConnectionServer> users = getUsers();


    public static void startClient(ConnectionServer net, String name) {
        SendAllClients sac = new SendAllClients();
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
                RequestReceiver requestReceiver = RequestReceiver.getRequestReceiver();
                requestReceiver.getReceiver(request);
                System.out.println(request);
            } catch (NullPointerException e) {
                users.remove(net);
                System.out.println("удаление клиента из списка");
                sac.sendAllClients("ACTION");
                sac.sendAllClients(name + "offline");
                throw new NullPointerException("Клиент "+net.toString()+ " завершил соединение2");
            } catch (Exception e) {
                users.remove(net);
                System.out.println("удаление клиента из списка");
                sac.sendAllClients("ACTION");
                sac.sendAllClients(name + "offline");
                e.printStackTrace();
            }
        }
    }
}

