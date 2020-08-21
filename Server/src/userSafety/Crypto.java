package userSafety;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Crypto {

    private static final String key = "Spartak";


    public static String encrypt(String password){

        try {
            byte[] keyData = (key).getBytes();
            SecretKeySpec secKey = new SecretKeySpec(keyData,"Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE,secKey);
            byte [] hasil = cipher.doFinal(password.getBytes());
            return new String(Base64.getEncoder().encode(hasil));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String decrypt(String string) {
        try {
            byte[] keyData = (key).getBytes();
            SecretKeySpec secKey = new SecretKeySpec(keyData,"Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, secKey);
            byte[] decrypted = cipher.doFinal(java.util.Base64.getDecoder().decode(string));
            return new String(decrypted);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}