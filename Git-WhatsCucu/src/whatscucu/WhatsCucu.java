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

    //  Thread di ricezione
    public static class RecvRunnable implements Runnable {
        @Override
        public void run() {
            //  Crea-Inizializza i buffer e pacchetto di ricezione
            byte[] recvMessage = new byte[512];
            DatagramPacket recvPacket = new DatagramPacket(recvMessage, recvMessage.length);
            
            //  While di ricezione, stampa il messaggio ricevuto
            while(!isStopped){
                try {                    
                    socket.receive(recvPacket);
                } catch (IOException ex) { System.out.println("Errore: ricezione recvPacket "); }
                String message = new String(recvMessage, 0, recvPacket.getLength());
                /*if(message.endsWith("-connect"))
                    System.out.println(message.split(":")[0] + " si è unito al cucu");*/
                System.out.println(message);
            }
            stopSocket();
        }
    }
    
    //  Inizializza tutte le cose che servono
    public static void startSocket(){
        try {
            //  Inizializza il socket e joina il gruppo Multicast
            socket = new MulticastSocket(port);
            socket.joinGroup(InetAddress.getByName(groupIp));
            
            //  Inizializza e starta il thread che gestisce le connessioni
            connectThread = new Thread(new Connection.ConnectRunnable(), "connectThread");
            connectThread.start();
            
            //  Inizializza e starta il thread che ascolta i messaggi in arrivo
            recvThread = new Thread(new RecvRunnable(), "recvThread");        
            recvThread.start(); 
            
            //send("si è attaccato a 'stocuccu");
        } catch (Exception e) { System.out.println("Errore: istanza recvSocket "); }
    }
    
    // Esce dal gruppo, ferma tutti i thread e chiude il socket
    public static void stopSocket(){
        try {
            socket.leaveGroup(InetAddress.getByName(groupIp));
        } catch (IOException ex) { System.out.println("Errore: leave gruppo "); } 
                
        recvThread.interrupt();
        connectThread.interrupt();
        socket.close();
    }
    
    //  Overload del send con parqametri predefiniti
    public static void send(String message){ send(myName + ":\t" + message, port); }
    
    //  Invia un mesasggio al gruppo
    public static void send(String message, int port){
        //  Crea-Inizializza buffer e pacchetto di invio
        byte[] sendMessage = message.getBytes();
        DatagramPacket sendPacket = null;
        try {
            sendPacket = new DatagramPacket(sendMessage, sendMessage.length, InetAddress.getByName(groupIp), port);
        } catch (UnknownHostException ex) { System.out.println("Errore: istanza sendPacket"); }        
        
        try {
            //  Invia il pacchetto
            socket.send(sendPacket);
        } catch (IOException ex) { System.out.println("Errore: invio sendPacket"); }   
    }
    
    public static String getName() { return myName; }
    
    public static void setName(String name) { myName = name; }
    
    public static boolean isStopped() { return isStopped; }
    
    public static void setStopped(boolean stopped){ 
        isStopped = stopped; 
        if (stopped) 
            //  Invia l'ultimo messaggio per stoppare tutto, poi userà un'altra funzione quando la creeremo
            send("si è staccato da 'stocuccu");
    }
}
