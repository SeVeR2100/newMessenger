package responseLogic;

import connection.ConnectionClient;

public class Verification implements ILogic {
    private ConnectionClient net;

    public Verification(ConnectionClient net) {
        this.net = net;
    }

    @Override
    public String action(String text) {
        return text;
    }
}
