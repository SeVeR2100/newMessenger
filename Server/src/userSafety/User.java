import userSafety.Crypto;

import java.io.*;
import java.util.Scanner;

public class User {
    private int id;
    private String name;
    private String password;
    private static int count;
    static File userFile = new File("D:\\Project\\Messenger\\Server\\src\\userSafety\\userFile.txt");



    public User(String name, String password) throws Exception {
        this.id += count;
        this.name = name;
        this.password = Crypto.encrypt(password);
        count++;
        try {
            Writer pw = new FileWriter(userFile, true);
            pw.write( this.name + " " + this.password + "\r\n");
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkUserPass(String name, String password) throws Exception {
        String checkName = name ;
        String checkPass = password;
        try {
            Reader fr = new FileReader(userFile);
            Scanner scanner = new Scanner(fr);
            while(scanner.hasNextLine()) {
                String delimetr = " ";
                String [] searchMatch = scanner.nextLine().split(delimetr);
                String encryptedPass = Crypto.decrypt(searchMatch[1]);
                if (checkName.matches(searchMatch[0]) & checkPass.matches(encryptedPass) ) {
                    return true;
                }
                fr.close();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean userAlreadyReg(String name, String password) throws Exception {
        String checkName = name ;
        String checkPass = password;
        try {
            Reader fr = new FileReader(userFile);
            Scanner scanner = new Scanner(fr);
            while(scanner.hasNextLine()) {
                String delimetr = " ";
                String [] searchMatch = scanner.nextLine().split(delimetr);
                String encryptedPass = Crypto.decrypt(searchMatch[1]);
                if (checkName.matches(searchMatch[0]) & checkPass.matches(encryptedPass) ) {
                    return true;
                }
                fr.close();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }




    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
