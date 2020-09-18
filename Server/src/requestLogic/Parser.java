package requestLogic;

public class Parser {

    private static String actionRequest;
    private static String text;
    private static String delimiter = "///]]]";

    public Parser(){}

    public void getParser(String request) throws NullPointerException{
        String[] parsRequest = request.split(delimiter);
        this.actionRequest = parsRequest[0];
        this.text = parsRequest[1];
    }

    public String getActionRequest() {
        return actionRequest;
    }

    public String getText() {
        return text;
    }
}
