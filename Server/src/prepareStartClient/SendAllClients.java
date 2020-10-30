package prepareStartClient;

import connection.ConnectionServer;
import java.util.List;

import static server.Server.getUsersConnections;

public class SendAllClients {

    private static List<ConnectionServer> users;

    public void sendMessageToAllClients(String msg) {
        users = getUsersConnections();
        for (ConnectionServer user : users) {
            user.write(msg);
        }
    }
}
