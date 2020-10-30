package requestLogic;

import connection.ConnectionServer;
import prepareStartClient.SendAllClients;
import yarovoy.History;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static server.Server.getUsers;

public class NewMessage implements ILogic{

    private static ConnectionServer net;
    private String text ;
    private static List<ConnectionServer> users = getUsers();
    private static DateFormat df = new SimpleDateFormat();
    private static History history = new History();
    private SendAllClients sac = new SendAllClients();

    public NewMessage(ConnectionServer net) {
        this.net =net;
    }

    @Override
    public void action() {
        Parser parser = new Parser();
        text = parser.getText();
        String message = df.format(new Date()) + " | " + text;
        history.addMessageInHistory(message);
        sac.sendAllClients("NewMessage///]]]"+message);
    }
}
