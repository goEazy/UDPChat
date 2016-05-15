import java.io.*;
import java.net.*;

class SendMessage implements Runnable{
    int port = 0;
    InetAddress ip = null;
    DatagramSocket smSocket = null;
    BufferedReader br = null;
    
    public SendMessage(DatagramSocket smSocket,InetAddress ip, int port){
        this.smSocket = smSocket;
        this.ip = ip;
        this.port = port;
    }
    public void run(){
        String sndMsg = "";
        br = new BufferedReader(new InputStreamReader(System.in));
        try{
            while(true){
                byte sd[] = new byte[32];
                sndMsg = br.readLine();
                sd = sndMsg.getBytes();
                DatagramPacket sp = new DatagramPacket(sd, sd.length, ip, port);
                smSocket.send(sp);
            }
        }catch(Exception e){
            System.out.println("Error in Sending Message : " + e.getMessage());
        }
        finally{
            smSocket.close();
        }
    }
}