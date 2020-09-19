import connection.ConnectionClient;
import frames.RegisterFrame;
import frames.SingInFrame;
import org.w3c.dom.ls.LSOutput;


import javax.swing.*;
import java.io.*;

public class Client {

    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ConnectionClient net = new ConnectionClient();
                SingInFrame s1 = new SingInFrame(net);
            }
        });
    }
}