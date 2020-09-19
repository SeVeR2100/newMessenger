package frames;

import connection.ConnectionClient;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;


public class ChatRoomFrame extends JFrame{

    private final ConnectionClient net;
    private String response = null;
    private final String action = "ACTION";
    private final String user;
    private final JPanel southPanel = new JPanel();
    private final JPanel eastPanel = new JPanel();
    private final JTextArea inMess = new JTextArea();
    private final JTextArea logs = new JTextArea(5,5);
    private final JTextField outMess = new JTextField(40);
    private final JLabel name = new JLabel();
    private final JButton send = new JButton();
    private final Container main = getContentPane();
    private final Thread thread;


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
        getRootPane().setDefaultButton(send);

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
                while(!thread.isInterrupted()) {
                    try {
                        System.out.println("Ждёт ответа!");
                        String answer = net.read();
                        System.out.println(answer);
                        if (!action.matches(answer)) {
                            inMess.append(answer + "\r\n");
                        } else {
                            logs.append(net.read());
                        }
                    } catch (RuntimeException e){
                        thread.interrupt();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

    }
    public class ProcessorHook extends Thread {
        @Override
        public void run() {
            System.out.println(1);
            thread.interrupt();
            System.out.println(2);
//            net.close();
            System.out.println(3);
            System.out.println("Нажал на крестик");
            System.exit(0);
        }
    }
}
