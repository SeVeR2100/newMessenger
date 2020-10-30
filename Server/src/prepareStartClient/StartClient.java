package prepareStartClient;

import connection.ConnectionServer;

import static yarovoy.History.getLastMess;

public class StartClient {
    private ConnectionServer net;
    private String name;

    public StartClient(ConnectionServer net, String name) {
        this.net = net;
        this.name = name;
    }

    public void startClient() {
        System.out.println("Обслуживание клиента: " + name + "|||" + net.toString() + " почалось!! ");
        String[] lastMess = getLastMess();
        StringBuilder builder = new StringBuilder();
        for (String r : lastMess) {
            builder.append(r + " ///");
        }
        String history = builder.toString();
        net.write("MessageHistory///]]]" + history);
        System.out.println("История сообщений отправлена: " + name + "|||" + net.toString());
        SendOnlineList sendOnlineList = new SendOnlineList(net);
        sendOnlineList.updateOnlineList();
    }
}

