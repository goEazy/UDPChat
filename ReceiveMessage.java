import java.io.*;
import java.net.*;

class ReceiveMessage implements Runnable{
    DatagramSocket rmSocket = null;
    
    public ReceiveMessage(DatagramSocket rmSocket){
        this.rmSocket = rmSocket;
    }
    public void run(){
        String rcvMsg = "";
        try{
            while(true){
                byte[] rv = new byte[32];
                DatagramPacket rp = new DatagramPacket(rv, rv.length);
                rmSocket.receive(rp);
                rcvMsg = printData(rp);
                System.out.println("Received : " + rcvMsg);
            }
        }catch(Exception e){
            System.out.println("Problem in Receiving Message : " + e.getMessage());
        }
        finally{
            rmSocket.close();
        }
    }
    private String printData(DatagramPacket request) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(request.getData())));
        String line = br.readLine();
        return line;
    }
}