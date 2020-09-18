package requestLogic;

import connection.ConnectionServer;
import userSafety.User;
import userSafety.UserAllreadyReg;


public class NewAccount implements ILogic {

    private static NewAccount INSTANCE;
    private static ConnectionServer net;
    private String text ;
    private String delimiter = "<<<>>>";

    private NewAccount(ConnectionServer net) {
        this.net = net;
    }

    public static NewAccount getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NewAccount(net);
        }
        return INSTANCE;
    }

    @Override
    public void action(ConnectionServer net) {
        Parser parser = new Parser();
        text = parser.getText();
        try {
            String[]parsText=text.split(delimiter);
            String name = parsText[0];
            String pass = parsText[1];
            if(UserAllreadyReg.userAlreadyReg(name,pass) == true){
                net.write("ERROR");
            } else{
                new User(name,pass);
                net.write("ACCEPT");
                server.startClient.startClient(net, name);
            }
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
}
