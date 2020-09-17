package frames;


import connection.ConnectionClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ChatRoomFrame extends JFrame{

    private final ConnectionClient net;
    private String response = null;
    private String action = "ACTION";
    private String user;
    private final JPanel southPanel = new JPanel();
    private final JPanel eastPanel = new JPanel();
    private final JTextArea inMess = new JTextArea();
    private final JTextArea logs = new JTextArea(5,5);
    private final JTextField outMess = new JTextField(40);
    private final JLabel name = new JLabel();
    private final JButton send = new JButton();
    private final Container main = getContentPane();
    private Thread thread;


    public ChatRoomFrame (ConnectionClient net,String user) {
        this.net = net;
        this.user = user;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Runtime.getRuntime().addShutdownHook(new ProcessorHook());
        setVisible(true);
        setTitle("Держи краба!");
        setSize(600,400);
        setLocationRelativeTo(null);

        main.setLayout(new BorderLayout());
        southPanel.setLayout(new FlowLayout());
        inMess.setEditable(false);
        inMess.setLineWrap(true);
        main.add(inMess, BorderLayout.CENTER);
        southPanel.add(outMess);
        name.setText("Добро пожаловать, " + this.user + " !");
        main.add(name, BorderLayout.NORTH);
        send.setText("Отправить");
        southPanel.add(send );
        main.add(southPanel,BorderLayout.SOUTH);

        eastPanel.setLayout(new BoxLayout(eastPanel,BoxLayout.PAGE_AXIS));
        eastPanel.add(new JLabel("Активность     "));
        logs.setEditable(false);
        logs.setBackground(Color.green);
        eastPanel.add(logs);
        main.add(eastPanel,BorderLayout.EAST);

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                net.write(user + " : " + outMess.getText());
                outMess.setText("");
            }
        });

        getRootPane().setDefaultButton(send);

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!thread.isInterrupted()) {
                    try {
                        String answer = net.read();
                        if (!action.matches(answer)) {
                            inMess.append(answer + "\r\n");
                        }  else  {
                            logs.append(net.read());
                        }
                    } catch (RuntimeException e){

                    }
                }
            }
        });
        thread.start();

    }
    public class ProcessorHook extends Thread {
        @Override
        public void run() {
            thread.interrupt();
            net.close();
        }
    }
}
