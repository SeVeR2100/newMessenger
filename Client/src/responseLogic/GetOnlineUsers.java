package responseLogic;

import connection.ConnectionClient;

import static frames.ChatRoomFrame.getLogs;

public class GetOnlineUsers implements ILogic {
    private ConnectionClient net;

    public GetOnlineUsers(ConnectionClient net) {
        this.net = net;
    }


    @Override
    public String action(String onlineList) {
        String[] online = onlineList.split(" ///");
        for (String s : online) {
            getLogs().setText(s + "\r\n");
        }
        return online[0];
    }
}
