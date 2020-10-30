package responseLogic;

public class Parser {
    private static String actionResponse;
    private static String text;
    private static String delimiter = "///]]]";

    public Parser(){}

    public void getParser(String request) throws NullPointerException{
        String[] parsRequest = request.split(delimiter);
        this.actionResponse = parsRequest[0];
        this.text = parsRequest[1];
    }

    public String getActionResponse() {
        return actionResponse;
    }

    public String getText() {
        return text;
    }
}
