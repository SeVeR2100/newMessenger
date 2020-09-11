package requestLogic;

import connection.ConnectionServer;
import userSafety.User;
import userSafety.UserAllreadyReg;


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
