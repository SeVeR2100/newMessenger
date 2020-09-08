package requestLogic;

import connection.ConnectionServer;
import userSafety.User;

import static server.Server.startClient;

public class NewAccount implements Logic {

    private static ConnectionServer net;

    public NewAccount(ConnectionServer net) {
        this.net = net;
    }

    @Override
    public void action() {
        try {
            String name = net.read();
            String pass = net.read();
            if(User.userAlreadyReg(name,pass) == true){
                net.write("ERROR");
            } else{
                new User(name,pass);
                net.write("ACCEPT");
                startClient(net, name);
            }
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
}
