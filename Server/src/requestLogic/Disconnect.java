package requestLogic;

import connection.ConnectionServer;
import prepareStartClient.SendOnlineList;
import server.Server;

import static requestLogic.RequestReceiver.getCurrentUserName;

public class Disconnect implements ILogic {
    private static ConnectionServer net;

    public Disconnect(ConnectionServer net) {
        this.net = net;
    }

    @Override
    public void action() {
        net.write("Disconnect");
        Server.getUsersConnections().remove(net);
        Server.getUsersOnline().remove(getCurrentUserName());
        SendOnlineList sendOnlineList = new SendOnlineList(net);
        sendOnlineList.updateOnlineList();
        net.close();
    }
}
