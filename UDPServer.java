import java.net.*;  
import java.io.*;

public class UDPServer
{
    public static void main(String[] args) throws IOException 
    {
        DatagramSocket socket = null;
        try
        {
            int serverPort = Integer.parseInt(args[0]);
            socket = new DatagramSocket(serverPort);
            System.out.println("Waiting for Client...");
            byte[] rv = new byte[32];
            DatagramPacket rp = new DatagramPacket(rv, rv.length);
            socket.receive(rp);
            InetAddress ip = rp.getAddress();
            int port = rp.getPort();
            
            ////////////////Receive Message in Server/////////////////
            ReceiveMessage rcvServer = new ReceiveMessage(socket);
            Thread t1 = new Thread(rcvServer);
            t1.start();
            
            ////////////////Send MEssage From Server//////////////////
            SendMessage sndServer = new SendMessage(socket, ip, port);
            Thread t2 = new Thread(sndServer);
            t2.start();
        }catch(Exception e){
            System.out.println("Connection Error");
        }
    }
}  