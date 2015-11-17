package whatscucu;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 *
 * @author Cito
 */
public class WhatsCucu {
    private static final String groupIp = "224.0.0.0";
    private static final int port = 8000, otherPort = 8001;
    
    private static String myName = "";
    private static boolean isStopped = false;

    private static MulticastSocket socket;
    private static Thread recvThread, connectThread;

    public static class RecvRunnable implements Runnable {
        @Override
        public void run() {
            byte[] recvMessage = new byte[512];
            DatagramPacket recvPacket = new DatagramPacket(recvMessage, recvMessage.length);
            
            while(!isStopped){
                try {                    
                    socket.receive(recvPacket);
                } catch (IOException ex) { System.out.println("Errore: ricezione recvPacket "); }
                String message = new String(recvMessage, 0, recvPacket.getLength());
                if(message.endsWith("-connect"))
                    System.out.println(message.split(":")[0] + " si è unito al cucu");
                System.out.println(message);
            }
            stopSocket();
        }
    }
    
    public static void startSocket(){
        try {
            socket = new MulticastSocket(port);
            socket.joinGroup(InetAddress.getByName(groupIp));
            
            //connectThread = new Thread(new Connection.ConnectRunnable(), "connectThread");
            connectThread.start();
            
            recvThread = new Thread(new RecvRunnable(), "recvThread");        
            recvThread.start();
            
            
            
            //send("si è attaccato a 'stocuccu");
        } catch (Exception e) { System.out.println("Errore: istanza recvSocket "); }
    }
    
    public static void stopSocket(){
        try {
            socket.leaveGroup(InetAddress.getByName(groupIp));
        } catch (IOException ex) { System.out.println("Errore: leave gruppo "); } 
        
        
        recvThread.interrupt();
        connectThread.interrupt();
        socket.close();
    }
    
    public static void send(String message){ send(myName + ":\t" + message, port); }
    
    public static void send(String message, int port){
        byte[] sendMessage = message.getBytes();
        DatagramPacket sendPacket = null;
        try {
            sendPacket = new DatagramPacket(sendMessage, sendMessage.length, InetAddress.getByName(groupIp), port);
        } catch (UnknownHostException ex) { System.out.println("Errore: istanza sendPacket"); }
        
        try {
            socket.send(sendPacket);
        } catch (IOException ex) { System.out.println("Errore: invio sendPacket"); }   
    }
    
    public static String getName() { return myName; }
    
    public static void setName(String name) { myName = name; }
    
    public static boolean isStopped() { return isStopped; }
    
    public static void setStopped(boolean stopped){ 
        isStopped = stopped; 
        if (stopped) send("si è staccato da 'stocuccu");
    }
}
