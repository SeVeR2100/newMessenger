package yarovoy;


import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class History {

    private static File messageHistory = new File("D:\\Project\\Messenger\\Server\\src\\yarovoy\\messageHistory.txt");

    public void addMessageInHistory (String message){
        DateFormat df = new SimpleDateFormat();
        try {
            Writer pw = new FileWriter(messageHistory, true);
            pw.write(message + "\r\n");
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static int countLine(){
        int lineNumber = 0;
        try{
            FileReader fileReader = new FileReader(messageHistory);
            LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
            lineNumber = 0;
            while (lineNumberReader.readLine() != null){
                lineNumber++;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return lineNumber;
    }


    public static String[] getLastMess() throws FileNotFoundException {
        Scanner scanner = new Scanner(messageHistory);
        int o = 0;
        String[] lastMess = new String[10];
        int countLine = countLine();
        for (int i = 1; i<=countLine;i++){
            String s = scanner.nextLine();
            if (i >= countLine-9 ){
                lastMess[o] = s;
                o++;
            }
        }

        return lastMess;
    }


}



