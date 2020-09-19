package connection;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

public class ConnectionClient {

    private String ip = "192.168.0.101";
    private int port = 7775;
    private Socket socket;
    private static BufferedReader reader;
    private static BufferedWriter writer;

    public ConnectionClient (){
        try {
            this.socket = new Socket(ip,port);
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),Charset.forName("UTF-8")));

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
    
    public void close() throws IOException {
            reader.close();
            writer.close();
            socket.close();

    }

    public boolean isClosed(){
        return socket.isClosed();
    }

}
