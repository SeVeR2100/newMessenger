package userSafety;

import java.io.*;
import java.util.Scanner;

import static userSafety.Crypto.getDecrypt;

public class UserAllreadyReg {

    static File userFile = new File("D:\\Project\\Messenger\\Server\\src\\userSafety\\userFile.txt");

    public static boolean userAlreadyReg(String name, String password) {
        String checkName = name;
        String checkPass = password;
        try {
            Reader fr = new FileReader(userFile);
            Scanner scanner = new Scanner(fr);
            while (scanner.hasNextLine()) {
                String delimetr = " ";
                String[] searchMatch = scanner.nextLine().split(delimetr);
                String encryptedPass = getDecrypt(searchMatch[1]);
                if (checkName.matches(searchMatch[0]) & checkPass.matches(encryptedPass)) {
                    return true;
                }
                fr.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
