package responseLogic;

import java.util.Map;

import static responseLogic.ResponseReceiver.getResponseMap;

public class LogicFactory {
    private static Map<String,ILogic> responseMap = getResponseMap();

    public static ILogic getImpl(String request) {
        return responseMap.get(request);
    }
}
