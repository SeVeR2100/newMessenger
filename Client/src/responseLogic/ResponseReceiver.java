package responseLogic;

import connection.ConnectionClient;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseReceiver {
    private static Parser parser = new Parser();
    private static Map<String,ILogic> responseMap = new LinkedHashMap<>();

    public ResponseReceiver(ConnectionClient net) {
        Verification verification = new Verification(net);
        NewMessage newMessage = new NewMessage(net);
        GetHistory getHistory = new GetHistory(net);

        responseMap.put("Verification", verification);
        responseMap.put("NewMessage", newMessage);
        responseMap.put("MessageHistory", getHistory);

    }
    public String getResponse(String responseFromServer){
        parser.getParser(responseFromServer);
        ILogic handler = LogicFactory.getImpl(parser.getActionResponse());
        return handler.action(parser.getText());
    }

    public static Map<String, ILogic> getResponseMap() {
        return responseMap;
    }
}
