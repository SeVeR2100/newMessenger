package requestLogic;

import connection.ConnectionServer;
import userSafety.UserAllreadyReg;


public class CheckAccount implements ILogic {
    private static CheckAccount INSTANCE;
    private static ConnectionServer net;
    private String text ;
    private String delimiter = "<<<>>>";

    private CheckAccount() {
    }

    public static CheckAccount getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CheckAccount();
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
            if(UserAllreadyReg.userAlreadyReg(name,pass) == true ){
                net.write("ACCEPT");
                server.startClient.startClient(net, name);
            } else{
                net.write("ERROR");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
