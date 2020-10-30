package responseLogic;

import connection.ConnectionClient;
import frames.ChatRoomFrame;

public class NewMessage implements ILogic {
    private ConnectionClient net;

    public NewMessage(ConnectionClient net) {
        this.net = net;
    }

    @Override
    public String action(String newMessage) {
        setMess(newMessage);
        return newMessage;
    }

    public void setMess(String string){
        ChatRoomFrame.getInMess().append(string + "\r\n");
    }
}
