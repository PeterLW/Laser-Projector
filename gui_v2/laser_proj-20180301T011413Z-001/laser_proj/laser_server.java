package laser_proj;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;


public class laser_server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private boolean running = false;
    private int port;

    private OutputStream output;
    private InputStream input;

    public laser_server(int port){
        this.port = port;
    }

    public void run(){
        try {
            this.serverSocket = new ServerSocket(this.port);
            running = true;
            System.out.println("Waiting for client connection...");
            clientSocket = serverSocket.accept();

            if(this.serverSocket != null) {
                while(running){
                    System.out.println("Connecting to the client...");

                    while(true) {
                        byte[] msg = receiveMessageString();

                        //square, s = 115 in ascii
                        if(msg[0] == 115) {
                            //todo
                            String msg_in_str = new String(msg);
                            System.out.println(msg_in_str);
                        }
                        //triangle, t = 116 in ascii
                        else if(msg[0] == 116){
                            //todo
                            String msg_in_str = new String(msg);
                            System.out.println(msg_in_str);
                        }
                        else{
                            System.out.println("Invalid input");
                        }
                    }

                }
            }
        }catch(IOException ioe){
            System.out.println("server connection error in run()");
        }
    }

    public static void main(String[] args) {
        laser_server ls = new laser_server(6000);
        ls.run();
    }



    public boolean sendMessage(byte[] msg) {
        byte[] msgBytes = msg;
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

    public byte[] receiveMessageString() throws IOException {
        int index = 0;
        byte[] msgBytes = new byte[1000];
        input = clientSocket.getInputStream();
        byte read = 0;

        while(read != 46) {
            read = (byte) input.read(); // blocks until input data available
            msgBytes[index] = read;
            index++;
            System.out.println(read);
        }
//        String msg_in_str = new String(msgBytes);
//        System.out.println(msg_in_str);
//
//        if(msg_in_str.equals("square."))
//            System.out.println("square in receive");
//        if(msg_in_str.equals("triangle."))
//            System.out.println("square in receive");

        return msgBytes;
    }

}
