package requestLogic;

import connection.ConnectionServer;

import java.io.IOException;

public class NullMess implements ILogic {

    private static NullMess INSTANCE;
    private static ConnectionServer net;

    private NullMess(ConnectionServer net) {
        this.net = net;
    }

    public static NullMess getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NullMess(net);
        }
        return INSTANCE;
    }

    @Override
    public void action(ConnectionServer net) {
        try {
            System.out.println("Клиент завершил соединение");
            net.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
