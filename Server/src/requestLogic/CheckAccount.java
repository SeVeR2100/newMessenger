package requestLogic;

import connection.ConnectionServer;
import userSafety.UserAllreadyReg;


public class CheckAccount implements ILogic {
    private static CheckAccount INSTANCE;
    private static ConnectionServer net;

    private CheckAccount(ConnectionServer net) {
        this.net = net;
    }

    public static CheckAccount getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CheckAccount(net);
        }
        return INSTANCE;
    }

    @Override
    public void action(ConnectionServer net) {
        try {
            String name = net.read();
            String pass = net.read();
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
