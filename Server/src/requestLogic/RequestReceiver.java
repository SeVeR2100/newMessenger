package requestLogic;

import connection.ConnectionServer;

public class RequestReceiver{

    private static ConnectionServer net;
    private static String request;

    public RequestReceiver(ConnectionServer net, String request) {
        this.net = net;
        ILogic handler = LogicFactory.getImpl(request);
        handler.action(net);
    }
}
