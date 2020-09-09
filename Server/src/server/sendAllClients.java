package server;

import connection.ConnectionServer;

import java.util.ArrayList;

public class sendAllClients {

    private static ArrayList<ConnectionServer> users = new ArrayList<>();

    protected static void sendAllClients(String msg) {
        for (ConnectionServer u : users) {
            u.write(msg);
        }

    }

    public static ArrayList<ConnectionServer> getUsers(){
        return users;
    }
}
