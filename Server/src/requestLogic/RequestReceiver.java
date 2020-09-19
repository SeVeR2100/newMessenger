package requestLogic;

import connection.ConnectionServer;
import java.util.LinkedHashMap;
import java.util.Map;

public class RequestReceiver{

    private static RequestReceiver requestReceiver;
    private static ConnectionServer net;
    private static Parser parser = new Parser();
    private static Map<String,ILogic> requestMap = new LinkedHashMap<>();




    public RequestReceiver(ConnectionServer net) {
        this.net=net;
        this.requestReceiver = this;
        NewMessage newMessage = new NewMessage(net);
        CheckAccount checkAccount = new CheckAccount(net);
        NewAccount newAccount = new NewAccount(net);

        requestMap.put("New_Acc",newAccount);
        requestMap.put("Check_Acc",checkAccount);
        requestMap.put("New_Message",newMessage);
    }

    public void getReceiver(String requestFromClient){
        parser.getParser(requestFromClient);
        ILogic handler = LogicFactory.getImpl(parser.getActionRequest());
        handler.action();
    }

    public static Map<String, ILogic> getRequestMap() {
        return requestMap;
    }

    public static RequestReceiver getRequestReceiver() {
        return requestReceiver;
    }
}
