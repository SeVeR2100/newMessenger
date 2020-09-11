package requestLogic;

import connection.ConnectionServer;
import userSafety.UserAllreadyReg;


public class CheckAccount implements Logic{

    private static ConnectionServer net;

    public CheckAccount(ConnectionServer net) {
        this.net = net;
    }

    @Override
    public void action() {
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
