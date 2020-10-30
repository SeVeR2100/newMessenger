package frames;

import connection.ConnectionClient;
import responseLogic.ResponseReceiver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChatRoomFrame extends JFrame {

    private final ConnectionClient net;
    private final String user;
    private final JPanel southPanel = new JPanel();
    private final JPanel eastPanel = new JPanel();
    private static JTextArea inMess = new JTextArea();
    private static JTextArea logs = new JTextArea(5, 5);
    private final JTextField outMess = new JTextField(40);
    private final JLabel name = new JLabel();
    private final JButton send = new JButton();
    private final Container main = getContentPane();
    private final Thread thread;
    private ResponseReceiver responseReceiver;


    public ChatRoomFrame(ConnectionClient net, String user) {
        this.net = net;
        this.user = user;
        responseReceiver = new ResponseReceiver(net);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Runtime.getRuntime().addShutdownHook(new ProcessorHook());
        setVisible(true);
        setTitle("Держи краба!");
        setSize(600, 400);
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
        southPanel.add(send);
        main.add(southPanel, BorderLayout.SOUTH);

        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));
        eastPanel.add(new JLabel("Онлайн     "));
        logs.setEditable(false);
        logs.setBackground(Color.green);
        eastPanel.add(logs);
        main.add(eastPanel, BorderLayout.EAST);
        getRootPane().setDefaultButton(send);

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                net.write("New_Message///]]]" + user + " : " + outMess.getText());
                outMess.setText("");
            }
        });

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!thread.isInterrupted()) {
                    try {
                        String answer = net.read();
                        responseReceiver.getResponse(answer);
                        System.out.println(answer);

                    } catch (RuntimeException e) {
                        net.close();
                        thread.interrupt();
                    }
                }
            }
        });
        thread.start();
    }

    public class ProcessorHook extends Thread {
        @Override
        public void run() {
            net.write("Disconnect///]]]True");
            net.close();
        }
    }

    public static JTextArea getInMess() {
        return inMess;
    }

    public static JTextArea getLogs() {
        return logs;
    }
}
