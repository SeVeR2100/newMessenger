package responseLogic;

import connection.ConnectionClient;
import frames.ChatRoomFrame;

public class GetHistory implements ILogic{
    private ConnectionClient net;

    public GetHistory(ConnectionClient net) {
        this.net = net;
    }

    @Override
    public String action(String messageHistory) {
        String[]history=messageHistory.split(" ///");
        setHistory(history);
        return history[0];
    }
    public void setHistory(String[]array){
        for(String s:array){
            ChatRoomFrame.getInMess().append(s + "\r\n");
        }
    }
}
