import connection.ConnectionClient;
import frames.RegisterFrame;


import javax.swing.*;
import java.io.*;

public class Client {

    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ConnectionClient net = new ConnectionClient();
                RegisterFrame s1 = new RegisterFrame(net);

            }
        });


    }
}
