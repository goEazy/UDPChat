import java.io.*;
import java.net.*;
import java.util.*;
class UDPClient
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sendMessage = "";
        String receiveMessage = "";
        DatagramSocket socket = null;
        try
        {
            socket = new DatagramSocket();
            InetAddress ip = InetAddress.getByName(args[0]);
            int port = Integer.parseInt(args[1]);
            System.out.println("connected to server");
            byte[] sd = "".getBytes();
            DatagramPacket sp = new DatagramPacket(sd, sd.length, ip, port);
            socket.send(sp);
            
            ////////////////Receive Message in Client/////////////////
            ReceiveMessage rcvClient = new ReceiveMessage(socket);
            Thread t1 = new Thread(rcvClient);
            t1.start();
            
            ////////////////Send MEssage From Client//////////////////
            SendMessage sndClient = new SendMessage(socket, ip, port);
            Thread t2 = new Thread(sndClient);
            t2.start();
            
        }catch(Exception e){
            System.out.println("Unable to Connect to Server");
        }
    }
}


