package yarovoy;


import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

    public static void main(String[] args) {
        System.out.println(countLine());
    }




}



