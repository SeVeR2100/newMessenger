package connection;

import java.io.*;
import java.net.Inet4Address;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

public class ConnectionClient {

    private String ip;
    private int port = 7775;
    private Socket socket;
    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static Scanner scan;

    public ConnectionClient (){
        try {
            ip = Inet4Address.getLocalHost().getHostAddress();
            this.socket = new Socket(ip,port);
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),Charset.forName("UTF-8")));
            scan = new Scanner(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void write (String string){
        try{
            writer.write(string);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read () throws RuntimeException{
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new NullPointerException("lalalalalala");
        }
    }

    public boolean scanNextLine(){
        return scan.hasNextLine();
    }
    
    public void close() {
        try {
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public boolean isClosed(){
        return socket.isClosed();
    }

}
