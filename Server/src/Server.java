import connection.ConnectionServer;
import yarovoy.History;

import java.io.*;
import java.net.ServerSocket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Server {

    private static ArrayList<ConnectionServer> users = new ArrayList<>();
    private static History history = new History();
    private static DateFormat df = new SimpleDateFormat();



    public static void main(String[] args) throws IOException {


        try (ServerSocket server = new ServerSocket(8000)) {

            System.out.println("Server Started!");

            while(true) {
                try ( ConnectionServer net = new ConnectionServer(server) ){
                    Thread newThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (!server.isClosed()) {
                                String request = net.read();
                                switch (request){
                                    case "New_Acc" :
                                        try {
                                            String name = net.read();
                                            String pass = net.read();
                                            if(User.userAlreadyReg(name,pass) == true){
                                                net.write("ERROR");
                                            } else{
                                                new User(name,pass);
                                                net.write("ACCEPT");
                                                startClient(net);
                                            }
                                        } catch (Exception e ) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    case "Check_Acc" :
                                        try {
                                            String name = net.read();
                                            String pass = net.read();
                                            if(User.userAlreadyReg(name,pass) == true ){
                                                net.write("ACCEPT");
                                                startClient(net);
                                            } else{
                                                net.write("ERROR");
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                }
                            }
                        }
                    });
                    newThread.start();
                }
            }
        } catch (RuntimeException e) {
        }
    }

    private static void startClient(ConnectionServer net){
        users.add(net);
        System.out.println("Обслуживание клиента почалось!! " + net.toString());
        while(!net.isClosed()) {
            String message = df.format(new Date()) + " | " + net.read();
            history.addMessageInHistory(message);
            sendAllClients(message);
            }
            users.remove(net);
            try {
                net.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    private static void sendAllClients(String msg) {
        for (ConnectionServer u : users) {
            u.write(msg);
        }

    }

}




