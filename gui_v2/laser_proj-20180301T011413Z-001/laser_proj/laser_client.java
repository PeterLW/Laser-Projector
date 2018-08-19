package laser_proj;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class laser_client{
    private Socket clientSocket;
    private OutputStream output;
    private InputStream input;
    private boolean running;

    String address;
    int port;

    public laser_client(String address, int port){
        this.address = address;
        this.port = port;
    }

    public boolean connect()throws IOException{
        clientSocket = new Socket(address, port);
        running = true;
        return running;
    }

    public void disconnect()throws IOException{
        running = false;
        clientSocket.close();
    }

    public boolean sendMessage(byte[] msg) {
        byte[] msgBytes = msg;
        String msg_in_str = new String(msgBytes);
        System.out.println("send: "+ msg_in_str);
        try {
            output = clientSocket.getOutputStream();
            output.write(msgBytes, 0, msgBytes.length);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String receiveMessageString() throws IOException {
        int index = 0;
        byte[] msgBytes = new byte[1000];
        input = clientSocket.getInputStream();
        byte read = 0;

        while(read != 46) { //period as end of character
            read = (byte) input.read(); // blocks until input data available
            msgBytes[index] = read;
            index++;
            //System.out.println(read);
        }

        String msg_in_str = new String(msgBytes);

        return msg_in_str;
    }


}
