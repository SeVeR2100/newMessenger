package connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class ConnectionServer {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public ConnectionServer (ServerSocket server){
        try {
            this.socket = server.accept();
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
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
  //          e.printStackTrace();
        }
    }

    public String read () throws RuntimeException{
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new NullPointerException("lalalalalala");
        }
    }

    public void close()  {
        try {
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void disconnect(){
//        try {
//            reader.close();
//            writer.close();
//            socket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public boolean isClosed(){
        return socket.isClosed();
    }
}

