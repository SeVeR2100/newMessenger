package frames;

import connection.ConnectionClient;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SingInFrame extends JFrame {

    private final ConnectionClient net;
    private JFrame jframe = new JFrame();
    private String error = "ERROR";
    private String accept = "ACCEPT";

    public SingInFrame(ConnectionClient net) {

        this.net = net;
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Runtime.getRuntime().addShutdownHook(new ProcessorHook());
        jframe.setVisible(true);
        jframe.setTitle("Держи краба!");

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        jframe.setBounds(dimension.width / 2 - 200, dimension.height / 2 - 100, 400, 200);
        jframe.setLayout(null);
        jframe.setResizable(false);

        JLabel label = new JLabel("Авторизация");
        label.setBounds(160, 0, 500, 50);
        jframe.add(label);
        JLabel name = new JLabel("Имя:");
        name.setBounds(10, 20, 50, 50);
        JLabel pass = new JLabel("Пароль:");
        jframe.add(name);
        pass.setBounds(10, 60, 500, 50);
        jframe.add(pass);
        JTextArea nameField = new JTextArea(1, 30);
        nameField.setBounds(90, 36, 240, 20);
        jframe.add(nameField);
        JPasswordField passField = new JPasswordField(30);
        passField.setBounds(90, 76, 240, 20);
        jframe.add(passField);
        JButton enterButton = new JButton("Войти");
        enterButton.setBounds(300, 120, 70, 30);
        jframe.add(enterButton);

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tryLogIn(nameField.getText(), String.valueOf(passField.getPassword()));
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
        net.write("Check_Acc");
        net.write(name);
        net.write(String.valueOf(pass));
        String response = net.read();
        System.out.println(response);
        if (response.matches(accept)) {
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