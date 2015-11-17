package whatscucu;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

/**
 *
 * @author Cito
 */
public class Connection {
    private static String[] connectionList;
    
    
    private static final int otherPort = 8001;
    
    public static class ConnectRunnable implements Runnable{
        @Override
        public void run() {
            WhatsCucu.send("-connect");
        }        
    }
    
    public static class DisconnectRunnable implements Runnable{
        @Override
        public void run() {
            try {
                MulticastSocket stayAliveCucu = new MulticastSocket(otherPort);
                byte[] recvMessage = new byte[512];
                DatagramPacket recvPacket = new DatagramPacket(recvMessage, recvMessage.length);
                while(!WhatsCucu.isStopped()){
                    stayAliveCucu.receive(recvPacket);
                    String message = new String(recvMessage, 0, recvPacket.getLength());
                    
                    //connectionList.
                    
                }                
            } catch (IOException ex) { System.out.println("Errore disconnect socket"); }
            
        }        
    }

    public static String[] getConnectionList() {
        return connectionList;
    }

    public static int getOtherPort() {
        return otherPort;
    }
}
