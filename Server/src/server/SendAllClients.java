package server;

import connection.ConnectionServer;
import java.util.List;
import static server.Server.getUsers;

public class SendAllClients {

    private static List<ConnectionServer> users ;

    public void sendAllClients(String msg) {
        users = getUsers();
        for (ConnectionServer user : users) {
                user.write(msg);
        }
    }
}
