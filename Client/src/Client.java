import connection.ConnectionClient;
import frames.SingInFrame;
import java.io.*;

public class Client {

    public static void main(String[] args) throws IOException {
                ConnectionClient net = new ConnectionClient();
                SingInFrame s1 = new SingInFrame(net);
    }
}