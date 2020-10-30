package userSafety;

import java.io.*;

import static userSafety.Crypto.getEncrypt;

public class User {
    private int id;
    private String name;
    private String password;
    private static int count;
    private static File userFile = new File("D:\\Project\\Messenger\\Server\\src\\userSafety\\userFile.txt");

    public User(String name, String password) throws Exception {
        this.id += count;
        this.name = name;
        this.password = getEncrypt(password);
        count++;
        try {
            Writer pw = new FileWriter(userFile, true);
            pw.write(this.name + " " + this.password + "\r\n");
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
