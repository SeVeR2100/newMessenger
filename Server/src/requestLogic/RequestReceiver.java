package requestLogic;

import connection.ConnectionServer;

public class RequestReceiver{

    private static ConnectionServer net;

    public RequestReceiver(ConnectionServer net) {
        this.net = net;
    }

    public void requestReceiver(String request){
        switch (request) {
            case "New_Acc":
                new NewAccount(net).action();
                break;
            case "Check_Acc":
                new CheckAccount(net).action();
                break;
            default:
                net.write("ERROR");
        }
    }
}
