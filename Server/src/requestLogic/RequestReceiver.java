package requestLogic;

import connection.ConnectionServer;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class RequestReceiver{

    private static ConnectionServer net;
    private Parser parser = new Parser();
    private static Map<String,ILogic> requestMap = new LinkedHashMap<>();

    public RequestReceiver(ConnectionServer net,String requestFromClient) {
        this.net=net;
        requestMap.put("New_Acc",NewAccount.getInstance());
        requestMap.put("Check_Acc",CheckAccount.getInstance());
        requestMap.put("New_Message",NewAccount.getInstance());
        requestMap.put(null,NullMess.getInstance());
        
        parser.getParser(requestFromClient);
        ILogic handler = LogicFactory.getImpl(parser.getActionRequest());
        handler.action(net);
    }

    public static Map<String, ILogic> getRequestMap() {
        return requestMap;
    }
}
