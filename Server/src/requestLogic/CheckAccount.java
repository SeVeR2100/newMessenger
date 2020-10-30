package requestLogic;

import connection.ConnectionServer;
import prepareStartClient.StartClient;
import server.Server;
import userSafety.UserAllreadyReg;


public class CheckAccount implements ILogic {

    private static ConnectionServer net;
    private String text ;
    private String delimiter = "<<<>>>";

    public CheckAccount(ConnectionServer net) {
        this.net = net;
    }

    @Override
    public void action() {
        Parser parser = new Parser();
        text = parser.getText();
        try {
            String[]parsText=text.split(delimiter);
            String name = parsText[0];
            String pass = parsText[1];
            if(UserAllreadyReg.userAlreadyReg(name,pass) == true ){
                net.write("Verification///]]]ACCEPT");
                Server.getUsers().add(net);
               new StartClient(net,name).startClient();
            } else{
                net.write("Verification///]]]ERROR");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
