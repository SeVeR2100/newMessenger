package requestLogic;

import connection.ConnectionServer;
import userSafety.User;
import userSafety.UserAllreadyReg;


public class NewAccount implements ILogic {

    private static ConnectionServer net;
    private String text ;
    private String delimiter = "<<<>>>";

    public NewAccount(ConnectionServer net) {
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
