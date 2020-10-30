package requestLogic;

import connection.ConnectionServer;

import java.util.LinkedHashMap;
import java.util.Map;

public class RequestReceiver {

    private static Parser parser = new Parser();
    private static Map<String, ILogic> requestMap = new LinkedHashMap<>();
    private static String currentUserName;

    public RequestReceiver(ConnectionServer net) {
        NewMessage newMessage = new NewMessage(net);
        CheckAccount checkAccount = new CheckAccount(net);
        NewAccount newAccount = new NewAccount(net);
        Disconnect disconnect = new Disconnect(net);
        requestMap.put("New_Acc", newAccount);
        requestMap.put("Check_Acc", checkAccount);
        requestMap.put("New_Message", newMessage);
        requestMap.put("Disconnect", disconnect);
    }

    public void getReceiver(String requestFromClient) {
        parser.getParser(requestFromClient);
        ILogic handler = LogicFactory.getImpl(parser.getActionRequest());
        handler.action();
    }

    public static Map<String, ILogic> getRequestMap() {
        return requestMap;
    }

    public static String getCurrentUserName() {
        return currentUserName;
    }

    public static void setCurrentUserName(String currentUserName) {
        RequestReceiver.currentUserName = currentUserName;
    }
}
