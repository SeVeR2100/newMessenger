package userSafety;

public class Crypto {

    private static String privateEncrypt(String password){
        char[] chars = password.toCharArray();
        StringBuilder str2 = new StringBuilder();
        for (byte b = 0; chars.length > b; b++) {
            chars[b] = (char) (chars[b] + 2);
            str2.append(chars[b]);
            }
            return str2.toString();
        }

    private static String privateDecrypt(String string) {
        char[] chars = string.toCharArray();
        StringBuilder str2 = new StringBuilder();
        for (byte b = 0; chars.length > b; b++) {
            chars[b] = (char) (chars[b] - 2);
            str2.append(chars[b]);
        }
        return str2.toString();
    }

    public static String getEncrypt(String password){
        return privateEncrypt(password);
    }

    public static String getDecrypt(String password){
        return privateDecrypt(password);
    }


}
