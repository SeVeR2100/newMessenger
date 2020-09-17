package requestLogic;

import connection.ConnectionServer;
import userSafety.User;
import userSafety.UserAllreadyReg;


public class NewAccount implements ILogic {

    private static NewAccount INSTANCE;
    private static ConnectionServer net;

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
        try {
            String name = net.read();
            String pass = net.read();
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
