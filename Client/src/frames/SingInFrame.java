package frames;

import connection.ConnectionClient;
import responseLogic.ResponseReceiver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SingInFrame extends JFrame {

    private final ConnectionClient net;
    private JFrame jframe = new JFrame();
    private String accept = "ACCEPT";
    private JLabel label = new JLabel();
    private JLabel name = new JLabel("Имя:");
    private JLabel pass = new JLabel("Пароль:");
    private JTextArea nameField = new JTextArea(1, 30);
    private JPasswordField passField = new JPasswordField(30);
    private JButton enterButton = new JButton("Войти");
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private ResponseReceiver responseReceiver;

    public SingInFrame(ConnectionClient net) {
        this.net = net;
        responseReceiver = new ResponseReceiver(net);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Runtime.getRuntime().addShutdownHook(new ProcessorHook());
        jframe.setVisible(true);
        jframe.setTitle("Держи краба!");

        Dimension dimension = toolkit.getScreenSize();
        jframe.setBounds(dimension.width / 2 - 200, dimension.height / 2 - 100, 400, 200);
        jframe.setLayout(null);
        jframe.setResizable(false);

        label.setText("Авторизация");
        label.setBounds(160, 0, 500, 50);
        jframe.add(label);

        name.setText("Имя:");
        name.setBounds(10, 20, 50, 50);
        jframe.add(name);

        pass.setText("Пароль:");
        pass.setBounds(10, 60, 500, 50);
        jframe.add(pass);

        nameField.setBounds(90, 36, 240, 20);
        jframe.add(nameField);

        passField.setBounds(90, 76, 240, 20);
        jframe.add(passField);

        enterButton.setText("Войти");
        enterButton.setBounds(300, 120, 70, 30);
        jframe.add(enterButton);

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!String.valueOf(passField.getPassword()).matches("") & !nameField.getText().matches("")) {
                    tryLogIn(nameField.getText(), String.valueOf(passField.getPassword()));
                } else JOptionPane.showMessageDialog(jframe, "Введены некорректные данные");
            }
        });
        JButton RegistrationButton = new JButton("Регистрация");
        RegistrationButton.setBounds(20, 120, 120, 30);
        jframe.add(RegistrationButton);
        RegistrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jframe.dispose();
                new RegisterFrame(net);
            }
        });
    }

    public void tryLogIn(String name, String pass) {
        net.write("Check_Acc///]]]" + name + "<<<>>>" + pass);
        String response = net.read();
        String result = responseReceiver.getResponse(response);
        System.out.println(response);
        if (result.matches(accept)) {
            jframe.dispose();
            new ChatRoomFrame(net, name);
        } else
            JOptionPane.showMessageDialog(jframe, "Не верное имя или пароль!!");
    }

    public class ProcessorHook extends Thread {
        @Override
        public void run() {
            net.close();
        }
    }
}