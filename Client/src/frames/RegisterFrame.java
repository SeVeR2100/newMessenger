package frames;

import connection.ConnectionClient;

import javax.swing.*;
import java.awt.*;


public class RegisterFrame extends JFrame{


    private final ConnectionClient net;
    private JFrame jframe = new JFrame();
    private String error = "ERROR";
    private String accept = "ACCEPT";


    public RegisterFrame(ConnectionClient net){
        this.net = net;
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Runtime.getRuntime().addShutdownHook(new ProcessorHook());
        jframe.setVisible(true);
        jframe.setTitle("Держи краба!");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jframe.setBounds(dimension.width/2 - 200,dimension.height/2 - 100,400,200);
        jframe.setLayout(null);
        jframe.setResizable(false);
        JLabel label = new JLabel("Регистрация");
        label.setBounds(160,0,500,50);
        jframe.add(label);
        JLabel name = new JLabel("Имя:");
        name.setBounds(10,20,50,50);
        JLabel pass = new JLabel("Пароль:");
        jframe.add(name);
        pass.setBounds(10,60,500,50);
        jframe.add(pass);
        JTextArea nameField = new JTextArea(1,30);
        nameField.setBounds(90,36,240,20);
        jframe.add(nameField);
        JTextArea passField = new JTextArea(1,30);
        passField.setBounds(90,76,240,20);
        jframe.add(passField);
        JButton enterButton = new JButton("Войти");
        enterButton.setBounds(300,120,70,30);
        jframe.add(enterButton);
        enterButton.addActionListener(e -> {
            trySingIn(nameField.getText(),passField.getText());
        });

        JButton logInButton = new JButton("Авторизация");
        logInButton.setBounds(20,120,120,30);
        jframe.add(logInButton);
        logInButton.addActionListener(e -> {
            jframe.dispose();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new SingInFrame(net);
                }
            });

        });
    }

    public void trySingIn(String name,String pass){
        net.write("New_Acc");
        net.write(name);
        net.write(String.valueOf(pass));
        String response = net.read();
        System.out.println(response);
        if (response.matches(accept)) {
            jframe.dispose();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new ChatRoomFrame(net, name);
                }
            });
        } else
            JOptionPane.showMessageDialog(jframe,"Аккаунт с таким именем уже существует!!");

    }
    public class ProcessorHook extends Thread {
        @Override
        public void run() {
            net.close();
        }
    }

}

