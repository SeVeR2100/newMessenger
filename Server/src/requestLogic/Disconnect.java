package requestLogic;

import connection.ConnectionServer;
import server.Server;

public class Disconnect implements ILogic{
    private static ConnectionServer net;

    public Disconnect(ConnectionServer net) {
        this.net=net;
    }

    @Override
    public void action() {
        net.write("Disconnect");
        Server.getUsers().remove(net);
        net.close();
    }
}
