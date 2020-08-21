package frames;


import connection.ConnectionClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatRoomFrame extends JFrame{

    private final ConnectionClient net;
    private String response;
    private String user;
    private final JPanel panel = new JPanel();
    private final JTextArea inMess = new JTextArea();
    private final JTextField outMess = new JTextField(40);
    private final JLabel name = new JLabel();
    private final JButton send = new JButton();
    private final Container main = getContentPane();
    Thread thread = new Thread();


    public ChatRoomFrame (ConnectionClient net,String user) {
        this.net = net;
        this.user = user;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Держи краба!");
        setSize(600,400);
        setLocationRelativeTo(null);
        main.setLayout(new BorderLayout());
        panel.setLayout(new FlowLayout());
        inMess.setEditable(false);
        inMess.setLineWrap(true);
        main.add(inMess, BorderLayout.CENTER);
        panel.add(outMess);
        name.setText("Добро пожаловать, " + this.user + " !");
        main.add(name, BorderLayout.NORTH);
        send.setText("Отправить");
        panel.add(send );
        main.add(panel,BorderLayout.SOUTH);

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                net.write(user + " : " + outMess.getText());
                outMess.setText("");
            }
        });

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!thread.isInterrupted())
                    inMess.append(net.read()+ "\r\n");

            }
        });
        thread.start();

    }
}
