package frames;

import connection.ConnectionClient;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RegisterFrame extends JFrame{

    private final ConnectionClient net;
    private JFrame jframe = new JFrame();
    private String error = "ERROR";
    private String accept = "ACCEPT";
    private JLabel label = new JLabel();
    private JLabel name = new JLabel();
    private JLabel pass = new JLabel();
    private JTextArea nameField = new JTextArea(1, 30);
    private JTextArea passField = new JTextArea(1,30);
    private JButton enterButton = new JButton();
    private JButton registrationButton = new JButton();
    private Toolkit toolkit = Toolkit.getDefaultToolkit();


    public RegisterFrame(ConnectionClient net){

        this.net = net;
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Runtime.getRuntime().addShutdownHook(new ProcessorHook());
        jframe.setVisible(true);
        jframe.setTitle("Держи краба!");

        Dimension dimension = toolkit.getScreenSize();
        jframe.setBounds(dimension.width/2 - 200,dimension.height/2 - 100,400,200);

        jframe.setLayout(null);
        jframe.setResizable(false);

        label.setText("Регистрация");
        label.setBounds(160, 0, 500, 50);

        jframe.add(label);
        name.setText("Имя:");
        name.setBounds(10, 20, 50, 50);

        pass.setText("Пароль:");
        jframe.add(name);
        pass.setBounds(10, 60, 500, 50);
        jframe.add(pass);

        nameField.setBounds(90, 36, 240, 20);
        jframe.add(nameField);
        passField.setBounds(90, 76, 240, 20);
        jframe.add(passField);

        enterButton.setBounds(300, 120, 70, 30);
        jframe.add(enterButton);
        enterButton.setText("Войти");
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!nameField.getText().matches("") || !passField.getText().matches("")) {
                    trySingIn(nameField.getText(), passField.getText());
                } else JOptionPane.showMessageDialog(jframe, "Введены не корректные данные");
            }
        });
        registrationButton.setText("Авторизация");
        registrationButton.setBounds(20, 120, 120, 30);
        jframe.add(registrationButton);
        registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jframe.dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new SingInFrame(net);
                    }
                });
            }
        });

    }

    public void trySingIn(String name,String pass){
        net.write("New_Acc///]]]"+name+"<<<>>>"+pass);
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
