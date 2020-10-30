package prepareStartClient;

import connection.ConnectionServer;
import java.io.FileNotFoundException;
import static yarovoy.History.getLastMess;

public class StartClient {
    private ConnectionServer net;
    private String name;

    public StartClient(ConnectionServer net, String name) {
        this.net = net;
        this.name = name;
    }

    public void startClient() {
        SendAllClients sac = new SendAllClients();
        System.out.println("Обслуживание клиента " + net.toString() + " почалось!! ");
        String[] lastMess = getLastMess();
        StringBuilder builder = new StringBuilder();
        for (String r : lastMess) {
            builder.append(r + " ///");
        }
        String history = builder.toString();
        net.write("MessageHistory///]]]" + history);
        System.out.println("История сообщений отправлена : " + net.toString());

//        sac.sendAllClients("ACTION");
//        sac.sendAllClients(name + " online");
    }

}

