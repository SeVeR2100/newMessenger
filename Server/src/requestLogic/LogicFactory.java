package requestLogic;

import java.util.Map;
import static requestLogic.RequestReceiver.getRequestMap;

public class LogicFactory {
    private static Map<String,ILogic> requestMap = getRequestMap();

    public static ILogic getImpl(String request) {
        return requestMap.get(request);
    }
}