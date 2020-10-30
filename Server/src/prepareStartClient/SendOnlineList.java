package prepareStartClient;

import connection.ConnectionServer;

import java.util.List;

import static server.Server.getUsersOnline;

public class SendOnlineList {
    private static List<String> usersOnline = getUsersOnline();
    private static ConnectionServer net;


    public SendOnlineList(ConnectionServer net) {
        this.net = net;
    }

    public void updateOnlineList() {
        SendAllClients sendAllClients = new SendAllClients();
        StringBuilder onlineList = new StringBuilder();
        for (String r : usersOnline) {
            onlineList.append(r + " ///");
        }
        String list = onlineList.toString();
        sendAllClients.sendMessageToAllClients("Online///]]]" + list);
    }
}
