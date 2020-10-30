package requestLogic;

import connection.ConnectionServer;
import prepareStartClient.StartClient;
import server.Server;
import userSafety.User;
import userSafety.UserAllreadyReg;

import static requestLogic.RequestReceiver.setCurrentUserName;


public class NewAccount implements ILogic {

    private static ConnectionServer net;
    private String text;
    private String delimiter = "<<<>>>";

    public NewAccount(ConnectionServer net) {
        this.net = net;
    }

    @Override
    public void action() {
        Parser parser = new Parser();
        text = parser.getText();
        try {
            String[] parsText = text.split(delimiter);
            String name = parsText[0];
            String pass = parsText[1];
            if (UserAllreadyReg.userAlreadyReg(name, pass) == true) {
                net.write("Verification///]]]ERROR");
            } else {
                new User(name, pass);
                Server.getUsersConnections().add(net);
                setCurrentUserName(name);
                Server.getUsersOnline().add(name);
                net.write("Verification///]]]ACCEPT");
                new StartClient(net, name).startClient();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
